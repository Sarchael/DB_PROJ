package com.project.bootfx.app.controllers;


import com.project.bootfx.app.dao.IDataAccessObject;
import com.project.bootfx.app.entity.Klient;
import com.project.bootfx.app.properties.KlientFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WyszukajKlientaTabController {

    @Autowired
    IDataAccessObject dataAccessObject;

    @FXML
    private TextField tfSzukaj;

    @FXML
    private TableView<KlientFX> tvKlienci;

    @FXML
    private TableColumn<KlientFX, String> cImie;

    @FXML
    private TableColumn<KlientFX, String> cNazwisko;

    @FXML
    private TableColumn<KlientFX, String> cPesel;

    @FXML
    private TableColumn<KlientFX, String> cUlica;

    private ObservableList<KlientFX> fxKlienci;

    @FXML
    public void initialize(){
        //Fill observable list
        List<Klient> klienci = dataAccessObject.readAll(Klient.class);
        List<KlientFX> kliencifx = new ArrayList<>();
        klienci.forEach(klient -> kliencifx.add(new KlientFX(klient.getImie(), klient.getNazwisko(), klient.getPesel(), klient.getUlica())));
        fxKlienci = FXCollections.observableList(kliencifx);

        //Initialize the columns.
        cImie.setCellValueFactory(cellData -> cellData.getValue().imieProperty());
        cNazwisko.setCellValueFactory(cellData -> cellData.getValue().nazwiskoProperty());
        cPesel.setCellValueFactory(cellData -> cellData.getValue().peselProperty());
        cUlica.setCellValueFactory(cellData -> cellData.getValue().ulicaProperty());

        //Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<KlientFX> filteredData = new FilteredList<>(fxKlienci, p -> true);

        //Set the filter Predicate whenever the filter changes.
        tfSzukaj.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(klient -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (klient.getImie().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (klient.getNazwisko().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                else if (klient.getPesel().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                else if (klient.getUlica().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        //Wrap the FilteredList in a SortedList.
        SortedList<KlientFX> sortedData = new SortedList<>(filteredData);

        //Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tvKlienci.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tvKlienci.setItems(sortedData);
    }
}
