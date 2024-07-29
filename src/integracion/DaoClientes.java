package integracion;

import modelo.Cliente;
import modelo.Domicilio;
import modelo.ServicioClientes;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TreeSet;

public class DaoClientes implements ServicioClientes {
    private String url;
    private String usuario;
    private String password;
    private String query;
    private Connection conexion;
    private Statement sentencia;
    private ResultSet renglones;

    public DaoClientes(String url, String usuario, String password) {
        this.setUrl(url);
        this.setUsuario(usuario);
        this.setPassword(password);

        try {
            setConexion(DriverManager.getConnection(url, usuario, password));
            setSentencia(getConexion().createStatement());
        } catch (SQLException e) {
            System.out.println("Fallo la conexion con la BD!!!");
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public Statement getSentencia() {
        return sentencia;
    }

    public void setSentencia(Statement sentencia) {
        this.sentencia = sentencia;
    }

    public ResultSet getRenglones() {
        return renglones;
    }

    public void setRenglones(ResultSet renglones) {
        this.renglones = renglones;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        String query = "INSERT INTO CLIENTES (numero, nombre, apellido, edad, rfc, telefono, fechaNacimiento, calle, numDomicilio, colonia, estado, codigoPostal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {

            pstmt.setInt(1, cliente.getNumeroCliente());
            pstmt.setString(2, cliente.getNombre() != null ? cliente.getNombre() : "");
            pstmt.setString(3, cliente.getApellido() != null ? cliente.getApellido() : "");
            pstmt.setInt(4, cliente.getEdad());

            pstmt.setString(5, cliente.getRfc() != null ? cliente.getRfc() : "");
            pstmt.setString(6, cliente.getTelefono() != null ? cliente.getTelefono() : "");
            pstmt.setDate(7, cliente.getFechaNacimiento() != null ? java.sql.Date.valueOf(cliente.getFechaNacimiento()) : null);

            Domicilio domicilio = cliente.getDomicilio();
            if (domicilio != null) {
                pstmt.setString(8, domicilio.getCalle() != null ? domicilio.getCalle() : "");
                pstmt.setInt(9, domicilio.getNumero());
                pstmt.setString(10, domicilio.getColonia() != null ? domicilio.getColonia() : "");
                pstmt.setString(11, domicilio.getEstado() != null ? domicilio.getEstado() : "");
                pstmt.setInt(12, domicilio.getCodigoPostal());
            } else {
                pstmt.setNull(8, java.sql.Types.VARCHAR);
                pstmt.setNull(9, java.sql.Types.INTEGER);
                pstmt.setNull(10, java.sql.Types.VARCHAR);
                pstmt.setNull(11, java.sql.Types.VARCHAR);
                pstmt.setNull(12, java.sql.Types.INTEGER);
            }

            int afectados = pstmt.executeUpdate();

            if (afectados == 1) {
                System.out.println("Se insertó el cliente en la BD");
                return true;
            } else {
                System.out.println("No se insertaron registros");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Hubo un problema al agregar al cliente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarCliente(int numero) {
        String query = "DELETE FROM clientes WHERE numero = ?";
        int filasAfectadas = 0;

        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, numero);

            // Ejecuta la consulta de eliminación
            filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Cliente con número " + numero + " eliminado exitosamente.");
                return true;
            } else {
                System.out.println("No se encontró un cliente con número " + numero + " para eliminar.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al intentar eliminar el cliente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Cliente consultarCliente(int numero) {
        System.out.println("*-".repeat(20));
        query = String.format("SELECT * FROM clientes WHERE numero = %d;", numero);
        try {
            renglones = sentencia.executeQuery(query);
            System.out.println("Datos del Cliente Numero: " + numero);
            System.out.println("Fecha: " + LocalDateTime.now());
            System.out.println("*-".repeat(20));

            if (renglones.next()) {
                // Obtener información del cliente con manejo de nulos
                String nombre = renglones.getString("nombre");
                String apellido = renglones.getString("apellido");
                int edad = renglones.getInt("edad");
                int numCliente = renglones.getInt("numero");
                String rfc = renglones.getString("rfc");
                String telefono = renglones.getString("telefono");

                java.sql.Date sqlDate = renglones.getDate("fechaNacimiento");
                LocalDate fechaNacimiento = (sqlDate != null) ? sqlDate.toLocalDate() : null;

                String calle = renglones.getString("calle");
                int numeroDomicilio = renglones.getInt("numDomicilio");
                String colonia = renglones.getString("colonia");
                String estado = renglones.getString("estado");
                int codigoPostal = renglones.getInt("codigoPostal");

                Domicilio domicilio = new Domicilio(
                        calle != null ? calle : "", // Valor por defecto si es null
                        numeroDomicilio, // Asume que este campo no es null
                        colonia != null ? colonia : "", // Valor por defecto si es null
                        estado != null ? estado : "", // Valor por defecto si es null
                        codigoPostal // Asume que este campo no es null
                );

                Cliente c = new Cliente(
                        nombre != null ? nombre : "", // Valor por defecto si es null
                        apellido != null ? apellido : "", // Valor por defecto si es null
                        domicilio,
                        edad,
                        numCliente,
                        rfc != null ? rfc : "", // Valor por defecto si es null
                        telefono != null ? telefono : "", // Valor por defecto si es null
                        fechaNacimiento
                );

                System.out.println("APELLIDO: " + apellido);
                System.out.println(c);

                renglones.close();
                return c;
            }
        } catch (SQLException e) {
            System.out.println("Fallo al recuperar los renglones de la BD");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TreeSet<Cliente> obtenerClientes() {
        return null;
    }

    @Override
    public Cliente buscarClientePorRFC(String rfc) {
        String query = "SELECT numero, nombre, apellido, edad, telefono, fechaNacimiento, " +
                "calle, numDomicilio, colonia, estado, codigoPostal " +
                "FROM clientes WHERE rfc = ?";

        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            System.out.println("Datos del Cliente con RFC: " + rfc);
            System.out.println("Fecha: " + LocalDateTime.now());
            pstmt.setString(1, rfc);

            try (ResultSet renglones = pstmt.executeQuery()) {
                if (renglones.next()) {
                    int numero = renglones.getInt("numero");
                    String nombre = renglones.getString("nombre");
                    String apellido = renglones.getString("apellido");
                    int edad = renglones.getInt("edad");
                    String telefono = renglones.getString("telefono");

                    java.sql.Date sqlDate = renglones.getDate("fechaNacimiento");
                    LocalDate fechaNacimiento = (sqlDate != null) ? sqlDate.toLocalDate() : null;

                    String calle = renglones.getString("calle");
                    int numeroDomicilio = renglones.getInt("numDomicilio");
                    String colonia = renglones.getString("colonia");
                    String estado = renglones.getString("estado");
                    int codigoPostal = renglones.getInt("codigoPostal");

                    Domicilio domicilio = new Domicilio(
                            calle != null ? calle : "",
                            numeroDomicilio,
                            colonia != null ? colonia : "",
                            estado != null ? estado : "",
                            codigoPostal
                    );

                    Cliente cliente = new Cliente(
                            nombre != null ? nombre : "",
                            apellido != null ? apellido : "",
                            domicilio,
                            edad,
                            numero,
                            rfc != null ? rfc : "",
                            telefono != null ? telefono : "",
                            fechaNacimiento
                    );

                    System.out.println(cliente);

                    return cliente;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar el cliente por RFC: " + e.getMessage());
        }

        System.out.println("Cliente no encontrado con RFC: " + rfc);
        return null;
    }

    @Override
    public void listarClientes() {
        String query = "SELECT numero, nombre, apellido, edad, rfc, telefono, fechaNacimiento, calle, numDomicilio, colonia, estado, codigoPostal FROM clientes";

        try (PreparedStatement pstmt = conexion.prepareStatement(query);
             ResultSet renglones = pstmt.executeQuery()) {

            System.out.println("Reporte de Clientes");
            System.out.println("Fecha: " + LocalDateTime.now());
            System.out.println("*-".repeat(20));

            while (renglones.next()) {
                int numero = renglones.getInt("numero");
                String nombre = renglones.getString("nombre");
                String apellido = renglones.getString("apellido");
                int edad = renglones.getInt("edad");
                String rfc = renglones.getString("rfc");
                String telefono = renglones.getString("telefono");

                java.sql.Date sqlDate = renglones.getDate("fechaNacimiento");
                LocalDate fechaNacimiento = (sqlDate != null) ? sqlDate.toLocalDate() : null;

                String calle = renglones.getString("calle");
                int numeroDomicilio = renglones.getInt("numDomicilio");
                String colonia = renglones.getString("colonia");
                String estado = renglones.getString("estado");
                int codigoPostal = renglones.getInt("codigoPostal");

                Domicilio domicilio = new Domicilio(
                        calle != null ? calle : "",
                        numeroDomicilio,
                        colonia != null ? colonia : "",
                        estado != null ? estado : "",
                        codigoPostal
                );

                Cliente cliente = new Cliente(
                        nombre != null ? nombre : "",
                        apellido != null ? apellido : "",
                        domicilio,
                        edad,
                        numero,
                        rfc != null ? rfc : "",
                        telefono != null ? telefono : "",
                        fechaNacimiento
                );

                System.out.println(cliente);
            }

            System.out.println("*-".repeat(20));
        } catch (SQLException e) {
            System.out.println("Fallo al recuperar los renglones de la BD: " + e.getMessage());
        }
    }

    @Override
    public void listarPorNumeroTelefono() {

    }
}

