package com.project.bootfx.app.controllers;

import com.project.bootfx.app.dao.IDataAccessObject;
import com.project.bootfx.app.entity.Klient;
import com.project.bootfx.app.entity.Model;
import com.project.bootfx.app.entity.Naprawa;
import com.project.bootfx.app.entity.Samochod;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class NaprawyTabController {

    @Autowired
    private IDataAccessObject dataAccessObject;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private TabPaneController tabPaneController;

    @FXML
    private ListView lvNaprawy;

    @FXML
    public void initialize() {
        List<String> wiersze = new ArrayList<>();
        List<Naprawa> naprawy = dataAccessObject.readAll(Naprawa.class);
        for (Naprawa naprawa : naprawy) {
            Samochod samochod = naprawa.getSamochod();
            Klient klient = samochod.getKlient();

            wiersze.add(klient.getImie() + " " + klient.getNazwisko() + "     "
                    + samochod.getModel().getMarka() + " " + samochod.getModel().getModel() + "     " + samochod.getNumerRejestracyjny() + "     "
                    + naprawa.getData());
        }

        ObservableList<String> fxWIerszeTabeli = FXCollections.observableList(new ArrayList<>(wiersze));
        lvNaprawy.setItems(fxWIerszeTabeli);
    }

    @FXML
    public void onDodajNaprawe() throws IOException {
        AnchorPane anchorPane;

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(context::getBean);
        fxmlLoader.setLocation(getClass().getResource("/DodajNapraweWindow.fxml"));
        anchorPane = fxmlLoader.load();
        anchorPane.getStyleClass().add("background");
        tabPaneController.getTabNaprawy().setContent(anchorPane);
    }
}
