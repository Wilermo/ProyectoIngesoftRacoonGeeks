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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Contenido;
import model.Curso;

import java.io.IOException;
import java.util.UUID;

public class PantallaEstudianteRegularContenidoController {
    private Curso curso;

    @FXML
    private Button btnCerrarSeionEstu;

    @FXML
    private Button btnVerContenido;

    @FXML
    private TableColumn<Contenido, UUID> columnaID;

    @FXML
    private TableColumn<Contenido, String> columnaTitulo;

    @FXML
    private TextArea contenidoEst;

    @FXML
    private Label labelUsername;

    @FXML
    private Label numIDContenido;

    @FXML
    private TableView<Contenido> tablaContenidos;

    @FXML
    private Label tituloContenido;

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @FXML
    void renderInicial() {
        renderWindowContenido();
    }

    @FXML
    void llenarCamposEnContenidoCurso(MouseEvent event) {
        numIDContenido.setText(tablaContenidos.getSelectionModel().getSelectedItem().getIdAsignacion().toString());
        tituloContenido.setText(tablaContenidos.getSelectionModel().getSelectedItem().getTitulo());
        contenidoEst.setText(tablaContenidos.getSelectionModel().getSelectedItem().getInstrucciones());
    }

    @FXML
    void renderWindowContenido(ActionEvent event) {
        labelUsername.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        clearWindowCursos();
        for (IAsignacion cont : curso.getAsignacionesCurso().values()){
            if (cont instanceof Contenido){
                tablaContenidos.getItems().add((Contenido) cont);
            }
        }
    }

    @FXML
    void renderWindowContenido() {
        labelUsername.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        clearWindowCursos();
        for (IAsignacion cont : curso.getAsignacionesCurso().values()){
            if (cont instanceof Contenido){
                tablaContenidos.getItems().add((Contenido) cont);
            }
        }
    }

    public void clearWindowCursos() {
        tablaContenidos.getItems().removeAll();
        tablaContenidos.getItems().clear();
        numIDContenido.setText("");
        tituloContenido.setText("");
        contenidoEst.setText("");
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
