package model;

import interfaceProgram.Global.IGlobalController;
import interfaceProgram.IAsignacion;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Tarea extends Asignacion implements IAsignacion {

    public Tarea(){}

    public Tarea(String titulo, String instrucciones, float calificacionMAX, Date fechaInicio, Date fechaCierre) {
        super(titulo, instrucciones);
        super.setIdAsignacion(UUID.randomUUID());
        this.calificacionMAX = calificacionMAX;
        this.fechaInicio = fechaInicio;
        this.fechaCierre = fechaCierre;
        formatFechasAString();
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "calificacionMAX=" + calificacionMAX +
                ", fechaInicio=" + fechaInicio +
                ", fechaCierre=" + fechaCierre +
                ", fechaStringIncio='" + fechaStringIncio + '\'' +
                ", fechaStringCierre='" + fechaStringCierre + '\'' +
                ", listaRespuestas=" + listaRespuestas +
                '}';
    }

    public Asignacion crearAsignacion(String titulo, String instrucciones, float calificacionMAX, Date fechaInicio, Date fechaCierre) {
        super.setTitulo(titulo);
        super.setIdAsignacion(UUID.randomUUID());
        super.setInstrucciones(instrucciones);
        setCalificacionMAX(calificacionMAX);
        setFechaInicio(fechaInicio);
        setFechaCierre(fechaCierre);
        formatFechasAString();
        return new Tarea(titulo,instrucciones,calificacionMAX,fechaInicio,fechaCierre);
    }

    public void eliminarAsignacion(){
        IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().buscarCurso(getIdAsignacion()).getAsignacionesCurso().remove(getIdAsignacion());
    }

    public void modificarAsignacion(String titulo, String instrucciones) {
        setTitulo(titulo);
        setInstrucciones(instrucciones);
    }

    private float calificacionMAX;
    private Date fechaInicio;
    private Date fechaCierre;
    private String fechaStringIncio;
    private String fechaStringCierre;
    private Map<String, Respuesta> listaRespuestas = new HashMap<>();

    public String getFechaStringIncio() {
        return fechaStringIncio;
    }

    public void setFechaStringIncio(String fechaStringIncio) {
        this.fechaStringIncio = fechaStringIncio;
    }

    public String getFechaStringCierre() {
        return fechaStringCierre;
    }

    public void setFechaStringCierre(String fechaStringCierre) {
        this.fechaStringCierre = fechaStringCierre;
    }

    public Map<String, Respuesta> getListaRespuestas() {
        return listaRespuestas;
    }

    public void setListaRespuestas(Map<String, Respuesta> listaRespuestas) {
        this.listaRespuestas = listaRespuestas;
    }

    public float getCalificacionMAX() {
        return calificacionMAX;
    }

    public void setCalificacionMAX(float calificacionMAX) {
        this.calificacionMAX = calificacionMAX;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public void formatFechasAString(){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaString;
        if(this.fechaInicio!=null){
            fechaString=format.format(fechaInicio);
            this.fechaStringIncio=fechaString;
            System.out.println(fechaString);
        }
        if(this.fechaCierre!=null){
            fechaString=format.format(fechaCierre);
            this.fechaStringCierre=fechaString;
            System.out.println(fechaString);
        }
    }

    public void formatFechasADate(String fechaStringIncio,String fechaStringCierre){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(!fechaStringIncio.equals(null)){
            try {
                fechaInicio = format.parse(fechaStringIncio);
                System.out.println(fechaInicio);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }if(!fechaStringCierre.equals(null)){
            try {
                fechaCierre = format.parse(fechaStringCierre);
                System.out.println(fechaCierre);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
