package view;

import controller.ControladorGeneral;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Curso;

import javafx.fxml.*;
import model.global;

import java.io.IOException;
import java.util.UUID;

public class PantallaAdminGestionCursoController {

    @FXML
    private Label IDCurso;

    @FXML
    private Label nombreCurso;

    @FXML
    private Button btnRegistrarCurso;

    @FXML
    private Button btnActualizarCurso;

    @FXML
    private Button btnEliminarCurso;

    @FXML
    private Button btnEstuDelCurso;

    @FXML
    private Button btnMonitoresDelCuso;

    @FXML
    private Button btnProfesDelCurso;

    @FXML
    private Button btnRegresarMenuAdmin;

    @FXML
    private TableView<Curso> tablaCursos=new TableView<>();

    @FXML
    private TableColumn<Curso, UUID> columnaID= new TableColumn<>("IdCurso");
/*
TableView<Curso> tableView = new TableView();
TableColumn<Curso, String> columnaID = new TableColumn<>("Nombre");
TableColumn<Curso, String> columnaNmbre = new TableColumn<>("Apellido");
tableView.getColumns().addAll(colNombre, colApellido);
 */
    @FXML
    private TableColumn<Curso, String> columnaNombre = new TableColumn<>("NombreCurso");

    @FXML
    void RegistrarCurso(ActionEvent event) {

    }

    @FXML
    void actualizarCurso(ActionEvent event) {

    }

    @FXML
    void eliminarCurso(ActionEvent event) {

    }

    public void renderWindowCursos() {
        btnEstuDelCurso.setDisable(true);
        clearWindowCursos();
        tablaCursos.getItems().addAll(global.controladorGeneral.getControlCursos().getListaCursos().values());
    }

    public void clearWindowCursos() {
        tablaCursos.getItems().removeAll();
    }

    @FXML
    public void llenarCampos(MouseEvent event){
        btnEstuDelCurso.setDisable(false);
        Curso curso=tablaCursos.getSelectionModel().getSelectedItem();
        IDCurso.setText(String.valueOf(curso.getIdCurso()));
        nombreCurso.setText(curso.getNombreCurso());

    }

    @FXML
    void estuDelCurso(ActionEvent event) throws IOException {
        Curso curso=tablaCursos.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaAdminGestionCursoEstudiantes.fxml"));
        Parent root = loader.load();
        PantallaAdminGestionCursoEstudiantesController pantallaAdminGestionCursoEstudiantesController = loader.getController();
        pantallaAdminGestionCursoEstudiantesController.setIdCurso(curso.getIdCurso());
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e-> {
            try {
                pantallaAdminGestionCursoEstudiantesController.regresarCursoPrincipal(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        Stage myStage = (Stage) this.btnEstuDelCurso.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void monitoresDelCurso(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaAdminGestionCursoMonitores.fxml"));
        Parent root = loader.load();
        PantallaAdminGestionCursoMonitoresController pantallaAdminGestionCursoMonitoresController = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e-> {
            try {
                pantallaAdminGestionCursoMonitoresController.regresarCursoPrincipal(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        Stage myStage = (Stage) this.btnMonitoresDelCuso.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void profesDelCurso(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaAdminGestionCursoProfesores.fxml"));
        Parent root = loader.load();
        PantallaAdminGestionCursoProfesoresController pantallaAdminGestionCursoProfesoresController = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e-> {
            try {
                pantallaAdminGestionCursoProfesoresController.regresarCursoPrincipal(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        Stage myStage = (Stage) this.btnProfesDelCurso.getScene().getWindow();
        myStage.close();
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
