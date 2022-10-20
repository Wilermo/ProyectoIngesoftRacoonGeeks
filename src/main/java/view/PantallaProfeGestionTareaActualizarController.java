package view;

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
import model.Tarea;
import utils.AlertUtils;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class PantallaProfeGestionTareaActualizarController {

    IAsignacion tarea;

    public IAsignacion getTarea() {
        return tarea;
    }

    public void setTarea(IAsignacion tarea) {
        this.tarea = tarea;
    }

    @FXML
    private TextField tituloTarea;

    @FXML
    private TextField notaMaxTarea;

    @FXML
    private TextField instruccionesTarea;

    @FXML
    private DatePicker fechaInicioTarea;

    @FXML
    private DatePicker fechaCierreTarea;

    @FXML
    private Button btnConfirmaActualizarTarea;

    @FXML
    private Button btnCancelarActualizarTarea;

    @FXML
    void actualizarTarea(ActionEvent event)throws IOException {
        llenarCamposActualizacion(tituloTarea.getText(),instruccionesTarea.getText(),fechaInicioTarea,fechaCierreTarea,Float.valueOf(notaMaxTarea.getText()));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaProfeGestionTarea.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage myStage = (Stage) this.btnConfirmaActualizarTarea.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void cancelarActualizarTarea(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaProfeGestionTarea.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage myStage = (Stage) this.btnCancelarActualizarTarea.getScene().getWindow();
        myStage.close();
    }

    void llenarCamposActualizacion(String titulo, String instruccionesT, DatePicker fechaInicioTar, DatePicker fechaCierreTar, float calificacionMaximaTar ) {
        tarea.setTitulo(titulo);
        tarea.setInstrucciones(instruccionesT);
        LocalDate localDate = fechaInicioTar.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        tarea.setFechaInicio(date);
        localDate = fechaCierreTar.getValue();
        instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        date = Date.from(instant);
        tarea.setFechaCierre(date);
        tarea.formatFechasAString();
        tarea.setCalificacionMAX(calificacionMaximaTar);
        AlertUtils.alertConfirmation("Tarea actualizada", "Gestión tareas", "La tarea ha sido actualizada correctamente");
    }
}
