package com.smarfat.webapp.gym.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smarfat.webapp.gym.model.Membresia;
import com.smarfat.webapp.gym.service.MembresiaService;
import com.smarfat.webapp.gym.system.Main;
import com.smarfat.webapp.gym.util.GymAlertas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

@Component
public class MenuMembresiaController implements Initializable {
    @Setter
    private Main stage;

    @FXML
    TextField tfId, tfDuracion,tfMembresiaId;
    @FXML
    ComboBox cmbVigencia;
    @FXML
    TableView tblMembresias;
    @FXML
    TableColumn colId, colDuracion,colVigencia;
    @FXML
    Button btnGuardar, btnEliminar, btnLimpiar,btnBuscar;

    @Autowired
    MembresiaService membresiaService;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        cargarDatos();
    }

    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnGuardar) {
            if (tfId.getText().isBlank()) {
                if(!tfDuracion.getText().equals("") && cmbVigencia.getValue() != null){
                    agregarMembresia();
                    GymAlertas.getInstance().mostrarAlertasInformacion(400);
                }else{
                    GymAlertas.getInstance().mostrarAlertasInformacion(33);
                    if(tfDuracion.getText().equals("")){
                        tfDuracion.requestFocus();
                    }
                }
            } else {
                if(!tfDuracion.getText().equals("") && cmbVigencia.getValue() != null){
                    if(GymAlertas.getInstance().mostrarAlertaConfirmacion(505).get() == ButtonType.OK){
                        editarMembresia();
                        GymAlertas.getInstance().mostrarAlertasInformacion(500);
                    }
                }else{
                    GymAlertas.getInstance().mostrarAlertasInformacion(33);
                    if(tfDuracion.getText().equals("")){
                        tfDuracion.requestFocus();
                    }
                }
            }
        } else if (event.getSource() == btnEliminar) {
            if(GymAlertas.getInstance().mostrarAlertaConfirmacion(404).get() == ButtonType.OK){
                eliminarMembresia();
            }
            
        } else if (event.getSource() == btnLimpiar) {
            limpiarTextField();
        } else if (event.getSource() == btnBuscar) {
            tblMembresias.getItems().clear();
            if (tfMembresiaId.getText().isBlank()) {
                cargarDatos();
            } else {
                buscarMembresia();
            }
        }
    }

    public void cargarDatos() {
        tblMembresias.getItems().clear();
        tblMembresias.setItems(listarMembresias());
        colId.setCellValueFactory(new PropertyValueFactory<Membresia, Long>("id"));
        colDuracion.setCellValueFactory(new PropertyValueFactory<Membresia, String>("duracion"));
        colVigencia.setCellValueFactory(new PropertyValueFactory<Membresia, String>("vigencia"));
        cmbVigencia.setItems(FXCollections.observableArrayList("Activa", "Inactiva"));
    }

    public ObservableList<Membresia> listarMembresias() {
        return FXCollections.observableArrayList(membresiaService.listarMembresias());
    }

    public void cargarTextField() {
        Membresia membresia = (Membresia) tblMembresias.getSelectionModel().getSelectedItem();
        if (membresia != null) {
            tfId.setText(Long.toString(membresia.getId()));
            tfDuracion.setText(String.valueOf(membresia.getDuracion()));
        }
    }

    public void limpiarTextField() {
        tfId.clear();
        tfDuracion.clear();
    }

    public void agregarMembresia() {
        Membresia membresia = new Membresia();
        membresia.setDuracion(Integer.parseInt(tfDuracion.getText()));
        
        if (cmbVigencia.getSelectionModel().getSelectedItem().equals("Activa")) {
            membresia.setVigencia(true); 
        } else {
            membresia.setVigencia(false); 
        }
    
        membresiaService.guardarMembresia(membresia);
        cargarDatos();
    }
    
    public void editarMembresia() {
        Membresia membresia = membresiaService.buscarMembresiaPorId(Long.parseLong(tfId.getText()));
        membresia.setDuracion(Integer.parseInt(tfDuracion.getText()));
        
        if (cmbVigencia.getSelectionModel().getSelectedItem().equals("Activa")) {
            membresia.setVigencia(true); 
        } else {
            membresia.setVigencia(false); 
        }
    
        membresiaService.guardarMembresia(membresia);
        cargarDatos();
    }
    
    

    public void eliminarMembresia() {
        Membresia membresia = membresiaService.buscarMembresiaPorId(Long.parseLong(tfId.getText()));
        membresiaService.eliminarMembresia(membresia);
        cargarDatos();
    }

    public void buscarMembresia() {
        Membresia membresia = membresiaService.buscarMembresiaPorId(Long.parseLong(tfMembresiaId.getText()));
            ObservableList<Membresia> resultadoBusqueda = FXCollections.observableArrayList(membresia);
            tblMembresias.setItems(resultadoBusqueda);
    }

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }

    
}

