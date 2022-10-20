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
import utils.archivos.ArchivoCursos;
import utils.archivos.ManejoArchivos;

import java.io.IOException;
import java.util.UUID;

public class PantallaEstudianteRegularController {

    boolean esMonitor; //True si es monitor, sirve para saber qué pantalla abrir al cerrar ventana

    public boolean isEsMonitor() {
        return esMonitor;
    }

    public void setEsMonitor(boolean esMonitor) {
        this.esMonitor = esMonitor;
    }

    @FXML
    private Button btnCerrarSeionEstu;

    @FXML
    private Button btnVerContenido;

    @FXML
    private Button btnVerCursos;

    @FXML
    private Button btnVerTareas;

    @FXML
    private Label carreraEstu;

    @FXML
    private TableColumn<Curso, UUID> columnaID;

    @FXML
    private TableColumn<Curso, String> columnaNombre;

    @FXML
    private Label correoEstu;

    @FXML
    private Label labelUsername;

    @FXML
    private Label nombreCursoEstu;

    @FXML
    private Label nombreEstu;

    @FXML
    private Label numIDCursoEstu;

    @FXML
    private Label numIDEstu;

    @FXML
    private TableView<Curso> tablaCursos;

    @FXML
    private Label usuarioEstu;

    @FXML
    void llenarCamposEnEstudiante(MouseEvent event){
        usuarioEstu.setText(((Estudiante) IGlobalController.controladorGeneral.actualUser).getUsuario());
        nombreEstu.setText(((Estudiante) IGlobalController.controladorGeneral.actualUser).getNombre());
        correoEstu.setText(((Estudiante) IGlobalController.controladorGeneral.actualUser).getCorreo());
        numIDEstu.setText(((Estudiante) IGlobalController.controladorGeneral.actualUser).getDocumentoEstud().toString());
        carreraEstu.setText(((Estudiante) IGlobalController.controladorGeneral.actualUser).getCarreraEstud());
        numIDCursoEstu.setText(tablaCursos.getSelectionModel().getSelectedItem().getIdCurso().toString());
        nombreCursoEstu.setText(tablaCursos.getSelectionModel().getSelectedItem().getNombreCurso());
        btnVerContenido.setDisable(false);
        btnVerTareas.setDisable(false);
    }

    @FXML
    void renderInicial() {
        renderWindowCursos();
        usuarioEstu.setText(((Estudiante) IGlobalController.controladorGeneral.actualUser).getUsuario());
        nombreEstu.setText(((Estudiante) IGlobalController.controladorGeneral.actualUser).getNombre());
        correoEstu.setText(((Estudiante) IGlobalController.controladorGeneral.actualUser).getCorreo());
        numIDEstu.setText(((Estudiante) IGlobalController.controladorGeneral.actualUser).getDocumentoEstud().toString());
        carreraEstu.setText(((Estudiante) IGlobalController.controladorGeneral.actualUser).getCarreraEstud());
    }

    @FXML
    void renderWindowCursos() {
        labelUsername.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        clearWindowCursos();
        btnVerContenido.setDisable(true);
        btnVerTareas.setDisable(true);
        tablaCursos.getItems().addAll(((Estudiante) IGlobalController.controladorGeneral.actualUser).getCursosPertenecenAEstudiante().values());
        System.out.println("Tabla");
    }

    @FXML
    void renderWindowCursos(ActionEvent event) {
        labelUsername.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        clearWindowCursos();
        btnVerContenido.setDisable(true);
        btnVerTareas.setDisable(true);
        tablaCursos.getItems().addAll(((Estudiante) IGlobalController.controladorGeneral.actualUser).getCursosPertenecenAEstudiante().values());
    }

    public void clearWindowCursos() {
        tablaCursos.getItems().removeAll();
        tablaCursos.getItems().clear();
    }

    @FXML
    void verContenidoEstu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaEstudianteRegularContenido.fxml"));
        Parent root = loader.load();
        PantallaEstudianteRegularContenidoController pantallaEstudianteRegularContenidoController = loader.getController();
        pantallaEstudianteRegularContenidoController.setCurso(tablaCursos.getSelectionModel().getSelectedItem());
        pantallaEstudianteRegularContenidoController.renderInicial();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e-> {
            try {
                pantallaEstudianteRegularContenidoController.cerrarSeionEstu(event);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Stage myStage = (Stage) this.btnVerContenido.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void verTareasEstu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaEstudianteRegularTarea.fxml"));
        Parent root = loader.load();
        tablaCursos.getSelectionModel().getSelectedItem();
        PantallaEstudianteRegularTareaController pantallaEstudianteRegularTareaController = loader.getController();
        pantallaEstudianteRegularTareaController.setCurso(tablaCursos.getSelectionModel().getSelectedItem());
        pantallaEstudianteRegularTareaController.setEstudiante((Estudiante) IGlobalController.controladorGeneral.actualUser);
        pantallaEstudianteRegularTareaController.renderInicial();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e-> {
            try {
                pantallaEstudianteRegularTareaController.cerrarSeionEstu(event);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Stage myStage = (Stage) this.btnVerTareas.getScene().getWindow();
        myStage.close();
    }


    @FXML
    void cerrarSeionEstu(ActionEvent event) throws IOException {
        ManejoArchivos.llenarCursos();
        FXMLLoader loader ;
        if(esMonitor){
            loader = new FXMLLoader(getClass().getResource("PantallaPrincipalEstudiante.fxml"));
        }else{
            loader = new FXMLLoader(getClass().getResource("PantallaInicio.fxml"));
        }

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage myStage = (Stage) this.btnCerrarSeionEstu.getScene().getWindow();
        myStage.close();
    }
}
