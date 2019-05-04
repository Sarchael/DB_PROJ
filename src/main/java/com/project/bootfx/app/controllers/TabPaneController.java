package com.project.bootfx.app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class TabPaneController {

    @Autowired
    private ApplicationContext context;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabWyszukajKlienta;

    @FXML
    private Tab tabDodajKlienta;

    @FXML
    private Tab tabDodajSamochod;

    @FXML
    public void initialize() throws Exception{
        AnchorPane anchorPane;

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(context::getBean);
        fxmlLoader.setLocation(getClass().getResource("/WyszukajKlienta.fxml"));
        anchorPane = fxmlLoader.load();
        tabWyszukajKlienta.setContent(anchorPane);

        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(context::getBean);
        fxmlLoader.setLocation(getClass().getResource("/DodajKlienta.fxml"));
        anchorPane = fxmlLoader.load();
        tabDodajKlienta.setContent(anchorPane);

        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(context::getBean);
        fxmlLoader.setLocation(getClass().getResource("/DodajSamochod.fxml"));
        anchorPane = fxmlLoader.load();
        tabDodajSamochod.setContent(anchorPane);
    }
}
