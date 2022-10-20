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

import java.io.IOException;

public class PantallaAdminActualizarEstudianteController {

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
    private Button btnConfirmaActualizarEst;

    @FXML
    private Button btnCancelarActualizarEst;

    @FXML
    void actualizarEst(ActionEvent event) throws IOException {
        if(realizarValidacionesActualizar()){
            Estudiante estudianteEncontrado = IGlobalController.controladorGeneral.getEstudianteFacade().getEstudianteBusiness().getControladorEstudiante().buscarEstudiante(usuarioEst.getText());
            IGlobalController.controladorGeneral.getEstudianteFacade().getEstudianteBusiness().getControladorEstudiante().actualizarEstudiante(estudianteEncontrado, nombresEst.getText(),usuarioEst.getText(),correoEst.getText());
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaAdminGestionEstudiante.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage myStage = (Stage) this.btnConfirmaActualizarEst.getScene().getWindow();
        myStage.close();
    }

    void llenarCampo(String usuario, String nombre, String correo, String carrera, Long cedula){
        usuarioEst.setText(usuario);
        numIDEst.setText(String.valueOf(cedula));
        correoEst.setText(correo);
        carreraEst.setText(carrera);
        nombresEst.setText(nombre);
    }

    public boolean realizarValidacionesActualizar(){
        boolean rta = true;
        if (!nombresEst.getText().isEmpty() && !correoEst.getText().isEmpty() && !carreraEst.getText().isEmpty()) {
            try {
                ValidacionesHelper.validarCaracteresLetras(nombresEst.getText());
                ValidacionesHelper.validadCorreo(correoEst.getText());
                ValidacionesHelper.validarCaracteresLetras(carreraEst.getText());
            } catch (RuntimeException e) {
                rta = false;
            }
        }
        return rta;
    }

    @FXML
    void cancelarActualizarEst(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaAdminGestionEstudiante.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage myStage = (Stage) this.btnCancelarActualizarEst.getScene().getWindow();
        myStage.close();
    }

}
