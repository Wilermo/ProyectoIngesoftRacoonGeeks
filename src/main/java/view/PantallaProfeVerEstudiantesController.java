package view;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import interfaceProgram.Global.IGlobalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Curso;
import model.Estudiante;
import model.Profesor;
import model.Usuario;

public class PantallaProfeVerEstudiantesController {
  private Curso curso;
  private UUID idCurso;

  @FXML
  private Button btnRegresar;

  @FXML
  private TableColumn<Estudiante, String> columnaCarrera = new TableColumn<>("CarreraEstud");

  @FXML
  private TableColumn<Estudiante, String> columnaCorreo = new TableColumn<>("Correo");

  @FXML
  private TableColumn<Estudiante, UUID> columnaID = new TableColumn<>("Usuario");

  @FXML
  private TableColumn<Estudiante, String> columnaNombre = new TableColumn<>("Nombre");

  @FXML
  private TableView<Estudiante> tablaEstudiantesCurso;

  public Curso getCurso() {
    return curso;
  }

  public void setCurso(Curso curso) {
    this.curso = curso;
  }

  public UUID getIdCurso() {
    return idCurso;
  }

  public void setIdCurso(UUID idCurso) {
    this.idCurso = idCurso;
  }

  @FXML
  public void renderWindow() {
    System.out.println("Si entro");
    System.out.println(curso.getIdCurso());
    for (Usuario usuario: curso.getEstudiantesPertenecenCurso().values()){
      if (usuario instanceof Estudiante) {
        System.out.println(usuario.getUsuario());
        tablaEstudiantesCurso.getItems().add((Estudiante) usuario);
      }
    }
    /*
    for(Estudiante est: IGlobalController.controladorGeneral.getControlCursos().buscarCurso(idCurso).getEstudiantesPertenecenCurso().values()){
      System.out.println(est.getUsuario());
      tablaEstudiantesCurso.getItems().add(est);
    }
//    clearWindow();
//    tablaEstudiantesCurso.getItems().addAll(((IGlobalController.controladorGeneral.actualCourse)));
    /*
    for (Curso cursos: IGlobalController.controladorGeneral.getControlCursos().getListaCursos().values()){
      if (cursos.getIdCurso().equals(curso.getIdCurso())){
        for (Estudiante est: curso.getEstudiantesPertenecenCurso().values()){
          tablaEstudiantesCurso.getItems().add(est);
        }
      }
    }*/
  }

  @FXML
  public void clearWindow(){
    tablaEstudiantesCurso.getItems().removeAll();
    tablaEstudiantesCurso.getItems().clear();
  }

  @FXML
  void salir(ActionEvent event) throws IOException {
    FXMLLoader loader ;
    if(IGlobalController.controladorGeneral.esMonitor){
      loader= new FXMLLoader(getClass().getResource("PantallaPrincipalProfesor.fxml"));
    }else{
      loader= new FXMLLoader(getClass().getResource("PantallaEstudianteMonitor.fxml"));
    }
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();

    Stage myStage = (Stage) this.btnRegresar.getScene().getWindow();
    myStage.close();
  }

}