package model;
import interfaceProgram.IAsignacion;

import java.util.*;


public class Curso {
    private UUID idCurso;
    private String nombreCurso;
    private Map <String, Profesor> profesoresPertenecenCurso =new HashMap<>();
    private Map <String, Estudiante> estudiantesPertenecenCurso =new HashMap<>();
    private Map <String,Monitor> monitoresCurso=new HashMap<>();
    private Map <UUID, IAsignacion> asignacionesCurso=new HashMap<>();

    public Curso(String nombreCurso, Map<String, Profesor> profesoresCurso, Map<String, Estudiante> estudiantesCurso){
        this.idCurso = UUID.randomUUID();
        this.nombreCurso = nombreCurso;
        this.profesoresPertenecenCurso = profesoresCurso;
        this.estudiantesPertenecenCurso = estudiantesCurso;
    }

    public Curso(String nombreCurso){
        this.idCurso = UUID.randomUUID();
        this.nombreCurso = nombreCurso;
    }

    public Curso(){}

    public Map<String, Monitor> getMonitoresCurso() {
        return monitoresCurso;
    }

    public void setMonitoresCurso(Map<String, Monitor> monitoresCurso) {
        this.monitoresCurso = monitoresCurso;
    }


    public void setProfesoresPertenecenCurso(Map<String, Profesor> profesoresPertenecenCurso) {
        this.profesoresPertenecenCurso = profesoresPertenecenCurso;
    }


    public Map<UUID, IAsignacion> getAsignacionesCurso() {
        return asignacionesCurso;
    }

    public void setAsignacionesCurso(Map<UUID, IAsignacion> asignacionesCurso) {
        this.asignacionesCurso = asignacionesCurso;
    }

    public Map<String,Profesor> getProfesoresPertenecenCurso(){
        return this.profesoresPertenecenCurso;
    }

    public void setEstudiantesPertenecenCurso(Map newEstudiantesCurso){
        this.estudiantesPertenecenCurso =newEstudiantesCurso;
    }

    public Map<String,Estudiante> getEstudiantesPertenecenCurso(){
        return estudiantesPertenecenCurso;
    }

    /*public Map<String, Monitor> getListaMonitores() {
        return listaMonitores;
    }

    public void setListaMonitores(Map<String, Monitor> listaMonitores) {
        this.listaMonitores = listaMonitores;
    }*/

    public void setIdCurso(UUID newIdCurso){
        this.idCurso=newIdCurso;
    }

    public UUID getIdCurso(){
        return this.idCurso;
    }

    public void setNombreCurso(String newIdCurso){
        this.nombreCurso=newIdCurso;
    }

    public String getNombreCurso(){
        return this.nombreCurso;
    }

   /* @Override
    public String toString() {
        return "Curso {" +
                "|| Nombre Curso: '" + this.getNombreCurso() + '\'' +
                "|| Estudiantes curso:'" + this.getEstudiantesPertenecenCurso() + '\'' +
                "|| Profesores curso:'" + this.getProfesoresPertenecenCurso() + '\'' +
                '}';
    }
*/

}
