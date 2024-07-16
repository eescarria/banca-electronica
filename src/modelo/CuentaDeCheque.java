package modelo;

public class CuentaDeCheque extends Cuenta{

    private Double costoManejoMensual;

    public CuentaDeCheque(int numero, String fechaApertura, double saldo, String fechaCancelacion) {
        super(numero, fechaApertura, saldo, fechaCancelacion);
        this.costoManejoMensual = definirCuota();
    }

    public Double definirCuota(){
        if(this.getSaldo() > 10000){
            return 3000.0;
        }else {
            return 500.0;
        }
    }

    public Double getCostoManejoMensual() {
        return costoManejoMensual;
    }

    public void setCostoManejoMensual(Double costoManejoMensual) {
        this.costoManejoMensual = costoManejoMensual;
    }

    @Override
    public String toString() {
        return super.toString() + "CuentaDeCheque{" +
                "costoManejoMensual=" + costoManejoMensual +
                '}';
    }
}
