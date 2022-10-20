package utils.archivos;

import interfaceProgram.Global.IGlobalController;
import model.*;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class ArchivoMonitor {
    public Map<String, Monitor> listarMonitores(){

        Map<String, Monitor> listarMonitores = new HashMap<>();
        //ArrayList<Map<String,ArrayList<UUID>>> cursosDeMonitorComoEstudiante=new ArrayList<>();
        //ArrayList<Map<String,ArrayList<UUID>>> cursosDeMonitorComoMonitor=new ArrayList<>();

        try{
            File archivo = new File("MonitorInfo.txt");
            Scanner in = null;
            //ArrayList<Map<String, ArrayList<UUID>>> cursosDelEstudiante=new ArrayList<>();
            in = new Scanner(archivo);
            in.useDelimiter("\n");
            int contMonitor = 0;

            while (in.hasNext()) {

                if (in.hasNext("<Monitor>") && in.next().equals("<Monitor>")) {

                    Monitor cur = new Monitor();
                    cur.setUsuario(in.next());
                    cur.setCorreo(in.next());
                    cur.setContrasenna(in.next());
                    cur.setNombre(in.next());
                    cur.setCarreraEstud(in.next());
                    cur.setDocumentoEstud(in.nextLong());
                    Map<String, ArrayList<UUID>> mapCursosEstudiante = new HashMap<>();
                    //cursosDeMonitorComoEstudiante.add(mapCursosEstudiante);
                    Map<UUID, Curso> cursoMap = new HashMap<>();
                    cur.setCursosPertenecenAEstudiante(cursoMap);
                    if (in.next().equals("<CursosMonitorEstudiante>")) {
                        //ArrayList<UUID> cursosMonitorEstudiante = new ArrayList<>();
                        //cursosDeMonitorComoEstudiante.get(contMonitor).put(cur.getUsuario(), cursosMonitorEstudiante);
                        while (!(in.hasNext("</CursosMonitorEstudiante>"))) {
                            cur.getCursosPertenecenAEstudiante().put(UUID.fromString(in.next()),null);
                            //cursosDeMonitorComoEstudiante.get(contMonitor).get(cur.getUsuario()).add(UUID.fromString(in.next()));
                            //monitoresCursosEstudiante.put(cur.getUsuario(), UUID.fromString(in.next()));
                        }
                        in.next();
                    }
                    //Map<String, ArrayList<UUID>> mapCursosMonitor = new HashMap<>();
                    //cursosDeMonitorComoMonitor.add(mapCursosMonitor);
                    Map<UUID, Curso> cursoMap1 = new HashMap<>();
                    cur.setCursosMonitor(cursoMap1);
                    if (in.next().equals("<CursosMonitor>")) {
                        //ArrayList<UUID> cursosMonitorMonitor = new ArrayList<>();
                        //cursosDeMonitorComoMonitor.get(contMonitor).put(cur.getUsuario(), cursosMonitorMonitor);
                        while (!in.hasNext("</CursosMonitor>")) {
                            UUID curso = UUID.fromString(in.next());
                            cur.getCursosMonitor().put(curso,null);
                            //cursosDeMonitorComoMonitor.get(contMonitor).get(cur.getUsuario()).add(curso);
                            // monitoresCursosMonitor.put(cur.getUsuario(), curso);
                        }
                        in.next();
                    }
                    if (in.next().equals("</Monitor>")) {
                        System.out.println("MONITORES GUARDADOS");
                        listarMonitores.put(cur.getUsuario(),cur);
                        //IGlobalController.controladorGeneral.getControlEstu().getListaMonitores().put(cur.getUsuario(), (Monitor) cur);
                        //IGlobalController.controladorGeneral.getUsuarios().put(cur.getUsuario(), (Monitor) cur);
                    }
                    contMonitor++;
                }
            }

            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return listarMonitores;
    }

    public static void escribirInfo(){

        FileWriter archivo = null;
        //arcchivo general
        try{

            archivo = new FileWriter("ProgramInfo2.txt",true);

            for(Usuario usr: IGlobalController.controladorGeneral.getUsuarios().values()) {
                if (usr instanceof Monitor) {
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
                    if (((Monitor) usr).getCursosPertenecenAEstudiante() != null) {
                        for (Curso curso : ((Monitor) usr).getCursosPertenecenAEstudiante().values()) {
                            if (curso != null) {
                                archivo.write(curso.getIdCurso().toString());
                                archivo.write("\n");
                            }

                        }
                    }
                    archivo.write("</CursosMonitorEstudiante>");
                    archivo.write("\n");
                    archivo.write("<CursosMonitor>");
                    archivo.write("\n");
                    if (((Monitor) usr).getCursosMonitor() != null) {
                        for (Curso curso : ((Monitor) usr).getCursosMonitor().values()) {
                            if (curso != null) {
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
            }

            archivo.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        //archivo especifico
        try{

            archivo = new FileWriter("MonitorInfo.txt");

            for(Usuario usr: IGlobalController.controladorGeneral.getUsuarios().values()) {
                if (usr instanceof Monitor) {
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
                    if (((Monitor) usr).getCursosPertenecenAEstudiante() != null) {
                        for (Curso curso : ((Monitor) usr).getCursosPertenecenAEstudiante().values()) {
                            if (curso != null) {
                                archivo.write(curso.getIdCurso().toString());
                                archivo.write("\n");
                            }

                        }
                    }
                    archivo.write("</CursosMonitorEstudiante>");
                    archivo.write("\n");
                    archivo.write("<CursosMonitor>");
                    archivo.write("\n");
                    if (((Monitor) usr).getCursosMonitor() != null) {
                        for (Curso curso : ((Monitor) usr).getCursosMonitor().values()) {
                            if (curso != null) {
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
            }

            archivo.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
