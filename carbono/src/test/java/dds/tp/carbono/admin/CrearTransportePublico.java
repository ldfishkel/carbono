package dds.tp.carbono.admin;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import dds.tp.carbono.dao.admin.TransportePublicoDao;
import dds.tp.carbono.entities.transport.Estacion;
import dds.tp.carbono.entities.transport.Linea;
import dds.tp.carbono.entities.transport.TipoTransportePublico;
import dds.tp.carbono.entities.transport.TransportePublico;
import dds.tp.carbono.services.admin.CreadorLineaTransportePublico;
import lombok.Getter;
import lombok.Setter;

public class CrearTransportePublico {
    
    private ParadasData data = null;

    @Before
    public void init() {
        String fileName = "paradas.json";

        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(fileName)) {
            this.data = new Gson().fromJson(new InputStreamReader(is), ParadasData.class);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void crearTransportePublico() {
        CreadorLineaTransportePublico creador = new CreadorLineaTransportePublico();
        TransportePublico transporte = creador.crearOActualizar(this.buildTransportePublico(TipoTransportePublico.COLECTIVO, 0));
        Assert.assertNotNull(transporte.getId());
    } 

    @Test
    public void actualizarTransportePublico() {
        CreadorLineaTransportePublico creador = new CreadorLineaTransportePublico();
        TransportePublico transporte = creador.crearOActualizar(this.buildTransportePublico(TipoTransportePublico.COLECTIVO, 0));

        Estacion nuevaEstacion = this.buildNuevaEstacion(); 

        Linea linea = transporte.getLinea();

        int cantidadEstaciones = linea.getEstaciones().size();
        
        linea.getEstaciones().get(linea.getEstaciones().size() - 1).setSiguiente(nuevaEstacion);
        linea.getEstaciones().add(nuevaEstacion);
        
        transporte.setLinea(linea);

        creador.crearOActualizar(transporte);
        

        int nuevaCantidadDeEstaciones = TransportePublicoDao.getInstance().getAll().get(0).getLinea().getEstaciones().size();

        Assert.assertEquals(nuevaCantidadDeEstaciones, cantidadEstaciones + 1);
    } 

    private Estacion buildNuevaEstacion() {
        Estacion nuevaEstacion = new Estacion();

        nuevaEstacion.setNombre("nueva estacion");
        nuevaEstacion.setDistanciaEstacionAnterior(Double.valueOf(1000.5));
        nuevaEstacion.setSiguiente(null);

        return nuevaEstacion;
    }

    private TransportePublico buildTransportePublico(TipoTransportePublico tipo, int lineaIndex) {
        TransportePublico transporte = new TransportePublico();
        transporte.setTipo(TipoTransportePublico.SUBTE);
        transporte.setLinea(this.buildLinea(lineaIndex));

        return transporte;
    }

    private Linea buildLinea(int lineaIndex) {
        Linea linea = this.data.getLineas().get(lineaIndex);

        for (int i = 0; i < linea.getEstaciones().size(); i++) {
            Estacion siguiente = null;

            if (i < linea.getEstaciones().size() - 1)
                siguiente = linea.getEstaciones().get(i + 1);
            
            linea.getEstaciones().get(i).setSiguiente(siguiente);
        }

        return linea;
    }

    private class ParadasData {
        @Getter @Setter private List<Linea> lineas;
    }
}
