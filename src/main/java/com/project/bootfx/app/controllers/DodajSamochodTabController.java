package com.project.bootfx.app.controllers;

import com.project.bootfx.app.dao.IDataAccessObject;
import com.project.bootfx.app.entity.Klient;
import com.project.bootfx.app.entity.Miasto;
import com.project.bootfx.app.entity.Model;
import com.project.bootfx.app.entity.Samochod;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DodajSamochodTabController {

    @Autowired
    private IDataAccessObject dataAccessObject;

    @FXML
    private TextField tfNrRej;

    @FXML
    private TextField tfVin;

    @FXML
    private TextField tfRokProdukcji;

    @FXML
    private TextField tfPrzebieg;

    @FXML
    private ComboBox cbMarka;

    @FXML
    private ComboBox cbModel;

    @FXML
    private ComboBox cbWersja;

    @FXML
    private ComboBox cbImieNazwisko;

    @FXML
    public void initialize() {
        List<String> imieNazwisko = new ArrayList<>();
        List<Klient> klienci = dataAccessObject.readAll(Klient.class);
        for (Klient klient : klienci)
            imieNazwisko.add(klient.getImie() + " " + klient.getNazwisko());

        ObservableList<String> fxImieNazwisko = FXCollections.observableList(imieNazwisko);
        cbImieNazwisko.setItems(fxImieNazwisko);

        Set<String> marki = new HashSet<>();
        List<Model> models = dataAccessObject.readAll(Model.class);
        for (Model model : models)
            marki.add(model.getMarka());

        ObservableList<String> fxMarki = FXCollections.observableList(new ArrayList<>(marki));
        cbMarka.setItems(fxMarki);


    }

    private void clearLabels() {
        tfNrRej.clear();
        tfPrzebieg.clear();
        tfRokProdukcji.clear();
        tfVin.clear();
        tfNrRej.setPromptText("numer rejestracyjny");
        tfVin.setPromptText("numer VIN");
        tfRokProdukcji.setPromptText("rok produkcji");
        tfPrzebieg.setPromptText("przebieg");
        cbMarka.setValue(null);
        cbImieNazwisko.setValue(null);
        cbModel.setValue(null);
        cbWersja.setValue(null);
        cbImieNazwisko.setPromptText("imię i nazwisko");
        cbWersja.setPromptText("wersja");
        cbModel.setPromptText("model");
        cbMarka.setPromptText("marka");
    }

    @FXML
    public void dodajSamochod(ActionEvent actionEvent) {
        Samochod samochod = new Samochod();
        if (!tfNrRej.getText().matches("\\w{2,3}[A-Z\\d]{5}")) {
            tfNrRej.setPromptText("BŁĘDNY NUMER REJESTRACYJNY");
            tfNrRej.clear();
        }
        else if (!tfVin.getText().matches("[\\d\\w]{17}")) {
            tfVin.setPromptText("BŁĘDNY VIN (17 znaków)!!");
            tfVin.clear();
        }
        else if (!tfRokProdukcji.getText().matches("[0-9]{4}")) {
            tfRokProdukcji.setPromptText("BŁĘDNY ROK PRODUKCJI (4 cyfry)!");
            tfRokProdukcji.clear();
        }
        else if (!tfPrzebieg.getText().matches("[0-9]{1,7}")) {
            tfPrzebieg.setPromptText("BŁĘDNY PRZEBIEG!");
            tfPrzebieg.clear();
        }
        else if (cbMarka.getValue() == null)
            cbMarka.setPromptText("WYBIERZ MARKA");
        else if (cbModel.getValue() == null)
            cbModel.setPromptText("WYBIERZ MODEL");
        else if (cbWersja.getValue() == null)
            cbWersja.setPromptText("WYBIERZ WERSJE");
        else if (cbImieNazwisko.getValue() == null)
            cbImieNazwisko.setPromptText("WYBIERZ OSOBĘ");
        else {
            samochod.setNumerRejestracyjny(tfNrRej.getText());
            samochod.setVin(tfVin.getText());
            samochod.setRokProdukcji(Integer.valueOf(tfRokProdukcji.getText()));
            samochod.setPrzebieg(Integer.valueOf(tfPrzebieg.getText()));
            List<Model> modele = dataAccessObject.readAll(Model.class);
            String nazwaWybranejMarki = String.valueOf(cbMarka.getValue());
            String nazwaWybranegoModelu = String.valueOf(cbModel.getValue());
            String nazwaWybranejWersji = String.valueOf(cbWersja.getValue());
            for (Model model : modele)
                if (model.getMarka().equals(nazwaWybranejMarki) && model.getModel().equals(nazwaWybranegoModelu) && model.getWersja().equals(nazwaWybranejWersji)) {
                    samochod.setModel(model);
                    break;
                }


            String[] imNazw = String.valueOf(cbImieNazwisko.getValue()).split(" ");
            String imie= imNazw[0];
            String nazwisko=imNazw[1];

            List<Klient>klienci=dataAccessObject.readAll(Klient.class);
            for(Klient klient : klienci){
                if(klient.getImie().equals(imie)&&klient.getNazwisko().equals(nazwisko)){
                    samochod.setKlient(klient);
                    break;
                }
            }

            dataAccessObject.save(samochod);
            clearLabels();
        }
    }

    @FXML
    public void onMarkaAction() {

        Set<String> nazwyModeli = new HashSet<>();
        List<Model> modele = dataAccessObject.readAll(Model.class);
        for (Model model : modele) {
            if (model.getMarka().equals(String.valueOf(cbMarka.getValue()))) {
                nazwyModeli.add(model.getModel());
            }
        }

        ObservableList<String> fxNazwyModeli = FXCollections.observableList(new ArrayList<>(nazwyModeli));
        cbModel.setItems(fxNazwyModeli);
    }

    @FXML
    public void onModelAction() {

        Set<String> nazwyWersji = new HashSet<>();
        List<Model> modele = dataAccessObject.readAll(Model.class);
        for (Model model : modele) {
            if (model.getModel().equals(String.valueOf(cbModel.getValue()))) {
                nazwyWersji.add(model.getWersja());
            }
        }

        ObservableList<String> fxNazwyWersji = FXCollections.observableList(new ArrayList<>(nazwyWersji));
        cbWersja.setItems(fxNazwyWersji);
    }
}
