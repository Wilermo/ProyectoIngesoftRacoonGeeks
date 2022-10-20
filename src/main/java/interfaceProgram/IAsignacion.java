package interfaceProgram;

import model.Asignacion;
import model.Respuesta;
import model.Tarea;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public interface IAsignacion {
    //public Asignacion crearAsignacion(String titulo, String instrucciones);
    public Asignacion crearAsignacion(String titulo, String instrucciones, float calificacionMAX, Date fechaInicio, Date fechaCierre);
    public UUID getIdAsignacion();
    public void eliminarAsignacion();
    public void modificarAsignacion(String titulo, String instrucciones);
    String getTitulo();
    String getInstrucciones();
    void setTitulo(String titulo);
    void setInstrucciones(String instrucciones);
    void setCalificacionMAX(float calificaionMAX);

    public String getFechaStringIncio();
    public void setFechaStringIncio(String fechaStringIncio);

    public String getFechaStringCierre();

    public void setFechaStringCierre(String fechaStringCierre);

    public Map<String, Respuesta> getListaRespuestas();

    public void setListaRespuestas(Map<String, Respuesta> listaRespuestas);

    public float getCalificacionMAX();

    public Date getFechaInicio();

    public void setFechaInicio(Date fechaInicio);

    public Date getFechaCierre();

    public void setFechaCierre(Date fechaCierre);

    public void formatFechasAString();
    public void formatFechasADate(String fechaStringIncio,String fechaStringCierre);

    public String tipo();

}
