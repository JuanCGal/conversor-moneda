package conversormoneda.modulos;

import com.google.gson.annotations.SerializedName;

public class Presentacion {
    @SerializedName("base_code")
    private String modenaOrigen;
    @SerializedName("target_code")
    private String monedaDestino;
    @SerializedName("conversion_rate")
    private double valorMoneda;
    @SerializedName("conversion_result")
    private double valorTotalCantidad;

    @Override
    public String toString() {
        return " " + modenaOrigen +
                ", Equivalen a " + valorTotalCantidad + " " + monedaDestino;
    }
}
