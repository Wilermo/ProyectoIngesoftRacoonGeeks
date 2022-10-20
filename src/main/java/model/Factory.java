package model;

import interfaceProgram.IAsignacion;

public class Factory {

    public static IAsignacion construir(String tipo){
        switch(tipo){
            case "Tarea":
                return new Tarea();
               // break;
            case "Contenido":
                return new Contenido();
              //  break;
            default:
                System.out.println("ERROR: Tipo no válido");
                return null;
             //   break;
        }
    }

}
