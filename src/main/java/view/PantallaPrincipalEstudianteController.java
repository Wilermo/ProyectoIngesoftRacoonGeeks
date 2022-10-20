package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PantallaPrincipalEstudianteController {

    @FXML
    private Button btnCerrarSesionEst;

    @FXML
    private Button btnEstMonitor;

    @FXML
    private Button btnEstRegular;

    @FXML
    void cerrarSesionEst(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaInicio.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage myStage = (Stage) this.btnCerrarSesionEst.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void estudianteMonitor(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaEstudianteMonitor.fxml"));
        Parent root = loader.load();
        PantallaEstudianteMonitorController pantallaEstudianteMonitorController = loader.getController();
        pantallaEstudianteMonitorController.renderInicial();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e-> {
            try {
                pantallaEstudianteMonitorController.cerrarSesionMonitor(event);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Stage myStage = (Stage) this.btnEstMonitor.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void estudianteRegular(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaEstudianteRegular.fxml"));
        Parent root = loader.load();
        PantallaEstudianteRegularController pantallaEstudianteRegularController = loader.getController();
        pantallaEstudianteRegularController.renderInicial();
        pantallaEstudianteRegularController.setEsMonitor(true);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e-> {
            try {
                pantallaEstudianteRegularController.cerrarSeionEstu(event);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Stage myStage = (Stage) this.btnEstRegular.getScene().getWindow();
        myStage.close();
    }

}
