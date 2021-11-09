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

public class PantallaAdminGestionEstudianteController {
    @FXML
    private Label usuarioEstu;

    @FXML
    private Label tipoEstu;

    @FXML
    private Label numIDEstu;

    @FXML
    private Label carreraEstu;

    @FXML
    private Label correoEstu;

    @FXML
    private Label nombreEstu;

    @FXML
    private Button btnRegistrarEstu;

    @FXML
    private Button btnActualizarEstu;

    @FXML
    private Button btnEliminarEstu;

    @FXML
    private Button btnVerCursosDelEstu;

    @FXML
    private Button btnRegresarMenuAdmin;

    @FXML
    void actualizarEstu(ActionEvent event) {

    }

    @FXML
    void cursosDelEstu(ActionEvent event) {

    }

    @FXML
    void eliminarEstu(ActionEvent event) {

    }

    @FXML
    void registrarEstu(ActionEvent event) {

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