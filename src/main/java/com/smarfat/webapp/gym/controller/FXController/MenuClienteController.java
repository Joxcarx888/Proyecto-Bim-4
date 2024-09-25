package com.smarfat.webapp.gym.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smarfat.webapp.gym.model.Cliente;
import com.smarfat.webapp.gym.model.Instructor;
import com.smarfat.webapp.gym.service.MembresiaService;
import com.smarfat.webapp.gym.model.Membresia;
import com.smarfat.webapp.gym.service.ClienteService;
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
public class MenuClienteController implements Initializable {
    @Setter
    private Main stage;

    @FXML
    TextField tfId,tfDpi,tfNombre,tfTelefono,tfClientesId;
    @FXML
    ComboBox cmbMembresia;
    @FXML
    TableView tblClientes;
    @FXML
    TableColumn colId, colDpi,colNombre,colTelefono,colMembresia;
    @FXML
    Button btnGuardar, btnEliminar, btnLimpiar,btnBuscar,btnRegresar;

    @Autowired
    ClienteService clienteService;
    @Autowired
    MembresiaService membresiaService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbMembresia.setItems(listarMembresias());
        cargarDatos();
    }
        public void handleButtonAction(ActionEvent event) {
            if (event.getSource() == btnGuardar) {
                if(tfId.getText().isBlank()){
                    if (!tfDpi.getText().equals("")&& !tfNombre.getText().equals("")&& !tfTelefono.getText().equals("") ) {
                        agregarCliente();
                        GymAlertas.getInstance().mostrarAlertasInformacion(400);
                    }else{
                        GymAlertas.getInstance().mostrarAlertasInformacion(33);
                    }
                }else{
                    if (!tfDpi.getText().equals("")&& !tfNombre.getText().equals("")&& !tfTelefono.getText().equals("") ) {
                        if (GymAlertas.getInstance().mostrarAlertaConfirmacion(505).get() == ButtonType.OK) {
                            editarCliente();
                            GymAlertas.getInstance().mostrarAlertasInformacion(500);
                        }
                    }else{
                        GymAlertas.getInstance().mostrarAlertasInformacion(33);
                        if (tfDpi.getText().equals("")&& tfNombre.getText().equals("")&& tfTelefono.getText().equals("")) {
                            tfDpi.requestFocus();
                            tfNombre.requestFocus();
                            tfTelefono.requestFocus();
                        }
                    }
                }
            }else if (event.getSource() == btnEliminar) {
                if (GymAlertas.getInstance().mostrarAlertaConfirmacion(404).get() == ButtonType.OK) {
                    eliminarCliente();
                    limpiarTextField();
                }
            }else if (event.getSource() == btnLimpiar) {
                limpiarTextField();
            }else if (event.getSource() == btnRegresar) {
                stage.menuPrincipalView();
            }else if (event.getSource() == btnBuscar) {
                tblClientes.getItems().clear();
                if (tfClientesId.getText().isBlank()) {
                    cargarDatos();
                }else{
                    buscarCliente();
                }
            }
        }
        public void cargarTextField(){
            Cliente cliente = (Cliente)tblClientes.getSelectionModel().getSelectedItem();
            if (cliente !=null) {
                tfId.setText(Long.toString(cliente.getId()));
                tfNombre.setText(cliente.getNombre());
                tfDpi.setText(cliente.getDpi());
                tfTelefono.setText(cliente.getTelefono());
            }
        }


    public void cargarDatos(){
        tblClientes.getItems().clear();
        tblClientes.setItems(listarClientes());
        colId.setCellValueFactory(new PropertyValueFactory<Cliente,Long>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nombre"));
        colDpi.setCellValueFactory(new PropertyValueFactory<Cliente,String>("dpi"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Cliente,String>("telefono"));
        colMembresia.setCellValueFactory(new PropertyValueFactory<Cliente,Membresia>("membresia"));
        
    }

    public ObservableList<Cliente> listarClientes(){
        return FXCollections.observableArrayList(clienteService.listarClientes());
    }

    public void limpiarTextField() {
        tfId.clear();
        tfDpi.clear();
        tfNombre.clear();
        tfTelefono.clear();
        cmbMembresia.getSelectionModel().clearSelection();
    }

    public void agregarCliente(){
        Cliente cliente = new Cliente();
        cliente.setDpi(tfDpi.getText());
        cliente.setNombre(tfNombre.getText());
        cliente.setTelefono(tfTelefono.getText());
        cliente.setMembresia((Membresia) cmbMembresia.getSelectionModel().getSelectedItem());
        clienteService.guardarCliente(cliente);
        cargarDatos();
    }

    public void editarCliente(){
        Cliente cliente = clienteService.buscarCliente(Long.parseLong(tfId.getText()));
        cliente.setNombre(tfNombre.getText());
        cliente.setDpi(tfDpi.getText());
        cliente.setTelefono(tfTelefono.getText());
        clienteService.guardarCliente(cliente);
        cargarDatos();
    }

    public void eliminarCliente(){
        Cliente cliente = clienteService.buscarCliente(Long.parseLong(tfId.getText()));
        clienteService.eliminarCliente(cliente);
        cargarDatos();
    }

    public void buscarCliente(){
        Cliente cliente = clienteService.buscarCliente(Long.parseLong(tfClientesId.getText()));
        ObservableList<Cliente> resultadosBusqueda = FXCollections.observableArrayList(cliente);
        tblClientes.setItems(resultadosBusqueda);
    }       

    public ObservableList<Membresia> listarMembresias(){
        return FXCollections.observableArrayList(membresiaService.listarMembresias());
    }
}