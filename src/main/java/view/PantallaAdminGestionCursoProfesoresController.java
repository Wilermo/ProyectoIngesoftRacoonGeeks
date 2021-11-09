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

public class PantallaAdminGestionCursoProfesoresController {

    @FXML
    private Label usuarioProfe;

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
    private Button btnAsignarProfeACurso;

    @FXML
    private Button btnEliminarProfeDeCurso;

    @FXML
    private Button btnBuscarProfeAAsignar;

    @FXML
    private Button btnRegresarCursoPrincipal;

    @FXML
    void asignarProfeACurso(ActionEvent event) {

    }

    @FXML
    void buscarProfeAAsignar(ActionEvent event) {

    }

    @FXML
    void eliminarProfeDeCurso(ActionEvent event) {

    }

    @FXML
    void regresarCursoPrincipal(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaAdminGestionCurso.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage myStage = (Stage) this.btnRegresarCursoPrincipal.getScene().getWindow();
        myStage.close();
    }

}
