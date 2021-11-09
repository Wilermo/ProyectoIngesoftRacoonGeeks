package model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Administrativo extends Usuario {

    private long cedulaAdmin;
    private TipoGeneral tipo;

    public Administrativo() {
    }

    public Administrativo(String usuario, String contrasenna, String nombre, String correo,  long cedulaAdmin, TipoGeneral tipo) {
        super(usuario, contrasenna, nombre, correo);
        this.cedulaAdmin = cedulaAdmin;
        this.tipo = tipo;
    }

    public long getCedulaAdmin() {
        return cedulaAdmin;
    }
    public void setCedulaAdmin(long cedulaAdmin) {
        this.cedulaAdmin = cedulaAdmin;
    }
}
