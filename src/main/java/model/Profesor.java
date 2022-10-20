package model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Profesor extends Usuario {

    private Long cedulaProfe;
    private Map<UUID, Curso> cursosPertenecenAProfesor = new HashMap<>();

    public Profesor() {
    }

    public Profesor(String usuario, String contrasenna, String nombre, String correo, Long cedulaProfe, Map<UUID, Curso> listaCursosProfesor) {
        super(usuario, contrasenna, nombre, correo);
        this.cedulaProfe = cedulaProfe;
        this.cursosPertenecenAProfesor = listaCursosProfesor;
    }

    public Profesor(String usuario, String contrasenna, String nombre, String correo, Long cedulaProfe) {
        super(usuario, contrasenna, nombre, correo);
        this.cedulaProfe = cedulaProfe;
    }

    public Profesor(String usuario, String nombre, String correo, Long cedulaProfe) {
        super(usuario, nombre, correo);
        this.cedulaProfe = cedulaProfe;
    }

    public Long getCedulaProfe() {
        return cedulaProfe;
    }
    public void setCedulaProfe(Long cedulaProfe) {
        this.cedulaProfe = cedulaProfe;
    }

    public Map<UUID, Curso> getCursosPertenecenAProfesor() {
        return cursosPertenecenAProfesor;
    }
    public void setCursosPertenecenAProfesor(Map<UUID, Curso> cursosPertenecenAProfesor) {
        this.cursosPertenecenAProfesor = cursosPertenecenAProfesor;
    }

    /*@Override
    public String toString() {
        return "Profesor{" +
                "nombreProfe='" + super.getNombre() + '\'' +
                "CorreoProfe='" + super.getCorreo() + '\'' +
                ", listaCursosProfesor=" + this.getCursosPertenecenAProfesor() +
                '}';
    }*/

}
