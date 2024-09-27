package com.smarfat.webapp.gym.controller.FXController;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smarfat.webapp.gym.model.Sesion;
import com.smarfat.webapp.gym.service.SesionService;
import com.smarfat.webapp.gym.system.Main;
import com.smarfat.webapp.gym.util.GymAlertas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

@Component
public class MenuSesionController implements Initializable {
    @Setter
    private Main stage;

    @FXML
    TextField tfId, tfEspecialidad, tfSesionId;
    @FXML
    TableView tblSesiones;
    @FXML
    TableColumn colId, colEspecialidad;
    @FXML
    Button btnGuardar, btnEliminar, btnLimpiar,btnBuscar,btnRegresar;

    @Autowired
    SesionService sesionService;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        cargarDatos();
    }

    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnGuardar) {
            if (tfId.getText().isBlank()) {
                if(!tfEspecialidad.getText().equals("")){
                    agregarSesion();
                    GymAlertas.getInstance().mostrarAlertasInformacion(400);
                }else{
                    GymAlertas.getInstance().mostrarAlertasInformacion(33);
                    if(tfEspecialidad.getText().equals("")){
                        tfEspecialidad.requestFocus();
                    }
                }
            } else {
                if(!tfEspecialidad.getText().equals("")){
                    if(GymAlertas.getInstance().mostrarAlertaConfirmacion(505).get() == ButtonType.OK){
                        editarSesion();
                        GymAlertas.getInstance().mostrarAlertasInformacion(500);
                    }
                }else{
                    GymAlertas.getInstance().mostrarAlertasInformacion(33);
                    if(tfEspecialidad.getText().equals("")){
                        tfEspecialidad.requestFocus();
                    }
                }
            }
        } else if (event.getSource() == btnEliminar) {
            if(GymAlertas.getInstance().mostrarAlertaConfirmacion(404).get() == ButtonType.OK){
                eliminarSesion();
            }
            
        } else if (event.getSource() == btnLimpiar) {
            limpiarTextField();
        } else if (event.getSource() == btnBuscar) {
            tblSesiones.getItems().clear();
            if (tfSesionId.getText().isBlank()) {
                cargarDatos();
            } else {
                buscarSesion();
            }
        } else if (event.getSource() == btnRegresar) {
            stage.menuPrincipalView();
        }
    }

    public void cargarDatos() {
        tblSesiones.getItems().clear();
        tblSesiones.setItems(listarSesiones());
        colId.setCellValueFactory(new PropertyValueFactory<Sesion, Long>("id"));
        colEspecialidad.setCellValueFactory(new PropertyValueFactory<Sesion, String>("especialidad"));
    }

    public ObservableList<Sesion> listarSesiones() {
        return FXCollections.observableArrayList(sesionService.listarSesiones());
    }

    public void cargarTextField() {
        Sesion sesion = (Sesion) tblSesiones.getSelectionModel().getSelectedItem();
        if (sesion != null) {
            tfId.setText(Long.toString(sesion.getId()));
            tfEspecialidad.setText(sesion.getEspecialidad());
        }
    }

    public void limpiarTextField() {
        tfId.clear();
        tfEspecialidad.clear();
    }

    public void agregarSesion() {
        Sesion sesion = new Sesion();
        sesion.setEspecialidad(tfEspecialidad.getText());
        
        sesionService.guardarSesion(sesion);
        cargarDatos();
    }
    
    public void editarSesion() {
        Sesion sesion = sesionService.buscarSesionPorId(Long.parseLong(tfId.getText()));
        sesion.setEspecialidad(tfEspecialidad.getText());
        
        sesionService.guardarSesion(sesion);
        cargarDatos();
    }
    
    

    public void eliminarSesion() {
        Sesion sesion = sesionService.buscarSesionPorId(Long.parseLong(tfId.getText()));
        sesionService.eliminarSesion(sesion);
        cargarDatos();
    }

    public void buscarSesion() {
        Sesion sesion = sesionService.buscarSesionPorId(Long.parseLong(tfSesionId.getText()));
            ObservableList<Sesion> resultadoBusqueda = FXCollections.observableArrayList(sesion);
            tblSesiones.setItems(resultadoBusqueda);
    }

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
}
