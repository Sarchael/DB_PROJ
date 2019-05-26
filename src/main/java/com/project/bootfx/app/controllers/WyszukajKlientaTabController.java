package com.project.bootfx.app.controllers;


import com.project.bootfx.app.dao.IDataAccessObject;
import com.project.bootfx.app.entity.Klient;
import com.project.bootfx.app.entity.Samochod;
import com.project.bootfx.app.properties.KlientFX;
import com.project.bootfx.app.properties.SamochodFX;
import javafx.beans.binding.NumberBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableView tableView;

    @FXML
    private ComboBox cbTable;

    private ObservableList fxData;

    @FXML
    public void initialize(){
        ObservableList<String> tableNames = FXCollections.observableList(new ArrayList<>());
        tableNames.addAll("Klienci", "Samochody");
        cbTable.setItems(tableNames);
        cbTable.setValue("Klienci");
        initTableForKlienci();
    }

    private void initTableForKlienci(){
        fillObservableKlienci();

        initializeKlienciColumns();

        initializeKlienciDataAndFilter();

        klienciRowDoubleclick();
    }

    private void initTableForSamochody(){
        fillObservableSamochody();

        initializeSamochodyColumns();

        initializeSamochodyDataAndFilter();

        samochodyRowDoubleclick();
    }

    private void fillObservableKlienci(){
        List<Klient> klienci = dataAccessObject.readAll(Klient.class);
        List<KlientFX> kliencifx = new ArrayList<>();
        klienci.forEach(klient -> kliencifx.add(new KlientFX(klient.getImie(), klient.getNazwisko(), klient.getPesel(), klient.getUlica())));
        fxData = FXCollections.observableList(kliencifx);
    }

    private void initializeKlienciColumns(){
        final int numberOfKlientColumns = 4;
        NumberBinding width = tableView.widthProperty().divide(numberOfKlientColumns);

        TableColumn<KlientFX, String> imieColumn = prepareColumn("Imie", "imie", width);
        TableColumn<KlientFX, String> nazwiskoColumn = prepareColumn("Nazwisko", "nazwisko", width);
        TableColumn<KlientFX, String> peselColumn = prepareColumn("Pesel", "pesel", width);
        TableColumn<KlientFX, String> ulicaColumn = prepareColumn("Ulica", "ulica", width);

        tableView.getColumns().addAll(imieColumn , nazwiskoColumn, peselColumn, ulicaColumn);
    }

    private void initializeKlienciDataAndFilter(){
        FilteredList<KlientFX> filteredData = new FilteredList<>(fxData, p -> true);

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
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        //Add sorted (and filtered) data to the table.
        tableView.setItems(sortedData);
    }

    private void klienciRowDoubleclick(){
        tableView.setRowFactory(tv -> {
            TableRow<KlientFX> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    KlientFX rowData = row.getItem();
                    SzczegolyWindowController.display(rowData);
                }
            });
            return row ;
        });
    }

    private void fillObservableSamochody(){
        List<Samochod> samochody = dataAccessObject.readAll(Samochod.class);
        List<SamochodFX> samochodyfx = new ArrayList<>();
        samochody.forEach(samochod -> samochodyfx.add(
                new SamochodFX(samochod.getNumerRejestracyjny(), String.valueOf(samochod.getRokProdukcji()), samochod.getModel().getMarka(), samochod.getModel().getModel()))
        );
        fxData = FXCollections.observableList(samochodyfx);
    }

    private void initializeSamochodyColumns(){
        final int numberOfKlientColumns = 4;
        NumberBinding width = tableView.widthProperty().divide(numberOfKlientColumns);

        TableColumn<SamochodFX, String> nrRejColumn = prepareColumn("Numer rej", "numerRejestracyjny", width);
        TableColumn<SamochodFX, String> rokColumn = prepareColumn("Rok", "rokProdukcji", width);
        TableColumn<SamochodFX, String> markaColumn = prepareColumn("Marka", "marka", width);
        TableColumn<SamochodFX, String> modelColumn = prepareColumn("Model", "model", width);

        tableView.getColumns().addAll(nrRejColumn , rokColumn, markaColumn, modelColumn);
    }

    private void initializeSamochodyDataAndFilter(){
        FilteredList<SamochodFX> filteredData = new FilteredList<>(fxData, p -> true);

        //Set the filter Predicate whenever the filter changes.
        tfSzukaj.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(samochod -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (samochod.getMarka().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (samochod.getModel().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                else if (samochod.getNumerRejestracyjny().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                else if (samochod.getRokProdukcji().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false; // Does not match.
            });
        });

        //Wrap the FilteredList in a SortedList.
        SortedList<SamochodFX> sortedData = new SortedList<>(filteredData);

        //Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        //Add sorted (and filtered) data to the table.
        tableView.setItems(sortedData);
    }

    private void samochodyRowDoubleclick(){
        tableView.setRowFactory(tv -> {
            TableRow<SamochodFX> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    SamochodFX rowData = row.getItem();
                    SzczegolyWindowController.display(rowData);
                }
            });
            return row ;
        });
    }

    private <K, V> TableColumn<K,V> prepareColumn(String title, String propertyName, NumberBinding width){
        TableColumn<K, V> tableColumn = new TableColumn<>(title);
        tableColumn.setCellValueFactory(
                new PropertyValueFactory<>(propertyName)
        );
        tableColumn.prefWidthProperty().bind(width);

        return tableColumn;
    }

    public void onTablicaAction(ActionEvent actionEvent) {
        tableView.getColumns().clear();
        //tableView.getItems().clear();
        if(cbTable.getValue().equals("Klienci")) {
            initTableForKlienci();
        } else if(cbTable.getValue().equals("Samochody")){
            initTableForSamochody();
        }
    }
}
