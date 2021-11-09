package model;
import java.util.*;


public class Curso {
    private UUID idCurso;
    private String nombreCurso;
    private Map <String, Profesor> profesoresPertenecenCurso =new HashMap<>();
    private Map <String, Estudiante> estudiantesPertenecenCurso =new HashMap<>();
    private Map <String,Monitor> monitoresCurso=new HashMap<>();

    public Curso(String nombreCurso, Map<String, Profesor> profesoresCurso, Map<String, Estudiante> estudiantesCurso){
        this.idCurso = UUID.randomUUID();
        this.nombreCurso = nombreCurso;
        this.profesoresPertenecenCurso = profesoresCurso;
        this.estudiantesPertenecenCurso = estudiantesCurso;
    }

    public Curso(){}

    public Map<String, Monitor> getMonitoresCurso() {
        return monitoresCurso;
    }

    public void setMonitoresCurso(Map<String, Monitor> monitoresCurso) {
        this.monitoresCurso = monitoresCurso;
    }

    public void setProfesoresPertenecenCurso(Map newProfesoresCurso){
        this.profesoresPertenecenCurso =newProfesoresCurso;
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

    @Override
    public String toString() {
        return "Curso {" +
                "|| Nombre Curso: '" + this.getNombreCurso() + '\'' +
                "|| Estudiantes curso:'" + this.getEstudiantesPertenecenCurso() + '\'' +
                "|| Profesores curso:'" + this.getProfesoresPertenecenCurso() + '\'' +
                '}';
    }


}
