package com.smarfat.webapp.gym.controller.FXController;

import com.smarfat.webapp.gym.system.Main;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ButtonType;
import com.smarfat.webapp.gym.util.GymAlertas;

@Component
public class MenuPrincipalController implements Initializable {
    
    private Main stage;
    
    @FXML
    private Button btnCerrarSesion, btnAgregarEntrenos, btnVerEntrenos;
    
    @FXML
    private MenuItem btnClientes, btnInstructores, btnMaquinas, btnSesiones, btnSedes, btnMembresias, btnEntrenos;
    
    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnClientes) {
            stage.menuClientesView();
        } else if (event.getSource() == btnInstructores) {
            stage.menuInstructoresView();
        } else if (event.getSource() == btnMaquinas) {
            stage.menuMaquinasView();
        } else if (event.getSource() == btnSesiones) {
            stage.menuSesionesView();
        } else if (event.getSource() == btnSedes) {
            stage.menuSedesView();
        } else if (event.getSource() == btnMembresias) {
            stage.menuMembresiasView();
        } else if (event.getSource() == btnEntrenos) {
            //stage.menuEntrenosView();
        } else if (event.getSource() == btnCerrarSesion) {
            if (GymAlertas.getInstance().mostrarAlertaConfirmacion(606).get() == ButtonType.OK) {
                stage.menuInicioSesionView();
            }
        } else if (event.getSource() == btnAgregarEntrenos) {
            //stage.menuAgregarEntrenosView();
        } else if (event.getSource() == btnVerEntrenos) {
            stage.menuEntrenosView();
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
}
