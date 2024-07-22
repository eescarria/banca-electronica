package modelo;

import java.util.Optional;
import java.util.TreeSet;

public class Banco implements ServicioClientes{

    private String nombre;
    private Domicilio domicilio;
    private String rfc;
    private String telefono;
    private TreeSet<Cliente> clientes;

    public Banco(String nombre, Domicilio domicilio, String rfc, String telefono) {
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.rfc = rfc;
        this.telefono = telefono;
        this.clientes = new TreeSet<>();
        //this.clientes = new TreeSet<>(new TelefonoComparator());
    }

    public Banco(String nombre, String calle, int numero, String colonia, String estado, int codigoPostal, String rfc, String telefono) {
        this.nombre = nombre;
        this.domicilio = new Domicilio(calle, numero, colonia, estado, codigoPostal);
        this.rfc = rfc;
        this.telefono = telefono;
        this.clientes = new TreeSet<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public TreeSet<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(TreeSet<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public String toString() {
        return "Banco{" +
                "nombre='" + nombre + '\'' +
                ", domicilio=" + domicilio +
                ", rfc='" + rfc + '\'' +
                ", telefono='" + telefono + '\'' +
                ", clientes=" + clientes +
                '}';
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        Cliente clienteBuscado = consultarCliente(cliente.getNumeroCliente());

        if(clienteBuscado == null){
            System.out.println("Cliente agregado correctamente");
        }else {
            System.out.println("Error al intentar agregar el cliente");
        }
        return clientes.add(cliente);
    }

    @Override
    public boolean eliminarCliente(int numero) {
        Cliente clienteBuscado = consultarCliente(numero);

        if(clienteBuscado != null){
            System.out.println("Operación eliminar " + clienteBuscado);
            return clientes.remove(clienteBuscado);
        }
        return false;
    }

    @Override
    public Cliente consultarCliente(int numero) {
//        for (Cliente cliente: clientes) {
//            if (cliente.getNumeroCliente() == numero){
//                System.out.println("Cliente encontrado con número: " + numero + " → " + cliente);
//                return cliente;
//            }
//        }
//        System.out.println("Cliente con número: " + numero + " no encontrado");
//        return null;

            Optional<Cliente> clienteEncontrado = clientes.stream()
                    .filter(cliente -> cliente.getNumeroCliente() == numero)
                    .findFirst();

            clienteEncontrado.ifPresentOrElse(
                    cliente -> System.out.println("Cliente encontrado con número: " + numero + " → " + cliente),
                    () -> System.out.println("Cliente con número: " + numero + " no encontrado")
            );

            return clienteEncontrado.orElse(null);

    }

    @Override
    public TreeSet<Cliente> obtenerClientes() {
        return clientes;
    }

    @Override
    public Cliente buscarClientePorRFC(String rfc) {
//        for (Cliente cliente: clientes) {
//            if (cliente.getRfc().equals(rfc)){
//                System.out.println("Cliente encontrado con rfc: " + rfc + " → " + cliente);
//                return cliente;
//            }
//        }
//        System.out.println("Cliente con rfc: " + rfc + " no encontrado");
//        return null;

        Optional<Cliente> clienteEncontrado = clientes.stream()
                .filter(cliente -> cliente.getRfc().equals(rfc))
                .findFirst();

        clienteEncontrado.ifPresentOrElse(
                cliente -> System.out.println("Cliente encontrado con RFC: " + rfc + " → " + cliente),
                () -> System.out.println("Cliente con RFC: " + rfc + " no encontrado")
        );

        return clienteEncontrado.orElse(null);
    }

    @Override
    public void listarPorNumeroTelefono() {
        clientes.stream()
                .sorted((p1, p2) -> ((String)p1.getTelefono()).compareTo(p2.getTelefono()))
                .forEach(System.out::println);
        System.out.println("=".repeat(50));
    }

    @Override
    public void listarClientes() {
//        for (Cliente c: clientes
//             ) {
//            System.out.println(c);
//        }
        clientes.forEach((System.out::println));
    }
}
