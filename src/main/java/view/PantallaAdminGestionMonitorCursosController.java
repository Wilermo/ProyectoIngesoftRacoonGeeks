package view;

import interfaceProgram.Global.IGlobalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Curso;
import model.Estudiante;
import model.Monitor;

import java.io.IOException;

public class PantallaAdminGestionMonitorCursosController {
    @FXML
    private TableView<Curso> tablaCursosEstudiante;

    @FXML
    private Button btnRegresar;

    @FXML
    public void renderWindowTablita(String usuario) {
        limpiarVentana();
        Estudiante monitor=IGlobalController.controladorGeneral.getEstudianteFacade().getEstudianteBusiness().getControladorEstudiante().buscarEstudiante(usuario);
        for(Curso curso:((Monitor)monitor).getCursosMonitor().values()){
            tablaCursosEstudiante.getItems().add(curso);
        }
    }

    @FXML
    public void limpiarVentana(){
        tablaCursosEstudiante.getItems().removeAll();
        tablaCursosEstudiante.getItems().clear();
    }

    @FXML
    void salir(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaAdminGestionEstudiante.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage myStage = (Stage) this.btnRegresar.getScene().getWindow();
        myStage.close();
    }
}
