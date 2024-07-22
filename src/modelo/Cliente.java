package modelo;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class Cliente extends Persona implements ServicioCuentas, Comparable<Cliente>{
    private int numCliente;
    private String rfc;
    private String telefono;
    private ArrayList<Cuenta> cuentas;
    private String fechaNacimiento;

    public Cliente(String nombre, String apellido, Domicilio domicilio, int edad, int numero, String rfc, String telefono, String fechaNacimiento) {
        super(nombre, apellido, domicilio, edad);
        this.numCliente = numero;
        this.rfc = rfc;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.cuentas = new ArrayList<>();
    }

    public Cliente(String nombre, String apellido, String calle, int numero, String colonia, String estado, int codigoPostal, int edad, int numCliente, String rfc, String telefono, String fechaNacimiento) {
        super(nombre, apellido, calle, numero, colonia, estado, codigoPostal, edad);
        this.numCliente = numCliente;
        this.rfc = rfc;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.cuentas = new ArrayList<>();
    }


    public int getNumeroCliente() {
        return numCliente;
    }

    public void setNumeroCliente(int numCliente) {
        this.numCliente = numCliente;
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

    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(ArrayList<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return super.toString() + "Cliente{" +
                "numero=" + numCliente +
                ", rfc='" + rfc + '\'' +
                ", telefono='" + telefono + '\'' +
                ", cuentas=" + cuentas +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente cliente)) return false;
        return numCliente == cliente.numCliente;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numCliente);
    }

    @Override
    public boolean agregarCuenta(Cuenta cuenta) {
        Cuenta cuentaBuscada = buscarCuenta(cuenta.getNumero());

        if(cuentaBuscada == null){
            System.out.println("Cuenta agregada correctamente");
        }else {
            System.out.println("Error al intentar agregar la cuenta");
        }
        return cuentas.add(cuenta);
    }

    @Override
    public boolean cancelarCuenta(int numero) {
        Cuenta cuentaBuscada = buscarCuenta(numero);

        if(cuentaBuscada != null){
            cuentaBuscada.setFechaCancelacion("Cancelada");
            System.out.println("Se cancela la cuenta con número: " + numero);
            return true;
        }else {
            System.out.println("No se ha podido realizar la cancelación de la cuenta, intenta nuevamente.");
            return false;
        }
    }

    @Override
    public void abonarCuenta(int numero, double abono) {
        Cuenta cuentaBuscada = buscarCuenta(numero);

        if(cuentaBuscada != null){
            cuentaBuscada.abonar(abono);
            System.out.println("Se realiza el abono exitosamente: $" + abono);
        }else {
            System.out.println("No se ha podido realizar el abono, intenta nuevamente.");
        }
    }

    @Override
    public void retirar(int numero, double retiro) {
        Cuenta cuentaBuscada = buscarCuenta(numero);

        if(cuentaBuscada != null){
            if(cuentaBuscada.retirar(retiro)){
                System.out.println("Se realiza el retiro exitosamente: $" + retiro);
            }

        }else {
            System.out.println("No se ha podido realizar el retiro, intenta nuevamente.");
        }

    }

    @Override
    public ArrayList<Cuenta> obtenerCuentas() {
        return cuentas;
    }

    @Override
    public void listarCuentas() {
//        System.out.println("=".repeat(50));
//        for (Cuenta c: cuentas
//        ) {
//            System.out.println(c);
//        }
//        System.out.println("=".repeat(50));

        cuentas.forEach((System.out::println));
    }

    @Override
    public Cuenta buscarCuenta(int numero) {
//        for (Cuenta cuenta: cuentas) {
//            if (cuenta.getNumero() == numero){
//                System.out.println("Cuenta encontrada con número: " + numero + " → " + cuenta);
//                return cuenta;
//            }
//        }
//        System.out.println("Cuenta con número: " + numero + " no encontrada");
//        return null;

        Optional<Cuenta> cuentaEncontrada = cuentas.stream()
                .filter(cuenta -> cuenta.getNumero() == numero)
                .findFirst();

        cuentaEncontrada.ifPresentOrElse(
                cuenta -> System.out.println("Cuenta encontrada con número: " + numero + " → " + cuenta),
                () -> System.out.println("Cuenta con número: " + numero + " no encontrada")
        );

        return cuentaEncontrada.orElse(null);
    }

    @Override
    public int compareTo(Cliente o) {
        if(this.numCliente < o.numCliente){
            return -1;
        } else  if (this.numCliente > o.numCliente){
            return +1;
        }
        return 0;
    }
}
