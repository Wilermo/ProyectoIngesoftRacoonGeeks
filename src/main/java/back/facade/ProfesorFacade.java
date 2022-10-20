package back.facade;

import back.business.ProfesorBusiness;
import model.Profesor;
import utils.archivos.ArchivoProfesor;

import java.util.Map;

public class ProfesorFacade {

    private ProfesorBusiness profesorBusiness;


    public ProfesorFacade(ProfesorBusiness profesorBusiness) {
        this.profesorBusiness = profesorBusiness;
    }

    public ProfesorFacade() {
        this.profesorBusiness = new ProfesorBusiness();
    }

    public void guardarProfes(){
        profesorBusiness.guardarProfes();
    }

    public Map<String, Profesor> listarProfesores(){
        return profesorBusiness.listarProfesores();
    }

    public ProfesorBusiness getProfesorBusiness() {
        return profesorBusiness;
    }

    public void setProfesorBusiness(ProfesorBusiness profesorBusiness) {
        this.profesorBusiness = profesorBusiness;
    }
}
