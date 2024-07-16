package modelo;

import java.util.Comparator;

public class TelefonoComparator implements Comparator<Cliente> {

    @Override
    public int compare(Cliente c1, Cliente c2) {
        return c1.getTelefono().compareTo(c2.getTelefono());
    }

}
