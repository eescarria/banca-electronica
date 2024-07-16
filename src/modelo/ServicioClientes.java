package modelo;

import java.util.ArrayList;
import java.util.TreeSet;

public interface ServicioClientes {

    public abstract boolean agregarCliente(Cliente cliente);
    boolean eliminarCliente(int numero);
    Cliente consultarCliente(int numero);
    TreeSet<Cliente> obtenerClientes();
    Cliente buscarClientePorRFC(String rfc);
    void listarClientes();
    void listarPorNumeroTelefono();
}
