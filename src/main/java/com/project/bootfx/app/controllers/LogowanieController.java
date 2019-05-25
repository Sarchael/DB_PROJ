package com.project.bootfx.app.controllers;

import com.project.bootfx.app.SpringBootFxApplication;
import com.project.bootfx.app.dao.DataAccessObject;
import com.project.bootfx.app.entity.Uzytkownik;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
public class LogowanieController {

    @FXML
    private TextField tfLogin;

    @FXML
    private PasswordField pfPassword;

    @Autowired
    private DataAccessObject dataAccessObject;

    @Autowired
    private ApplicationContext context;

    @FXML
    public void initialize(){

    }

    @FXML
    public void signIn() throws IOException {
        List<Uzytkownik> uzytkownicy = dataAccessObject.readAll(Uzytkownik.class);
        boolean isCorrect = true;

        if(tfLogin.getText().isEmpty()) {
            tfLogin.setPromptText("BLEDNY LOGIN!");
            isCorrect = false;
        }
        if(pfPassword.getText().isEmpty()) {
            pfPassword.setPromptText("BLEDNE HASLO!");
            isCorrect = false;
        }

        if(!isCorrect)
            return;

        isCorrect = false;

        for(Uzytkownik u : uzytkownicy)
            if(tfLogin.getText().equals(u.getLogin()) && pfPassword.getText().equals(u.getHaslo()))
                isCorrect = true;

        if(isCorrect){
            TabPane tabPane;
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(context::getBean);
            fxmlLoader.setLocation(getClass().getResource("/TabPane.fxml"));
            tabPane = fxmlLoader.load();
            SpringBootFxApplication.rootStage.setScene(new Scene(tabPane));
        }
        else{
            tfLogin.clear();
            tfLogin.setPromptText("NIEPOPRAWNE DANE!");
            pfPassword.clear();
            pfPassword.setPromptText("NIEPOPRAWNE DANE!");
        }
    }
}
