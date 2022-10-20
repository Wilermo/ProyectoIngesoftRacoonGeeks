package utils.archivos;

import interfaceProgram.Global.IGlobalController;
import model.*;

import java.io.File;
import java.io.FileWriter;
import java.security.spec.ECParameterSpec;
import java.util.*;

public class ArchivoEstudiante {

    public Map<String, Estudiante> listaEstudiantes() {

        Map<String, Estudiante> listaEstudiantes = new HashMap<>();

        try {
            File archivo = new File("EstudianteInfo.txt");
            Scanner in = null;
            //ArrayList<Map<String,ArrayList<UUID>>> cursosDelEstudiante=new ArrayList<>();
            in = new Scanner(archivo);
            in.useDelimiter("\n");
            int contEstudiante = 0;

            while (in.hasNext()) {

                if (in.hasNext("<Estudiante>") && in.next().equals("<Estudiante>")) {

                    //Map<String, ArrayList<UUID>> mapEstudiantes = new HashMap<>();
                    //cursosDelEstudiante.add(mapEstudiantes);
                    Estudiante cur = new Estudiante();
                    cur.setUsuario(in.next());
                    cur.setCorreo(in.next());
                    cur.setContrasenna(in.next());
                    cur.setNombre(in.next());
                    cur.setCarreraEstud(in.next());
                    cur.setDocumentoEstud(in.nextLong());
                    Map<UUID, Curso> cursoMap = new HashMap<>();
                    cur.setCursosPertenecenAEstudiante(cursoMap);
                    if (in.next().equals("<CursosEstudiante>")) {
                        ArrayList<UUID> cursos = new ArrayList<>();
                        //cursosDelEstudiante.get(contEstudiante).put(cur.getUsuario(), cursos);
                        while (!in.hasNext("</CursosEstudiante>")) {
                            UUID curso = UUID.fromString(in.next());
                            cur.getCursosPertenecenAEstudiante().put(curso,null);
                            //cursosDelEstudiante.get(contEstudiante).get(cur.getUsuario()).add(curso);
                        }
                        in.next();
                    }
                    if (in.next().equals("</Estudiante>")) {
                        System.out.println("Estudiantes GUARDADOS");
                        listaEstudiantes.put(cur.getUsuario(),cur);
                        //IGlobalController.controladorGeneral.getControlEstu().getListaEstudiantes().put(cur.getUsuario(), cur);
                        //IGlobalController.controladorGeneral.getUsuarios().put(cur.getUsuario(), cur);
                    }
                    contEstudiante++;
                }
            }

            in.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return listaEstudiantes;
    }

    public static void escribirInfo(){

        FileWriter archivo = null;
        //archivo general
        try {

            archivo = new FileWriter("ProgramInfo2.txt",true);

            for (Usuario usr : IGlobalController.controladorGeneral.getUsuarios().values()) {
                if (usr instanceof Estudiante && !(usr instanceof Monitor)) {
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

                }

            }

            archivo.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        //archivo especificp
        try {

            archivo = new FileWriter("EstudianteInfo.txt");

            for (Usuario usr : IGlobalController.controladorGeneral.getUsuarios().values()) {
                if (usr instanceof Estudiante && !(usr instanceof Monitor)) {
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

                }

            }

            archivo.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

