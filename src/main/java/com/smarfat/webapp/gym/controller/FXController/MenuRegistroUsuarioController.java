package com.smarfat.webapp.gym.controller.FXController;

import java.util.ResourceBundle;
import java.net.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smarfat.webapp.gym.model.Instructor;
import com.smarfat.webapp.gym.model.Usuario;
import com.smarfat.webapp.gym.service.InstructorService;
import com.smarfat.webapp.gym.service.UsuarioService;
import com.smarfat.webapp.gym.system.Main;
import com.smarfat.webapp.gym.util.GymAlertas;
import com.smarfat.webapp.gym.util.PasswordUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lombok.Setter;

@Component
public class MenuRegistroUsuarioController implements Initializable{

    @Setter
    private Main stage;
    @FXML
    TextField tfUsuario,tfContra;
    
    @FXML
    ComboBox cmbIntructores,cmbNivelAcceso;
    
    @FXML
    Button btnRegistrar,btnRegresar,btnInstructor;

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    InstructorService instructorService;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnRegresar){
                stage.menuInicioSesionView();
        }else if(!tfUsuario.getText().equals("") && !tfContra.getText().equals("")){
            if(event.getSource() == btnRegistrar){
                agregarUsuario();
                GymAlertas.getInstance().mostrarAlertasInformacion(400);
                stage.menuInicioSesionView();
            }
        }else if(event.getSource() == btnInstructor){
                //stage.formEmpleadosView(3);
        }else{
            GymAlertas.getInstance().mostrarAlertasInformacion(33);
            if(tfUsuario.getText().equals("")){
                tfUsuario.requestFocus();
            }else if(tfContra.getText().equals("")){
                tfContra.requestFocus();
            }   
        }
        
        
        
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbIntructores.setItems(listarIntructores());
        cmbNivelAcceso.setItems(FXCollections.observableArrayList("1", "2"));
    }   

    public ObservableList<Instructor> listarIntructores(){
        return FXCollections.observableArrayList(instructorService.listarInstructor());
    }

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }

    public void agregarUsuario(){
        Usuario usuario = new Usuario();
        usuario.setUsuario(tfUsuario.getText());
        usuario.setContrasenia(PasswordUtils.getIntance().encrytedPassword(tfContra.getText()));
        usuario.setNivelAccesoId(Integer.parseInt((String) cmbNivelAcceso.getSelectionModel().getSelectedItem()));
        usuario.setInstructor((Instructor) cmbIntructores.getSelectionModel().getSelectedItem());
        usuarioService.guardarUsuario(usuario);

    }

}
