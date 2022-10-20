package back.business;

import model.Administrativo;
import model.Profesor;
import utils.archivos.ArchivoAdministrador;

import java.util.Map;

public class AdministradorBusiness {
    private ArchivoAdministrador archivoAdministrador;

    public AdministradorBusiness(ArchivoAdministrador archivoAdministrador) {
        this.archivoAdministrador = archivoAdministrador;
    }

    public AdministradorBusiness() {
        this.archivoAdministrador = new ArchivoAdministrador();
    }

    public Map<String, Administrativo> listarAdmins(){
        return archivoAdministrador.listarAdministrador();
    }

    public void guardarAdministradores(){
        archivoAdministrador.escribirInfo();
    }


}
