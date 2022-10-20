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
import model.Estudiante;
import model.Monitor;
import model.Profesor;
import utils.AlertUtils;

import java.io.IOException;
import java.util.UUID;

public class PantallaAdminGestionEstudianteController {

    @FXML
    private TableView<Estudiante> tablaEstudiantes;

    @FXML
    private TableColumn<Long, Estudiante> IDEstTabla;

    @FXML
    private TableColumn<String, Estudiante> nombreEstTabla;

    @FXML
    private TableColumn<String, Estudiante> carreraEstTabla;

    @FXML
    private TableColumn<String, Estudiante> correoEstTabla;

    @FXML
    private TableColumn<?, ?> tipoEstTabla;

    @FXML
    private Label usuarioEstu;

    @FXML
    private Label tipoEstu;

    @FXML
    private Label numIDEstu;

    @FXML
    private Label carreraEstu;

    @FXML
    private Label correoEstu;

    @FXML
    private Label nombreEstu;

    @FXML
    private Label labelUsername;

    @FXML
    private Label labelCursosMonitoria;

    @FXML
    private Button btnRegistrarEstu;

    @FXML
    private Button btnActualizarEstu;

    @FXML
    private Button btnEliminarEstu;

    @FXML
    private Button btnVerCursosDelEstu;

    @FXML
    private Button btnRegresarMenuAdmin;

    @FXML
    private Button btnVerCursosDelMonitor;

    @FXML
    private Button btnQuitarMonitor;

    @FXML
    private Button btnCrearMonitor;

    @FXML
    private Button btnVerMonit;

    @FXML
    private Button btnVerEst;

    @FXML
    void llenarCamposEstu(MouseEvent event) {
        btnActualizarEstu.setDisable(false);
        btnEliminarEstu.setDisable(false);
        btnVerCursosDelEstu.setDisable(false);
        Estudiante est = tablaEstudiantes.getSelectionModel().getSelectedItem();
        nombreEstu.setText(String.valueOf(est.getNombre()));
        carreraEstu.setText(est.getCarreraEstud());
        usuarioEstu.setText(String.valueOf(est.getUsuario()));
        numIDEstu.setText(String.valueOf(est.getDocumentoEstud()));
        correoEstu.setText(String.valueOf(est.getCorreo()));
        if(est instanceof Monitor){
            btnCrearMonitor.setVisible(false);
            btnCrearMonitor.setDisable(true);
            btnQuitarMonitor.setVisible(true);
            btnQuitarMonitor.setDisable(false);
            labelCursosMonitoria.setVisible(true);
            btnVerCursosDelMonitor.setDisable(false);
            btnVerCursosDelMonitor.setVisible(true);
        }else{
            btnCrearMonitor.setVisible(true);
            btnCrearMonitor.setDisable(false);
            btnQuitarMonitor.setVisible(false);
            btnQuitarMonitor.setDisable(true);
            labelCursosMonitoria.setVisible(false);
            btnVerCursosDelMonitor.setDisable(true);
            btnVerCursosDelMonitor.setVisible(false);
        }
    }

