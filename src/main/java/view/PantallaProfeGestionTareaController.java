package view;

import interfaceProgram.Global.IGlobalController;
import interfaceProgram.IAsignacion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Asignacion;
import model.Curso;
import model.Tarea;
import utils.AlertUtils;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public class PantallaProfeGestionTareaController {

    private UUID curso;

    public UUID getCurso() {
        return curso;
    }

    public void setCurso(UUID curso) {
        this.curso = curso;
    }

    @FXML
    private TableView<IAsignacion> tablaTarea=new TableView<>();

    @FXML
    private TableColumn<Tarea, UUID> colId=new TableColumn<>("IdAsignacion");

    @FXML
    private TableColumn<Tarea, String> colTitulo = new TableColumn<>("Titulo");

    @FXML
    private TableColumn<Tarea, String> colFechaInicio=new TableColumn<>("FechaStringInicio");

    @FXML
    private TableColumn<Tarea, String> colFechaFinal= new TableColumn<>("FechaStringCierre");

    @FXML
    private TableColumn<Tarea,Float> colNota= new TableColumn<>("CalificacionMAX");

    @FXML
    private Label tituloTarea;

    @FXML
    private Label labelUsername;

    @FXML
    private Label notaMaxTarea;

    @FXML
    private Label instruccionesTarea;

    @FXML
    private Label fechaInicioTarea;

    @FXML
    private Label fechaFinalTarea;

    @FXML
    private Button btnRegistrarTarea;

    @FXML
    private Button btnActualizarTarea;

    @FXML
    private Button btnEliminarTarea;

    @FXML
    private Button btnRegresarMenuProfe;

    @FXML
    private Button btnVerRespuestas;

    public void renderTablita(){
        labelUsername.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());

        clearWindow();
        Curso cur = IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().getListaCursos().get(IGlobalController.controladorGeneral.actualCourse);
        if(cur!=null){
            for (IAsignacion asig : cur.getAsignacionesCurso().values()){
                if (asig instanceof Tarea){
                    System.out.println("ASIGN");
                    System.out.println(asig.toString());
                    tablaTarea.getItems().add(asig);
                }
            }
        }
    }

    public void clearWindow(){
        tablaTarea.getItems().removeAll();
        tablaTarea.getItems().clear();
    }

    @FXML
    void actualizarTarea(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaProfeGestionTareaActualizar.fxml"));
        Parent root = loader.load();
        PantallaProfeGestionTareaActualizarController pantallaProfeGestionTareaActualizarController = loader.getController();
        //pantallaProfeGestionTareaActualizarController.llenarCamposActualizacion(tituloTarea.getText(), instruccionesTarea.getText());
        pantallaProfeGestionTareaActualizarController.setTarea(tablaTarea.getSelectionModel().getSelectedItem());
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e-> {
            try {
                pantallaProfeGestionTareaActualizarController.cancelarActualizarTarea(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        Stage myStage = (Stage) this.btnActualizarTarea.getScene().getWindow();
        myStage.close();
        renderTablita();
    }

    @FXML
    void eliminarTarea(ActionEvent event) {
        IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().buscarCurso(IGlobalController.controladorGeneral.actualCourse).getAsignacionesCurso().remove(tablaTarea.getSelectionModel().getSelectedItem().getIdAsignacion());
        AlertUtils.alertInformation("Tarea eliminada","Gestión tareas","la tarea ha sido eliminada correctamente");
        renderTablita();
    }

    @FXML
    void llenarCamposTarea(MouseEvent event) {
        tituloTarea.setText(tablaTarea.getSelectionModel().getSelectedItem().getTitulo());
        instruccionesTarea.setText(tablaTarea.getSelectionModel().getSelectedItem().getInstrucciones());
        fechaInicioTarea.setText(tablaTarea.getSelectionModel().getSelectedItem().getFechaStringIncio());
        fechaFinalTarea.setText(tablaTarea.getSelectionModel().getSelectedItem().getFechaStringCierre());
        notaMaxTarea.setText(String.valueOf(tablaTarea.getSelectionModel().getSelectedItem().getCalificacionMAX()));
        btnActualizarTarea.setDisable(false);
        btnEliminarTarea.setDisable(false);
        btnVerRespuestas.setDisable(false);
    }

    @FXML
    void registrarTarea(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaProfeGestionTareaCrear.fxml"));
        Parent root = loader.load();
        PantallaProfeGestionTareaCrearController pantallaProfeGestionTareaCrearController = loader.getController();
        //pantallaProfeGestionTareaCrearController.renderWindowCursos();
        //pantallaProfeGestionTareaCrearController.setIdCurso(IGlobalController.controladorGeneral.actualCourse);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e-> {
            try {
                pantallaProfeGestionTareaCrearController.cancelarRegistroTarea(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        Stage myStage = (Stage) this.btnRegistrarTarea.getScene().getWindow();
        myStage.close();
       // AlertUtils.alertInformation("Tarea creada","Gestión tareas","la tarea ha sido creada correctamente");
    }

    @FXML
    void regresarMenuProfe(ActionEvent event) throws IOException {
        FXMLLoader loader ;
        if(!IGlobalController.controladorGeneral.esMonitor){
            loader= new FXMLLoader(getClass().getResource("PantallaPrincipalProfesor.fxml"));
        }else{
            loader= new FXMLLoader(getClass().getResource("PantallaEstudianteMonitor.fxml"));
        }

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage myStage = (Stage) this.btnRegresarMenuProfe.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void verRespuestas(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaProfeGestionTareaRespuestas.fxml"));
        Parent root = loader.load();
        PantallaProfeGestionTareaRespuestasController pantallaProfeGestionTareaRespuestasController = loader.getController();
        pantallaProfeGestionTareaRespuestasController.setAsignacion(tablaTarea.getSelectionModel().getSelectedItem());
        pantallaProfeGestionTareaRespuestasController.setCurso(curso);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        pantallaProfeGestionTareaRespuestasController.renderWindowTablita(tablaTarea.getSelectionModel().getSelectedItem().getIdAsignacion());
        stage.setOnCloseRequest(e-> {
            try {
                pantallaProfeGestionTareaRespuestasController.salir(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        Stage myStage = (Stage) this.btnVerRespuestas.getScene().getWindow();
        myStage.close();
    }

}
