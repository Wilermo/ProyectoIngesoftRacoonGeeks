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
import model.*;
import utils.AlertUtils;

import java.io.IOException;
import java.util.UUID;

public class PantallaAdminGestionCursoEstudiantesController {

    private UUID idCurso;
    private String usuarioTemp;

    public void setIdCurso(UUID idCurso) {
        this.idCurso = idCurso;
    }

    @FXML
    private TableView<Estudiante> tablaEstudiantes=new TableView<>();

    @FXML
    private TableColumn<Estudiante, String> colIdestudiantes=new TableColumn<>("Usuario");

    @FXML
    private TableColumn<Estudiante, String> colNombresEstudiantes=new TableColumn<>("Nombre");

    @FXML
    private TableColumn<Estudiante, String> colCarreraEstudiante=new TableColumn<>("CarreraEstud");

    @FXML
    private TableColumn<Estudiante, String> colCorreoEstudiante=new TableColumn<>("Correo");

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
    private Button btnAsignarEstACurso;

    @FXML
    private Button btnEliminarEstDeCurso;

    @FXML
    private TextField getTextUsuarioEstudiante;

    @FXML
    private Button btnBuscarEstuAAsignar;

    @FXML
    private Button btnRegresarCursoPrincipal;

    @FXML
    private Button btnVerEstudiantesCurso;

    @FXML
    private Button btnVerTodosEstudiantes;

    @FXML
    void asignarEstACurso(ActionEvent event) {
        //Estudiante est=tablaEstudiantes.getSelectionModel().getSelectedItem();
        global.controladorGeneral.asignarCursosAEstudiante(usuarioTemp,idCurso);
        global.controladorGeneral.asignarEstudiantesACurso(idCurso, usuarioTemp);
        getTextUsuarioEstudiante.clear();
        AlertUtils.alertInformation("Estudiante Asignado", "Gestión Estudiante", "El estudiante ha sido asignado con éxito");
        renderEstudiantesCurso();
    }


    @FXML
    void buscarEstuAAsignar(ActionEvent event) {
        if(global.controladorGeneral.getControlEstu().getListaEstudiantes().containsKey(getTextUsuarioEstudiante.getText())) {
            if(global.controladorGeneral.getControlCursos().getListaCursos().get(idCurso).getEstudiantesPertenecenCurso().containsKey(getTextUsuarioEstudiante.getText())){
                btnEliminarEstDeCurso.setDisable(false);
                btnAsignarEstACurso.setDisable(true);
            }else {
                btnEliminarEstDeCurso.setDisable(true);
                btnAsignarEstACurso.setDisable(false);
            }
            Estudiante estudiante = global.controladorGeneral.getControlEstu().getListaEstudiantes().get(getTextUsuarioEstudiante.getText());
            usuarioEstu.setText(estudiante.getUsuario());
            usuarioTemp=estudiante.getUsuario();
            tipoEstu.setText(String.valueOf(TipoEstudiante.REGULAR));
            numIDEstu.setText(String.valueOf(estudiante.getDocumentoEstud()));
            carreraEstu.setText(estudiante.getCarreraEstud());
            correoEstu.setText(estudiante.getCorreo());
            nombreEstu.setText(estudiante.getNombre());
        }else{
            AlertUtils.alertError("Error al buscar estudiante", "Error de gestión estudiante", "El usuario proporcionado no se encuentra, revise la información suministrada y vuelva a intentarlo");
        }
    }

    @FXML
    void eliminarEstDeCurso(ActionEvent event) {
        global.controladorGeneral.getControlCursos().getListaCursos().get(idCurso).getEstudiantesPertenecenCurso().remove(usuarioTemp);
        global.controladorGeneral.getControlEstu().getListaEstudiantes().get(usuarioTemp).getCursosPertenecenAEstudiante().remove(idCurso);
        getTextUsuarioEstudiante.clear();
        AlertUtils.alertInformation("Estudiante Eliminado", "Gestión Estudiante", "El estudiante ha sido eliminado con éxito");
        renderEstudiantesCurso();
    }

