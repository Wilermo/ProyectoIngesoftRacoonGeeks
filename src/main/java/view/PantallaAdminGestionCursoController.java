package view;

import back.helper.ValidacionesHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Curso;

import interfaceProgram.Global.IGlobalController;
import utils.AlertUtils;

import javax.swing.plaf.IconUIResource;
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
    private TextField txt_nuevoNombreCurso;

    @FXML
    private TextField txt_ActualizarNombreCurso;

    @FXML
    private Label labelNuevoNombre;

    @FXML
    private Button btnCambiarCurso;

    @FXML
    private Label labelUsername;

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
        if(realizarValidacionesRegistro()){
            IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().crearCurso(txt_nuevoNombreCurso.getText());
        }
        renderWindowCursos();
    }

    public boolean realizarValidacionesRegistro() {
        boolean rta = true;
        if(!txt_nuevoNombreCurso.getText().isEmpty()){
            try {
                ValidacionesHelper.validarCaracteresLetras(txt_nuevoNombreCurso.getText());
            } catch (RuntimeException e) {
                rta = false;
            }
        }
        return rta;
    }

    @FXML
    void actualizarCurso(ActionEvent event) {
       txt_ActualizarNombreCurso.setVisible(true);
       labelNuevoNombre.setVisible(true);
       btnCambiarCurso.setVisible(true);
       btnCambiarCurso.setDisable(false);

    }


    @FXML
    void eliminarCurso(ActionEvent event) {
        Curso curso = tablaCursos.getSelectionModel().getSelectedItem();
        IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().eliminarCurso(curso);
        AlertUtils.alertConfirmation("Curso eliminado", "Confirmación eliminacion", "El curso se elimino con exito");
        renderWindowCursos();
    }

    @FXML
    void CambiarCurso(ActionEvent event) {
        if(realizarValidacionesActualizar()){
            Curso nuevoCurso = tablaCursos.getSelectionModel().getSelectedItem();
            IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().modificarCurso(nuevoCurso, txt_ActualizarNombreCurso.getText());
            txt_ActualizarNombreCurso.clear();
            AlertUtils.alertInformation("Curso actualizado","Confirmación actualización","El curso ha sido actualizado satisfactoriamente");
        }else{
            AlertUtils.alertError("Error al actualizar curso","Error","Revise el nombre ingresado e intentelo nuevamente");
        }
        renderWindowCursos();
        txt_ActualizarNombreCurso.setVisible(false);
        labelNuevoNombre.setVisible(false);
        btnCambiarCurso.setVisible(false);
        btnCambiarCurso.setDisable(true);
    }

    public boolean realizarValidacionesActualizar() {
        boolean rta = true;
        if(!txt_nuevoNombreCurso.getText().isEmpty()){
            try {
                ValidacionesHelper.validarCaracteresLetras(txt_nuevoNombreCurso.getText());
            } catch (RuntimeException e) {
                rta = false;
            }
        }
        return rta;
    }

    public void renderWindowCursos() {
        labelUsername.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        btnEstuDelCurso.setDisable(true);
        btnActualizarCurso.setDisable(true);
        btnEliminarCurso.setDisable(true);
        btnMonitoresDelCuso.setDisable(true);
        btnProfesDelCurso.setDisable(true);
        clearWindowCursos();
        tablaCursos.getItems().addAll(IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().getListaCursos().values());
    }

    public void clearWindowCursos() {
        tablaCursos.getItems().removeAll();
        tablaCursos.getItems().clear();
    }

    @FXML
    public void llenarCampos(MouseEvent event){
        btnEstuDelCurso.setDisable(false);
        btnActualizarCurso.setDisable(false);
        btnEliminarCurso.setDisable(false);
        btnMonitoresDelCuso.setDisable(false);
        btnProfesDelCurso.setDisable(false);
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
        pantallaAdminGestionCursoEstudiantesController.renderEstudiantesCurso();
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
        Curso curso=tablaCursos.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaAdminGestionCursoMonitores.fxml"));
        Parent root = loader.load();
        PantallaAdminGestionCursoMonitoresController pantallaAdminGestionCursoMonitoresController = loader.getController();
        pantallaAdminGestionCursoMonitoresController.setIdCurso(curso.getIdCurso());
        pantallaAdminGestionCursoMonitoresController.renderMonitoresCurso();
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
        Curso curso = tablaCursos.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaAdminGestionCursoProfesores.fxml"));
        Parent root = loader.load();
        PantallaAdminGestionCursoProfesoresController pantallaAdminGestionCursoProfesoresController = loader.getController();
        pantallaAdminGestionCursoProfesoresController.setIdCurso(curso.getIdCurso());
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
