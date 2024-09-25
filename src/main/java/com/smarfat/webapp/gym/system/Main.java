package com.smarfat.webapp.gym.system;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.smarfat.webapp.gym.GymApplication;
import com.smarfat.webapp.gym.controller.FXController.MenuEntrenoController;
import com.smarfat.webapp.gym.controller.FXController.MenuInicioSesionController;
import com.smarfat.webapp.gym.controller.FXController.MenuMaquinasController;
import com.smarfat.webapp.gym.controller.FXController.MenuMembresiaController;
import com.smarfat.webapp.gym.controller.FXController.MenuPrincipalController;
import com.smarfat.webapp.gym.controller.FXController.MenuRegistroUsuarioController;
import com.smarfat.webapp.gym.controller.FXController.MenuSedeController;
import com.smarfat.webapp.gym.controller.FXController.MenuSesionController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage stage;
    private Scene scene;

    private ApplicationContext applicationContext;  // Variable para almacenar el contexto de Spring

    @Override
    public void init() {
        this.applicationContext = new SpringApplicationBuilder(GymApplication.class).run();
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        stage.setTitle("GYM SmartFat");
        menuRegistroView();
        stage.show();
    }

    public Initializable cambiarEscena(String fxmlName, int width, int height) throws IOException {
        Initializable initializable = null;
        FXMLLoader loader = new FXMLLoader();

        loader.setControllerFactory(applicationContext::getBean);
        InputStream archivo = Main.class.getResourceAsStream("/templates/" + fxmlName);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource("/templates/" + fxmlName));

        scene = new Scene((AnchorPane) loader.load(archivo), width, height);

        stage.setScene(scene);
        stage.sizeToScene();

        initializable = (Initializable) loader.getController();

        return initializable;
    }

    public void menuInicioSesionView() {
        try {
            MenuInicioSesionController menuInicioView = (MenuInicioSesionController) cambiarEscena("MenuInicioSesionView.fxml", 450, 600);
            menuInicioView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void menuRegistroView() {
        try {
            MenuRegistroUsuarioController menuRegistroView = (MenuRegistroUsuarioController) cambiarEscena("MenuRegistroUsuario.fxml", 500, 650);
            menuRegistroView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void menuPrincipalView() {
        try {
            MenuPrincipalController menuPrincipalView = (MenuPrincipalController) cambiarEscena("MenuPrincipalView.fxml", 950, 700);
            menuPrincipalView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void menuMembresiasView() {
        try {
            MenuMembresiaController menuMembresiaView = (MenuMembresiaController) cambiarEscena("MenuMembresiaView.fxml", 600, 400);
            menuMembresiaView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void menuSedesView() {
        try {
            MenuSedeController menuSedesView = (MenuSedeController) cambiarEscena("MenuSedesView.fxml", 600, 400);
            menuSedesView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void menuSesionesView() {
        try {
            MenuSesionController menuSesionesView = (MenuSesionController) cambiarEscena("MenuSesionView.fxml", 600, 400);
            menuSesionesView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void menuMaquinasView(){
        try {
            MenuMaquinasController menuMaquinasView = (MenuMaquinasController) cambiarEscena("MenuMaquinasView.fxml", 900, 600);
            menuMaquinasView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void menuEntrenosView(){
        try {
            MenuEntrenoController menuEntrenosView = (MenuEntrenoController) cambiarEscena("MenuEntrenoView.fxml", 1000, 600);
            menuEntrenosView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
