package dds.tp.carbono.repository.organization;

import java.util.List;

import dds.tp.carbono.dao.organization.OrganizacionDao;
import dds.tp.carbono.entities.auth.Usuario;
import dds.tp.carbono.entities.organization.Organizacion;
import dds.tp.carbono.entities.organization.metrics.MetricaOrganizacion;

public class OrganizacionRepository {

    private OrganizacionDao dao; 

    public OrganizacionRepository() {
        this.dao = OrganizacionDao.getInstance();
    }

    public Organizacion guardar(Organizacion organizacion) {
        return this.dao.save(organizacion); 
    }

    public boolean exists(String razonSocial) {
        return this.dao.getAll().stream().anyMatch(o -> o.getRazonSocial().equals(razonSocial));
    }

    public void addMetrics(List<MetricaOrganizacion> metricas, Organizacion organizacion) {
        Organizacion org = this.dao.getAll().stream().filter(o -> o.getRazonSocial().equals(organizacion.getRazonSocial())).findFirst().orElse(null);
        org.getMetricas().addAll(metricas);
        this.dao.update(org);
    }

    public Organizacion getByUser(Usuario user) {
        return this.dao.getAll().stream().filter(o -> o.getUser().equals(user)).findFirst().orElse(null);
    }    
}
