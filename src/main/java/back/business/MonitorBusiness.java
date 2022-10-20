package back.business;

import controller.ControladorMonitores;
import model.Monitor;
import utils.archivos.ArchivoMonitor;

import java.util.Map;

public class MonitorBusiness {

    public ControladorMonitores getControladorMonitores() {
        return controladorMonitores;
    }

    public void setControladorMonitores(ControladorMonitores controladorMonitores) {
        this.controladorMonitores = controladorMonitores;
    }

    public ArchivoMonitor getArchivoMonitor() {
        return archivoMonitor;
    }

    public void setArchivoMonitor(ArchivoMonitor archivoMonitor) {
        this.archivoMonitor = archivoMonitor;
    }

    private ArchivoMonitor archivoMonitor;
    private ControladorMonitores controladorMonitores;

    public MonitorBusiness(ArchivoMonitor archivoMonitor) {
        this.archivoMonitor = archivoMonitor;
    }

    public MonitorBusiness() {
        this.archivoMonitor = new ArchivoMonitor();
        this.controladorMonitores=new ControladorMonitores();
    }

    public Map<String, Monitor> listarMonitores(){
        return archivoMonitor.listarMonitores();
    }

    public void guardarMonitores(){
        archivoMonitor.escribirInfo();
    }
}
