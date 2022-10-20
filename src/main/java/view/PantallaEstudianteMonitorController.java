package view;

import interfaceProgram.Global.IGlobalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Curso;
import model.Monitor;
import model.Profesor;
import model.Usuario;
import utils.archivos.ManejoArchivos;

import java.io.IOException;
import java.util.UUID;

public class PantallaEstudianteMonitorController {

    @FXML
    private TableView<Curso> tablaCursosMoni = new TableView();

    @FXML
    private Label usuarioMoni;

    @FXML
    private Label numIDMoni;

    @FXML
    private Label correoMoni;

    @FXML
    private Label nombreMoni;

    @FXML
    private Label numIDCursoMoni;

    @FXML
    private Label nombreCursoMoni;

    @FXML
    private Button btnGestionarTareas;

    @FXML
    private Button btnGestionarContenidos;

    @FXML
    private Button btnVerEstudiantes;

    @FXML
    private Button btnCerrarSesionMonitor;

    @FXML
    private Button btnVerCursos;

    @FXML
    private Label labelUsername;

    @FXML
    void gestionarContenidos(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaProfeGestionContenido.fxml"));
        Parent root = loader.load();
        PantallaProfeGestionContenidoController pantallaProfeGestionContenidoController = loader.getController();
        IGlobalController.controladorGeneral.esMonitor=true;
        pantallaProfeGestionContenidoController.renderWindowContenidos();
//        pantallaAdminGestionCursoController.renderWindowCursos();
        IGlobalController.controladorGeneral.actualCourse=tablaCursosMoni.getSelectionModel().getSelectedItem().getIdCurso();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e-> {
            try {
                pantallaProfeGestionContenidoController.regresarMenuProfe(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        Stage myStage = (Stage) this.btnGestionarContenidos.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void gestionarTareas(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaProfeGestionTarea.fxml"));
        Parent root = loader.load();
        PantallaProfeGestionTareaController pantallaProfeGestionTareaController = loader.getController();
        IGlobalController.controladorGeneral.esMonitor=true;
        IGlobalController.controladorGeneral.actualCourse=tablaCursosMoni.getSelectionModel().getSelectedItem().getIdCurso();
        pantallaProfeGestionTareaController.renderTablita();
        pantallaProfeGestionTareaController.setCurso(tablaCursosMoni.getSelectionModel().getSelectedItem().getIdCurso());
        //pantallaProfeGestionTareaController.setIdCurso(tablaCursos.getSelectionModel().getSelectedItem().getIdCurso());
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e-> {
            try {
                pantallaProfeGestionTareaController.regresarMenuProfe(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        Stage myStage = (Stage) this.btnGestionarTareas.getScene().getWindow();
        myStage.close();
    }


    @FXML
    public void llenarCamposEnMonitor(MouseEvent mouseEvent) {
        IGlobalController.controladorGeneral.actualCourse=tablaCursosMoni.getSelectionModel().getSelectedItem().getIdCurso();
        nombreCursoMoni.setText(tablaCursosMoni.getSelectionModel().getSelectedItem().getNombreCurso());
        numIDCursoMoni.setText(String.valueOf(tablaCursosMoni.getSelectionModel().getSelectedItem().getIdCurso()));
        btnVerEstudiantes.setDisable(false);
        btnGestionarContenidos.setDisable(false);
        btnGestionarTareas.setDisable(false);
    }

    void renderInicial(){
        renderWindowCursos();
    }

    @FXML
    void renderWindowCursos(ActionEvent event) {
        labelUsername.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        nombreMoni.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        usuarioMoni.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        correoMoni.setText(IGlobalController.controladorGeneral.actualUser.getCorreo());
        numIDMoni.setText(String.valueOf(((Monitor) IGlobalController.controladorGeneral.actualUser).getDocumentoEstud()));
        btnVerEstudiantes.setDisable(true);
        btnGestionarContenidos.setDisable(true);
        btnGestionarTareas.setDisable(true);
        clearWindowCursos();
        for (Curso cur: ((Monitor) IGlobalController.controladorGeneral.actualUser).getCursosMonitor().values()){
            System.out.println(cur.getNombreCurso());
            tablaCursosMoni.getItems().add(cur);
        }
//        tablaCursos.getItems().addAll((((Monitor) IGlobalController.controladorGeneral.actualUser).getCursosMonitor().values()));
    }

    void renderWindowCursos() {
        Usuario usuario = IGlobalController.controladorGeneral.actualUser;
        labelUsername.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        nombreMoni.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        usuarioMoni.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        correoMoni.setText(IGlobalController.controladorGeneral.actualUser.getCorreo());
        numIDMoni.setText(String.valueOf(((Monitor) IGlobalController.controladorGeneral.actualUser).getDocumentoEstud()));
        btnVerEstudiantes.setDisable(true);
        btnGestionarContenidos.setDisable(true);
        clearWindowCursos();
        for (Curso cur: ((Monitor) IGlobalController.controladorGeneral.actualUser).getCursosMonitor().values()){
            System.out.println(cur.getNombreCurso());
            tablaCursosMoni.getItems().add(cur);
        }
//        tablaCursos.getItems().addAll((((Monitor) IGlobalController.controladorGeneral.actualUser).getCursosMonitor().values()));
    }

    public void clearWindowCursos() {
        tablaCursosMoni.getItems().removeAll();
        tablaCursosMoni.getItems().clear();
    }

    @FXML
    void renderWindowEstudiante(ActionEvent event) throws IOException {
        labelUsername.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        Curso curso=tablaCursosMoni.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaProfeVerEstudiantes.fxml"));
        Parent root = loader.load();
        PantallaProfeVerEstudiantesController pantallaProfeVerEstudiantesController = loader.getController();
        IGlobalController.controladorGeneral.esMonitor=true;
        pantallaProfeVerEstudiantesController.setCurso(tablaCursosMoni.getSelectionModel().getSelectedItem());
        pantallaProfeVerEstudiantesController.setIdCurso(curso.getIdCurso());
        pantallaProfeVerEstudiantesController.renderWindow();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e-> {
            try {
                pantallaProfeVerEstudiantesController.salir(event);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Stage myStage = (Stage) this.btnVerEstudiantes.getScene().getWindow();
        myStage.close();
    }


    @FXML
    void cerrarSesionMonitor(ActionEvent event) throws IOException {
        IGlobalController.controladorGeneral.esMonitor=false;
        FXMLLoader loader =new FXMLLoader(getClass().getResource("PantallaPrincipalEstudiante.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage myStage = (Stage) this.btnCerrarSesionMonitor.getScene().getWindow();
        myStage.close();
    }


}
