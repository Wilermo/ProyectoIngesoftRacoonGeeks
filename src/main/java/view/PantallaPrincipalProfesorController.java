/*package view;

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
import model.Profesor;
import utils.archivos.ManejoArchivos;

import java.io.IOException;
import java.util.UUID;

public class PantallaPrincipalProfesorController {

    @FXML
    private TableView<Curso> tablaCursos = new TableView();

    @FXML
    private TableColumn< Curso, UUID> columnaID = new TableColumn<>("IdCurso");

    @FXML
    private TableColumn<Curso, String> columnaNombre = new TableColumn("NombreCurso"); //TENER EN CUENTA

    @FXML
    private Label usuarioProfe;

    @FXML
    private Label numIDProfe;

    @FXML
    private Label correoProfe;

    @FXML
    private Label nombreProfe;

    @FXML
    private Label numIDCursoProfe;

    @FXML
    private Label nombreCursoProfe;

    @FXML
    private Button btnGestionarTareas;

    @FXML
    private Button btnGestionarContenidos;

    @FXML
    private Button btnVerEstudiantes;

    @FXML
    private Button btnCerrarSeionProfe;

    @FXML
    private Button btnVerCursos;

    @FXML
    private Label labelUsername;

    @FXML
    void cerrarSeionProfe(ActionEvent event) throws IOException {
        ManejoArchivos.llenarCursos();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaInicio.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage myStage = (Stage) this.btnCerrarSeionProfe.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void gestionarContenidos(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaProfeGestionContenido.fxml"));
        Parent root = loader.load();
        PantallaProfeGestionContenidoController pantallaProfeGestionContenidoController = loader.getController();
        pantallaProfeGestionContenidoController.renderWindowContenidos();
        //pantallaAdminGestionCursoController.renderWindowCursos();
        IGlobalController.controladorGeneral.actualCourse=tablaCursos.getSelectionModel().getSelectedItem().getIdCurso();

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
        pantallaProfeGestionTareaController.renderTablita();
        IGlobalController.controladorGeneral.actualCourse=tablaCursos.getSelectionModel().getSelectedItem().getIdCurso();
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
    public void llenarCamposEnProfe(MouseEvent mouseEvent) {
        IGlobalController.controladorGeneral.actualCourse=tablaCursos.getSelectionModel().getSelectedItem().getIdCurso();
        nombreCursoProfe.setText(tablaCursos.getSelectionModel().getSelectedItem().getNombreCurso());
        numIDCursoProfe.setText(String.valueOf(tablaCursos.getSelectionModel().getSelectedItem().getIdCurso()));
        btnVerEstudiantes.setDisable(false);
        btnGestionarContenidos.setDisable(false);
        btnGestionarTareas.setDisable(false);
    }

    void renderInicial(){
        renderWindowCursos();
        labelUsername.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        nombreProfe.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        usuarioProfe.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        correoProfe.setText(IGlobalController.controladorGeneral.actualUser.getCorreo());
        numIDProfe.setText(String.valueOf(((Profesor) IGlobalController.controladorGeneral.actualUser).getCedulaProfe()));

    }

    @FXML
    void renderWindowCursos(ActionEvent event) {
        labelUsername.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        nombreProfe.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        usuarioProfe.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        correoProfe.setText(IGlobalController.controladorGeneral.actualUser.getCorreo());
        numIDProfe.setText(String.valueOf(((Profesor) IGlobalController.controladorGeneral.actualUser).getCedulaProfe()));
        btnVerEstudiantes.setDisable(true);
        btnGestionarContenidos.setDisable(true);
        btnGestionarTareas.setDisable(true);
        clearWindowCursos();
        tablaCursos.getItems().addAll((((Profesor) IGlobalController.controladorGeneral.actualUser).getCursosPertenecenAProfesor().values()));
    }
    void renderWindowCursos() {
        labelUsername.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        nombreProfe.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        usuarioProfe.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        correoProfe.setText(IGlobalController.controladorGeneral.actualUser.getCorreo());
        numIDProfe.setText(String.valueOf(((Profesor) IGlobalController.controladorGeneral.actualUser).getCedulaProfe()));
        btnVerEstudiantes.setDisable(true);
        btnGestionarContenidos.setDisable(true);
        clearWindowCursos();
        tablaCursos.getItems().addAll((((Profesor) IGlobalController.controladorGeneral.actualUser).getCursosPertenecenAProfesor().values()));
    }

    public void clearWindowCursos() {
        tablaCursos.getItems().removeAll();
        tablaCursos.getItems().clear();
    }

    @FXML
    void renderWindowEstudiante(ActionEvent event) throws IOException {
        Curso curso=tablaCursos.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaProfeVerEstudiantes.fxml"));
        Parent root = loader.load();
        PantallaProfeVerEstudiantesController pantallaProfeVerEstudiantesController = loader.getController();
        pantallaProfeVerEstudiantesController.setCurso(tablaCursos.getSelectionModel().getSelectedItem());
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


}
*/

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
import model.Estudiante;
import model.Profesor;
import utils.archivos.ManejoArchivos;

