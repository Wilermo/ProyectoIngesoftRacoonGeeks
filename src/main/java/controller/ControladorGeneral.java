package controller;

import model.*;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ControladorGeneral {

    private Map<String, Usuario> usuarios = new HashMap<>();
    ControladorProfesor controlProfe = new ControladorProfesor();
    private Map<String, Administrativo> listaAdministrador = new HashMap<>();
    ControladorEstudiante controlEstu = new ControladorEstudiante();
    ControlCursos controlCursos = new ControlCursos();
    public ControlCursos getControlCursos() {
        return controlCursos;
    }
    public Map<String, Administrativo> getListaAdministrador() {
        return listaAdministrador;
    }
    public void setControlCursos(ControlCursos controlCursos) {
        this.controlCursos = controlCursos;
    }
    public ControladorProfesor getControlProfe() {
        return controlProfe;
    }
    public ControladorEstudiante getControlEstu() {
        return controlEstu;
    }
    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }

    public int comprobarTipoUsuario(String usuario, String contrasenna) {
        //Segun el tipo de retorno, se abre una pestaña u otra
        Usuario UsuarioEncontrado = this.buscarUsuario(usuario);
        if (UsuarioEncontrado.getUsuario().equals(usuario) && UsuarioEncontrado.getContrasenna().equals(contrasenna)) {
            if (UsuarioEncontrado instanceof Estudiante) {
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
        if (controlEstu.getListaEstudiantes().containsKey(usuario)) {
            return controlEstu.getListaEstudiantes().get(usuario);
        } else if (this.listaAdministrador.containsKey(usuario)) {
            return this.listaAdministrador.get(usuario);
        } else if (controlProfe.getListaProfes().containsKey(usuario)) {
            return controlProfe.getListaProfes().get(usuario);
        }
        return null;
    }

    public void guardarUsuario(Usuario user) {
        this.usuarios.put(user.getUsuario(), user);
        if (user instanceof Estudiante) {
            this.controlEstu.getListaEstudiantes().put(user.getUsuario(), (Estudiante) user);
        } else if (user instanceof Administrativo) {
            this.listaAdministrador.put(user.getUsuario(), (Administrativo) user);
        } else if (user instanceof Profesor) {
            this.controlProfe.getListaProfes().put(user.getUsuario(), (Profesor) user);
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

    public void consultarAdministradores(){
        if(!this.listaAdministrador.isEmpty()) {
            for (Administrativo admin : this.listaAdministrador.values()) {
                System.out.println(admin.toString());
            }
            return;
        }
        System.out.println("Actualmente no existen administradores, por favor registre al menos uno");
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
            this.listaAdministrador.remove(admin);
            this.usuarios.remove(admin);
            System.out.println("El administrador ha sido eliminado con exito! ");
        }else{
            System.out.println("El administrador no existe, intente nuevamente");
        }
    }
/*
    Map<UUID,String> cursosMonitores=new HashMap<>();
        Map<UUID,String> cursosEstudiantes=new HashMap<>();
        Map<UUID,String> cursosProfesores= new HashMap<>();
        Map<String,UUID> monitoresCursosEstudiante = new HashMap<>();
        Map<String,UUID> monitoresCursosMonitor = new HashMap<>();
        Map<String,UUID> estudiantesCursos = new HashMap<>();
        Map<String,UUID> profesorCursos = new HashMap<>();*/

    public void asignarMonitoresACurso(UUID cursoID, String monitorId){
        if(controlCursos.getListaCursos().containsKey(cursoID)&&controlEstu.getListaMonitores().containsKey(monitorId)){
            controlCursos.buscarCurso(cursoID).getMonitoresCurso().put(monitorId,controlEstu.getListaMonitores().get(monitorId));
        }else{
            System.out.println("ERROR: El curso o el monitor no existen");
        }
    }

    public void asignarEstudiantesACurso(UUID cursoID, String estudianteId){
        if(controlCursos.getListaCursos().containsKey(cursoID)&&controlEstu.getListaEstudiantes().containsKey(estudianteId)){
            controlCursos.buscarCurso(cursoID).getEstudiantesPertenecenCurso().put(estudianteId,controlEstu.getListaEstudiantes().get(estudianteId));
        }else{
            System.out.println("ERROR: El curso o el estudiante no existen");
        }
    }

    public void asignarProfesoresACurso(UUID cursoID, String profesorId){
        if(controlCursos.getListaCursos().containsKey(cursoID)&&controlProfe.getListaProfes().containsKey(profesorId)){
            controlCursos.buscarCurso(cursoID).getProfesoresPertenecenCurso().put(profesorId,controlProfe.getListaProfes().get(profesorId));
        }else{
            System.out.println("ERROR: El curso o el profesor no existen");
        }
    }

    public void asignarCursosDeEstudianteAMonitor(String monitorId, UUID cursoID){
        if(controlCursos.getListaCursos().containsKey(cursoID)&&controlEstu.getListaMonitores().containsKey(monitorId)){
            controlEstu.getListaMonitores().get(monitorId).getCursosPertenecenAEstudiante().put(cursoID,controlCursos.buscarCurso(cursoID));
        }else{
            System.out.println("ERROR: El monitor o el curso no existen");
        }
    }

    public void asignarCursosDeMonitorAMonitor(String monitorId,UUID cursoID){
        if(controlCursos.getListaCursos().containsKey(cursoID)&&controlEstu.getListaMonitores().containsKey(monitorId)){
            controlEstu.getListaMonitores().get(monitorId).getCursosMonitor().put(cursoID,controlCursos.buscarCurso(cursoID));
        }else{
            System.out.println("ERROR: El monitor o el curso no existen");
        }
    }

    public void asignarCursosAEstudiante(String estudianteID,UUID cursoID){
        if(controlCursos.getListaCursos().containsKey(cursoID)&&controlEstu.getListaEstudiantes().containsKey(estudianteID)){
            controlEstu.getListaEstudiantes().get(estudianteID).getCursosPertenecenAEstudiante().put(cursoID,controlCursos.buscarCurso(cursoID));
        }else{
            System.out.println("ERROR: El estudiante o el curso no existen");
        }
    }

    public void asignarCursosAProfesores(String profesorID,UUID cursoID){
        if(controlCursos.getListaCursos().containsKey(cursoID)&&controlProfe.getListaProfes().containsKey(profesorID)){
            controlProfe.getListaProfes().get(profesorID).getCursosPertenecenAProfesor().put(cursoID,controlCursos.buscarCurso(cursoID));
        }else{
            System.out.println("ERROR: El profesor o el curso no existen");
        }
    }

    public void asignarEstudiante(Curso curso, Estudiante estudiante){
        Estudiante estEncontrado = controlEstu.buscarEstudiante(estudiante.getUsuario());
        Curso cursoEncontrado = controlCursos.buscarCurso(curso.getIdCurso());
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
        Profesor profEncontrado = controlProfe.buscarProfesor(profesor.getUsuario());
        Curso cursoEncontrado = controlCursos.buscarCurso(curso.getIdCurso());
        if(profEncontrado!=null && cursoEncontrado!=null){
            if(!cursoEncontrado.getEstudiantesPertenecenCurso().containsValue(profesor)){
                cursoEncontrado.getProfesoresPertenecenCurso().put(profesor.getUsuario(), profesor);
                profEncontrado.getCursosPertenecenAProfesor().put(curso.getIdCurso(), curso);
            }
        }
    }

    public void crearMonitorAPartirDeEstudiante(Estudiante estudiante){
        if(estudiante!= null){
            Monitor monitor= new Monitor(estudiante.getUsuario(),estudiante.getContrasenna(),estudiante.getNombre(),estudiante.getCorreo(), estudiante.getCarreraEstud(),estudiante.getDocumentoEstud(),TipoGeneral.ESTUDIANTE, estudiante.getCursosPertenecenAEstudiante());
            for(Curso curso: estudiante.getCursosPertenecenAEstudiante().values()){
                curso.getEstudiantesPertenecenCurso().replace(estudiante.getUsuario(),estudiante,monitor);
            }
            this.usuarios.replace(estudiante.getUsuario(),estudiante,monitor);
            this.controlEstu.getListaMonitores().put(monitor.getUsuario(),monitor);
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
        Estudiante estudiante=new Estudiante(monitor.getUsuario(),monitor.getContrasenna(), monitor.getNombre(), monitor.getCorreo(), monitor.getCarreraEstud(), monitor.getDocumentoEstud(),TipoGeneral.ESTUDIANTE, monitor.getCursosPertenecenAEstudiante());
        for(Curso curso: monitor.getCursosMonitor().values()){
            curso.getMonitoresCurso().remove(monitor);
        }
        for(Curso curso: monitor.getCursosPertenecenAEstudiante().values()){
            curso.getEstudiantesPertenecenCurso().replace(monitor.getUsuario(),monitor,estudiante);
        }
        this.usuarios.replace(estudiante.getUsuario(),monitor,estudiante);
        this.controlEstu.getListaMonitores().remove(monitor);
    }

    public void generarArchivo() {

        FileWriter archivo=null;
        try{
            archivo=new FileWriter("ProgramInfo.txt");
            for(Curso cur: global.controladorGeneral.getControlCursos().getListaCursos().values()){
                archivo.write("<Curso>");
                archivo.write("\n");
                archivo.write(cur.getIdCurso().toString());
                archivo.write("\n");
                archivo.write(cur.getNombreCurso());
                archivo.write("\n");
                archivo.write("<MonitoresCurso>");
                archivo.write("\n");
                if(cur.getMonitoresCurso()!=null){
                    for (Monitor monitor: cur.getMonitoresCurso().values()) {
                        if(monitor!=null) {
                            archivo.write(monitor.getUsuario());
                            archivo.write("\n");
                        }
                    }
                }
                archivo.write("</MonitoresCurso>");
                archivo.write("\n");
                archivo.write("<EstudiantesCurso>");
                archivo.write("\n");

                if(cur.getEstudiantesPertenecenCurso()!=null) {
                    for (Estudiante est : cur.getEstudiantesPertenecenCurso().values()) {
                        if(est!=null){
                            archivo.write(est.getUsuario());
                            archivo.write("\n");
                        }
                    }
                }
                archivo.write("</EstudiantesCurso>");
                archivo.write("\n");
                archivo.write("<ProfesoresCurso>");
                archivo.write("\n");
                if(cur.getProfesoresPertenecenCurso()!=null){
                    for(Profesor profesor: cur.getProfesoresPertenecenCurso().values()){
                        archivo.write(profesor.getUsuario());
                        archivo.write("\n");
                    }
                }
                archivo.write("</ProfesoresCurso>");
                archivo.write("\n");
                archivo.write("</Curso>");
                archivo.write("\n");
            }

            for(Usuario usr: global.controladorGeneral.getUsuarios().values()){
                if(usr instanceof Monitor){
                    archivo.write("<Monitor>");
                    archivo.write("\n");
                    archivo.write(usr.getUsuario());
                    archivo.write("\n");
                    archivo.write(usr.getCorreo());
                    archivo.write("\n");
                    archivo.write(usr.getContrasenna());
                    archivo.write("\n");
                    archivo.write(usr.getNombre());
                    archivo.write("\n");
                    archivo.write(((Monitor) usr).getCarreraEstud());
                    archivo.write("\n");
                    archivo.write(((Monitor) usr).getDocumentoEstud().toString());
                    archivo.write("\n");
                    archivo.write("<CursosMonitorEstudiante>");

                    archivo.write("\n");
                    if(((Monitor) usr).getCursosPertenecenAEstudiante()!=null){
                        for(Curso curso: ((Monitor) usr).getCursosPertenecenAEstudiante().values()){
                            if(curso!=null){
                                archivo.write(curso.getIdCurso().toString());
                                archivo.write("\n");
                            }

                        }
                    }
                    archivo.write("</CursosMonitorEstudiante>");
                    archivo.write("\n");
                    archivo.write("<CursosMonitor>");
                    archivo.write("\n");
                    if(((Monitor)usr).getCursosMonitor()!=null){
                        for(Curso curso: ((Monitor) usr).getCursosMonitor().values()){
                            if(curso!=null){
                                archivo.write(curso.getIdCurso().toString());
                                archivo.write("\n");
                            }
                        }
                    }
                    archivo.write("</CursosMonitor>");
                    archivo.write("\n");
                    archivo.write("</Monitor>");
                    archivo.write("\n");
                }
                else if(usr instanceof Estudiante){
                    archivo.write("<Estudiante>");
                    archivo.write("\n");
                    archivo.write(usr.getUsuario());
                    archivo.write("\n");
                    archivo.write(usr.getCorreo());
                    archivo.write("\n");
                    archivo.write(usr.getContrasenna());
                    archivo.write("\n");
                    archivo.write(usr.getNombre());
                    archivo.write("\n");
                    archivo.write(((Estudiante) usr).getCarreraEstud());
                    archivo.write("\n");
                    archivo.write(((Estudiante) usr).getDocumentoEstud().toString());
                    archivo.write("\n");
                    archivo.write("<CursosEstudiante>");

                    archivo.write("\n");
                    if(((Estudiante) usr).getCursosPertenecenAEstudiante()!=null){
                        for(Curso curso: ((Estudiante) usr).getCursosPertenecenAEstudiante().values()){
                            if(curso!=null){
                                archivo.write(curso.getIdCurso().toString());
                                archivo.write("\n");
                            }

                        }
                    }
                    archivo.write("</CursosEstudiante>");
                    archivo.write("\n");
                    archivo.write("</Estudiante>");
                    archivo.write("\n");


                }else if(usr instanceof Profesor){
                    archivo.write("<Profesor>");
                    archivo.write("\n");
                    archivo.write(usr.getUsuario());
                    archivo.write("\n");
                    archivo.write(usr.getCorreo());
                    archivo.write("\n");
                    archivo.write(usr.getContrasenna());
                    archivo.write("\n");
                    archivo.write(usr.getNombre());
                    archivo.write("\n");
                    archivo.write(String.valueOf(((Profesor) usr).getCedulaProfe()));
                    archivo.write("\n");

                    archivo.write("<CursosProfesor>");
                    archivo.write("\n");
                    if(((Profesor) usr).getCursosPertenecenAProfesor()!=null){
                        for(Curso curso: ((Profesor) usr).getCursosPertenecenAProfesor().values()) {
                            if(curso!=null){
                                archivo.write(curso.getIdCurso().toString());
                                archivo.write("\n");
                            }

                        }
                    }

                    archivo.write("</CursosProfesor>");
                    archivo.write("\n");

                    archivo.write("</Profesor>");
                    archivo.write("\n");
                }else if(usr instanceof Administrativo){
                    archivo.write("<Administrativo>");
                    archivo.write("\n");
                    archivo.write(usr.getUsuario());
                    archivo.write("\n");
                    archivo.write(usr.getCorreo());
                    archivo.write("\n");
                    archivo.write(usr.getContrasenna());
                    archivo.write("\n");
                    archivo.write(usr.getNombre());
                    archivo.write("\n");
                    archivo.write(String.valueOf(((Administrativo) usr).getCedulaAdmin()));
                    archivo.write("\n");

                    archivo.write("</Administrativo>");
                    archivo.write("\n");
                }
            }
            archivo.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }



        /*controladorGeneral.getReportesUsuarios().setUsuarios(controladorGeneral.getUsuarios());
        controladorGeneral.getReportesEstudiantes().setUsuarios(controladorGeneral.getControlEstu().getListaEstudiantes());
        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        File ruta = AlertUtils.openFileChooserModeWrite(filtro, ((Button) event.getSource()).getScene().getWindow());
        try {
            if (controladorGeneral.getUsuarios().size() == 0){
                AlertUtils.alertError("Vacio","Archivo vacio","El archivo esta vacio");
            }
            FileUtils.saveXML(ruta, controladorGeneral.getReportesUsuarios());
            //FileUtils.saveXML(ruta,controladorGeneral.getReportesEstudiantes());
            AlertUtils.alertConfirmation("Generar reporte", "El reporte de los usuarios se ha generado exitosamente", "Presiona OK para continuar");
        } catch (IOException ioe) {
            ioe.printStackTrace();
            AlertUtils.alertError("Error", "No se pueden obtener los usuarios", "Revise los datos que ingresó e inténtelo de nuevo");
        } catch (JAXBException jex) {
            jex.printStackTrace();
            AlertUtils.alertError("Error", "No se pueden obtener los usuarios", "Revise los datos que ingresó e inténtelo de nuevo");
        }

        /*OutputStream out;
        try {
            out = new FileOutputStream("C:\\Users\\willi\\Documents\\ProyectoIngesoft\\Proyecto\\RaccoonGeeks\\Prueba.xml");
            java.beans.XMLEncoder encoder = new XMLEncoder(out);
            for(Usuario usr: controladorGeneral.getControlEstu().getListaEstudiantes().values()){
                encoder.writeObject(usr);
            }
            encoder.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