    @FXML
    void llenarCamposEstudiante(MouseEvent event) {
        Estudiante est=tablaEstudiantes.getSelectionModel().getSelectedItem();
        usuarioEstu.setText(est.getUsuario());
        usuarioTemp=est.getUsuario();
        tipoEstu.setText(String.valueOf(TipoEstudiante.REGULAR));
        numIDEstu.setText(String.valueOf(est.getDocumentoEstud()));
        carreraEstu.setText(est.getCarreraEstud());
        correoEstu.setText(est.getCorreo());
        nombreEstu.setText(est.getNombre());
        if(global.controladorGeneral.getControlCursos().buscarCurso(idCurso).getEstudiantesPertenecenCurso().containsKey(est.getUsuario())){
            btnAsignarEstACurso.setDisable(true);
            btnEliminarEstDeCurso.setDisable(false);
        }else{
            btnAsignarEstACurso.setDisable(false);
            btnEliminarEstDeCurso.setDisable(true);
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

    @FXML
    void renderEstudiantesCurso(ActionEvent event) {
        clearWindowEstud();
        for(Estudiante est: global.controladorGeneral.getControlCursos().buscarCurso(idCurso).getEstudiantesPertenecenCurso().values()){
            if(est instanceof Monitor){
                Estudiante estudiante=new Estudiante(est.getUsuario(),est.getContrasenna(),est.getNombre(),est.getCorreo(),est.getCarreraEstud(),est.getDocumentoEstud(), TipoGeneral.ESTUDIANTE,est.getCursosPertenecenAEstudiante());
                tablaEstudiantes.getItems().add(estudiante);
            }else{
                tablaEstudiantes.getItems().add(est);
            }

        }

        //tablaEstudiantes.getItems().addAll((Estudiante) global.controladorGeneral.getControlCursos().buscarCurso(idCurso).getEstudiantesPertenecenCurso().values());
    }

    void clearWindowEstud(){
        tablaEstudiantes.getItems().removeAll();
        tablaEstudiantes.getItems().clear();
    }

    @FXML
    void renderTodosEstudiantes(ActionEvent event) {
        clearWindowEstud();
        for(Estudiante est: global.controladorGeneral.getControlEstu().getListaEstudiantes().values()){
            if(est instanceof Monitor){
                Estudiante estudiante=new Estudiante(est.getUsuario(),est.getContrasenna(),est.getNombre(),est.getCorreo(),est.getCarreraEstud(),est.getDocumentoEstud(), TipoGeneral.ESTUDIANTE,est.getCursosPertenecenAEstudiante());
                tablaEstudiantes.getItems().add(estudiante);
            }else{
                tablaEstudiantes.getItems().add(est);
            }

        }
        //tablaEstudiantes.getItems().addAll((Estudiante) global.controladorGeneral.getControlEstu().getListaEstudiantes().values());
    }

    void renderEstudiantesCurso() {
        clearWindowEstud();
        for(Estudiante est: global.controladorGeneral.getControlCursos().buscarCurso(idCurso).getEstudiantesPertenecenCurso().values()){
            if(est instanceof Monitor){
                Estudiante estudiante=new Estudiante(est.getUsuario(),est.getContrasenna(),est.getNombre(),est.getCorreo(),est.getCarreraEstud(),est.getDocumentoEstud(), TipoGeneral.ESTUDIANTE,est.getCursosPertenecenAEstudiante());
                tablaEstudiantes.getItems().add(estudiante);
            }else{
                tablaEstudiantes.getItems().add(est);
            }

        }

        //tablaEstudiantes.getItems().addAll((Estudiante) global.controladorGeneral.getControlCursos().buscarCurso(idCurso).getEstudiantesPertenecenCurso().values());
    }

}
