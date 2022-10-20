package model;

import interfaceProgram.Global.IGlobalController;
import interfaceProgram.IAsignacion;

import java.util.UUID;

public class Asignacion  {

    private String titulo;
    private String instrucciones;
    private UUID idAsignacion;

    public Asignacion(){
    }


    public void  eliminarAsignacion(Asignacion asignacion) {
        IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().buscarCurso(idAsignacion).getAsignacionesCurso().remove(getIdAsignacion());
    }

    public void modificarAsignacion(String titulo, String instrucciones) {
        setTitulo(titulo);
        setInstrucciones(instrucciones);
    }

    public String tipo() {
        return null;
    }

    public UUID getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(UUID idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public Asignacion(String titulo, String instrucciones) {
        this.titulo = titulo;
        this.instrucciones = instrucciones;
        this.idAsignacion=UUID.randomUUID();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }
}
