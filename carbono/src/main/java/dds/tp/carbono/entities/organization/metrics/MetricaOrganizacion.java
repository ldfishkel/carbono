package dds.tp.carbono.entities.organization.metrics;

import lombok.Getter;
import lombok.Setter;

public class MetricaOrganizacion {
    @Setter @Getter private Actividad actividad;
    @Setter @Getter private TipoActividad tipoActividad;
    @Setter @Getter private TipoDeConsumo tipoDeConsumo;
    @Setter @Getter private PeriodoDeImputacion periodoDeImputacion;

    public boolean isValid() {
        return this.actividad != null &&
                this.periodoDeImputacion != null &&
                this.tipoDeConsumo != null &&
                this.tipoDeConsumo != null;
    }
}
