package utils.archivos;

import interfaceProgram.Global.IGlobalController;
import model.Curso;
import model.Profesor;
import model.Usuario;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class ArchivoProfesor {

    public Map<String, Profesor> listarProfesores(){

        Map<String,Profesor> listarProfesores = new HashMap<>();

        try {
            File archivo =new File("ProfesorInfo.txt");

            Scanner in =null;
            //ArrayList<Map<String, ArrayList<UUID>>> cursosDelProfesor=new ArrayList<>();
            in = new Scanner(archivo);
            in.useDelimiter("\n");
            int contProfesores = 0;

            while (in.hasNext()) {
                if (in.hasNext("<Profesor>")&& in.next().equals("<Profesor>")) {

                    System.out.println("abre profe");
                    //Map<String, ArrayList<UUID>> mapCursos = new HashMap<>();
                   // cursosDelProfesor.add(mapCursos);

                    Profesor cur = new Profesor();
                    cur.setUsuario(in.next());
                    cur.setCorreo(in.next());
                    cur.setContrasenna(in.next());
                    cur.setNombre(in.next());
                    cur.setCedulaProfe(in.nextLong());

                    Map<UUID, Curso> cursoMap = new HashMap<>();
                    cur.setCursosPertenecenAProfesor(cursoMap);
                    if (in.next().equals("<CursosProfesor>")) {
                        ArrayList<UUID> cursos = new ArrayList<>();
                        //cursosDelProfesor.get(contProfesores).put(cur.getUsuario(), cursos);
                        while (!in.hasNext("</CursosProfesor>")) {
                            UUID curso = UUID.fromString(in.next());
                            cur.getCursosPertenecenAProfesor().put(curso,null);
                           // cursosDelProfesor.get(contProfesores).get(cur.getUsuario()).add(curso);
                            //profesorCursos.put(cur.getUsuario(),curso);
                        }
                        in.next();
                    }
                    if (in.next().equals("</Profesor>")) {
                        System.out.println("PROFESORES GUARDADOS");
                        listarProfesores.put(cur.getUsuario(),cur);
                    }
                    contProfesores++;
                }
            }

            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return listarProfesores;
    }

    public static void escribirInfo(){

        FileWriter archivo = null;
        //archivo general
        try{

            archivo = new FileWriter("ProgramInfo2.txt",true);

            for (Usuario usr:IGlobalController.controladorGeneral.getUsuarios().values()){
                if(usr instanceof Profesor){
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
                }
            }

            archivo.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        //archivo especifico
        try{

            archivo = new FileWriter("ProfesorInfo.txt");

            for (Usuario usr:IGlobalController.controladorGeneral.getUsuarios().values()){
                if(usr instanceof Profesor){
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
                }
            }

            archivo.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
