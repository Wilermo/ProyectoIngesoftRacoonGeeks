
package model;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.*;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Monitor extends Estudiante {
    private Map <UUID, Curso> CursosMonitor=new HashMap<>();

    public Monitor() {
    }

    public Monitor(String usuario, String contrasenna, String nombre, String correo, String carreraEstud, Long documentoEstud, TipoGeneral tipo, Map<UUID, Curso> listaCursosEstudiante, Map<UUID, Curso> cursosMonitor) {
        super(usuario, contrasenna, nombre, correo, carreraEstud, documentoEstud, tipo, listaCursosEstudiante);
        CursosMonitor = cursosMonitor;
    }

    public Monitor(String usuario, String contrasenna, String nombre, String correo, String carreraEstud, Long documentoEstud, TipoGeneral tipo, Map<UUID, Curso> listaCursosEstudiante) {
        super(usuario, contrasenna, nombre, correo, carreraEstud, documentoEstud, tipo, listaCursosEstudiante);
    }

    public Map<UUID, Curso> getCursosMonitor() {
        return CursosMonitor;
    }

    public void setCursosMonitor(Map<UUID, Curso> cursosMonitor) {
        CursosMonitor = cursosMonitor;
    }
}

