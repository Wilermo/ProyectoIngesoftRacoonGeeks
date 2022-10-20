package back.helper;

import utils.AlertUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidacionesHelper {
    public static void validarCaracteresLetras(String cadena){
        boolean invalido = false;
        for (int x = 0; x < cadena.length(); x++) {
            char c = cadena.charAt(x);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
                invalido = true;
                break;
            }
        }
        if(invalido){
            AlertUtils.alertError("Error información", "Ingresar información válida", "No ingrese números ni carácteres en campos alfabéticos");
            throw new RuntimeException();
        }
    }

    public static void validadCorreo(String correo){
        boolean invalido = false;
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(correo);
        if(!mather.find()){
            invalido = true;
        }
        if(invalido){
            AlertUtils.alertError("Error información", "Ingresar información válida", "El correo ingresado no es válido");
            throw new RuntimeException();
        }
    }

    public static void validadNotaMax(Float numero){
        if(numero < 0 || numero >5.0){
            AlertUtils.alertError("Error información", "Ingresar información válida", "La nota no se encuentra en el invervalo [0,5.0]");
            throw new RuntimeException();
        }
    }


    public static void validarCedula(String numero){
        boolean invalido = false;
        for (int x = 0; x < numero.length(); x++) {
            if (!Character.isDigit(numero.charAt(x))) {
                invalido = true;
                break;
            }
        }
        if(invalido){
            AlertUtils.alertError("Error información", "Ingresar información válida", "El número de identificación ingesado no es válido");
            throw new RuntimeException();
        }
    }
}
