package modelo;

public class CuentaDeAhorro extends Cuenta{

    private Double tasaInteresMensual;

    public CuentaDeAhorro(int numero, String fechaApertura, double saldo, String fechaCancelacion) {
        super(numero, fechaApertura, saldo, fechaCancelacion);
        this.tasaInteresMensual = definirTasa();
    }

    public Double calcularIntereses(){
        System.out.println("Los intereses son: " + getSaldo() * definirTasa());
        return this.getSaldo() * definirTasa();
    }

    public Double definirTasa(){
        if(this.getSaldo() > 10000){
            return 0.03;
        }else {
            return 0.01;
        }
    }

    public Double getTasaInteresMensual() {
        return tasaInteresMensual;
    }

    public void setTasaInteresMensual(Double tasaInteresMensual) {
        this.tasaInteresMensual = tasaInteresMensual;
    }

    @Override
    public String toString() {
        return super.toString() + "CuentaDeAhorro{" +
                "tasaInteresMensual=" + tasaInteresMensual +
                '}';
    }
}
