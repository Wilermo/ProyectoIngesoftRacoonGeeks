package view;

import interfaceProgram.Global.IGlobalController;
import interfaceProgram.IAsignacion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Curso;
import model.Profesor;
import model.Respuesta;
import model.Tarea;
import utils.AlertUtils;
import javafx.scene.control.*;

import javax.sound.midi.Soundbank;
import java.awt.*;
import java.io.IOException;
import java.util.UUID;

public class PantallaProfeGestionTareaRespuestasController {

    private UUID curso;
    private IAsignacion asignacion;

    public IAsignacion getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(IAsignacion asignacion) {
        this.asignacion = asignacion;
    }

    public UUID getCurso() {
        return curso;
    }

    public void setCurso(UUID curso) {
        this.curso = curso;
    }

    @FXML
    private Button btnRegresar;

    @FXML
    private TableView<Respuesta> tablaRespuestasProfe;

    @FXML
    private Label labelUsername;

    @FXML
    private Label nombreUsuario;

    @FXML
    private TextArea contenidoField;

    @FXML
    private Button btnCalificar;

    @FXML
    private Button btnAsignarNota;

    @FXML
    private Text nuevaNotaText;

    @FXML
    private TextField fieldNewNote;

    @FXML
    void asignarNota(ActionEvent event) {
        if(fieldNewNote.getText()!=null){
            IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().getListaCursos().get(curso).getAsignacionesCurso().get(asignacion.getIdAsignacion()).getListaRespuestas().get(tablaRespuestasProfe.getSelectionModel().getSelectedItem().getUsuario()).setNotaRespuesta(Double.valueOf(fieldNewNote.getText()));
        }
        AlertUtils.alertInformation("Nota asignada","Gestión tareas","La nota a la respuesta ha sido asignada con éxito");
        renderWindowTablita(asignacion.getIdAsignacion());
    }

    @FXML
    void calificar(ActionEvent event) {
        btnAsignarNota.setDisable(false);
        btnAsignarNota.setVisible(true);
        nuevaNotaText.setVisible(true);
        fieldNewNote.setVisible(true);
    }

    @FXML
    public void renderWindowTablita(UUID idTarea) {
        labelUsername.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        limpiarVentana();
        labelUsername.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        Curso cur=IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().buscarCurso(IGlobalController.controladorGeneral.actualCourse);
        if(cur!=null) {
            for (IAsignacion asignacion : cur.getAsignacionesCurso().values()) {
                if (asignacion.getIdAsignacion().equals(idTarea)) {
                    for (Respuesta respuesta: ((Tarea) asignacion).getListaRespuestas().values()) {
                        System.out.println(respuesta.getContenidoRespuesta());
                        tablaRespuestasProfe.getItems().add(respuesta);
                    }
                }
            }
        }
    }

    @FXML
    public void limpiarVentana(){
        tablaRespuestasProfe.getItems().removeAll();
        tablaRespuestasProfe.getItems().clear();
    }

    @FXML
    public void llenarCampos(MouseEvent event){
        btnCalificar.setDisable(false);
        nombreUsuario.setText(tablaRespuestasProfe.getSelectionModel().getSelectedItem().getUsuario());
        contenidoField.setText(tablaRespuestasProfe.getSelectionModel().getSelectedItem().getContenidoRespuesta());
    }

    @FXML
    void salir(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaProfeGestionTarea.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage myStage = (Stage) this.btnRegresar.getScene().getWindow();
        myStage.close();
    }

}
