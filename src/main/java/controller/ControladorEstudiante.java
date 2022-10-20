package controller;

import back.facade.EstudianteFacade;
import back.facade.MonitorFacade;
import interfaceProgram.Global.IGlobalController;
import javafx.fxml.FXML;
import model.Estudiante;
import model.Monitor;
import model.Profesor;

import java.util.HashMap;
import java.util.Map;

public class ControladorEstudiante {
    private Map<String, Estudiante> listaEstudiantes = new HashMap<>();
    private Map<String, Monitor> listaMonitores=new HashMap<>();

    public ControladorEstudiante() {
    }
    

    public Map<String, Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }
    public void setListaEstudiantes(Map<String, Estudiante> listaEstudiantes) { this.listaEstudiantes = listaEstudiantes; }

    public Estudiante buscarEstudiante(String usuario){
        if(this.listaEstudiantes.containsKey(usuario)){
            return this.listaEstudiantes.get(usuario);
        }
        return null;
    }

    public void insertarEstudiante(Estudiante nuevoEstudiante){
        if (buscarEstudiante(nuevoEstudiante.getUsuario()) == null) {
            this.listaEstudiantes.put(nuevoEstudiante.getUsuario(), nuevoEstudiante);
            System.out.println("Cliente registrado con éxito!");
            System.out.println(nuevoEstudiante.toString());
        }
        else{
            System.out.println("Ya existe un estudiante con este nombre de Usuario, inténtelo nuevamente!");
        }
    }

    public boolean consultarEstudiantes(){
        boolean ver = false;
        if(!this.listaEstudiantes.isEmpty()) {
            for (Estudiante estudiante : this.listaEstudiantes.values()) {
                System.out.println(estudiante.toString());
                ver = true;
            }
        }
        System.out.println("Actualmente no existen estudiantes, por favor registre al menos uno");
        return ver;
    }

    public void modificarEstudianteBasico(Estudiante estudiante, String nuevaContrasenna, String nuevoNombre, String nuevaCarrera){
        if (buscarEstudiante(estudiante.getUsuario()) != null) {
            estudiante.setContrasenna(nuevaContrasenna);
            estudiante.setNombre(nuevoNombre);
            estudiante.setCarreraEstud(nuevaCarrera);
            System.out.println("Los datos actualizados del profesor son: ");
            System.out.println(estudiante.toString());
        }
        else{
            System.out.println("El estudiante no existe, intente nuevamente");
        }
    }

    public void eliminarEstudiante(Estudiante estudiante){
        if (buscarEstudiante(estudiante.getUsuario()) != null) {
            System.out.println("Estudiante encontrado! ");
            System.out.println(estudiante.toString());
            IGlobalController.controladorGeneral.getUsuarios().remove(estudiante.getUsuario());
            this.listaEstudiantes.remove(estudiante.getUsuario());
            System.out.println("El estudiante ha sido eliminado con exito! ");
        }
        else{
            System.out.println("El estudiante no existe, intente nuevamente");
        }
    }

    public void actualizarEstudiante(Estudiante estudiante, String nombre, String usuario, String correo){
        if (buscarEstudiante(estudiante.getUsuario()) != null) {
            System.out.println("Estudiante encontrado!");
            System.out.println(estudiante.toString());
            estudiante.setNombre(nombre);
            estudiante.setUsuario(usuario);
            estudiante.setCorreo(correo);
            System.out.println("Los datos actualizados del estudiante son: ");

        }
        else{
            System.out.println("El estudiante no existe, intente nuevamente");
        }
    }

}
