package com.project.bootfx.app.controllers;


import com.project.bootfx.app.dao.IDataAccessObject;
import com.project.bootfx.app.entity.Klient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WyszukajKlientaTabController {

    @Autowired
    IDataAccessObject dataAccessObject;

    @FXML
    private Label lWynikiWyszukiwania;

    @FXML
    private Button bWyszukajWszystkich;

    @FXML
    private TextField tfSzukaj;

    @FXML
    private TableView<Klient> tvKlienci;

    @FXML
    private TableColumn<Klient, String> kolumnaImie;

    @FXML
    private TableColumn<Klient, String> kolumnaNazwisko;

    @FXML
    private TableColumn<Klient, String> kolumnaPesel;

    private ObservableList<Klient> fxKlienci;

    @FXML
    public void initialize(){
        List<Klient> klienci = dataAccessObject.readAll(Klient.class);
        fxKlienci = FXCollections.observableList(klienci);

       // kolumnaImie.setCellValueFactory(cellData -> cellData.getValue().getImie());
       // kolumnaNazwisko.setCellValueFactory(cellData -> cellData.getValue().getNazwisko());
    }

    @FXML
    public void wynikiWyszukiwania(){

    }
}
