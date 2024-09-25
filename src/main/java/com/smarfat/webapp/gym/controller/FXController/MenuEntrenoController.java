package com.smarfat.webapp.gym.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smarfat.webapp.gym.model.Cliente;
import com.smarfat.webapp.gym.model.Entreno;
import com.smarfat.webapp.gym.model.Instructor;
import com.smarfat.webapp.gym.model.Maquina;
import com.smarfat.webapp.gym.model.Membresia;
import com.smarfat.webapp.gym.model.Sesion;
import com.smarfat.webapp.gym.service.MembresiaService;
import com.smarfat.webapp.gym.service.EntrenoService;
import com.smarfat.webapp.gym.service.InstructorService;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

@Component
public class MenuEntrenoController implements Initializable{
    @Setter
    private Main stage;

    @FXML
    TextField tfId, tfEntrenoId;
    @FXML
    ComboBox cmbIntructores,cmbSesiones;
    @FXML
    TableView tblEntrenos,tblClientes,tblMaquinas;
    @FXML
    TableColumn colId, colInstructorId, colSesionId,colMaquinaId,colClienteId,colMarca,colEspecialidad;
    @FXML
    Button btnGuardar, btnVaciar, btnRegresar, btnEliminar,btnModificarClientes,btnModificarMaquinas,btnLimpiar,btnBuscar;

    @Autowired
    MembresiaService membresiaService;
    
    @Autowired
    EntrenoService entrenoService; 

    @Autowired
    InstructorService instructorService; 

    @Autowired
    SesionService sesionService; 


    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnGuardar) {
            if (tfId.getText().isBlank()) {
                if (cmbIntructores.getValue() != null && cmbSesiones.getValue() != null) {
                    agregarEntreno();
                    GymAlertas.getInstance().mostrarAlertasInformacion(400);
                } else {
                    GymAlertas.getInstance().mostrarAlertasInformacion(33);
                }
            } else {
                if (cmbIntructores.getValue() != null && cmbSesiones.getValue() != null) {
                    if (GymAlertas.getInstance().mostrarAlertaConfirmacion(505).get() == ButtonType.OK) {
                        editarEntreno();
                        GymAlertas.getInstance().mostrarAlertasInformacion(500);
                    }
                } else {
                    GymAlertas.getInstance().mostrarAlertasInformacion(33);
                }
            }
        } else if (event.getSource() == btnEliminar) {
            if (GymAlertas.getInstance().mostrarAlertaConfirmacion(404).get() == ButtonType.OK) {
                eliminarEntreno();
                limpiarTextField();
            }
        } else if (event.getSource() == btnVaciar) {
            limpiarTextField();
        } else if (event.getSource() == btnRegresar) {
            stage.menuPrincipalView();
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbIntructores.setItems(listarIntructores());
        cmbSesiones.setItems(listarSesiones());
        cargarDatos();
    }


    public void limpiarTextField() {
        tfId.clear();
        tfEntrenoId.clear();
    }

    public void cargarTextField() {
        Entreno entreno = (Entreno) tblEntrenos.getSelectionModel().getSelectedItem();
        if (entreno != null) {
            tfId.setText(Long.toString(entreno.getId()));
        }
    }


    public void cargarDatos() {
        tblEntrenos.getItems().clear();
        tblEntrenos.setItems(listarEntrenos());
        colId.setCellValueFactory(new PropertyValueFactory<Entreno, Long>("id"));
        colInstructorId.setCellValueFactory(new PropertyValueFactory<Entreno, String>("instructor"));
        colSesionId.setCellValueFactory(new PropertyValueFactory<Entreno, String>("sesion"));

    }
    
    public void agregarEntreno() {
        Entreno entreno = new Entreno();
        entreno.setInstructor((Instructor) cmbIntructores.getValue());
        entreno.setSesion((Sesion) cmbSesiones.getValue());
        entrenoService.guardarEntreno(entreno);
        cargarDatos();
    }

    public void editarEntreno() {
        Entreno entreno = entrenoService.buscarEntrenoPorId(Long.parseLong(tfId.getText()));
        entreno.setInstructor((Instructor) cmbIntructores.getValue());
        entreno.setSesion((Sesion) cmbSesiones.getValue());
        
        entrenoService.guardarEntreno(entreno);
        cargarDatos();
    }

    public void eliminarEntreno() {
        Entreno entreno = entrenoService.buscarEntrenoPorId(Long.parseLong(tfId.getText()));
        entrenoService.eliminarEntreno(entreno);
        cargarDatos();
    }

    public void buscarEntreno() {
        Entreno entreno = entrenoService.buscarEntrenoPorId(Long.parseLong(tfEntrenoId.getText()));
            ObservableList<Entreno> resultadoBusqueda = FXCollections.observableArrayList(entreno);
            tblEntrenos.setItems(resultadoBusqueda);
    }



    public ObservableList<Instructor> listarIntructores(){
        return FXCollections.observableArrayList(instructorService.listarInstructor());
    }

    public ObservableList<Entreno> listarEntrenos(){
        return FXCollections.observableArrayList(entrenoService.listarEntrenos());
    }

    public ObservableList<Sesion> listarSesiones() {
        return FXCollections.observableArrayList(sesionService.listarSesiones());
    }
    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }

 
   
    
}

