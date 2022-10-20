package view;

import interfaceProgram.Global.IGlobalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Profesor;
import utils.AlertUtils;

import java.io.IOException;

public class PantallaAdminGestionProfesorController {

    @FXML
    private Button btnActualizarProfe;

    @FXML
    private Button btnEliminarProfe;

    @FXML
    private Button btnRegistrarProfe;

    @FXML
    private Button btnRegresarMenuAdmin;

    @FXML
    private Label usuarioProfe;

    @FXML
    private Label numIDProfe;

    @FXML
    private Label correoProfe;

    @FXML
    private Label nombreProfe;

    @FXML
    private Button btnVerCursosDelProfesor;

    @FXML
    private TableView<Profesor> tablaProfes;

    @FXML
    private Label labelUsername;

    @FXML
    void llenarCamposProfe(MouseEvent event) {
        btnActualizarProfe.setDisable(false);
        btnEliminarProfe.setDisable(false);
        btnVerCursosDelProfesor.setDisable(false);
        Profesor profe = tablaProfes.getSelectionModel().getSelectedItem();
        nombreProfe.setText(String.valueOf(profe.getNombre()));
        usuarioProfe.setText(String.valueOf(profe.getUsuario()));
        numIDProfe.setText(String.valueOf(profe.getCedulaProfe()));
        correoProfe.setText(String.valueOf(profe.getCorreo()));
    }

    @FXML
    void cursosDelProfesor(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaAdminGestionProfesorCursos.fxml"));
        Parent root = loader.load();
        PantallaAdminGestionProfesorCursosController pantallaAdminGestionProfesorCursosController = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        pantallaAdminGestionProfesorCursosController.renderWindowTablita(usuarioProfe.getText());
        stage.setOnCloseRequest(e-> {
            try {
                pantallaAdminGestionProfesorCursosController.salir(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        Stage myStage = (Stage) this.btnRegistrarProfe.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void eliminarProfe(ActionEvent event) {
        Profesor profe = tablaProfes.getSelectionModel().getSelectedItem();
        IGlobalController.controladorGeneral.getProfesorFacade().getProfesorBusiness().getControladorProfesor().eliminarProfesor(profe);
        AlertUtils.alertConfirmation("Profe eliminado", "Confirmación eliminacion", "El profe se elimino con exito");
        renderWindowProfe();
    }

    @FXML
    void renderWindowProfe() {
        labelUsername.setText(IGlobalController.controladorGeneral.actualUser.getUsuario());
        limpiarVentana();
        for(Profesor prof : IGlobalController.controladorGeneral.getProfesorFacade().getProfesorBusiness().getControladorProfesor().getListaProfes().values()){
            tablaProfes.getItems().add(prof);
        }
        btnActualizarProfe.setDisable(true);
        btnEliminarProfe.setDisable(true);
        btnVerCursosDelProfesor.setDisable(true);
    }

    @FXML
    public void limpiarVentana(){
        tablaProfes.getItems().removeAll();
        tablaProfes.getItems().clear();
    }

    @FXML
    void registrarProfe(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaAdminRegistrarProfesor.fxml"));
        Parent root = loader.load();
        PantallaAdminRegistrarProfesorController pantallaRegistrarProfesorController = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e-> {
            try {
                pantallaRegistrarProfesorController.cancelarRegistroProfe(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        Stage myStage = (Stage) this.btnRegistrarProfe.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void actualizarProfe(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaAdminActualizarProfesor.fxml"));
        Parent root = loader.load();
        PantallaAdminActualizarProfesorController pantallaActualizarProfesorController = loader.getController();
        pantallaActualizarProfesorController.llenarCampo(usuarioProfe.getText(), numIDProfe.getText(), correoProfe.getText(), nombreProfe.getText());
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e-> {
            try {
                pantallaActualizarProfesorController.cancelarActualizarProfe(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        Stage myStage = (Stage) this.btnRegistrarProfe.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void regresarMenuAdmin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaPrincipalAdmin.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage myStage = (Stage) this.btnRegresarMenuAdmin.getScene().getWindow();
        myStage.close();
    }


}
