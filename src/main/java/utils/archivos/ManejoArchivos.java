package utils.archivos;

import interfaceProgram.Global.IGlobalController;
import interfaceProgram.IAsignacion;
import model.*;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class ManejoArchivos {

    public static void generarArchivo() {

        FileWriter archivo=null;
        try{
            archivo=new FileWriter("ProgramInfo.txt");
            for(Curso cur: IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().getListaCursos().values()){
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

                archivo.write("<Asignaciones>");
                archivo.write("\n");
                if(cur.getAsignacionesCurso()!=null){
                    for(IAsignacion asignacion :cur.getAsignacionesCurso().values()){

                        if(asignacion instanceof Tarea){
                            archivo.write("<Tarea>");
                            archivo.write("\n");
                            archivo.write(asignacion.getTitulo());
                            archivo.write("\n");
                            archivo.write(asignacion.getInstrucciones());
                            archivo.write("\n");
                            archivo.write(String.valueOf(asignacion.getIdAsignacion()));
                            archivo.write("\n");
                            archivo.write(String.valueOf(((Tarea) asignacion).getCalificacionMAX()));
                            archivo.write("\n");
                            archivo.write(((Tarea) asignacion).getFechaStringIncio());
                            archivo.write("\n");
                            archivo.write(((Tarea) asignacion).getFechaStringCierre());
                            archivo.write("\n");
                            archivo.write("<Respuestas>");
                            archivo.write("\n");
                            if(((Tarea) asignacion).getListaRespuestas()!=null){
                                for(Respuesta respuesta: ((Tarea) asignacion).getListaRespuestas().values()){
                                    archivo.write(respuesta.getComentario());
                                    archivo.write("\n");
                                    archivo.write(respuesta.getUsuario());
                                    archivo.write("\n");
                                    archivo.write(respuesta.getContenidoRespuesta());
                                    archivo.write("\n");
                                    archivo.write(String.valueOf(respuesta.getNotaRespuesta()));
                                    archivo.write("\n");
                                }
                            }
                            archivo.write("</Respuestas>");
                            archivo.write("\n");
                            archivo.write("</Tarea>");
                            archivo.write("\n");
                        }else if(asignacion instanceof Contenido){
                            archivo.write("<Contenido>");
                            archivo.write("\n");
                            archivo.write(asignacion.getTitulo());
                            archivo.write("\n");
                            archivo.write(asignacion.getInstrucciones());
                            archivo.write("\n");
                            archivo.write(String.valueOf(asignacion.getIdAsignacion()));
                            archivo.write("\n");
                            archivo.write("</Contenido>");
                            archivo.write("\n");
                        }
                    }
                }
                archivo.write("</Asignaciones>");
                archivo.write("\n");

                archivo.write("</Curso>");
                archivo.write("\n");
            }

            for(Usuario usr: IGlobalController.controladorGeneral.getUsuarios().values()){
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
    }

    public static void llenarInfo(){
        File archivo =new File("ProgramInfo.txt");
        System.out.println("COMIENZA");
        Scanner in =null;
        ArrayList<Map<UUID, ArrayList<String>>> MonitoresDelCurso = new ArrayList<>();
        ArrayList<Map<UUID,ArrayList<String>>> EstudiantesDelCurso = new ArrayList<>();
        ArrayList<Map<UUID,ArrayList<String>>> profesoresDelCurso = new ArrayList<>();
        ArrayList<Map<String,ArrayList<UUID>>> cursosDeMonitorComoEstudiante=new ArrayList<>();
        ArrayList<Map<String,ArrayList<UUID>>> cursosDeMonitorComoMonitor=new ArrayList<>();
        ArrayList<Map<String,ArrayList<UUID>>> cursosDelEstudiante=new ArrayList<>();
        ArrayList<Map<String,ArrayList<UUID>>> cursosDelProfesor=new ArrayList<>();

        try{
            in= new Scanner(archivo);
            in.useDelimiter("\n");
            int contCurso=0,contMonitor=0, contEstudiante=0,contProfesores=0;

            while (in.hasNext()){
                //ADMINISTRADORES--------------------------
                if(in.hasNext("<Administrativo>")&&in.next().equals("<Administrativo>")){
                    Administrativo cur=new Administrativo();
                    cur.setUsuario(in.next());
                    cur.setCorreo(in.next());

                    cur.setContrasenna(in.next());
                    cur.setNombre(in.next());
                    cur.setCedulaAdmin(in.nextLong());

                    if(in.next().equals("</Administrativo>")){
                        System.out.println("ADMINISTRADORES GUARDADOS");
                        IGlobalController.controladorGeneral.getListaAdministrador().put(cur.getUsuario(),cur);
                        IGlobalController.controladorGeneral.getUsuarios().put(cur.getUsuario(),cur);
                    }
                }
                //CURSO________________________________
                if(in.hasNext("<Curso>")&&in.next().equals("<Curso>")){

                    Map<UUID,ArrayList<String>> mapMonitores=new HashMap<>();
                    MonitoresDelCurso.add(mapMonitores);
                    Curso cur=new Curso();
                    cur.setIdCurso(UUID.fromString(in.next()));
                    cur.setNombreCurso(in.next());
                    System.out.println(cur.getNombreCurso());
                    Map<String, Monitor>monitorMap=new HashMap<>();
                    cur.setMonitoresCurso(monitorMap);
                    if(in.next().equals("<MonitoresCurso>")){
                        ArrayList <String> monitores=new ArrayList<>();
                        MonitoresDelCurso.get(contCurso).put(cur.getIdCurso(),monitores);
                        while(!in.hasNext("</MonitoresCurso>")){
                            String usuario=in.next();
                            MonitoresDelCurso.get(contCurso).get(cur.getIdCurso()).add(usuario);
                        }
                        in.next();
                    }
                    Map<UUID,ArrayList<String>> mapEstudiantes=new HashMap<>();
                    EstudiantesDelCurso.add(mapEstudiantes);
                    Map<String,Estudiante> estudianteMap=new HashMap<>();
                    cur.setEstudiantesPertenecenCurso(estudianteMap);
                    if(in.next().equals("<EstudiantesCurso>")){
                        ArrayList<String > estudiantes=new ArrayList<>();
                        EstudiantesDelCurso.get(contCurso).put(cur.getIdCurso(),estudiantes);
                        while(!in.hasNext("</EstudiantesCurso>")){
                            String usuario=in.next();
                            EstudiantesDelCurso.get(contCurso).get(cur.getIdCurso()).add(usuario);
                        }
                        in.next();
                    }
                    Map<UUID,ArrayList<String>> mapProfesores=new HashMap<>();
                    profesoresDelCurso.add(mapProfesores);
                    Map<String,Profesor> profesorMap=new HashMap<>();
                    cur.setProfesoresPertenecenCurso(profesorMap);
                    if(in.next().equals("<ProfesoresCurso>")){
                        ArrayList<String> profesores=new ArrayList<>();
                        profesoresDelCurso.get(contCurso).put(cur.getIdCurso(),profesores);
                        while(!in.hasNext("</ProfesoresCurso>")){
                            String usuario=in.next();

                            profesoresDelCurso.get(contCurso).get(cur.getIdCurso()).add(usuario);

                        }
                        in.next();
                    }
                    System.out.println("Asignaciones");
                    if(in.next().equals("<Asignaciones>")){
                        System.out.println("Entra asignaciones");
                        Map<UUID, IAsignacion> asignacionMap=new HashMap<>();
                        while(!in.hasNext("</Asignaciones>")){
                            System.out.println("Entra While");
                            if(in.hasNext("<Tarea>")){
                                Tarea auxAsignacion=new Tarea();
                                in.next();
                                auxAsignacion.setTitulo(in.next());
                                System.out.println("6");
                                auxAsignacion.setInstrucciones(in.next());
                                System.out.println("5");
                                auxAsignacion.setIdAsignacion(UUID.fromString(in.next()));
                                System.out.println("4");
                                auxAsignacion.setCalificacionMAX(Float.valueOf(in.next()));
                                System.out.println("3");
                                auxAsignacion.setFechaStringIncio(in.next());
                                System.out.println("2");
                                auxAsignacion.setFechaStringCierre(in.next());
                                System.out.println("1");
                                if(in.next().equals("<Repuesta>")){
                                    while(!in.hasNext("</Respuesta>")){
                                        System.out.println("While2");
                                        Respuesta auxRespuesta= new Respuesta();
                                        auxRespuesta.setComentario(in.next());
                                        auxRespuesta.setUsuario(in.next());
                                        auxRespuesta.setContenidoRespuesta(in.next());
                                        auxRespuesta.setNotaRespuesta(in.nextDouble());
                                        auxAsignacion.getListaRespuestas().put(auxRespuesta.getUsuario(),auxRespuesta);
                                    }
                                    in.next();
                                }
                                auxAsignacion.formatFechasADate(auxAsignacion.getFechaStringIncio(),auxAsignacion.getFechaStringCierre());
                                asignacionMap.put(auxAsignacion.getIdAsignacion(),auxAsignacion);
                            }else if(in.hasNext("<Contenido>")){
                                Contenido auxAsignacion=new Contenido();
                                in.next();
                                auxAsignacion.setTitulo(in.next());
                                auxAsignacion.setInstrucciones(in.next());
                                auxAsignacion.setIdAsignacion(UUID.fromString(in.next()));
                                asignacionMap.put(auxAsignacion.getIdAsignacion(),auxAsignacion);
                            }
                            in.next();
                        }
                        in.next();
                        cur.setAsignacionesCurso(asignacionMap);
                    }
                    System.out.println("Fin tareas");

                    if(in.next().equals("</Curso>")){
                        System.out.println("CURSOS GUARDADOS");
                        IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().getListaCursos().put(cur.getIdCurso(),cur);
                    }else{
                        System.out.println("efe");
                    }
                    contCurso++;
                }

                //MONITORES------------------------------------
                if(in.hasNext("<Monitor>")&&in.next().equals("<Monitor>")) {

                    Monitor cur = new Monitor();
                    cur.setUsuario(in.next());
                    cur.setCorreo(in.next());
                    cur.setContrasenna(in.next());
                    cur.setNombre(in.next());
                    cur.setCarreraEstud(in.next());
                    cur.setDocumentoEstud(in.nextLong());
                    Map<String,ArrayList<UUID>> mapCursosEstudiante=new HashMap<>() ;
                    cursosDeMonitorComoEstudiante.add(mapCursosEstudiante);
                    Map<UUID,Curso> cursoMap=new HashMap<>();
                    cur.setCursosPertenecenAEstudiante(cursoMap);
                    if (in.next().equals("<CursosMonitorEstudiante>")){
                        ArrayList<UUID> cursosMonitorEstudiante=new ArrayList<>();
                        cursosDeMonitorComoEstudiante.get(contMonitor).put(cur.getUsuario(),cursosMonitorEstudiante);
                        while (!(in.hasNext("</CursosMonitorEstudiante>"))) {
                            cursosDeMonitorComoEstudiante.get(contMonitor).get(cur.getUsuario()).add(UUID.fromString(in.next()));
                            //monitoresCursosEstudiante.put(cur.getUsuario(), UUID.fromString(in.next()));
                        }
                        in.next();
                    }
                    Map<String,ArrayList<UUID>> mapCursosMonitor=new HashMap<>();
                    cursosDeMonitorComoMonitor.add(mapCursosMonitor);
                    Map<UUID,Curso>cursoMap1=new HashMap<>();
                    cur.setCursosMonitor(cursoMap1);
                    if (in.next().equals("<CursosMonitor>")) {
                        ArrayList<UUID> cursosMonitorMonitor=new ArrayList<>();
                        cursosDeMonitorComoMonitor.get(contMonitor).put(cur.getUsuario(),cursosMonitorMonitor);
                        while (!in.hasNext("</CursosMonitor>")) {
                            UUID curso = UUID.fromString(in.next());
                            cursosDeMonitorComoMonitor.get(contMonitor).get(cur.getUsuario()).add(curso);
                            // monitoresCursosMonitor.put(cur.getUsuario(), curso);
                        }
                        in.next();
                    }
                    if (in.next().equals("</Monitor>")) {
                        System.out.println("MONITORES GUARDADOS");
                        IGlobalController.controladorGeneral.getMonitorFacade().getMonitorBusiness().getControladorMonitores().getListaMonitores().put(cur.getUsuario(), (Monitor) cur);
                        IGlobalController.controladorGeneral.getUsuarios().put(cur.getUsuario(), (Monitor) cur);
                    }
                    contMonitor++;
                }
                //ESTUDIANTES--------------------------
                if(in.hasNext("<Estudiante>")&&in.next().equals("<Estudiante>")){

                    Map<String,ArrayList<UUID>> mapEstudiantes = new HashMap<>();
                    cursosDelEstudiante.add(mapEstudiantes);
                    Estudiante cur=new Estudiante();
                    cur.setUsuario(in.next());
                    cur.setCorreo(in.next());
                    cur.setContrasenna(in.next());
                    cur.setNombre(in.next());
                    cur.setCarreraEstud(in.next());
                    cur.setDocumentoEstud(in.nextLong());
                    Map<UUID,Curso> cursoMap=new HashMap<>();
                    cur.setCursosPertenecenAEstudiante(cursoMap);
                    if(in.next().equals("<CursosEstudiante>")){
                        ArrayList<UUID> cursos=new ArrayList<>();
                        cursosDelEstudiante.get(contEstudiante).put(cur.getUsuario(),cursos);
                        while(!in.hasNext("</CursosEstudiante>")){
                            UUID curso=UUID.fromString(in.next());
                            cursosDelEstudiante.get(contEstudiante).get(cur.getUsuario()).add(curso);
                            //estudiantesCursos.put(cur.getUsuario(),curso);
                        }
                        in.next();
                    }
                    if(in.next().equals("</Estudiante>")){
                        System.out.println("Estudiantes GUARDADOS");
                        IGlobalController.controladorGeneral.getEstudianteFacade().getEstudianteBusiness().getControladorEstudiante().getListaEstudiantes().put(cur.getUsuario(), cur);
                        IGlobalController.controladorGeneral.getUsuarios().put(cur.getUsuario(),cur);
                    }
                    contEstudiante++;
                }

                //PROFESORES--------------------------
                if(in.hasNext("<Profesor>")&&in.next().equals("<Profesor>")){
                    Map<String,ArrayList<UUID>> mapCursos = new HashMap<>();
                    cursosDelProfesor.add(mapCursos);

                    Profesor cur=new Profesor();
                    cur.setUsuario(in.next());
                    cur.setCorreo(in.next());
                    cur.setContrasenna(in.next());
                    cur.setNombre(in.next());
                    cur.setCedulaProfe(in.nextLong());

                    Map<UUID,Curso>cursoMap= new HashMap<>();
                    cur.setCursosPertenecenAProfesor(cursoMap);
                    if(in.next().equals("<CursosProfesor>")){
                        ArrayList<UUID> cursos=new ArrayList<>();
                        cursosDelProfesor.get(contProfesores).put(cur.getUsuario(),cursos);
                        while(!in.hasNext("</CursosProfesor>")){
                            UUID curso=UUID.fromString(in.next());
                            cursosDelProfesor.get(contProfesores).get(cur.getUsuario()).add(curso);
                            //profesorCursos.put(cur.getUsuario(),curso);
                        }
                        in.next();
                    }
                    if(in.next().equals("</Profesor>")){
                        System.out.println("PROFESORES GUARDADOS");
                        IGlobalController.controladorGeneral.getProfesorFacade().getProfesorBusiness().getControladorProfesor().getListaProfes().put(cur.getUsuario(),cur);
                        IGlobalController.controladorGeneral.getUsuarios().put(cur.getUsuario(),cur);
                    }
                    contProfesores++;
                }

            }

        }catch(Exception ex){
            ex.printStackTrace();
        }

        for(Map<UUID,ArrayList<String>> Map: EstudiantesDelCurso) {
            for (ArrayList<String> arr : Map.values()) {
                for (String str : arr) {
                    UUID uuid=Map.keySet().iterator().next();
                    IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().getListaCursos().get(uuid).getEstudiantesPertenecenCurso().put(str, IGlobalController.controladorGeneral.getEstudianteFacade().getEstudianteBusiness().getControladorEstudiante().getListaEstudiantes().get(str));

                    //controladorGeneral.getControlEstu().getListaEstudiantes().get(str);//.getCursosPertenecenAEstudiante();//.put(uuid,controladorGeneral.getControlCursos().getListaCursos().get(uuid));
                }
            }
        }

        for(Map<UUID,ArrayList<String>> Map: MonitoresDelCurso) {
            for (ArrayList<String> arr : Map.values()) {
                for (String str : arr) {
                    IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().getListaCursos().get(Map.keySet().iterator().next()).getMonitoresCurso().put(str, IGlobalController.controladorGeneral.getMonitorFacade().getMonitorBusiness().getControladorMonitores().getListaMonitores().get(str));
                    //controladorGeneral.getControlEstu().getListaMonitores().get(str).getCursosMonitor().put(Map.keySet().iterator().next(),controladorGeneral.getControlCursos().getListaCursos().get(Map.keySet().iterator().next()));

                }
            }
        }

        for(Map<UUID,ArrayList<String>> Map: profesoresDelCurso) {
            for (ArrayList<String> arr : Map.values()) {
                for (String str : arr) {
                    IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().getListaCursos().get(Map.keySet().iterator().next()).getProfesoresPertenecenCurso().put(str, IGlobalController.controladorGeneral.getProfesorFacade().getProfesorBusiness().getControladorProfesor().getListaProfes().get(str));
                    // controladorGeneral.getControlProfe().getListaProfes().get(str).getCursosPertenecenAProfesor().put(Map.keySet().iterator().next(),controladorGeneral.getControlCursos().getListaCursos().get(Map.keySet().iterator().next()));

                }
            }
        }

        for(Map<String,ArrayList<UUID>> Map: cursosDeMonitorComoEstudiante) {
            for (ArrayList<UUID> arr : Map.values()) {
                for (UUID str : arr) {
                    IGlobalController.controladorGeneral.getMonitorFacade().getMonitorBusiness().getControladorMonitores().getListaMonitores().get(Map.keySet().iterator().next()).getCursosPertenecenAEstudiante().put(str, IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().getListaCursos().get(str));
                    // controladorGeneral.getControlCursos().getListaCursos().get(str).getEstudiantesPertenecenCurso().put(Map.keySet().iterator().next(),controladorGeneral.getControlEstu().getListaMonitores().get(Map.keySet().iterator().next()));
                }
            }
        }

        for(Map<String,ArrayList<UUID>> Map: cursosDeMonitorComoMonitor) {
            for (ArrayList<UUID> arr : Map.values()) {
                for (UUID str : arr) {
                    IGlobalController.controladorGeneral.getMonitorFacade().getMonitorBusiness().getControladorMonitores().getListaMonitores().get(Map.keySet().iterator().next()).getCursosMonitor().put(str, IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().getListaCursos().get(str));
                    // controladorGeneral.getControlCursos().getListaCursos().get(str).getMonitoresCurso().put(Map.keySet().iterator().next(),controladorGeneral.getControlEstu().getListaMonitores().get(Map.keySet().iterator().next()));

                }
            }
        }

        for(Map<String,ArrayList<UUID>> Map: cursosDelEstudiante) {
            for (ArrayList<UUID> arr : Map.values()) {
                for (UUID str : arr) {
                    IGlobalController.controladorGeneral.getEstudianteFacade().getEstudianteBusiness().getControladorEstudiante().getListaEstudiantes().get(Map.keySet().iterator().next()).getCursosPertenecenAEstudiante().put(str, IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().getListaCursos().get(str));
                    //controladorGeneral.getControlCursos().getListaCursos().get(str).getEstudiantesPertenecenCurso().put(Map.keySet().iterator().next(),controladorGeneral.getControlEstu().getListaEstudiantes().get(Map.keySet().iterator().next()));

                }
            }
        }

        for(Map<String,ArrayList<UUID>> Map: cursosDelProfesor) {
            for (ArrayList<UUID> arr : Map.values()) {
                for (UUID str : arr) {
                    IGlobalController.controladorGeneral.getProfesorFacade().getProfesorBusiness().getControladorProfesor().getListaProfes().get(Map.keySet().iterator().next()).getCursosPertenecenAProfesor().put(str, IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().getListaCursos().get(str));
                    //controladorGeneral.getControlCursos().getListaCursos().get(str).getProfesoresPertenecenCurso().put(Map.keySet().iterator().next(),controladorGeneral.getControlProfe().getListaProfes().get(Map.keySet().iterator().next()));

                }
            }
        }
    }

    public static void relacionar(Map<String,Profesor> profesores, Map<UUID,Curso> cursos, Map<String,Estudiante> estudiantes, Map<String,Monitor> monitores, Map<String,Administrativo> administradores) {
        //llenar cursos para profesor
        for (Profesor pro : profesores.values()) {
            for (UUID idCur : pro.getCursosPertenecenAProfesor().keySet()) {
                for (UUID idGenCur : cursos.keySet()) {
                    if (idCur.equals(idGenCur)) {
                        pro.getCursosPertenecenAProfesor().replace(idCur, null, cursos.get(idGenCur));
                    }
                }
            }
        }
        //Llenar cursos para estudiante
        for (Estudiante est : estudiantes.values()) {
            if (!(est instanceof Monitor)) {
                for (UUID idcur : est.getCursosPertenecenAEstudiante().keySet()) {
                    for (UUID idGenCur : cursos.keySet()) {
                        if (idcur.equals(idGenCur)) {
                            est.getCursosPertenecenAEstudiante().replace(idcur, null, cursos.get(idGenCur));
                        }
                    }
                }
            } else {
                System.out.println("HAY MONITOR EN ESTUDIANTES");
            }

        }
        //LLenar cursos monitor
        for (Monitor monitor : monitores.values()) {
            //cursos de monitoria
            for (UUID idcur : monitor.getCursosMonitor().keySet()) {
                for (UUID idGenCur : cursos.keySet()) {
                    if (idcur.equals(idGenCur)) {
                        monitor.getCursosMonitor().replace(idcur, null, cursos.get(idGenCur));
                    }
                }
            }
            //cursos estudiate
            for (UUID idcur : monitor.getCursosPertenecenAEstudiante().keySet()) {
                for (UUID idGenCur : cursos.keySet()) {
                    if (idcur.equals(idGenCur)) {
                        monitor.getCursosPertenecenAEstudiante().replace(idcur, null, cursos.get(idGenCur));
                    }
                }
            }
        }
        //llenar cursos con usuarios
        for (Curso cur : cursos.values()) {
            //llenado profesores
            for (String userProfe : cur.getProfesoresPertenecenCurso().keySet()) {
                for (String userGenProfe : profesores.keySet()) {
                    if (userProfe.equals(userGenProfe)) {
                        cur.getProfesoresPertenecenCurso().replace(userProfe, null, profesores.get(userGenProfe));
                    }
                }
            }
            //llenado estudiantes
            for (String userEst : cur.getEstudiantesPertenecenCurso().keySet()) {
                if(estudiantes.containsKey(userEst)){
                    for (String userGenEst : estudiantes.keySet()) {
                        if (userEst.equals(userGenEst)) {
                            cur.getEstudiantesPertenecenCurso().replace(userEst, null, estudiantes.get(userGenEst));
                        }
                    }
                }else if(monitores.containsKey(userEst)){
                    for (String userGenEst : monitores.keySet()) {
                        if (userEst.equals(userGenEst)) {
                            cur.getEstudiantesPertenecenCurso().replace(userEst, null, monitores.get(userGenEst));
                        }
                    }
                }


            }
            //llenado Monitores
            for (String userMon : cur.getMonitoresCurso().keySet()) {
                for (String userGenMon : monitores.keySet()) {
                    if (userMon.equals(userGenMon)) {
                        cur.getMonitoresCurso().replace(userMon, null, monitores.get(userGenMon));
                    }
                }
            }
        }

        IGlobalController.controladorGeneral.getProfesorFacade().getProfesorBusiness().getControladorProfesor().setListaProfes(profesores);
        IGlobalController.controladorGeneral.getEstudianteFacade().getEstudianteBusiness().getControladorEstudiante().setListaEstudiantes(estudiantes);
        IGlobalController.controladorGeneral.getMonitorFacade().getMonitorBusiness().getControladorMonitores().setListaMonitores(monitores);
        IGlobalController.controladorGeneral.getEstudianteFacade().getEstudianteBusiness().getControladorEstudiante().getListaEstudiantes().putAll(monitores);
        IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().setListaCursos(cursos);
        IGlobalController.controladorGeneral.setListaAdministrador(administradores);

        IGlobalController.controladorGeneral.getUsuarios().putAll(profesores);
        IGlobalController.controladorGeneral.getUsuarios().putAll(estudiantes);
        IGlobalController.controladorGeneral.getUsuarios().putAll(administradores);
        IGlobalController.controladorGeneral.getUsuarios().putAll(monitores);

        //***************
        for(Curso cur: IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().getListaCursos().values()){
            System.out.println("NOMBRE CUR: "+cur.getNombreCurso());
            for(IAsignacion asignacion:cur.getAsignacionesCurso().values()) {
                if (asignacion instanceof Tarea) {
                    System.out.println("Tarea: "+asignacion.getTitulo());
                    for (Respuesta ans : asignacion.getListaRespuestas().values()) {
                        System.out.println("Respuesta " + ans.getContenidoRespuesta());
                    }
                }
            }
        }
        //****************
    }

    public static void llenarArchivos(){
        IGlobalController.controladorGeneral.getAdministradorFacade().guardarAdministradores();
        IGlobalController.controladorGeneral.getProfesorFacade().guardarProfes();
        IGlobalController.controladorGeneral.getMonitorFacade().guardarMonitores();
        IGlobalController.controladorGeneral.getEstudianteFacade().guardarEstudiantes();
        IGlobalController.controladorGeneral.getCursoFacade().guardarCursos();
    }

    public static void llenarCursos(){
        IGlobalController.controladorGeneral.getCursoFacade().guardarCursos();
    }

}
