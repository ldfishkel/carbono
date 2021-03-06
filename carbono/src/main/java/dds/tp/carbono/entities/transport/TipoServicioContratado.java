package dds.tp.carbono.entities.transport;

import lombok.Getter;
import lombok.Setter;

public class TipoServicioContratado {
    @Getter @Setter private Integer id;
    @Getter @Setter private String nombre;

    public TipoServicioContratado(String nombre) {
        this.nombre = nombre;
    }
}
