package modelo;

import java.time.LocalDate;

public abstract class Cuenta implements Comparable<Cuenta>{

    private int numero;
    private LocalDate fechaApertura;
    private double saldo;
    private LocalDate fechaCancelacion;

    public Cuenta(int numero, LocalDate fechaApertura, double saldo) {
        this.numero = numero;
        this.fechaApertura = fechaApertura;
        this.saldo = saldo;
    }

    public void abonar(Double cantidad){
        this.saldo = this.saldo + cantidad;
    }
    public boolean retirar(Double cantidad){
        if(this.saldo >= cantidad){
            this.saldo = this.saldo - cantidad;
            System.out.println("Retiro exitoso por: " + cantidad);
            return true;
        }
        System.out.println("Saldo insuficiente");
        return false;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public LocalDate getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(LocalDate fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "numero=" + numero +
                ", fechaApertura='" + fechaApertura + '\'' +
                ", saldo=" + saldo +
                ", fechaCancelacion='" + fechaCancelacion + '\'' +
                '}';
    }

    @Override
    public int compareTo(Cuenta o) {
        if(this.numero < o.numero){
            return -1;
        } else  if (this.numero > o.numero){
            return +1;
        }
        return 0;
    }
}