    @FXML
    void cursosDelEstu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaAdminGestionEstudianteCursos.fxml"));
        Parent root = loader.load();
        PantallaAdminGestionEstudianteCursosController pantallaAdminGestionEstudianteCursosController = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        pantallaAdminGestionEstudianteCursosController.renderWindowTablita(usuarioEstu.getText());
        stage.setOnCloseRequest(e-> {
            try {
                pantallaAdminGestionEstudianteCursosController.salir(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        Stage myStage = (Stage) this.btnRegistrarEstu.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void eliminarEstu(ActionEvent event) {
        Estudiante estudiante = tablaEstudiantes.getSelectionModel().getSelectedItem();
        IGlobalController.controladorGeneral.getEstudianteFacade().getEstudianteBusiness().getControladorEstudiante().eliminarEstudiante(estudiante);
        AlertUtils.alertConfirmation("Estudiante eliminado", "Confirmación eliminacion", "El estudiante se elimino con exito");
        renderWindowEst();
    }

    @FXML
    void registrarEstu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaAdminRegistrarEstudiante.fxml"));
        Parent root = loader.load();
        PantallaAdminRegistrarEstudianteController pantallaAdminRegistrarEstudianteController = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e-> {
            try {
                pantallaAdminRegistrarEstudianteController.cancelarRegistroEst(event);;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        Stage myStage = (Stage) this.btnActualizarEstu.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void actualizarEstu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaAdminActualizarEstudiante.fxml"));
        Parent root = loader.load();
        PantallaAdminActualizarEstudianteController pantallaAdminActualizarEstudianteController = loader.getController();
        pantallaAdminActualizarEstudianteController.llenarCampo(usuarioEstu.getText(),nombreEstu.getText(),correoEstu.getText(),carreraEstu.getText(),Long.valueOf(numIDEstu.getText()));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e-> {
            try {
                pantallaAdminActualizarEstudianteController.cancelarActualizarEst(event);;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        Stage myStage = (Stage) this.btnActualizarEstu.getScene().getWindow();
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

    @FXML
    void renderWindowEst(){
        labelUsername.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        limpiarVentana();
        for(Estudiante estu : IGlobalController.controladorGeneral.getEstudianteFacade().getEstudianteBusiness().getControladorEstudiante().getListaEstudiantes().values()){
            tablaEstudiantes.getItems().add(estu);
        }

        btnActualizarEstu.setDisable(true);
        btnEliminarEstu.setDisable(true);
        btnVerCursosDelEstu.setDisable(true);
        btnVerCursosDelMonitor.setVisible(false);
        labelCursosMonitoria.setVisible(false);
    }


    @FXML
    public void limpiarVentana(){
        tablaEstudiantes.getItems().removeAll();
        tablaEstudiantes.getItems().clear();
    }

    @FXML
    public void renderWindowMonitor(ActionEvent event){
        labelUsername.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        limpiarVentana();
        for(Monitor monitor:IGlobalController.controladorGeneral.getMonitorFacade().getMonitorBusiness().getControladorMonitores().getListaMonitores().values()){
            tablaEstudiantes.getItems().add(monitor);
        }
        btnActualizarEstu.setDisable(true);
        btnEliminarEstu.setDisable(true);
        btnVerCursosDelEstu.setDisable(true);
        btnVerCursosDelMonitor.setVisible(true);
        btnVerCursosDelMonitor.setDisable(true);
        labelCursosMonitoria.setVisible(true);
    }

    @FXML
    public void cursosDelMonitor(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaAdminGestionMonitorCursos.fxml"));
        Parent root = loader.load();
        PantallaAdminGestionMonitorCursosController pantallaAdminGestionMonitorCursosController = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        pantallaAdminGestionMonitorCursosController.renderWindowTablita(usuarioEstu.getText());
        stage.setOnCloseRequest(e-> {
            try {
                pantallaAdminGestionMonitorCursosController.salir(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        Stage myStage = (Stage) this.btnRegistrarEstu.getScene().getWindow();
        myStage.close();
    }

    @FXML
    public void convertirAMonitor(ActionEvent event){
        IGlobalController.controladorGeneral.crearMonitorAPartirDeEstudiante(IGlobalController.controladorGeneral.getEstudianteFacade().getEstudianteBusiness().getControladorEstudiante().buscarEstudiante(usuarioEstu.getText()));
        AlertUtils.alertInformation("Monitor Creado","Gestión monitores","El estudiante ha sido convertido en monitor satisfactoriamente");
        renderWindowMonitor(event);
    }

    @FXML
    public void quitarRolMonitor(ActionEvent event){
        IGlobalController.controladorGeneral.removerRolDeMonitorAUnEstudiante(IGlobalController.controladorGeneral.getMonitorFacade().getMonitorBusiness().getControladorMonitores().getListaMonitores().get(usuarioEstu.getText()));
        AlertUtils.alertInformation("Monitor removidoWilly123   ","Gestión monitores","El rol del monitor ha sido removido");
        renderWindowEst();
    }
}