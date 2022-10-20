package back.facade;

import back.business.MonitorBusiness;
import model.Monitor;

import java.util.Map;

public class MonitorFacade {

    private MonitorBusiness monitorBusiness;

    public MonitorFacade(MonitorBusiness monitorBusiness) {
        this.monitorBusiness = monitorBusiness;
    }

    public MonitorFacade() {
        this.monitorBusiness = new MonitorBusiness();
    }

    public Map<String, Monitor> listarMonitores(){
        return monitorBusiness.listarMonitores();
    }

    public  void guardarMonitores(){
        monitorBusiness.guardarMonitores();
    }

    public MonitorBusiness getMonitorBusiness() {
        return monitorBusiness;
    }

    public void setMonitorBusiness(MonitorBusiness monitorBusiness) {
        this.monitorBusiness = monitorBusiness;
    }
}
