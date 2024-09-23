package com.smarfat.webapp.gym.controller.FXController;

    import java.net.URL;
    import java.util.ResourceBundle;
    
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Component;

    import com.smarfat.webapp.gym.model.Usuario;
    import com.smarfat.webapp.gym.service.UsuarioService;
    import com.smarfat.webapp.gym.system.Main;
    import com.smarfat.webapp.gym.util.GymAlertas;
    import com.smarfat.webapp.gym.util.PasswordUtils;

    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.Initializable;
    import javafx.scene.control.Button;
    import javafx.scene.control.PasswordField;
    import javafx.scene.control.TextField;
    import lombok.Setter;
    
    @Component
    public class MenuInicioSesionController implements Initializable {
        
        @Setter
        private Main stage;
        private int op = 0;

        @Autowired
        UsuarioService usuarioService;
    
        @FXML
        PasswordField tfContra;
        
        @FXML
        TextField tfUsuario;
        
        @FXML
        Button btnIniciar, btnRegistrar;
        
        
        @FXML
        private void handleButtonAction(ActionEvent event) {
            
            
            if(event.getSource() == btnRegistrar){
                    stage.menuRegistroView();
            }else if(!tfUsuario.getText().equals("") && !tfContra.getText().equals("")){
                if(event.getSource() == btnIniciar){
                    Usuario usuario = usuarioService.buscarUsuario(tfUsuario.getText());
                    if(op == 0){
                        if(usuario != null){
                            if(PasswordUtils.getIntance().checkPassword(tfContra.getText(), usuario.getContrasenia())){
                                if(usuario.getNivelAccesoId() == 1){
                                    btnRegistrar.setDisable(false);
                                    btnIniciar.setText("IR AL MENU");
                                    op = 33;
                                }else if(usuario.getNivelAccesoId() == 2){                 
                                    stage.menuPrincipalView(); 
                                    GymAlertas.getInstance().alertaSaludo(usuario.getUsuario());
                                }
                            }else{
                                GymAlertas.getInstance().mostrarAlertasInformacion(888);
                            }
                            
                        }else{
                            GymAlertas.getInstance().mostrarAlertasInformacion(5);
                        }
                    }else{
                        stage.menuPrincipalView();
                        GymAlertas.getInstance().alertaSaludo(usuario.getUsuario());
                    }

                }
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

    }    
    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }

}