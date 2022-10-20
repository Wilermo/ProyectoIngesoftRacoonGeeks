package view;

import back.helper.ValidacionesHelper;
import interfaceProgram.Global.IGlobalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Profesor;
import model.Usuario;
import utils.AlertUtils;

import java.io.IOException;

public class PantallaAdminRegistrarProfesorController {

    ValidacionesHelper validacionesHelper = new ValidacionesHelper();
    @FXML
    private TextField usuarioProfe;

    @FXML
    private TextField nombresProfe;

    @FXML
    private TextField correoProfe;

    @FXML
    private TextField numIDProfe;

    @FXML
    private TextField contrasenaProfe;

    @FXML
    private Button btnConfirmaRegistroProfe;

    @FXML
    private Button btnCancelarRegistroProfe;

    @FXML
    void registrarProfe(ActionEvent event) throws IOException {
        if(realizarValidacionesRegistro()) {
            Usuario profeNuevo = new Profesor(usuarioProfe.getText(), contrasenaProfe.getText(), nombresProfe.getText(), correoProfe.getText(), Long.valueOf(numIDProfe.getText()));
            IGlobalController.controladorGeneral.guardarUsuario(profeNuevo);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaAdminGestionProfesor.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            Stage myStage = (Stage) this.btnConfirmaRegistroProfe.getScene().getWindow();
            myStage.close();
        }
    }

    public boolean realizarValidacionesRegistro(){
        boolean rta = true;
        if (!usuarioProfe.getText().isEmpty() && !nombresProfe.getText().isEmpty() && !correoProfe.getText().isEmpty() && !numIDProfe.getText().isEmpty() && !contrasenaProfe.getText().isEmpty()) {
            try {
                ValidacionesHelper.validarCaracteresLetras(nombresProfe.getText());
                ValidacionesHelper.validadCorreo(correoProfe.getText());
                ValidacionesHelper.validarCedula(numIDProfe.getText());
            } catch (RuntimeException e) {
               rta = false;
            }
        }
        return rta;
    }

    @FXML
    void cancelarRegistroProfe(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaAdminGestionProfesor.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage myStage = (Stage) this.btnCancelarRegistroProfe.getScene().getWindow();
        myStage.close();
    }

}
