package dds.tp.carbono.huella;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dds.tp.carbono.entities.huella.FactorEmision;
import dds.tp.carbono.entities.huella.HuellaCarbono;
import dds.tp.carbono.entities.huella.UnidadFE;
import dds.tp.carbono.entities.huella.UnidadHC;
import dds.tp.carbono.entities.organization.Organizacion;
import dds.tp.carbono.entities.organization.metrics.Actividad;
import dds.tp.carbono.entities.organization.metrics.CombustionFija;
import dds.tp.carbono.entities.organization.metrics.Consumo;
import dds.tp.carbono.entities.organization.metrics.MetricaOrganizacion;
import dds.tp.carbono.entities.organization.metrics.Periodicidad;
import dds.tp.carbono.entities.organization.metrics.PeriodoDeImputacion;
import dds.tp.carbono.entities.organization.metrics.TipoActividad;
import dds.tp.carbono.entities.organization.metrics.TipoDeConsumo;
import dds.tp.carbono.entities.organization.metrics.Unidad;
import dds.tp.carbono.repository.huella.FactorEmisionRepository;
import dds.tp.carbono.repository.organization.OrganizacionRepository;
import dds.tp.carbono.services.huella.calculador.CalculadorHuellaCarbono;
import dds.tp.carbono.services.huella.calculador.CalculadorHuellaMetrica;

public class CalculadorHCTest {
    private FactorEmisionRepository repositoryMockFE;

    @Before
    public void inicializarRepositoryMockFE(){
        this.repositoryMockFE = mock(FactorEmisionRepository.class);
        FactorEmision factorGasNatural = new FactorEmision(TipoDeConsumo.GasNatural, TipoActividad.Combustion_Fija, 4.00, UnidadFE.kgCO2eq_M3);

        when(this.repositoryMockFE.get(TipoDeConsumo.GasNatural, TipoActividad.Combustion_Fija)).thenReturn(factorGasNatural);
        
    }
    /*
     *  public HuellaCarbono calcularHuellaParaLasMetricas()  throws Exception { //chequear el tema de catch y try en obtener HC

        List <MetricaOrganizacion> metricasACalcular = this.organizacion.getMetricas();
        
        metricasACalcular = filtrarMetricas();
        
        List <HuellaCarbono> huellasDeMetricas = obtenerHC(metricasACalcular);
        
        HuellaCarbono huellaTotal = new HuellaCarbono();
        huellaTotal = obtenerTotal(huellaTotal, huellasDeMetricas);
        
        return huellaTotal;
    } 
     */
    @Test 
    public void calcularHuellaParaLasMetricasTest() throws Exception{

        Organizacion org = this.crearOrganizacionConMetricas();
        PeriodoDeImputacion periodo = this.buildPeriodoDeImputacion();
        CalculadorHuellaCarbono calculador = new CalculadorHuellaCarbono(org, periodo);

        
        HuellaCarbono huella = calculador.calcula();

        Assert.assertEquals(Double.valueOf(1206.00), huella.getValor());

        
    }

    
    
    //-----------------------------------INSTANCIAS ----------------------------------------------

    private Organizacion crearOrganizacionConMetricas() throws Exception{
        
        MetricaOrganizacion metrica1 = crearMetricaCombustionFija();
        MetricaOrganizacion metrica2 = crearMetricaCombustionFija();
        MetricaOrganizacion metrica3 = crearMetricaCombustionFija();

        List<MetricaOrganizacion> metricas = new ArrayList<MetricaOrganizacion>();
        metricas.add(metrica1);
        metricas.add(metrica2);
        metricas.add(metrica3);
        Organizacion org = buildOrganizacion(metricas);
        return org;
    }

    private MetricaOrganizacion crearMetricaCombustionFija() throws Exception{
        
        Consumo consumo = buildConsumo(100.5, Periodicidad.ANUAL, Unidad.KG, TipoDeConsumo.GasNatural);
        Actividad actividad = buildActividadCombustionFija(consumo);
        PeriodoDeImputacion periodoDeImputacion = buildPeriodoDeImputacion();
        MetricaOrganizacion metrica = buildMetricaOrganizacion(actividad , TipoActividad.Combustion_Fija, TipoDeConsumo.GasNatural, periodoDeImputacion);
        
        
        return metrica;
    }
    //----------------------------------CONSTRUCTORES----------------------------------------

    private Organizacion buildOrganizacion(List<MetricaOrganizacion> metricas) {
        Organizacion org = new Organizacion();
        org.setMetricas(metricas);
        
        return org;
    }

    private PeriodoDeImputacion buildPeriodoDeImputacion() throws Exception {
        PeriodoDeImputacion periodo = new PeriodoDeImputacion("03/2020");
        periodo.setPeriodicidad(Periodicidad.ANUAL);
        return periodo;
    }

    private Consumo buildConsumo(Double valor,Periodicidad periodicidad,Unidad unidad,TipoDeConsumo tipo){
        Consumo consumo = new Consumo();
        consumo.setValor(valor);
        consumo.setPeriodicidad(periodicidad);
        consumo.setUnidad(unidad);
        consumo.setTipo(tipo);
        return consumo;
    }

    private Actividad buildActividadCombustionFija (Consumo consumo){
        Actividad actividad = new CombustionFija();
        actividad.setConsumo(consumo);
        return actividad;
    }

    private MetricaOrganizacion buildMetricaOrganizacion (Actividad actividad, TipoActividad tipoActividad, TipoDeConsumo tipoDeConsumo, PeriodoDeImputacion periodoDeImputacion){
        MetricaOrganizacion metrica = new MetricaOrganizacion();
        metrica.setActividad(actividad);
        metrica.setTipoActividad(tipoActividad);
        metrica.setTipoDeConsumo(tipoDeConsumo);
        metrica.setPeriodoDeImputacion(periodoDeImputacion);
        return metrica;
    }
}

