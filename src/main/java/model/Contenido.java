package model;

import interfaceProgram.Global.IGlobalController;
import interfaceProgram.IAsignacion;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class Contenido extends Asignacion implements IAsignacion {

    public Contenido(){};
    public Contenido(String titulo, String instrucciones) {
        super(titulo, instrucciones);
    }

    public Asignacion crearAsignacion(String titulo, String instrucciones) {
        return new Contenido(titulo,instrucciones);
    }
    public Asignacion crearAsignacion(String titulo, String instrucciones, float calificacionMAX, Date fechaInicio, Date fechaCierre) {
        super.setTitulo(titulo);
        super.setInstrucciones(instrucciones);
        super.setIdAsignacion(UUID.randomUUID());
        return new Contenido(titulo,instrucciones);
    }

    public void eliminarAsignacion() {
       IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().buscarCurso(getIdAsignacion()).getAsignacionesCurso().remove(getIdAsignacion());
    }

    public void modificarAsignacion(String titulo, String instrucciones) {
        setTitulo(titulo);
        setInstrucciones(instrucciones);
    }

    public String tipo() {
        return null;
    }


    public void setCalificacionMAX(Float calificaionMAX){};

    public String getFechaStringIncio(){return null;};
    public void setFechaStringIncio(String fechaStringIncio){};

    public String getFechaStringCierre(){return null;};

    public void setFechaStringCierre(String fechaStringCierre){};

    public Map<String, Respuesta> getListaRespuestas(){return null;};

    public void setListaRespuestas(Map<String, Respuesta> listaRespuestas){};

    public float getCalificacionMAX(){return 0;};

    public void setCalificacionMAX(float calificacionMAX){};

    public Date getFechaInicio(){return null;};

    public void setFechaInicio(Date fechaInicio){};

    public Date getFechaCierre(){return null;};

    public void setFechaCierre(Date fechaCierre){};

    public Map<String, Respuesta> getListaRepsuestas(){return null;};
    public void setListaRepsuestas(Map<String, Respuesta> listaRepsuestas){};

    public void formatFechasAString(){};
    public void formatFechasADate(String fechaStringIncio,String fechaStringCierre){};

}
