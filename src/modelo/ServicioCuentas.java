package modelo;

import java.util.ArrayList;

public interface ServicioCuentas {

    boolean agregarCuenta(Cuenta cuenta);
    boolean cancelarCuenta(int numero);
    void abonarCuenta(int numero, double abono);
    void retirar(int numero, double retiro);
    ArrayList<Cuenta> obtenerCuentas();
    void listarCuentas();
    Cuenta buscarCuenta(int numero);
}
