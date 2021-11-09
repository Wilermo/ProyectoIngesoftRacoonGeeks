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

    private String carreraProfe;
    private Long cedulaProfe;
    private TipoProfesor tipoProfesor;
    private TipoGeneral tipo;
    private Map<UUID, Curso> cursosPertenecenAProfesor = new HashMap<>();

    public Profesor() {
    }

    public Profesor(String usuario, String contrasenna, String nombre, String correo, String carreraProfe, Long cedulaProfe, TipoProfesor tipoProfesor, TipoGeneral tipo, Map<UUID, Curso> listaCursosProfesor) {
        super(usuario, contrasenna, nombre, correo);
        this.carreraProfe = carreraProfe;
        this.cedulaProfe = cedulaProfe;
        this.tipoProfesor = tipoProfesor;
        this.tipo = tipo;
        this.cursosPertenecenAProfesor = listaCursosProfesor;
    }

    public Long getCedulaProfe() {
        return cedulaProfe;
    }
    public void setCedulaProfe(Long cedulaProfe) {
        this.cedulaProfe = cedulaProfe;
    }
    public TipoProfesor getTipoProfesor() {
        return tipoProfesor;
    }
    public void setTipoProfesor(TipoProfesor tipoProfesor) {
        this.tipoProfesor = tipoProfesor;
    }
    public TipoGeneral getTipo() { return tipo; }
    public void setTipo(TipoGeneral tipo) { this.tipo = tipo; }
    public Map<UUID, Curso> getCursosPertenecenAProfesor() {
        return cursosPertenecenAProfesor;
    }
    public void setCursosPertenecenAProfesor(Map<UUID, Curso> cursosPertenecenAProfesor) {
        this.cursosPertenecenAProfesor = cursosPertenecenAProfesor;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "nombreProfe='" + super.getNombre() + '\'' +
                "CorreoProfe='" + super.getCorreo() + '\'' +
                ", listaCursosProfesor=" + this.getCursosPertenecenAProfesor() +
                '}';
    }
}