import java.io.IOException;
import java.util.UUID;

public class PantallaPrincipalProfesorController {

    @FXML
    private TableView<Curso> tablaCursos = new TableView();

    @FXML
    private TableColumn< Curso, UUID> columnaID = new TableColumn<>("IdCurso");

    @FXML
    private TableColumn<Curso, String> columnaNombre = new TableColumn("NombreCurso"); //TENER EN CUENTA

    @FXML
    private Label usuarioProfe;

    @FXML
    private Label numIDProfe;

    @FXML
    private Label correoProfe;

    @FXML
    private Label nombreProfe;

    @FXML
    private Label numIDCursoProfe;

    @FXML
    private Label nombreCursoProfe;

    @FXML
    private Button btnGestionarTareas;

    @FXML
    private Button btnGestionarContenidos;

    @FXML
    private Button btnVerEstudiantes;

    @FXML
    private Button btnCerrarSeionProfe;

    @FXML
    private Button btnVerCursos;

    @FXML
    private Label labelUsername;

    @FXML
    void cerrarSeionProfe(ActionEvent event) throws IOException {
        ManejoArchivos.llenarCursos();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaInicio.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage myStage = (Stage) this.btnCerrarSeionProfe.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void gestionarContenidos(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaProfeGestionContenido.fxml"));
        Parent root = loader.load();
        PantallaProfeGestionContenidoController pantallaProfeGestionContenidoController = loader.getController();
        pantallaProfeGestionContenidoController.renderWindowContenidos();
//        pantallaAdminGestionCursoController.renderWindowCursos();
        IGlobalController.controladorGeneral.actualCourse=tablaCursos.getSelectionModel().getSelectedItem().getIdCurso();

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
        IGlobalController.controladorGeneral.actualCourse=tablaCursos.getSelectionModel().getSelectedItem().getIdCurso();
        pantallaProfeGestionTareaController.renderTablita();
        pantallaProfeGestionTareaController.setCurso(tablaCursos.getSelectionModel().getSelectedItem().getIdCurso());
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
    public void llenarCamposEnProfe(MouseEvent mouseEvent) {
        IGlobalController.controladorGeneral.actualCourse=tablaCursos.getSelectionModel().getSelectedItem().getIdCurso();
        nombreCursoProfe.setText(tablaCursos.getSelectionModel().getSelectedItem().getNombreCurso());
        numIDCursoProfe.setText(String.valueOf(tablaCursos.getSelectionModel().getSelectedItem().getIdCurso()));
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
        nombreProfe.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        usuarioProfe.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        correoProfe.setText(IGlobalController.controladorGeneral.actualUser.getCorreo());
        numIDProfe.setText(String.valueOf(((Profesor) IGlobalController.controladorGeneral.actualUser).getCedulaProfe()));
        btnVerEstudiantes.setDisable(true);
        btnGestionarContenidos.setDisable(true);
        btnGestionarTareas.setDisable(true);
        clearWindowCursos();
        tablaCursos.getItems().addAll((((Profesor) IGlobalController.controladorGeneral.actualUser).getCursosPertenecenAProfesor().values()));
    }

    void renderWindowCursos() {
        labelUsername.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        nombreProfe.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        usuarioProfe.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        correoProfe.setText(IGlobalController.controladorGeneral.actualUser.getCorreo());
        numIDProfe.setText(String.valueOf(((Profesor) IGlobalController.controladorGeneral.actualUser).getCedulaProfe()));
        btnVerEstudiantes.setDisable(true);
        btnGestionarContenidos.setDisable(true);
        clearWindowCursos();
        tablaCursos.getItems().addAll((((Profesor) IGlobalController.controladorGeneral.actualUser).getCursosPertenecenAProfesor().values()));
    }

    public void clearWindowCursos() {
        tablaCursos.getItems().removeAll();
        tablaCursos.getItems().clear();
    }

    @FXML
    void renderWindowEstudiante(ActionEvent event) throws IOException {
        labelUsername.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        Curso curso=tablaCursos.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaProfeVerEstudiantes.fxml"));
        Parent root = loader.load();
        PantallaProfeVerEstudiantesController pantallaProfeVerEstudiantesController = loader.getController();
        pantallaProfeVerEstudiantesController.setCurso(tablaCursos.getSelectionModel().getSelectedItem());
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


}
