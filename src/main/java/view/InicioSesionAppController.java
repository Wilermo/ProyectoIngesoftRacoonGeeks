package view;

import back.business.ProfesorBusiness;
import back.facade.ProfesorFacade;
import interfaceProgram.Global.IGlobalController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.Profesor;
import model.Usuario;
import utils.AlertUtils;
import utils.archivos.ArchivoProfesor;
import utils.archivos.ManejoArchivos;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.Map;


import java.io.*;

public class InicioSesionAppController {

    @FXML
    private PasswordField fieldContraseña;

    @FXML
    private TextField fieldUsuario;

    @FXML
    private Button ButtonIniciarSesion;

    private Stage stage;

    @FXML
    private Button ButtonGenerarArchivo;

    @FXML
    public void iniciarSesion(ActionEvent event) throws IOException{

        String usuario = fieldUsuario.getText();
        String contrasenna = fieldContraseña.getText();
        System.out.println("Preexito");
        IGlobalController.controladorGeneral.leerArchivos();
        System.out.println("EXITO");
        try {
            int tipoUsuario = IGlobalController.controladorGeneral.comprobarTipoUsuario(usuario, contrasenna);
            switch (tipoUsuario) {
                case 0:
                    mostrarPantallaMonitor(event);
                    break;
                case 1:
                    mostrarPantallaEstudiante(event);
                    break;
                case 2:
                    mostrarPantallaProfesor(event);
                    break;
                case 3:
                    mostrarPantallaAdministrativo(event);
                    break;
                case 4:
                    AlertUtils.alertError("Error al Iniciar Sesión", "Iniciar Sesión", "El nombre de usuario o contraseña son incorrectos, revíselos y vuelva a intentarlo");
                    break;
            }
        }catch (Exception ex){
            ex.printStackTrace();
            AlertUtils.alertError("Error al Iniciar Sesión", "Iniciar Sesión", "Error al iniciar sesión, revise sus credenciales e inténtelo nuevamente");
        }
    }

    public void mostrarPantallaMonitor(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaPrincipalEstudiante.fxml"));
        Parent root = loader.load();
        PantallaPrincipalEstudianteController pntEstudianteController = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e-> {
            try {
                pntEstudianteController.cerrarSesionEst(event);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Stage myStage = (Stage) this.ButtonIniciarSesion.getScene().getWindow();
        myStage.close();
    }

    public void mostrarPantallaEstudiante(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaEstudianteRegular.fxml"));
        Parent root = loader.load();
        PantallaEstudianteRegularController pntEstudianteController = loader.getController();
        pntEstudianteController.setEsMonitor(false);
        pntEstudianteController.renderInicial();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e-> {
            try {
                pntEstudianteController.cerrarSeionEstu(event);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Stage myStage = (Stage) this.ButtonIniciarSesion.getScene().getWindow();
        myStage.close();
    }

    public void mostrarPantallaProfesor(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaPrincipalProfesor.fxml"));
        Parent root = loader.load();
        PantallaPrincipalProfesorController pantallaPrincipalProfeController = loader.getController();
        pantallaPrincipalProfeController.renderInicial();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e-> {
            try {
                pantallaPrincipalProfeController.cerrarSeionProfe(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        Stage myStage = (Stage) this.ButtonIniciarSesion.getScene().getWindow();
        myStage.close();
    }

    public void mostrarPantallaAdministrativo(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaPrincipalAdmin.fxml"));
        Parent root = loader.load();
        PantallaPrincipalAdminController pantallaPrincipalAdminController = loader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e-> {
            try {
                pantallaPrincipalAdminController.cerrarSesionAdmin(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        Stage myStage = (Stage) this.ButtonIniciarSesion.getScene().getWindow();
        myStage.close();
    }
}


