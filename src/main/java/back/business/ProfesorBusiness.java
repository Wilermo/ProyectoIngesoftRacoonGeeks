package back.business;

import controller.ControlCursos;
import controller.ControladorProfesor;
import model.Profesor;
import utils.archivos.ArchivoProfesor;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.Map;

public class ProfesorBusiness {
    private ArchivoProfesor archivoProfesor;
    private ControladorProfesor controladorProfesor;

    public ControladorProfesor getControladorProfesor(){
        return this.controladorProfesor;
    }

    public ProfesorBusiness(ArchivoProfesor archivoProfesor) {
        this.archivoProfesor = archivoProfesor;
    }

    public ProfesorBusiness() {
        this.archivoProfesor = new ArchivoProfesor();
        this.controladorProfesor=new ControladorProfesor();
    }

    public Map<String, Profesor> listarProfesores(){
        return archivoProfesor.listarProfesores();
    }

    public void guardarProfes(){
        archivoProfesor.escribirInfo();
    }
}
