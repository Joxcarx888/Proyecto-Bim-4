package com.smarfat.webapp.gym.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smarfat.webapp.gym.model.Maquina;
import com.smarfat.webapp.gym.service.MaquinaService;
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
public class MenuMaquinasController implements Initializable{

    @Setter
    private Main stage;

    @FXML
    TextField tfId, tfMarca, tfEspecialidad, tfBuscar;
    @FXML
    TableView tblMaquinas;
    @FXML
    TableColumn colId, colMarca, colEspecialidad;
    @FXML
    Button btnGuardar, btnVaciar, btnRegresar, btnEliminar, btnBuscar;

    @Autowired
    MaquinaService maquinaService;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
       cargarDatos();
    }

    public void handleButtonAction(ActionEvent event) {
    if (event.getSource() == btnGuardar) {
        if (tfId.getText().isBlank()) {
            if (!tfMarca.getText().isBlank() && !tfEspecialidad.getText().isBlank()) {
                agregarMaquina();
                GymAlertas.getInstance().mostrarAlertasInformacion(400); 
            } else {
                GymAlertas.getInstance().mostrarAlertasInformacion(33);
                if (tfMarca.getText().isBlank()) {
                    tfMarca.requestFocus();
                } else if (tfEspecialidad.getText().isBlank()) {
                    tfEspecialidad.requestFocus();
                }
            }
        } else {
            if (!tfMarca.getText().isBlank() && !tfEspecialidad.getText().isBlank()) {
                if (GymAlertas.getInstance().mostrarAlertaConfirmacion(505).get() == ButtonType.OK) {
                    editarCategoria();
                    GymAlertas.getInstance().mostrarAlertasInformacion(500);
                }
            } else {
                GymAlertas.getInstance().mostrarAlertasInformacion(33);
                if (tfMarca.getText().isBlank()) {
                    tfMarca.requestFocus();
                } else if (tfEspecialidad.getText().isBlank()) {
                    tfEspecialidad.requestFocus();
                }
            }
        }
    } else if (event.getSource() == btnEliminar) {
        if (GymAlertas.getInstance().mostrarAlertaConfirmacion(404).get() == ButtonType.OK) {
            eliminarMaquina();
            limpiarTextFiled();
        }
    } else if (event.getSource() == btnBuscar) {
        tblMaquinas.getItems().clear();
        if (tfBuscar.getText().isBlank()) {
            cargarDatos();
        } else {
            buscarMaquina();    
        }
    }else if (event.getSource() == btnVaciar) {
        limpiarTextFiled();
    } else if (event.getSource() == btnRegresar) {
        stage.menuPrincipalView();
    }
}


    public void cargarDatos(){
        tblMaquinas.getItems().clear();
        tblMaquinas.setItems(listarMaquinas());
        colId.setCellValueFactory(new PropertyValueFactory<Maquina, Long>("id"));
        colMarca.setCellValueFactory(new PropertyValueFactory<Maquina, String>("marca"));
        colEspecialidad.setCellValueFactory(new PropertyValueFactory<Maquina, String>("especialidad"));
    }

    public void cargarTextField(){
        Maquina maquina = (Maquina)tblMaquinas.getSelectionModel().getSelectedItem();
        if (maquina != null) {
            tfId.setText(Long.toString(maquina.getId()));
            tfMarca.setText(maquina.getMarca());
            tfEspecialidad.setText(maquina.getEspecialidad());
        }
    }

    public void limpiarTextFiled(){
        tfId.clear();
        tfMarca.clear();
        tfEspecialidad.clear();
    }

    public ObservableList<Maquina> listarMaquinas(){
        return FXCollections.observableArrayList(maquinaService.listarMaquinas());
    }

    public void agregarMaquina(){
        Maquina maquina = new Maquina();
        maquina.setMarca(tfMarca.getText());
        maquina.setEspecialidad(tfEspecialidad.getText());
        maquinaService.guardarMaquina(maquina);
        cargarDatos();
    }
    
    public void editarCategoria(){

        Maquina maquina = maquinaService.buscarMaquinaPorId(Long.parseLong(tfId.getText()));
        maquina.setMarca(tfMarca.getText());
        maquina.setEspecialidad(tfEspecialidad.getText());
        maquinaService.guardarMaquina(maquina);
        cargarDatos();
    }

    public void eliminarMaquina(){
        Maquina maquina = maquinaService.buscarMaquinaPorId(Long.parseLong(tfId.getText()));
        maquinaService.eliminarMaquina(maquina);
        cargarDatos();
    }

    public void buscarMaquina(){
        Maquina maquina = maquinaService.buscarMaquinaPorId(Long.parseLong(tfBuscar.getText()));
            ObservableList<Maquina> resultadoBusqueda = FXCollections.observableArrayList(maquina);
            tblMaquinas.setItems(resultadoBusqueda);

    }

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
}