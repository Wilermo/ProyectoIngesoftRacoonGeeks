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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Curso;
import model.Estudiante;
import model.Respuesta;
import model.Tarea;
import utils.AlertUtils;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public class PantallaEstudianteRegularTareaController {
    private Curso curso;
    private Estudiante estudiante;

    @FXML
    private Button btnCerrarSeionEstu;

    @FXML
    private Button btnEliminarRta;

    @FXML
    private Button btnSubirRta;

    @FXML
    private Button btnVerRta;

    @FXML
    private Button btnVerTareas;

    @FXML
    private TableColumn<Tarea, Date> columnaFechaCierre;

    @FXML
    private TableColumn<Tarea, Date> columnaFechaInicio;

    @FXML
    private TableColumn<Tarea, UUID> columnaID;

    @FXML
    private TableColumn<Tarea, String> columnaTitulo;

    @FXML
    private Label fechaCierreTarea;

    @FXML
    private Label fechaInicioTarea;

    @FXML
    private Label instruccionesTarea;

    @FXML
    private Label labelUsername;

    @FXML
    private Label notaMaxTarea;

    @FXML
    private TableView<Tarea> tablaTareas;

    @FXML
    private Label tituloTarea;

    @FXML
    private GridPane camposRespuesta;

    @FXML
    private TextArea contenidoRespuesta;

    @FXML
    private TextField comentario;

    @FXML
    private Label usuario;

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    @FXML
    void llenarCamposEnTareaCurso(MouseEvent event) {
        tituloTarea.setText(tablaTareas.getSelectionModel().getSelectedItem().getTitulo());
        instruccionesTarea.setText(tablaTareas.getSelectionModel().getSelectedItem().getInstrucciones());
        fechaInicioTarea.setText(tablaTareas.getSelectionModel().getSelectedItem().getFechaInicio().toString());
        fechaCierreTarea.setText(tablaTareas.getSelectionModel().getSelectedItem().getFechaCierre().toString());
        notaMaxTarea.setText(String.valueOf(tablaTareas.getSelectionModel().getSelectedItem().getCalificacionMAX()));

        btnVerRta.setDisable(false);
        btnSubirRta.setDisable(false);
        btnEliminarRta.setDisable(false);

        usuario.setText(estudiante.getUsuario());
    }

    @FXML
    void renderInicial() {
        renderWindowTareas();
    }

    @FXML
    void renderWindowTareas() {
        labelUsername.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        clearWindowCursos();
        btnVerRta.setDisable(true);
        btnSubirRta.setDisable(true);
        btnEliminarRta.setDisable(true);
        for (IAsignacion asignacion: curso.getAsignacionesCurso().values()){
            if ( asignacion instanceof Tarea) {
                tablaTareas.getItems().add((Tarea) asignacion);
            }
        }
    }

    @FXML
    void renderWindowTareas(ActionEvent event) {
        labelUsername.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        clearWindowCursos();
        btnVerRta.setDisable(true);
        btnSubirRta.setDisable(true);
        btnEliminarRta.setDisable(true);
        for (IAsignacion asignacion: curso.getAsignacionesCurso().values()){
            if (asignacion instanceof Tarea) {
                tablaTareas.getItems().add((Tarea) asignacion);
            }
        }
    }

    public void clearWindowCursos() {
        tablaTareas.getItems().removeAll();
        tablaTareas.getItems().clear();
        tituloTarea.setText("");
        instruccionesTarea.setText("");
        fechaInicioTarea.setText("");
        fechaCierreTarea.setText("");
        notaMaxTarea.setText("");
        usuario.setText("");
        comentario.setText("");
        contenidoRespuesta.setText("");
    }

    @FXML
    void subirRta(ActionEvent event) {
        Respuesta respuesta=new Respuesta();
        respuesta.setComentario(comentario.getText());
        respuesta.setContenidoRespuesta(contenidoRespuesta.getText());
        respuesta.setUsuario(usuario.getText());
        respuesta.setNotaRespuesta(0.0);
        if (respuesta.getUsuario() != null && respuesta.getComentario() != null && respuesta.getContenidoRespuesta() != null){
            IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().getListaCursos().get(curso.getIdCurso()).getAsignacionesCurso().get(tablaTareas.getSelectionModel().getSelectedItem().getIdAsignacion()).getListaRespuestas().put(respuesta.getUsuario(),respuesta);

            AlertUtils.alertConfirmation("Tarea cargada", "Tarea cargada :) 😎", "Tarea cargada exitosamente");
        }



       /* Tarea tarea = tablaTareas.getSelectionModel().getSelectedItem();
        Respuesta respuesta = new Respuesta();
        tarea.getListaRespuestas().put(usuario.getText(), respuesta);
        if (respuesta.getUsuario() != null && respuesta.getComentario() != null && respuesta.getContenidoRespuesta() != null){
            tarea.getListaRespuestas().put(usuario.getText(), respuesta);
            AlertUtils.alertConfirmation("Tarea cargada", "Tarea cargada :) 😎", "Tarea cargada exitosamente");
        }
        clearWindowCursos();*/
    }

    @FXML
    void verRta(ActionEvent event) {
        Tarea tarea = tablaTareas.getSelectionModel().getSelectedItem();
        for (Respuesta res: tarea.getListaRespuestas().values()) {
            if (usuario.getText().equals(res.getUsuario())) {
                usuario.setText(res.getUsuario());
                comentario.setText(res.getComentario());
                contenidoRespuesta.setText(res.getContenidoRespuesta());
            }
        }
    }

    @FXML
    void eliminarRta(ActionEvent event) {
        Tarea tarea = tablaTareas.getSelectionModel().getSelectedItem();
        for (Respuesta res: tarea.getListaRespuestas().values()) {
            if (usuario.getText().equals(res.getUsuario())) {
                usuario.setText(res.getUsuario());
                comentario.setText(res.getComentario());
                contenidoRespuesta.setText(res.getContenidoRespuesta());
                tarea.getListaRespuestas().remove(res.getUsuario());
                AlertUtils.alertConfirmation("Respuesta Eliminada", "Respuesta Eliminada ༼ つ ◕_◕ ༽つ", "La Respuesta ha sido Eliminada ");
                clearWindowCursos();
            }
        }
    }

    @FXML
    void cerrarSeionEstu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaEstudianteRegular.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage myStage = (Stage) this.btnCerrarSeionEstu.getScene().getWindow();
        myStage.close();
    }


}
