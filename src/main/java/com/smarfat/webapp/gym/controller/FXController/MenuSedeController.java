package com.smarfat.webapp.gym.controller.FXController;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smarfat.webapp.gym.model.Sede;
import com.smarfat.webapp.gym.service.SedeService;
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
public class MenuSedeController implements Initializable {
    @Setter
    private Main stage;

    @FXML
    TextField tfId, tfDireccion, tfSedeId;
    @FXML
    TableView tblSedes;
    @FXML
    TableColumn colId, colDireccion;
    @FXML
    Button btnGuardar, btnEliminar, btnLimpiar,btnBuscar,btnRegresar;

    @Autowired
    SedeService sedeService;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        cargarDatos();
    }

    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnGuardar) {
            if (tfId.getText().isBlank()) {
                if(!tfDireccion.getText().equals("")){
                    agregarSede();
                    GymAlertas.getInstance().mostrarAlertasInformacion(400);
                }else{
                    GymAlertas.getInstance().mostrarAlertasInformacion(33);
                    if(tfDireccion.getText().equals("")){
                        tfDireccion.requestFocus();
                    }
                }
            } else {
                if(!tfDireccion.getText().equals("")){
                    if(GymAlertas.getInstance().mostrarAlertaConfirmacion(505).get() == ButtonType.OK){
                        editarSede();
                        GymAlertas.getInstance().mostrarAlertasInformacion(500);
                    }
                }else{
                    GymAlertas.getInstance().mostrarAlertasInformacion(33);
                    if(tfDireccion.getText().equals("")){
                        tfDireccion.requestFocus();
                    }
                }
            }
        } else if (event.getSource() == btnEliminar) {
            if(GymAlertas.getInstance().mostrarAlertaConfirmacion(404).get() == ButtonType.OK){
                eliminarSede();
            }
            
        } else if (event.getSource() == btnLimpiar) {
            limpiarTextField();
        } else if (event.getSource() == btnBuscar) {
            tblSedes.getItems().clear();
            if (tfSedeId.getText().isBlank()) {
                cargarDatos();
            } else {
                buscarSede();
            }
        } else if (event.getSource() == btnRegresar) {
            stage.menuPrincipalView();
        }
    }

    public void cargarDatos() {
        tblSedes.getItems().clear();
        tblSedes.setItems(listarSedes());
        colId.setCellValueFactory(new PropertyValueFactory<Sede, Long>("id"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<Sede, String>("direccion"));
    }

    public ObservableList<Sede> listarSedes() {
        return FXCollections.observableArrayList(sedeService.listarSedes());
    }

    public void cargarTextField() {
        Sede sede = (Sede) tblSedes.getSelectionModel().getSelectedItem();
        if (sede != null) {
            tfId.setText(Long.toString(sede.getId()));
            tfDireccion.setText(sede.getDireccion());
        }
    }

    public void limpiarTextField() {
        tfId.clear();
        tfDireccion.clear();
    }

    public void agregarSede() {
        Sede sede = new Sede();
        sede.setDireccion(tfDireccion.getText());
        
        sedeService.guardarSede(sede);
        cargarDatos();
    }
    
    public void editarSede() {
        Sede sede = sedeService.buscarSedePorId(Long.parseLong(tfId.getText()));
        sede.setDireccion(tfDireccion.getText());
        
        sedeService.guardarSede(sede);
        cargarDatos();
    }
    
    

    public void eliminarSede() {
        Sede sede = sedeService.buscarSedePorId(Long.parseLong(tfId.getText()));
        sedeService.eliminarSede(sede);
        cargarDatos();
    }

    public void buscarSede() {
        Sede sede = sedeService.buscarSedePorId(Long.parseLong(tfSedeId.getText()));
            ObservableList<Sede> resultadoBusqueda = FXCollections.observableArrayList(sede);
            tblSedes.setItems(resultadoBusqueda);
    }

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
}
