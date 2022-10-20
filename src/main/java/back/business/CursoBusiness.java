package back.business;

import controller.ControlCursos;
import model.Curso;
import model.Profesor;
import utils.archivos.ArchivoCursos;
import utils.archivos.ArchivoProfesor;

import java.util.Map;
import java.util.UUID;

public class CursoBusiness {
    private ArchivoCursos archivoCursos;
    private ControlCursos controlCursos;

    public ControlCursos getControlCursos(){
        return this.controlCursos;
    }

    public CursoBusiness(ArchivoCursos archivoCursos) {
        this.archivoCursos = archivoCursos;
    }

    public CursoBusiness() {
        this.archivoCursos =  new ArchivoCursos();
        this.controlCursos=new ControlCursos();
    }



    public Map<UUID, Curso> listarCursos(){
        return archivoCursos.listarCurso();
    }

    public void guardarCursos(){
        archivoCursos.escribirInfo();
    }
}
