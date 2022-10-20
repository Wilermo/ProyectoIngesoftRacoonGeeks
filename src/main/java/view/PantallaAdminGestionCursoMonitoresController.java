package view;

import interfaceProgram.Global.IGlobalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Estudiante;
import model.Monitor;
import utils.AlertUtils;

import java.io.IOException;
import java.util.UUID;

public class PantallaAdminGestionCursoMonitoresController {

    private UUID idCurso;
    private String usuarioTemp;

    public void setIdCurso(UUID idCurso){
        this.idCurso=idCurso;
    }

    @FXML
    private TableView<Monitor> tablaMonitores;

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
    private Button btnAsignarMoniACurso;

    @FXML
    private Button btnEliminarMoniDeCurso;

    @FXML
    private TextField getTextUsuario;

    @FXML
    private Button btnBuscarMoniAAsignar;

    @FXML
    private Button btnRegresarCursoPrincipal;

    @FXML
    private Button btnVerMonitoresDelCurso;

    @FXML
    private Button btnVerTodosMonitores;

    @FXML
    private Label labelUsername;

    @FXML
    void asignarMoniACurso(ActionEvent event) {
//Estudiante est=tablaEstudiantes.getSelectionModel().getSelectedItem();
        IGlobalController.controladorGeneral.asignarCursosDeMonitorAMonitor(usuarioTemp,idCurso);
        IGlobalController.controladorGeneral.asignarMonitoresACurso(idCurso, usuarioTemp);
        getTextUsuario.clear();
        AlertUtils.alertInformation("Monitor Asignado", "Gestión Monitor", "El monitor ha sido asignado con éxito");
        renderMonitoresCurso();
    }

    @FXML
    void buscarMoniAAsignar(ActionEvent event) {
        if(IGlobalController.controladorGeneral.getMonitorFacade().getMonitorBusiness().getControladorMonitores().getListaMonitores().containsKey(getTextUsuario.getText())) {
            if(IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().getListaCursos().get(idCurso).getMonitoresCurso().containsKey(getTextUsuario.getText())){
                btnEliminarMoniDeCurso.setDisable(false);
                btnAsignarMoniACurso.setDisable(true);
            }else {
                btnEliminarMoniDeCurso.setDisable(true);
                btnAsignarMoniACurso.setDisable(false);
            }
            Monitor estudiante = IGlobalController.controladorGeneral.getMonitorFacade().getMonitorBusiness().getControladorMonitores().getListaMonitores().get(getTextUsuario.getText());
            usuarioEstu.setText(estudiante.getUsuario());
            usuarioTemp=estudiante.getUsuario();
            numIDEstu.setText(String.valueOf(estudiante.getDocumentoEstud()));
            carreraEstu.setText(estudiante.getCarreraEstud());
            correoEstu.setText(estudiante.getCorreo());
            nombreEstu.setText(estudiante.getNombre());
        }else{
            AlertUtils.alertError("Error al buscar monitor", "Error de gestión monitor", "El usuario proporcionado no se encuentra, revise la información suministrada y vuelva a intentarlo");
        }
    }


    @FXML
    void renderMonitoresCurso(ActionEvent event) {
        labelUsername.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        clearWindowMonit();
        for(Monitor moni: IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().buscarCurso(idCurso).getMonitoresCurso().values()){
            tablaMonitores.getItems().add(moni);
        }
    }

    void renderMonitoresCurso() {
        labelUsername.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        clearWindowMonit();
        for(Monitor moni: IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().buscarCurso(idCurso).getMonitoresCurso().values()){
            tablaMonitores.getItems().add(moni);
        }
    }

    @FXML
    void renderTodosMonitores(ActionEvent event){
        labelUsername.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        clearWindowMonit();
        for(Monitor mon: IGlobalController.controladorGeneral.getMonitorFacade().getMonitorBusiness().getControladorMonitores().getListaMonitores().values()){
            tablaMonitores.getItems().add(mon);
        }
    }

    void clearWindowMonit(){
        tablaMonitores.getItems().removeAll();
        tablaMonitores.getItems().clear();
    }

    @FXML
    void llenarCamposMonitor(MouseEvent event) {
        Monitor est=tablaMonitores.getSelectionModel().getSelectedItem();
        usuarioEstu.setText(est.getUsuario());
        usuarioTemp=est.getUsuario();
        numIDEstu.setText(String.valueOf(est.getDocumentoEstud()));
        carreraEstu.setText(est.getCarreraEstud());
        correoEstu.setText(est.getCorreo());
        nombreEstu.setText(est.getNombre());
        if(IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().buscarCurso(idCurso).getMonitoresCurso().containsKey(est.getUsuario())){
            btnAsignarMoniACurso.setDisable(true);
            btnEliminarMoniDeCurso.setDisable(false);
        }else{
            btnAsignarMoniACurso.setDisable(false);
            btnEliminarMoniDeCurso.setDisable(true);
        }
    }

    @FXML
    void eliminarMoniDeCurso(ActionEvent event) {
        IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().getListaCursos().get(idCurso).getMonitoresCurso().remove(usuarioTemp);
        IGlobalController.controladorGeneral.getMonitorFacade().getMonitorBusiness().getControladorMonitores().getListaMonitores().get(usuarioTemp).getCursosMonitor().remove(idCurso);
        getTextUsuario.clear();
        AlertUtils.alertInformation("Monitor Eliminado", "Gestión Monitor", "El monitor ha sido eliminado del curso con éxito");
        renderMonitoresCurso();
    }

    @FXML
    void regresarCursoPrincipal(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaAdminGestionCurso.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage myStage = (Stage) this.btnRegresarCursoPrincipal.getScene().getWindow();
        myStage.close();
    }

}
