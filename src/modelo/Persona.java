package modelo;

import java.util.Objects;

public class Persona {

    private String nombre;
    private String apellido;
    private Domicilio domicilio;
    private int edad;

    public Persona(String nombre, String apellido, Domicilio domicilio, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.edad = edad;
    }

    public Persona(String nombre, String apellido, String calle, int numero, String colonia, String estado, int codigoPostal, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = new Domicilio(calle, numero, colonia, estado, codigoPostal);
        this.edad = edad;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(nombre.isBlank()){
            System.out.println("Nombre inválido!!!");
        }else {
            this.nombre = nombre;
        }

    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad < 0){
            System.out.println("Edad inválida!!!");
        }else {
        this.edad = edad;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return edad == persona.edad && Objects.equals(nombre, persona.nombre) && Objects.equals(apellido, persona.apellido) && Objects.equals(domicilio, persona.domicilio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, domicilio, edad);
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", edad=" + edad +
                '}';
    }
}
