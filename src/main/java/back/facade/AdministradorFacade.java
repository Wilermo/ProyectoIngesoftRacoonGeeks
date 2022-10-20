package back.facade;

import back.business.AdministradorBusiness;
import model.Administrativo;

import java.util.Map;

public class AdministradorFacade {

    private AdministradorBusiness administradorBusiness;

    public AdministradorBusiness getAdministradorBusiness() {
        return administradorBusiness;
    }

    public void setAdministradorBusiness(AdministradorBusiness administradorBusiness) {
        this.administradorBusiness = administradorBusiness;
    }

    public AdministradorFacade(AdministradorBusiness administradorBusiness) {
        this.administradorBusiness = new AdministradorBusiness();
    }

    public AdministradorFacade(){
        this.administradorBusiness = new AdministradorBusiness();
    }

    public Map<String,Administrativo> listarAdministradores(){
        return administradorBusiness.listarAdmins();
    }

    public void guardarAdministradores(){
        administradorBusiness.guardarAdministradores();
    }
}
