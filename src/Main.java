import integracion.DaoClientes;
import modelo.*;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Banco banco = new Banco("Bancolombia","Avenida", 5, "Medellin", "Antioquia", 76000, "abc123", "12345678");

        Cliente cliente1 = new Cliente("Pepe", "Ruiz","Carrera 69", 20, "Hacienda", "Valle", 7600, 30,30,"abc123","12345678", LocalDate.of(1990,1,14));
        Cliente cliente2 = new Cliente("Estefania", "Escarria","Calle 6", 2, "Caney", "Valle", 7601, 34,21,"abc456","02468024",LocalDate.of(1992,5,20));
        Cliente cliente3 = new Cliente("Juan", "Alvear","Calle 5", 30, "Limonar", "Valle", 7603, 37,22,"abc789","02468022",LocalDate.of(1987,8,20));
        Cliente cliente4 = new Cliente("Pepe", "Perez","Calle 4", 20, "Lido", "Valle", 7621, 52,23,"abc012","02468020",LocalDate.of(1996,4,13));
        Cliente cliente5 = new Cliente("Pepe", "Perez","Calle 4", 20, "Lido", "Valle", 7621, 52,24,"abc012","02468020",LocalDate.of(2000,12,10));
        //System.out.println(cliente);

        Persona persona1 = new Persona("Estefania", "Escarria", "Carrera 69", 20, "Hacienda", "Valle", 7600, 34);
        //System.out.println(persona1.hashCode());
        Persona persona2 = new Persona("Estefania", "Escarria", "Carrera 69", 20, "Hacienda", "Valle", 7600, 34);
        //System.out.println(persona2.hashCode());

//
//        System.out.println("Método agregar clientes: ");
//        banco.agregarCliente(cliente1);
//        banco.agregarCliente(cliente2);
//
//        System.out.println("=".repeat(100));
//        System.out.println("Método listar clientes: ");
//        banco.listarClientes();
//
//        System.out.println("=".repeat(100));
//        System.out.println("Método eliminar clientes: ");
//        banco.eliminarCliente(1);
//        banco.listarClientes();
//
//        System.out.println("=".repeat(100));
//        System.out.println("Métodos buscar clientes por RFC y por número: ");
//        banco.agregarCliente(cliente3);
//        banco.consultarCliente(3);
//        banco.buscarClientePorRFC("abc456");
//        banco.agregarCliente(cliente4);
//        banco.agregarCliente(cliente5);
//
//        banco.listarClientes();
//
//        /// Ejemplo cuenta de ahorro
//        System.out.println("=".repeat(100));
//        System.out.println("Métodos agregar cuentas de ahorro: ");
//        CuentaDeAhorro cuentaDeAhorro1 = new CuentaDeAhorro(1,LocalDate.of(2024,7,1),10000);
//        CuentaDeAhorro cuentaDeAhorro2 = new CuentaDeAhorro(2,LocalDate.of(2024,7,1),5000);
//        cliente2.agregarCuenta(cuentaDeAhorro1);
//        cliente2.agregarCuenta(cuentaDeAhorro2);
//
//        System.out.println("=".repeat(100));
//        System.out.println("Métodos abonar y retirar de cuentas de ahorro: ");
//        cliente2.abonarCuenta(1,2000);
//        cliente2.retirar(1,1000);
//        cliente2.retirar(2,6000);
//
//        System.out.println("=".repeat(100));
//        System.out.println("Método cálcular intereses de cuentas de ahorro: ");
//        cuentaDeAhorro1.calcularIntereses();
//        cuentaDeAhorro2.calcularIntereses();
//
//        System.out.println("=".repeat(100));
//        System.out.println("Métodos listar y cancelar cuentas de ahorro: ");
//        cliente2.listarCuentas();
//        cliente2.cancelarCuenta(2);
//        cliente2.listarCuentas();
//
//        /// Ejemplo cuenta de cheque
//        System.out.println("=".repeat(100));
//        System.out.println("Métodos agregar cuentas de cheque: ");
//        CuentaDeCheque cuentaDeCheque1 = new CuentaDeCheque(1,LocalDate.of(2024,7,1),15000);
//        CuentaDeCheque cuentaDeCheque2 = new CuentaDeCheque(2,LocalDate.of(2024,7,1),2000);
//
//        cliente3.agregarCuenta(cuentaDeCheque1);
//        cliente3.agregarCuenta(cuentaDeCheque2);
//
//        System.out.println("=".repeat(100));
//        System.out.println("Métodos abonar y retirar de cuentas de cheque: ");
//        cliente3.listarCuentas();
//        cliente3.abonarCuenta(1,5000);
//        cliente3.retirar(1,2000);
//        cliente3.listarCuentas();
//        cliente3.retirar(2,5000);
//
//        System.out.println("=".repeat(100));
//        System.out.println("Lista clientes por número de teléfono");
//        banco.listarPorNumeroTelefono();
//
//        System.out.println("=".repeat(100));

        String url = "jdbc:mysql://localhost:3306/banca";
        String usuario = "root";
        String password = "root123";

        DaoClientes dao = new DaoClientes(url,usuario,password);
        dao.listarClientes();
        dao.consultarCliente(7);
        dao.agregarCliente(cliente1);
        dao.agregarCliente(cliente2);
        dao.agregarCliente(cliente3);
        dao.listarClientes();
        dao.eliminarCliente(30);
        dao.listarClientes();
        dao.buscarClientePorRFC("abc456");

    }
}
