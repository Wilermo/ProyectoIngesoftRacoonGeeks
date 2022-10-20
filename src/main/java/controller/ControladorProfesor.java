package controller;
import back.facade.ProfesorFacade;
import interfaceProgram.Global.IGlobalController;
import javafx.fxml.FXML;
import model.Profesor;
import java.util.HashMap;
import java.util.Map;

public class ControladorProfesor {
    private Map<String, Profesor> listaProfes = new HashMap<>();


    public ControladorProfesor(){
    }



    public Map<String, Profesor> getListaProfes() {
        return listaProfes;
    }
    public void setListaProfes(Map<String, Profesor> listaProfes) {
        this.listaProfes = listaProfes;
    }


    public Profesor buscarProfesor(String usuario){
        if(this.listaProfes.containsKey(usuario)){
            return this.listaProfes.get(usuario);
        }
        return null;
    }

    public void insertarProfesor(Profesor profesorNuevo){
        if (buscarProfesor(profesorNuevo.getUsuario()) == null) {
//            IGlobal.controladorGeneral.getControlProfe().getListaProfes().put(profesorNuevo.getUsuario(), profesorNuevo);
            this.listaProfes.put(profesorNuevo.getUsuario(), profesorNuevo);
            System.out.println("Profesor registrado con éxito!");
            System.out.println(profesorNuevo.toString());
        }
        else{
            System.out.println("Ya existe un profesor con este nombre de Usuario, inténtelo nuevamente!");
            //throw new ExcCliente("Ya existe un cliente con este número de cedula, inténtelo nuevamente!");
        }
    }

    public boolean consultarProfesores(){
        boolean ver = false;
        if(!this.listaProfes.isEmpty()) {
            for (Profesor profesor : this.listaProfes.values()) {
                System.out.println(profesor.toString());
            }
            ver = true;
        }
        System.out.println("Actualmente no existen profesores, por favor registre al menos uno");
        return ver;
    }

    public void modificarProfesoresBasico(Profesor profesor, String nuevaContrasenna){
        if (buscarProfesor(profesor.getUsuario()) != null) {
            System.out.println("Profesor encontrado!");
            System.out.println(profesor.toString());
            profesor.setContrasenna(nuevaContrasenna);
            System.out.println("Los datos actualizados del profesor son: ");
            System.out.println(profesor.toString());
        }
        else{
            System.out.println("El profesor no existe, intente nuevamente");
        }
    }

    public void actualizarProfesor(Profesor profesor, String nombre, String usuario, String correo){
        if (buscarProfesor(profesor.getUsuario()) != null) {
            System.out.println("Profesor encontrado!");
            System.out.println(profesor.toString());
            profesor.setNombre(nombre);
            profesor.setUsuario(usuario);
            profesor.setCorreo(correo);
            System.out.println("Los datos actualizados del profesor son: ");

        }
        else{
            System.out.println("El profesor no existe, intente nuevamente");
        }
    }

    public void eliminarProfesor(Profesor profesor){
        if (buscarProfesor(profesor.getUsuario()) != null) {
            System.out.println("Profesor encontrado!");
            System.out.println(profesor.toString());
            // this.listaClientes.remove(this.listaClientes.indexOf(cliente));
            IGlobalController.controladorGeneral.getUsuarios().remove(profesor.getUsuario());
            this.listaProfes.remove(profesor.getUsuario());
            System.out.println("El profesor ha sido eliminado con exito! ");
        }
        else{
            System.out.println("El profesor no existe, intente nuevamente");
        }
    }

}
