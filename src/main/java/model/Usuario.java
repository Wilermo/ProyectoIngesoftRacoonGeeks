package model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({Estudiante.class,Profesor.class,Administrativo.class})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Usuario {
    private String usuario;
    private String contrasenna;
    private String nombre;
    private String correo;

    public String getUsuario() {
        return usuario;
    }

    public Usuario() {
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Usuario(String usuario, String contrasenna, String nombre, String correo) {
        this.usuario = usuario;
        this.contrasenna = contrasenna;
        this.nombre = nombre;
        this.correo = correo;
    }
}
