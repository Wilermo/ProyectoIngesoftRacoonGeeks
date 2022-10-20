package model;

public class Respuesta {

    private String usuario;
    private String comentario;
    private String contenidoRespuesta;
    private Double notaRespuesta;

    public Double getNotaRespuesta() {
        return notaRespuesta;
    }

    public void setNotaRespuesta(Double notaRespuesta) {
        this.notaRespuesta = notaRespuesta;
    }



    public Respuesta() {
    }

    public Respuesta(String comentario, String estudiante, String contenidoRespuesta) {
        this.comentario = comentario;
        this.usuario = estudiante;
        this.contenidoRespuesta = contenidoRespuesta;
    }



    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getComentario() {
        return comentario;
    }

    public String getContenidoRespuesta() {
        return contenidoRespuesta;
    }

    public void setContenidoRespuesta(String contenidoRespuesta) {
        this.contenidoRespuesta = contenidoRespuesta;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

}
