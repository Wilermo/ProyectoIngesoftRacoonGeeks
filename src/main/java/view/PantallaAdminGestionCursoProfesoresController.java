package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Profesor;
import interfaceProgram.Global.IGlobalController;
import utils.AlertUtils;

import java.io.IOException;
import java.util.UUID;

public class PantallaAdminGestionCursoProfesoresController {

    private UUID IDcurso;

    private String usuarioTemp;

    public void setIdCurso(UUID IDcurso) {
        this.IDcurso = IDcurso;
    }

    @FXML
    private Label usuarioProfe;

    @FXML
    private Label numIDProfe;

    @FXML
    private Label correoProfe;

    @FXML
    private Label nombreProfe;

    @FXML
    private Button btnAsignarProfeACurso;

    @FXML
    private Button btnEliminarProfeDeCurso;

    @FXML
    private Button btnBuscarProfeAAsignar;

    @FXML
    private Button btnRegresarCursoPrincipal;

    @FXML
    private Button btnVerProfesoresCurso;

    @FXML
    private Button btnVerTodosProfesores;

    @FXML
    private TableView<Profesor> tablaProfesores = new TableView<>();

    @FXML
    private TableColumn<Profesor, String> col_IDProfesor = new TableColumn<>("Usuario");

    @FXML
    private TableColumn<Profesor, String> col_NombreProfesor = new TableColumn<>("Nombre");

    @FXML
    private TableColumn<Profesor, String> col_CorreoProfesor = new TableColumn<>("Correo");

    @FXML
    private TextField txt_BuscarUsuarioProfesor;

    @FXML
    private Label labelUsername;

    @FXML
    void asignarProfeACurso(ActionEvent event) {
        IGlobalController.controladorGeneral.asignarCursosAProfesores(usuarioTemp,IDcurso);
        IGlobalController.controladorGeneral.asignarProfesoresACurso(IDcurso,usuarioTemp);
        txt_BuscarUsuarioProfesor.clear();
        AlertUtils.alertInformation("Profesor Asignado", "Gestión Profesor", "El profesor ha sido asignado con exito");
        renderProfesoresCurso();
    }

    @FXML
    void buscarProfeAAsignar(ActionEvent event) {
        if(IGlobalController.controladorGeneral.getProfesorFacade().getProfesorBusiness().getControladorProfesor().getListaProfes().containsKey(txt_BuscarUsuarioProfesor.getText())) {
            if(IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().getListaCursos().get(IDcurso).getProfesoresPertenecenCurso().containsKey(txt_BuscarUsuarioProfesor.getText())){
                btnEliminarProfeDeCurso.setDisable(false);
                btnAsignarProfeACurso.setDisable(true);
            }else {
                btnEliminarProfeDeCurso.setDisable(true);
                btnAsignarProfeACurso.setDisable(false);
            }

            Profesor profesor = IGlobalController.controladorGeneral.getProfesorFacade().getProfesorBusiness().getControladorProfesor().getListaProfes().get(txt_BuscarUsuarioProfesor.getText());
            usuarioProfe.setText(profesor.getUsuario());
            nombreProfe.setText(profesor.getNombre());
            usuarioTemp = profesor.getUsuario();
            correoProfe.setText(profesor.getCorreo());
            numIDProfe.setText(String.valueOf(profesor.getCedulaProfe()));
        }else{
            AlertUtils.alertError("Error al buscar profesor", "Error de gestión profesor", "El usuario proporcionado no se encuentra, revise la información suministrada y vuelva a intentarlo");
        }
    }

    @FXML
    void eliminarProfeDeCurso(ActionEvent event) {
        IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().getListaCursos().get(IDcurso).getProfesoresPertenecenCurso().remove(usuarioTemp);
        IGlobalController.controladorGeneral.getProfesorFacade().getProfesorBusiness().getControladorProfesor().getListaProfes().get(usuarioTemp).getCursosPertenecenAProfesor().remove(IDcurso);
        txt_BuscarUsuarioProfesor.clear();
        AlertUtils.alertInformation("Profesor eliminado", "Gestion profesor", "El profesor ha sido eliminado con exito,");
        renderProfesoresCurso();
    }

    @FXML
    void renderProfesoresCurso(ActionEvent event) {
        labelUsername.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        ClearWindow();

        for(Profesor pr : IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().buscarCurso(IDcurso).getProfesoresPertenecenCurso().values()){
            tablaProfesores.getItems().add(pr);
        }
    }

    public void renderProfesoresCurso() {
        labelUsername.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        ClearWindow();

        for(Profesor pr : IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().buscarCurso(IDcurso).getProfesoresPertenecenCurso().values()){
            tablaProfesores.getItems().add(pr);
        }
    }

    @FXML
    void renderTodosProfesores(ActionEvent event) {
        labelUsername.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        ClearWindow();
        labelUsername.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());

        for(Profesor pr : IGlobalController.controladorGeneral.getProfesorFacade().getProfesorBusiness().getControladorProfesor().getListaProfes().values()){
            tablaProfesores.getItems().add(pr);
        }
    }

    public void ClearWindow(){
        tablaProfesores.getItems().removeAll();
        tablaProfesores.getItems().clear();
    }

    @FXML
    void llenarCamposProfesor(MouseEvent event) {
        Profesor profesor = tablaProfesores.getSelectionModel().getSelectedItem();
        usuarioProfe.setText(profesor.getUsuario());
        nombreProfe.setText(profesor.getNombre());
        usuarioTemp = profesor.getUsuario();
        correoProfe.setText(profesor.getCorreo());
        numIDProfe.setText(String.valueOf(profesor.getCedulaProfe()));

        if(IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().buscarCurso(IDcurso).getProfesoresPertenecenCurso().containsKey(profesor.getUsuario())){
            btnAsignarProfeACurso.setDisable(true);
            btnEliminarProfeDeCurso.setDisable(false);
        }else{
            btnAsignarProfeACurso.setDisable(false);
            btnEliminarProfeDeCurso.setDisable(true);
        }
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
