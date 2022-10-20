package view;

import interfaceProgram.Global.IGlobalController;
import interfaceProgram.IAsignacion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.Factory;
import model.Tarea;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;
import java.util.Date;

public class PantallaProfeGestionTareaCrearController {

    @FXML
    private TextField tituloTarea;

    @FXML
    private TextField notaMaxTarea;

    @FXML
    private TextField intruccionesTarea;

    @FXML
    private DatePicker fechaInicioTarea;

    @FXML
    private DatePicker fechaFinalTarea;

    @FXML
    private Button btnConfirmaRegistroTarea;

    @FXML
    private Button btnCancelarRegistroTarea;

    @FXML
    void cancelarRegistroTarea(ActionEvent event) throws IOException  {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaProfeGestionTarea.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage myStage = (Stage) this.btnCancelarRegistroTarea.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void registrarTarea(ActionEvent event) throws IOException {
        //IAsignacion tarea= Factory.construir("Tarea");
        //tarea
        IAsignacion tarea=Factory.construir("Tarea");

        //tarea.setTitulo(tituloTarea.getText());
        //tarea.setInstrucciones(intruccionesTarea.getText());
        LocalDate localDate = fechaInicioTarea.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        //tarea.setFechaInicio(date);
        LocalDate localDate2=fechaFinalTarea.getValue();
        Instant instant2=Instant.from(localDate2.atStartOfDay(ZoneId.systemDefault()));
        Date date2=Date.from(instant2);
        //tarea.setFechaCierre(date);

        tarea.crearAsignacion(tituloTarea.getText(),intruccionesTarea.getText(),Float.valueOf(notaMaxTarea.getText()),date,date2);
        tarea.formatFechasAString();
        IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().aniadirTarea(IGlobalController.controladorGeneral.actualCourse,tarea);
        System.out.println(IGlobalController.controladorGeneral.getCursoFacade().getCursoBusiness().getControlCursos().buscarCurso(IGlobalController.controladorGeneral.actualCourse).getAsignacionesCurso().get(tarea.getIdAsignacion()).getTitulo());
       // tituloTarea.getText();
        //PantallaProfeGestionTareaController pantallaProfeGestionTareaController = new PantallaProfeGestionTareaController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaProfeGestionTarea.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage myStage = (Stage) this.btnConfirmaRegistroTarea.getScene().getWindow();
        // pantallaProfeGestionTareaController.renderTablita();
        myStage.close();
    }

}
