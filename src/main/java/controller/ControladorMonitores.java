package controller;

import java.util.Map;
import model.Monitor;

public class ControladorMonitores {
    private Map<String, Monitor> listaMonitores;

    public Map<String, Monitor> getListaMonitores() {
        return listaMonitores;
    }

    public void setListaMonitores(Map<String, Monitor> listaMonitores) {
        this.listaMonitores = listaMonitores;
    }
}
