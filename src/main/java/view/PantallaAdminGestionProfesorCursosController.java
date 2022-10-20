package view;

import java.io.IOException;

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
import model.Profesor;

public class PantallaAdminGestionProfesorCursosController {

  @FXML
  private TableView<Curso> tablaCursosProfes;

  @FXML
  private Button btnRegresar;

  @FXML
  public void renderWindowTablita(String usuario) {
    limpiarVentana();
    for(Profesor prof : IGlobalController.controladorGeneral.getProfesorFacade().getProfesorBusiness().getControladorProfesor().getListaProfes().values()){
      if (prof.getUsuario().equals(usuario)){
        for (Curso cur : prof.getCursosPertenecenAProfesor().values()){
          tablaCursosProfes.getItems().add(cur);
        }
      }
    }
  }

  @FXML
  public void limpiarVentana(){
    tablaCursosProfes.getItems().removeAll();
    tablaCursosProfes.getItems().clear();
  }

  public void salir(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaAdminGestionProfesor.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();
//    stage.getScene().getWindow();
    Stage myStage = (Stage) this.btnRegresar.getScene().getWindow();
    myStage.close();
  }

}
