package view;

import back.helper.ValidacionesHelper;
import interfaceProgram.Global.IGlobalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Estudiante;
import model.Profesor;
import model.Usuario;

import java.io.IOException;

public class PantallaAdminRegistrarEstudianteController {

    @FXML
    private TextField usuarioEst;

    @FXML
    private TextField nombresEst;

    @FXML
    private TextField correoEst;

    @FXML
    private TextField numIDEst;

    @FXML
    private TextField contrasenaEst;

    @FXML
    private TextField carreraEst;

    @FXML
    private ComboBox<?> tipoEst;

    @FXML
    private Button btnConfirmaRegistroEst;

    @FXML
    private Button btnCancelarRegistroEst;

    @FXML
    void cancelarRegistroEst(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaAdminGestionEstudiante.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage myStage = (Stage) this.btnCancelarRegistroEst.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void registrarEst(ActionEvent event) throws IOException {
        if(realizarValidacionesRegistro()){
            Usuario estudianteNuevo = new Estudiante(usuarioEst.getText(), contrasenaEst.getText(), nombresEst.getText(), correoEst.getText(),carreraEst.getText(), Long.valueOf(numIDEst.getText()));
            IGlobalController.controladorGeneral.guardarUsuario(estudianteNuevo);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaAdminGestionEstudiante.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            Stage myStage = (Stage) this.btnConfirmaRegistroEst.getScene().getWindow();
            myStage.close();
        }
    }

    public boolean realizarValidacionesRegistro() {
        boolean rta = true;
        if(!usuarioEst.getText().isEmpty() && !contrasenaEst.getText().isEmpty() && !nombresEst.getText().isEmpty() && !correoEst.getText().isEmpty() &&
            !carreraEst.getText().isEmpty() && !numIDEst.getText().isEmpty()){
            try {
                ValidacionesHelper.validarCaracteresLetras(nombresEst.getText());
                ValidacionesHelper.validadCorreo(correoEst.getText());
                ValidacionesHelper.validarCaracteresLetras(carreraEst.getText());
                ValidacionesHelper.validarCedula(numIDEst.getText());
            } catch (RuntimeException e) {
                rta = false;
            }
        }
        return rta;
    }

}
