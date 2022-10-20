package controller;


import back.facade.*;
import interfaceProgram.Global.IGlobalController;
import model.*;
import utils.archivos.ManejoArchivos;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ControladorGeneral {

    public Usuario actualUser;
    public UUID actualCourse;
    public boolean esMonitor=false;

    //FACADE

    private AdministradorFacade administradorFacade;
    private CursoFacade cursoFacade;
    private EstudianteFacade estudianteFacade;
    private MonitorFacade monitorFacade;
    private ProfesorFacade profesorFacade;

    public ControladorGeneral(){
        administradorFacade=new AdministradorFacade();
        cursoFacade = new CursoFacade();
        estudianteFacade = new EstudianteFacade();
        monitorFacade = new MonitorFacade();
        profesorFacade = new ProfesorFacade();
    }

    public CursoFacade getCursoFacade() {
        return cursoFacade;
    }

    public void setCursoFacade(CursoFacade cursoFacade) {
        this.cursoFacade = cursoFacade;
    }

    public EstudianteFacade getEstudianteFacade() {
        return estudianteFacade;
    }

    public void setEstudianteFacade(EstudianteFacade estudianteFacade) {
        this.estudianteFacade = estudianteFacade;
    }

    public MonitorFacade getMonitorFacade() {
        return monitorFacade;
    }

    public void setMonitorFacade(MonitorFacade monitorFacade) {
        this.monitorFacade = monitorFacade;
    }

    public ProfesorFacade getProfesorFacade() {
        return profesorFacade;
    }

    public void setProfesorFacade(ProfesorFacade profesorFacade) {
        this.profesorFacade = profesorFacade;
    }

    private Map<String, Administrativo> listaAdministrador = new HashMap<>();
    private Map<String, Usuario> usuarios = new HashMap<>();
    public Map<String, Administrativo> getListaAdministrador() {return listaAdministrador;}

public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }
    public AdministradorFacade getAdministradorFacade() {
        return administradorFacade;
    }

    public void setAdministradorFacade(AdministradorFacade administradorFacade) {
        this.administradorFacade = administradorFacade;
    }

    public int comprobarTipoUsuario(String usuario, String contrasenna) {
        //Segun el tipo de retorno, se abre una pestaña u otra
        Usuario UsuarioEncontrado = this.buscarUsuario(usuario);

        if (UsuarioEncontrado.getUsuario().equals(usuario) && UsuarioEncontrado.getContrasenna().equals(contrasenna)) {
            this.actualUser=usuarios.get(usuario);
            if(UsuarioEncontrado instanceof Monitor){
                return 0;
            }else if (UsuarioEncontrado instanceof Estudiante) {
                return 1;
            } else if (UsuarioEncontrado instanceof Profesor) {
                return 2;
            } else if (UsuarioEncontrado instanceof Administrativo) {
                return 3;
            }
            return 4;
        } else {
            System.out.println("No existe usuario");
            return 4;
        }
    }

    Usuario buscarUsuario(String usuario) {
        if (estudianteFacade.getEstudianteBusiness().getControladorEstudiante().getListaEstudiantes().containsKey(usuario)) {
            return estudianteFacade.getEstudianteBusiness().getControladorEstudiante().getListaEstudiantes().get(usuario);
        } else if (this.listaAdministrador.containsKey(usuario)) {
            return this.listaAdministrador.get(usuario);
        } else if (profesorFacade.getProfesorBusiness().getControladorProfesor().getListaProfes().containsKey(usuario)) {
            return profesorFacade.getProfesorBusiness().getControladorProfesor().getListaProfes().get(usuario);
        }
        return null;
    }

    public void guardarUsuario(Usuario user) {
        this.usuarios.put(user.getUsuario(), user);
        if (user instanceof Estudiante) {
            this.estudianteFacade.getEstudianteBusiness().getControladorEstudiante().getListaEstudiantes().put(user.getUsuario(), (Estudiante) user);
        } else if (user instanceof Administrativo) {
            this.listaAdministrador.put(user.getUsuario(), (Administrativo) user);
        } else if (user instanceof Profesor) {
            this.profesorFacade.getProfesorBusiness().getControladorProfesor().getListaProfes().put(user.getUsuario(), (Profesor) user);
        }
    }

    public void setListaAdministrador(Map<String, Administrativo> listaAdministrador) {
        this.listaAdministrador = listaAdministrador;
    }

    public Administrativo buscarAdministradores(String usuario){
        if(this.listaAdministrador.containsKey(usuario)){
            return this.listaAdministrador.get(usuario);
        }
        return null;
    }

    public void insertarAdmin(Administrativo adminNuevo){
        if (buscarAdministradores(adminNuevo.getUsuario()) == null) {
            this.listaAdministrador.put(adminNuevo.getUsuario(), adminNuevo);
            System.out.println("Administrador registrado con éxito!");
            System.out.println(adminNuevo.toString());
        }
        else{
            System.out.println("Ya existe un administrador con este nombre de Usuario, inténtelo nuevamente!");
        }
    }

    public boolean consultarAdministradores(){
        boolean ver = false;
        if(!this.listaAdministrador.isEmpty()) {
            for (Administrativo admin : this.listaAdministrador.values()) {
                System.out.println(admin.toString());
            }
            ver = true;
        }
        System.out.println("Actualmente no existen administradores, por favor registre al menos uno");
        return ver;
    }

    public void modificarAdminBasico(Administrativo admin, String nuevaContrasenna){
        if (buscarAdministradores(admin.getUsuario()) != null) {
            System.out.println("Administrador encontrado!");
            System.out.println(admin.toString());
            admin.setContrasenna(nuevaContrasenna);
            System.out.println("Los datos actualizados del administrador son: ");
            System.out.println(admin.toString());
        }
        else{
            System.out.println("El administrador no existe, intente nuevamente");
        }
    }

    public void eliminarAdministrador(Administrativo admin){
        if (buscarAdministradores(admin.getUsuario()) != null) {
            System.out.println("Administrador encontrado!");
            System.out.println(admin.toString());
            // this.listaClientes.remove(this.listaClientes.indexOf(cliente);
            IGlobalController.controladorGeneral.getUsuarios().remove(admin.getUsuario());
            this.getListaAdministrador().remove(admin.getUsuario());
            this.listaAdministrador.remove(admin);
            this.usuarios.remove(admin);
            System.out.println("El administrador ha sido eliminado con exito! ");
        }else{
            System.out.println("El administrador no existe, intente nuevamente");
        }
    }

    public void asignarMonitoresACurso(UUID cursoID, String monitorId){
        if(cursoFacade.getCursoBusiness().getControlCursos().getListaCursos().containsKey(cursoID)&&monitorFacade.getMonitorBusiness().getControladorMonitores().getListaMonitores().containsKey(monitorId)){
            cursoFacade.getCursoBusiness().getControlCursos().buscarCurso(cursoID).getMonitoresCurso().put(monitorId,monitorFacade.getMonitorBusiness().getControladorMonitores().getListaMonitores().get(monitorId));
        }else{
            System.out.println("ERROR: El curso o el monitor no existen");
        }
    }

    public void asignarEstudiantesACurso(UUID cursoID, String estudianteId){
        if(cursoFacade.getCursoBusiness().getControlCursos().getListaCursos().containsKey(cursoID)&&estudianteFacade.getEstudianteBusiness().getControladorEstudiante().getListaEstudiantes().containsKey(estudianteId)){
            cursoFacade.getCursoBusiness().getControlCursos().buscarCurso(cursoID).getEstudiantesPertenecenCurso().put(estudianteId,estudianteFacade.getEstudianteBusiness().getControladorEstudiante().getListaEstudiantes().get(estudianteId));
        }else{
            System.out.println("ERROR: El curso o el estudiante no existen");
        }
    }

    public void asignarProfesoresACurso(UUID cursoID, String profesorId){
        if(cursoFacade.getCursoBusiness().getControlCursos().getListaCursos().containsKey(cursoID)&&profesorFacade.getProfesorBusiness().getControladorProfesor().getListaProfes().containsKey(profesorId)){
            cursoFacade.getCursoBusiness().getControlCursos().buscarCurso(cursoID).getProfesoresPertenecenCurso().put(profesorId,profesorFacade.getProfesorBusiness().getControladorProfesor().getListaProfes().get(profesorId));
        }else{
            System.out.println("ERROR: El curso o el profesor no existen");
        }
    }

    public void asignarCursosDeEstudianteAMonitor(String monitorId, UUID cursoID){
        if(cursoFacade.getCursoBusiness().getControlCursos().getListaCursos().containsKey(cursoID)&&monitorFacade.getMonitorBusiness().getControladorMonitores().getListaMonitores().containsKey(monitorId)){
            monitorFacade.getMonitorBusiness().getControladorMonitores().getListaMonitores().get(monitorId).getCursosPertenecenAEstudiante().put(cursoID,cursoFacade.getCursoBusiness().getControlCursos().buscarCurso(cursoID));
        }else{
            System.out.println("ERROR: El monitor o el curso no existen");
        }
    }

    public void asignarCursosDeMonitorAMonitor(String monitorId,UUID cursoID){
        if(cursoFacade.getCursoBusiness().getControlCursos().getListaCursos().containsKey(cursoID)&&monitorFacade.getMonitorBusiness().getControladorMonitores().getListaMonitores().containsKey(monitorId)){
            monitorFacade.getMonitorBusiness().getControladorMonitores().getListaMonitores().get(monitorId).getCursosMonitor().put(cursoID,cursoFacade.getCursoBusiness().getControlCursos().buscarCurso(cursoID));
        }else{
            System.out.println("ERROR: El monitor o el curso no existen");
        }
    }

    public void asignarCursosAEstudiante(String estudianteID,UUID cursoID){
        if(cursoFacade.getCursoBusiness().getControlCursos().getListaCursos().containsKey(cursoID)&&estudianteFacade.getEstudianteBusiness().getControladorEstudiante().getListaEstudiantes().containsKey(estudianteID)){
            estudianteFacade.getEstudianteBusiness().getControladorEstudiante().getListaEstudiantes().get(estudianteID).getCursosPertenecenAEstudiante().put(cursoID,cursoFacade.getCursoBusiness().getControlCursos().buscarCurso(cursoID));
        }else{
            System.out.println("ERROR: El estudiante o el curso no existen");
        }
    }

    public void asignarCursosAProfesores(String profesorID,UUID cursoID){
        if(cursoFacade.getCursoBusiness().getControlCursos().getListaCursos().containsKey(cursoID)&&profesorFacade.getProfesorBusiness().getControladorProfesor().getListaProfes().containsKey(profesorID)){
            profesorFacade.getProfesorBusiness().getControladorProfesor().getListaProfes().get(profesorID).getCursosPertenecenAProfesor().put(cursoID,cursoFacade.getCursoBusiness().getControlCursos().buscarCurso(cursoID));
        }else{
            System.out.println("ERROR: El profesor o el curso no existen");
        }
    }

    public void asignarEstudiante(Curso curso, Estudiante estudiante){
        Estudiante estEncontrado = estudianteFacade.getEstudianteBusiness().getControladorEstudiante().buscarEstudiante(estudiante.getUsuario());
        Curso cursoEncontrado = cursoFacade.getCursoBusiness().getControlCursos().buscarCurso(curso.getIdCurso());
        if(estEncontrado!=null && cursoEncontrado!=null){
            if(!cursoEncontrado.getEstudiantesPertenecenCurso().containsValue(estudiante)){
                cursoEncontrado.getEstudiantesPertenecenCurso().put(estEncontrado.getUsuario(), estEncontrado);
                estEncontrado.getCursosPertenecenAEstudiante().put(curso.getIdCurso(), curso);
            }
        }
        System.out.println("Fin de asignar estudient");
        //si el estudiante existe, asignar (put) al curso
    }

    public void asingarProfesor(Curso curso, Profesor profesor){
        //si el profesor existe, asignar (put) al curso
        Profesor profEncontrado = profesorFacade.getProfesorBusiness().getControladorProfesor().buscarProfesor(profesor.getUsuario());
        Curso cursoEncontrado = cursoFacade.getCursoBusiness().getControlCursos().buscarCurso(curso.getIdCurso());
        if(profEncontrado!=null && cursoEncontrado!=null){
            if(!cursoEncontrado.getEstudiantesPertenecenCurso().containsValue(profesor)){
                cursoEncontrado.getProfesoresPertenecenCurso().put(profesor.getUsuario(), profesor);
                profEncontrado.getCursosPertenecenAProfesor().put(curso.getIdCurso(), curso);
            }
        }
    }

    public void crearMonitorAPartirDeEstudiante(Estudiante estudiante){
        if(estudiante!= null){
            Monitor monitor= new Monitor(estudiante.getUsuario(),estudiante.getContrasenna(),estudiante.getNombre(),estudiante.getCorreo(), estudiante.getCarreraEstud(),estudiante.getDocumentoEstud(),  estudiante.getCursosPertenecenAEstudiante());
            for(Curso curso: estudiante.getCursosPertenecenAEstudiante().values()){
                curso.getEstudiantesPertenecenCurso().replace(estudiante.getUsuario(),estudiante,monitor);
            }
            this.usuarios.replace(estudiante.getUsuario(),estudiante,monitor);
            this.estudianteFacade.getEstudianteBusiness().getControladorEstudiante().getListaEstudiantes().replace(monitor.getUsuario(),estudiante,monitor);
            this.monitorFacade.getMonitorBusiness().getControladorMonitores().getListaMonitores().put(monitor.getUsuario(),monitor);
        }else{
            System.out.println("ERROR: El estudiante es Nulo");
        }
    }

    public void asignarMonitor(Monitor monitor, Curso curso) {
        if(monitor!=null&&curso!=null){
            if(!curso.getEstudiantesPertenecenCurso().containsKey(monitor.getUsuario())){
                curso.getMonitoresCurso().put(monitor.getUsuario(),monitor);
            }
            else{
                System.out.println("ERROR: El monitor es estudiante del curso al que se quiere asignar");
            }
        }else{
            System.out.println("ERROR: El monitor o el curso son nulos");
        }
        //si el estudiante existe, darle el poder del profesor:v y asignar (put) al curso
    }

    public void removerRolDeMonitorAUnEstudiante(Monitor monitor){
        Estudiante estudiante=new Estudiante(monitor.getUsuario(),monitor.getContrasenna(), monitor.getNombre(), monitor.getCorreo(), monitor.getCarreraEstud(), monitor.getDocumentoEstud(), monitor.getCursosPertenecenAEstudiante());
        for(Curso curso: monitor.getCursosMonitor().values()){
            curso.getMonitoresCurso().remove(monitor.getUsuario());
        }
        for(Curso curso: monitor.getCursosPertenecenAEstudiante().values()){
            curso.getEstudiantesPertenecenCurso().replace(monitor.getUsuario(),monitor,estudiante);
        }
        this.usuarios.replace(estudiante.getUsuario(),monitor,estudiante);
        this.estudianteFacade.getEstudianteBusiness().getControladorEstudiante().getListaEstudiantes().replace(monitor.getUsuario(),monitor,estudiante);
        this.monitorFacade.getMonitorBusiness().getControladorMonitores().getListaMonitores().remove(monitor.getUsuario());
    }

    public void leerArchivos(){
        ManejoArchivos.relacionar(profesorFacade.listarProfesores(),cursoFacade.listarCursos(),estudianteFacade.listaEstudiantes(),monitorFacade.getMonitorBusiness().listarMonitores(),administradorFacade.listarAdministradores());
        System.out.println("ECITO2");
    }
    public void llenarArchivos(){
        ManejoArchivos.llenarArchivos();
    }
}
