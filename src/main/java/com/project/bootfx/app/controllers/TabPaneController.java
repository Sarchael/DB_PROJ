package com.project.bootfx.app.controllers;

import javafx.event.Event;
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

    @Autowired
    private DodajSamochodTabController dodajSamochodTabController;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabWyszukajKlienta;

    @FXML
    private Tab tabDodajKlienta;

    @FXML
    private Tab tabDodajSamochod;

    @FXML
    private Tab tabNaprawy;

    @FXML
    public void initialize() throws Exception{

        tabPane.getStylesheets().add("test.css");
        tabPane.getStyleClass().add("tab-pane");
        tabDodajKlienta.getStyleClass().add("single-tab");
        tabDodajSamochod.getStyleClass().add("single-tab");
        tabWyszukajKlienta.getStyleClass().add("single-tab");
        tabNaprawy.getStyleClass().add("single-tab");

        AnchorPane anchorPane;

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(context::getBean);
        fxmlLoader.setLocation(getClass().getResource("/WyszukajKlienta.fxml"));
        anchorPane = fxmlLoader.load();
        anchorPane.getStyleClass().add("background");
        tabWyszukajKlienta.setContent(anchorPane);

        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(context::getBean);
        fxmlLoader.setLocation(getClass().getResource("/DodajKlienta.fxml"));
        anchorPane = fxmlLoader.load();
        anchorPane.getStyleClass().add("background");
        tabDodajKlienta.setContent(anchorPane);

        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(context::getBean);
        fxmlLoader.setLocation(getClass().getResource("/DodajSamochod.fxml"));
        anchorPane = fxmlLoader.load();
        anchorPane.getStyleClass().add("background");
        tabDodajSamochod.setContent(anchorPane);

        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(context::getBean);
        fxmlLoader.setLocation(getClass().getResource("/Naprawy.fxml"));
        anchorPane = fxmlLoader.load();
        anchorPane.getStyleClass().add("background");
        tabNaprawy.setContent(anchorPane);
    }

    public void onSamochodTabSelected(Event event) {
        dodajSamochodTabController.updateKlienci();
    }

    public Tab getTabNaprawy() {
        return tabNaprawy;
    }
}
