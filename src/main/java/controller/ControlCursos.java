package controller;
import back.facade.CursoFacade;
import interfaceProgram.Global.IGlobalController;
import interfaceProgram.IAsignacion;
import model.Asignacion;
import model.Curso;
import model.Estudiante;

import model.Profesor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ControlCursos {



    public ControlCursos (){

    }

    private Map<UUID, Curso> listaCursos = new HashMap<>();

    public Map<UUID, Curso> getListaCursos() {
        return listaCursos;
    }
    public void setListaCursos(Map<UUID, Curso> listaCursos) {
        this.listaCursos = listaCursos;
    }

    public Curso crearCurso(String nombre){
        Curso nuevoCurso = new Curso(nombre);
        insertarCurso(nuevoCurso);
        return nuevoCurso;
    }

    public Curso buscarCurso(UUID id){
        if(this.listaCursos.containsKey(id)){
            return this.listaCursos.get(id);
        }
        return null;
    }

    public void insertarCurso(Curso nuevoCurso){
        if (buscarCurso(nuevoCurso.getIdCurso()) == null) {
            this.listaCursos.put(nuevoCurso.getIdCurso(), nuevoCurso);
            System.out.println("El curso se agrego con exito.");
            System.out.println(nuevoCurso.toString());
        }
        else{
            System.out.println("Ese curso ya existe. No puede agregar un curso existente.");
        }
    }

    public boolean consultarCurso(){
        boolean ver = false;
        if(!this.listaCursos.isEmpty()) {
            for (Curso cur : this.listaCursos.values()) {
                System.out.println(cur.toString());
                ver = true;
            }
        }
        System.out.println("En este momento no hay ningun curso registrado");
        return ver;
    }

    public void modificarCurso(Curso curso, String nuevoNombre){
        Curso curso2=buscarCurso(curso.getIdCurso());
        if (curso2 != null) {
            curso.setNombreCurso(nuevoNombre);
            listaCursos.replace(curso.getIdCurso(),curso2,curso);
        }else{
            System.out.println("El curso ingresado no existe. Por favor, intentelo de nuevo.");
        }
    }

    public void eliminarCurso(Curso curso){
        if (buscarCurso(curso.getIdCurso()) != null) {
            System.out.println("El curso se encontro con exito!");
            System.out.println(curso.toString());
            this.listaCursos.remove(curso.getIdCurso());
            System.out.println("El curso se ha eliminado con exito! ");
        }
        else{
            System.out.println("El curso no existe. Por favor, ingreselo de nuevo.");
        }
    }

    public void aniadirTarea(UUID idCurso, IAsignacion asignacion   ){
        if(asignacion!=null){
            IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().buscarCurso(idCurso).getAsignacionesCurso().put(asignacion.getIdAsignacion(),asignacion);
            System.out.println("Tarea Guardada");
        }
    }
}
