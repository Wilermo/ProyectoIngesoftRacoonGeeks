package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class PantallaEstudianteController {

    private InicioSesionAppController inicioSesionAppController;
    private Stage stage;

    @FXML
    private Label text_NombreUsuario;

    @FXML
    private Button btn_CerrarSesionEstudiante;

    @FXML
    private Tab tab_Actividades;

    @FXML
    private TableView<?> tableView_Actividades;

    @FXML
    private TableColumn<?, ?> col_Actividad;

    @FXML
    private TableColumn<?, ?> col_FechaEntregaACT;

    @FXML
    private Tab tab_Actividades1;

    @FXML
    private TableView<?> tableView_Examenes;

    @FXML
    private TableColumn<?, ?> col_Examenes;

    @FXML
    private TableColumn<?, ?> col_FechaEntregaEXA;

    @FXML
    private Tab tab_Notas;

    @FXML
    private TableView<?> tableView_Notas_Actividad;

    @FXML
    private TableColumn<?, ?> col_Actividad_Notas;

    @FXML
    private TableColumn<?, ?> col_Notas_Actividad;

    @FXML
    private TableView<?> tableView_Notas_Examenes;

    @FXML
    private TableColumn<?, ?> col_Examen_Nombre;

    @FXML
    private TableColumn<?, ?> col_Examen_Nota;

    @FXML
    private PieChart grafica_Pie;

    @FXML
    private Tab tab_Calendario;

    @FXML
    private ChoiceBox<?> chBox_listaMaterias;

    @FXML
    void cerrarSesionEstudiante(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaPrincipalAdmin.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage myStage = (Stage) this.btn_CerrarSesionEstudiante.getScene().getWindow();
        myStage.close();
    }

    public void closeWindowsPrueba() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaPrincipalAdmin.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage myStage = (Stage) this.btn_CerrarSesionEstudiante.getScene().getWindow();
        myStage.close();
    }

    /*public void init(String text, Stage stage, InicioSesionAppController inicioSesionAppController) {
        this.inicioSesionAppController = inicioSesionAppController;
        this.stage = stage;
    }*/



}
