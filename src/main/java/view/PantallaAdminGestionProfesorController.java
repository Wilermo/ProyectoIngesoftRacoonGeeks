package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class PantallaAdminGestionProfesorController {

    @FXML
    private Button btnVerCursosDelProfesor;

    @FXML
    private Label usuarioProfe;

    @FXML
    private Label cursosAsociadosProfe;

    @FXML
    private Label tipoProfe;

    @FXML
    private Label numIDProfe;

    @FXML
    private Label carreraProfe;

    @FXML
    private Label correoProfe;

    @FXML
    private Label nombreProfe;

    @FXML
    private Button btnRegistrarProfe;

    @FXML
    private Button btnActualizarProfe;

    @FXML
    private Button btnEliminarProfe;

    @FXML
    private Button btnRegresarMenuAdmin;

    @FXML
    void actualizarProfe(ActionEvent event) {

    }

    @FXML
    void cursosDelProfesor(ActionEvent event) {

    }

    @FXML
    void eliminarProfe(ActionEvent event) {

    }

    @FXML
    void registrarProfe(ActionEvent event) {

    }

    @FXML
    void regresarMenuAdmin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaPrincipalAdmin.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage myStage = (Stage) this.btnRegresarMenuAdmin.getScene().getWindow();
        myStage.close();
    }


}
