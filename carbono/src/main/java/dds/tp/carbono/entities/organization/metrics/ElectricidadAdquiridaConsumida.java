package dds.tp.carbono.entities.organization.metrics;

import lombok.Getter;
import lombok.Setter;

public class ElectricidadAdquiridaConsumida extends Actividad {
    
    @Setter @Getter public Consumo consumo;
    
    public ElectricidadAdquiridaConsumida(Consumo consumo) {
        this.consumo = consumo;
        this.tipoActividad = TipoActividad.Electricidad_Adquirida_Consumida;
    }

    @Override
    protected Double getValorDA() {
        return this.consumo.getValor();
    }

    @Override
    protected Unidad getUnidadDA() {
        return this.consumo.getUnidad();
    }

    @Override
    public TipoDeConsumo getTipoDeConsumo() {
        return this.consumo.getTipo();
    }
}
