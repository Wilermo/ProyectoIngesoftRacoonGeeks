package utils.archivos;

import interfaceProgram.Global.IGlobalController;
import interfaceProgram.IAsignacion;
import model.*;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class ArchivoCursos {

    public Map<UUID, Curso> listarCurso(){

        Map<UUID, Curso> listarCurso = new HashMap<>();

        try{
            File archivo =new File("CursosInfo.txt");
            Scanner in =null;
            in = new Scanner(archivo);
            in.useDelimiter("\n");
            int contCurso = 0;

            while (in.hasNext()) {
                if(in.hasNext("<Curso>")&&in.next().equals("<Curso>")){

                    Curso cur=new Curso();
                    cur.setIdCurso(UUID.fromString(in.next()));
                    cur.setNombreCurso(in.next());
                    System.out.println(cur.getNombreCurso());
                    Map<String, Monitor>monitorMap=new HashMap<>();
                    cur.setMonitoresCurso(monitorMap);
                    if(in.next().equals("<MonitoresCurso>")){
                        ////MonitoresDelCurso.get(contCurso).put(cur.getIdCurso(),monitores);
                        while(!in.hasNext("</MonitoresCurso>")){
                            String usuario=in.next();
                            cur.getMonitoresCurso().put(usuario,null);
                            //.get(contCurso).get(cur.getIdCurso()).add(usuario);
                        }
                        in.next();
                    }

                    Map<String, Estudiante> estudianteMap=new HashMap<>();
                    cur.setEstudiantesPertenecenCurso(estudianteMap);
                    if(in.next().equals("<EstudiantesCurso>")){

                        while(!in.hasNext("</EstudiantesCurso>")){
                            String usuario=in.next();
                            cur.getEstudiantesPertenecenCurso().put(usuario,null);

                        }
                        in.next();
                    }
                    //Map<UUID,ArrayList<String>> mapProfesores=new HashMap<>();
                    //profesoresDelCurso.add(mapProfesores);
                    Map<String, Profesor> profesorMap=new HashMap<>();
                    cur.setProfesoresPertenecenCurso(profesorMap);
                    if(in.next().equals("<ProfesoresCurso>")){
                        //ArrayList<String> profesores=new ArrayList<>();
                        //profesoresDelCurso.get(contCurso).put(cur.getIdCurso(),profesores);
                        while(!in.hasNext("</ProfesoresCurso>")){
                            String usuario=in.next();
                            cur.getProfesoresPertenecenCurso().put(usuario,null);
                            //.get(contCurso).get(cur.getIdCurso()).add(usuario);
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
                                if(in.next().equals("<Respuestas>")){
                                    String delimitador="&%!>";
                                    while(!in.hasNext("</Respuestas>")){
                                        System.out.println("While2");
                                        Respuesta auxRespuesta= new Respuesta();
                                        auxRespuesta.setComentario(in.next());
                                        auxRespuesta.setUsuario(in.next());
                                        StringBuilder respuestaWrite=new StringBuilder();
                                        String respuestaAux=in.next();
                                        String[] arrayStr=respuestaAux.split(delimitador);
                                        for(int i=0; i<arrayStr.length;i++){
                                            respuestaWrite.append(arrayStr[i]);
                                            if(i!=(arrayStr.length-1)){
                                                respuestaWrite.append("\n");
                                            }
                                        }
                                        auxRespuesta.setContenidoRespuesta(respuestaWrite.toString());
                                        auxRespuesta.setNotaRespuesta(Double.valueOf(in.next()));
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
                        listarCurso.put(cur.getIdCurso(),cur);
                        //IGlobalController.controladorGeneral.getControlCursos().getListaCursos().put(cur.getIdCurso(),cur);
                    }else{
                        System.out.println("efe");
                    }
                    contCurso++;
                }
            }

            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return  listarCurso;
    }

    public static void escribirInfo(){

        FileWriter archivo = null;
//archivo general
        try{

            archivo=new FileWriter("ProgramInfo2.txt",true);

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

            archivo.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        //archivo especifico
        try{

            archivo=new FileWriter("CursosInfo.txt");

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
                                String delimitador="&%!>";
                                for(Respuesta respuesta: ((Tarea) asignacion).getListaRespuestas().values()){
                                    StringBuilder respuestaWrite=new StringBuilder();
                                    String []arrayAns=respuesta.getContenidoRespuesta().split("\n");
                                    for(int i=0;i<arrayAns.length;i++){
                                        String aux=arrayAns[i];
                                        respuestaWrite.append(aux);
                                        if(i!= (arrayAns.length-1)){
                                            respuestaWrite.append(delimitador);
                                        }
                                    }
                                    archivo.write(respuesta.getComentario());
                                    archivo.write("\n");
                                    archivo.write(respuesta.getUsuario());
                                    archivo.write("\n");
                                    archivo.write(respuestaWrite.toString());
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

            archivo.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
