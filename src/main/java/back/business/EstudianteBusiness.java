package back.business;

import controller.ControlCursos;
import controller.ControladorEstudiante;
import model.Estudiante;
import utils.archivos.ArchivoEstudiante;

import javax.sound.sampled.Control;
import java.util.Map;

public class EstudianteBusiness {
    private ArchivoEstudiante archivoEstudiante;
    private ControladorEstudiante controladorEstudiante;

    public ControladorEstudiante getControladorEstudiante(){
        return this.controladorEstudiante;
    }

    public EstudianteBusiness(ArchivoEstudiante archivoEstudiante) {
        this.archivoEstudiante = archivoEstudiante;
        this.controladorEstudiante=new ControladorEstudiante();
    }

    public EstudianteBusiness(){
        this.archivoEstudiante = new ArchivoEstudiante();
        this.controladorEstudiante=new ControladorEstudiante();
    }

    public Map<String, Estudiante> listaEstudiantes(){
        return archivoEstudiante.listaEstudiantes();
    }

    public void guardarEstudiantes(){
        archivoEstudiante.escribirInfo();
    }
}
