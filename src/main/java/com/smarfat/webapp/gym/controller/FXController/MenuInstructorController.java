package com.smarfat.webapp.gym.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smarfat.webapp.gym.model.Instructor;
import com.smarfat.webapp.gym.service.InstructorService;
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
public class MenuInstructorController implements Initializable {

    @Setter
    Main stage;

    @FXML
    TextField tfId;
    @FXML
    TextField tfNombre;
    @FXML
    TextField tfEspecialidad;
    @FXML
    TextField tfSueldo;
    @FXML
    TextField tfTelefono;
    @FXML
    TableView<Instructor> tblInstructores;
    @FXML
    TableColumn<Instructor, Long> colId;
    @FXML
    TableColumn<Instructor, String> colNombre;
    @FXML
    TableColumn<Instructor, String> colEspecialidad; 
    @FXML
    TableColumn<Instructor, Double> colSueldo; 
    @FXML
    Button btnGuardar;
    @FXML
    Button btnVaciar;
    @FXML
    Button btnRegresar;
    @FXML
    Button btnEliminar;
    @FXML
    Button btnBuscar; 

    @Autowired
    InstructorService instructorService;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        cargarDatos();
    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnGuardar) {
            if (tfId.getText().isBlank()) {
                if (!tfNombre.getText().isBlank() && !tfEspecialidad.getText().isBlank() && !tfSueldo.getText().isBlank()) {
                    agregarInstructor();
                    GymAlertas.getInstance().mostrarAlertasInformacion(400); 
                } else {
                    mostrarAlertasYFoco();
                }
            } else {
                if (!tfNombre.getText().isBlank() && !tfEspecialidad.getText().isBlank() && !tfSueldo.getText().isBlank()) {
                    if (GymAlertas.getInstance().mostrarAlertaConfirmacion(505).get() == ButtonType.OK) {
                        editarInstructor();
                        GymAlertas.getInstance().mostrarAlertasInformacion(500);
                    }
                } else {
                    mostrarAlertasYFoco();
                }
            }
        } else if (event.getSource() == btnEliminar) {
            if (GymAlertas.getInstance().mostrarAlertaConfirmacion(404).get() == ButtonType.OK) {
                eliminarInstructor();
                limpiarTextField();
            }
        } else if (event.getSource() == btnVaciar) {
            limpiarTextField();
        } else if (event.getSource() == btnRegresar) {
            stage.menuPrincipalView();
        } else if (event.getSource() == btnBuscar) {
            buscarInstructor();
        }
    }

    void mostrarAlertasYFoco() {
        GymAlertas.getInstance().mostrarAlertasInformacion(33);
        if (tfNombre.getText().isBlank()) {
            tfNombre.requestFocus();
        } else if (tfEspecialidad.getText().isBlank()) {
            tfEspecialidad.requestFocus();
        } else if (tfSueldo.getText().isBlank()) {
            tfSueldo.requestFocus();
        }
    }

    void cargarDatos() {
        tblInstructores.getItems().clear();
        tblInstructores.setItems(listarInstructores());
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreInstructor"));
        colEspecialidad.setCellValueFactory(new PropertyValueFactory<>("especialidadInstructor"));
        colSueldo.setCellValueFactory(new PropertyValueFactory<>("sueldoInstructor"));
    }

    @FXML
    public void cargarTextField() {
        Instructor instructor = tblInstructores.getSelectionModel().getSelectedItem();
        if (instructor != null) {
            tfId.setText(Long.toString(instructor.getId()));
            tfNombre.setText(instructor.getNombreInstructor());
            tfEspecialidad.setText(instructor.getEspecialidadInstructor());
            tfSueldo.setText(Double.toString(instructor.getSueldoInstructor()));
            tfTelefono.setText(instructor.getTelefonoInstructor());
        }
    }

    void limpiarTextField() {
        tfId.clear();
        tfNombre.clear();
        tfEspecialidad.clear();
        tfSueldo.clear();
        tfTelefono.clear();
    }

    ObservableList<Instructor> listarInstructores() {
        return FXCollections.observableArrayList(instructorService.listarInstructor());
    }

    public void agregarInstructor() {
        Instructor instructor = new Instructor();
        instructor.setNombreInstructor(tfNombre.getText());
        instructor.setEspecialidadInstructor(tfEspecialidad.getText());
        instructor.setSueldoInstructor(Double.parseDouble(tfSueldo.getText()));
        instructor.setTelefonoInstructor(tfTelefono.getText());
        instructorService.guardarInstructor(instructor);
        cargarDatos();
    }

    public void editarInstructor() {
        Instructor instructor = instructorService.buscarInstructorPorId(Long.parseLong(tfId.getText()));
        instructor.setNombreInstructor(tfNombre.getText());
        instructor.setEspecialidadInstructor(tfEspecialidad.getText());
        instructor.setSueldoInstructor(Double.parseDouble(tfSueldo.getText()));
        instructor.setTelefonoInstructor(tfTelefono.getText()); // Añadir teléfono
        instructorService.guardarInstructor(instructor);
        cargarDatos();
    }

    public void eliminarInstructor() {
        Instructor instructor = instructorService.buscarInstructorPorId(Long.parseLong(tfId.getText()));
        instructorService.eliminarInstructor(instructor);
        cargarDatos();
    }

    public void buscarInstructor() {
        try {
            long idBuscado = Long.parseLong(tfId.getText());
            Instructor instructor = instructorService.buscarInstructorPorId(idBuscado);
            ObservableList<Instructor> instructoresFiltrados = FXCollections.observableArrayList();
            if (instructor != null) {
                instructoresFiltrados.add(instructor);
            }
            tblInstructores.setItems(instructoresFiltrados);
        } catch (NumberFormatException e) {
            GymAlertas.getInstance().mostrarAlertasInformacion(33);
        }
    }
}

