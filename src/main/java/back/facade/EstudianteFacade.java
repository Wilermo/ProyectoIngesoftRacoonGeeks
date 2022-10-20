package back.facade;

import back.business.EstudianteBusiness;
import model.Estudiante;

import java.util.Map;

public class EstudianteFacade {
    private EstudianteBusiness estudianteBusiness;

    public EstudianteFacade(EstudianteBusiness estudianteBusiness) {
        this.estudianteBusiness = estudianteBusiness;
    }

    public EstudianteFacade(){
        this.estudianteBusiness = new EstudianteBusiness();
    }

    public Map<String, Estudiante> listaEstudiantes(){
        return estudianteBusiness.listaEstudiantes();
    }

    public void guardarEstudiantes(){
        estudianteBusiness.guardarEstudiantes();
    }

    public EstudianteBusiness getEstudianteBusiness() {
        return estudianteBusiness;
    }

    public void setEstudianteBusiness(EstudianteBusiness estudianteBusiness) {
        this.estudianteBusiness = estudianteBusiness;
    }
}
