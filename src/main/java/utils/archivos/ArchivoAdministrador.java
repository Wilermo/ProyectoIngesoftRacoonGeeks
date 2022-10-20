package utils.archivos;

import back.facade.AdministradorFacade;
import interfaceProgram.Global.IGlobalController;
import model.Administrativo;
import model.Profesor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class ArchivoAdministrador {

    public Map<String, Administrativo> listarAdministrador(){

        Map<String, Administrativo> listarAdministrador = new HashMap<>();

        try{

            File archivo =new File("AdminInfo.txt");
            Scanner in =new Scanner(archivo);
            in.useDelimiter("\n");

            while (in.hasNext()) {

                if (in.hasNext("<Administrativo>") && in.next().equals("<Administrativo>")) {
                    Administrativo cur = new Administrativo();
                    cur.setUsuario(in.next());
                    cur.setCorreo(in.next());

                    cur.setContrasenna(in.next());
                    cur.setNombre(in.next());
                    System.out.println(cur.getNombre());
                    cur.setCedulaAdmin(in.nextLong());

                    if (in.next().equals("</Administrativo>")) {
                        System.out.println("ADMINISTRADORES GUARDADOS");
                        listarAdministrador.put(cur.getUsuario(),cur);
                        //IGlobalController.controladorGeneral.getListaAdministrador().put(cur.getUsuario(), cur);
                        //IGlobalController.controladorGeneral.getUsuarios().put(cur.getUsuario(), cur);
                    }
                }
            }

        }catch(Exception exception){
            exception.printStackTrace();
        }

        return listarAdministrador;
    }

    public void escribirInfo(){

        FileWriter archivo = null;
        //aRchivo general
        try{
            archivo = new FileWriter("ProgramInfo2.txt");

            for(Administrativo ad : IGlobalController.controladorGeneral.getListaAdministrador().values()){
                archivo.write("<Administrativo>");
                archivo.write("\n");
                archivo.write(ad.getUsuario());
                archivo.write("\n");
                archivo.write(ad.getCorreo());
                archivo.write("\n");
                archivo.write(ad.getContrasenna());
                archivo.write("\n");
                archivo.write(ad.getNombre());
                archivo.write("\n");
                archivo.write(String.valueOf(((Administrativo) ad).getCedulaAdmin()));
                archivo.write("\n");

                archivo.write("</Administrativo>");
                archivo.write("\n");
            }

            archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //archivo especifico
        try{
            archivo = new FileWriter("AdminInfo.txt");

            for(Administrativo ad : IGlobalController.controladorGeneral.getListaAdministrador().values()){
                archivo.write("<Administrativo>");
                archivo.write("\n");
                archivo.write(ad.getUsuario());
                archivo.write("\n");
                archivo.write(ad.getCorreo());
                archivo.write("\n");
                archivo.write(ad.getContrasenna());
                archivo.write("\n");
                archivo.write(ad.getNombre());
                archivo.write("\n");
                archivo.write(String.valueOf(((Administrativo) ad).getCedulaAdmin()));
                archivo.write("\n");

                archivo.write("</Administrativo>");
                archivo.write("\n");
            }

            archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
