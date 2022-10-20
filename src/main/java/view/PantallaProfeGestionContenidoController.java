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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Asignacion;
import model.Contenido;
import model.Curso;
import model.Factory;
import utils.AlertUtils;

import java.io.Console;
import java.io.IOException;
import java.util.UUID;

public class PantallaProfeGestionContenidoController {

    @FXML
    private TableView<IAsignacion> tablaContenido=new TableView<>();

    @FXML
    private TableColumn<Contenido, UUID> colId=new TableColumn<>("IdAsignacion");

    @FXML
    private TableColumn<Contenido, String> colTitulo=new TableColumn<>("Titulo");

    @FXML
    private TableColumn<Contenido, String> colInstrucciones=new TableColumn<>("Instrucciones");

    @FXML
    private Label tituloContenido;

    @FXML
    private Label instruccionesContenido;

    @FXML
    private TextField textTituloCrear;

    @FXML
    private TextArea textInstruccionesCrear;

    @FXML
    private Button btnRegistrarContenido;

    @FXML
    private Button btnActualizarContenido;

    @FXML
    private Button btnEliminarContenido;

    @FXML
    private GridPane grIdActualizar;

    @FXML
    private TextField textTituloActualizar;

    @FXML
    private TextArea textInstruccionesActualz;

    @FXML
    private Button btnConfirmarCambios;

    @FXML
    private Button btnRegresarMenuProfe;

    @FXML
    private Label labelUsername;

    @FXML
    private Button btnVerContenidos;

    @FXML
    void btnConfirmarCambios(ActionEvent event) {
        btnConfirmarCambios.setVisible(false);
        grIdActualizar.setVisible(false);
        try {
            tablaContenido.getSelectionModel().getSelectedItem().setTitulo(textTituloActualizar.getText());
            tablaContenido.getSelectionModel().getSelectedItem().setInstrucciones(textInstruccionesActualz.getText());
            AlertUtils.alertInformation("Contenido actualizado","Gestión contenidos","El contenido ha sido actualizado correctamente");
        }catch (Exception ex){
            AlertUtils.alertError("Error al actualizar contenido","Gestión contenidos","El contenido no pudo ser actualizado, asegúrese de llenar toda la información correctamente e inténtelo de nuevo");
            ex.printStackTrace();
        }

        renderWindowContenidos();
    }

    @FXML
    void actualizarContenido(ActionEvent event) {
        grIdActualizar.setVisible(true);
        btnConfirmarCambios.setVisible(true);
    }

    @FXML
    void eliminarContenido(ActionEvent event) {
        IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().buscarCurso(IGlobalController.controladorGeneral.actualCourse).getAsignacionesCurso().remove(tablaContenido.getSelectionModel().getSelectedItem().getIdAsignacion());
        AlertUtils.alertInformation("Contenido eliminado","Gestión contenidos","El contenido ha sido eliminado correctamente");
        renderWindowContenidos();
    }

    @FXML
    void llenarCamposContenido(MouseEvent event) {
        tituloContenido.setText(tablaContenido.getSelectionModel().getSelectedItem().getTitulo());
        instruccionesContenido.setText(tablaContenido.getSelectionModel().getSelectedItem().getInstrucciones());
        btnActualizarContenido.setDisable(false);
        btnEliminarContenido.setDisable(false);
    }

    @FXML
    void registrarContenido(ActionEvent event) throws IOException {
        IAsignacion contenido= Factory.construir("Contenido");
       // Contenido contenido=new Contenido();
        if(!textTituloCrear.getText().equals("")&&!textInstruccionesCrear.getText().equals("")){
            contenido.crearAsignacion(textTituloCrear.getText(),textInstruccionesCrear.getText(),0,null,null);

            System.out.println("TITULO:"+contenido.getTitulo());

            IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().buscarCurso(IGlobalController.controladorGeneral.actualCourse).getAsignacionesCurso().put(contenido.getIdAsignacion(),contenido);
            AlertUtils.alertInformation("Contenido creado","Gestión contenidos","El contenido ha sido creado correctamente");
        }else{
            AlertUtils.alertError("Error al crear contenido", "Gestión contenidos","El contenido no se ha podido crear, revise la información suministrada e inténtelo nuevamente");
        }

        renderWindowContenidos();
    }

    @FXML
    void renderWindowContenidos(ActionEvent event) {
        labelUsername.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());

        clearWindow();
        btnActualizarContenido.setDisable(true);
        btnEliminarContenido.setDisable(true);
        Curso cur= IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().buscarCurso(IGlobalController.controladorGeneral.actualCourse);
        if(cur!=null){
            for(IAsignacion asig: cur.getAsignacionesCurso().values()){
                if(asig instanceof Contenido){
                    tablaContenido.getItems().add((Contenido) asig);
                }
            }
        }
    }

    void renderWindowContenidos() {
        labelUsername.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());

        clearWindow();
        Curso cur = IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().buscarCurso(IGlobalController.controladorGeneral.actualCourse);
        if (cur != null) {
            for (IAsignacion asig : cur.getAsignacionesCurso().values()) {
                if (asig instanceof Contenido) {
                    tablaContenido.getItems().add((Contenido) asig);
                }
            }
        }
    }

    void clearWindow(){
        tablaContenido.getItems().removeAll();
        tablaContenido.getItems().clear();
    }


    @FXML
    void regresarMenuProfe(ActionEvent event) throws IOException  {
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



}
