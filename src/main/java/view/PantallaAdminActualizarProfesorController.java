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

import java.io.IOException;

public class PantallaAdminActualizarProfesorController {

    @FXML
    private TextField usuarioProfe;

    @FXML
    private TextField contrasenaProfe;

    @FXML
    private TextField nombresProfe;

    @FXML
    private TextField correoProfe;

    @FXML
    private TextField numIDProfe;

    @FXML
    private Button btnConfirmaActualizarProfe;

    @FXML
    private Button btnCancelarActualizarProfe;


    void llenarCampo(String usuario, String cedula, String correo, String nombre){
        usuarioProfe.setText(usuario);
        numIDProfe.setText(cedula);
        correoProfe.setText(correo);
        nombresProfe.setText(nombre);
    }

    @FXML
    void actualizarProfe(ActionEvent event) throws IOException {
        if(realizarValidacionesActualizar()){
            Profesor profeEncontrado = IGlobalController.controladorGeneral.getProfesorFacade().getProfesorBusiness().getControladorProfesor().buscarProfesor(usuarioProfe.getText());
            IGlobalController.controladorGeneral.getProfesorFacade().getProfesorBusiness().getControladorProfesor().actualizarProfesor(profeEncontrado, nombresProfe.getText(), usuarioProfe.getText(), correoProfe.getText());
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaAdminGestionProfesor.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage myStage = (Stage) this.btnConfirmaActualizarProfe.getScene().getWindow();
        myStage.close();
    }

    public boolean realizarValidacionesActualizar(){
        boolean rta = true;
        if (!nombresProfe.getText().isEmpty() && !correoProfe.getText().isEmpty()) {
            try {
                ValidacionesHelper.validarCaracteresLetras(nombresProfe.getText());
                ValidacionesHelper.validadCorreo(correoProfe.getText());
            } catch (RuntimeException e) {
                rta = false;
            }
        }
        return rta;
    }

    @FXML
    void cancelarActualizarProfe(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaAdminGestionProfesor.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage myStage = (Stage) this.btnCancelarActualizarProfe.getScene().getWindow();
        myStage.close();
    }

}
