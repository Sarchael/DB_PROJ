package com.project.bootfx.app.controllers;

import com.project.bootfx.app.dao.IDataAccessObject;
import com.project.bootfx.app.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;


@Component
public class DodajNapraweController {
    @Autowired
    private IDataAccessObject dataAccessObject;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private TabPaneController tabPaneController;

    @FXML
    TextArea taOpis;

    @FXML
    ComboBox cbKlienci;

    @FXML
    ComboBox cbSamochody;

    @FXML
    ComboBox cbPracownicy;

    @FXML
    Button bPowrot;

    @FXML
    Button bZatwierdz;

    @FXML
    public void initialize() {
        updateKlienci();
        updatePracownicy();
    }

    @FXML
    public void onKlientAction(){
        List<String> nazwySamochodow = new ArrayList<>();
        List<Samochod> samochody = dataAccessObject.readAll(Samochod.class);
        for (Samochod samochod : samochody) {
            String imieNazwisko = samochod.getKlient().getImie() + " " + samochod.getKlient().getNazwisko();
            if (imieNazwisko.equals(String.valueOf(cbKlienci.getValue()))) {
                Model model = samochod.getModel();
                nazwySamochodow.add(model.getMarka() + " " + model.getModel() + " " + samochod.getNumerRejestracyjny());
            }
        }

        ObservableList<String> fxnazwySamochodow = FXCollections.observableList(new ArrayList<>(nazwySamochodow));
        cbSamochody.setItems(fxnazwySamochodow);
    }

    public  void updateKlienci(){
        List<String> imieNazwisko = new ArrayList<>();
        List<Klient> klienci = dataAccessObject.readAll(Klient.class);
        for (Klient klient : klienci)
            imieNazwisko.add(klient.getImie() + " " + klient.getNazwisko());

        ObservableList<String> fxImieNazwisko = FXCollections.observableList(imieNazwisko);
        cbKlienci.setItems(fxImieNazwisko);
    }

    public void updatePracownicy(){
        List<String> imieNazwisko = new ArrayList<>();
        List<Pracownik> pracownicy = dataAccessObject.readAll(Pracownik.class);
        for (Pracownik praciwnik : pracownicy)
            imieNazwisko.add(praciwnik.getImie() + " " + praciwnik.getNazwisko());

        ObservableList<String> fxImieNazwisko = FXCollections.observableList(imieNazwisko);
        cbPracownicy.setItems(fxImieNazwisko);
    }

    public void wrocDoListy() throws IOException {
        AnchorPane anchorPane;

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(context::getBean);
        fxmlLoader.setLocation(getClass().getResource("/Naprawy.fxml"));
        anchorPane = fxmlLoader.load();
        anchorPane.getStyleClass().add("background");
        tabPaneController.getTabNaprawy().setContent(anchorPane);
    }

    public void onPowrot(ActionEvent actionEvent) throws IOException {
        wrocDoListy();
    }

    public void onZatwierdz(ActionEvent actionEvent) throws IOException {
        Naprawa naprawa = new Naprawa();
        WykonawcaNaprawy wykonawcaNaprawy = new WykonawcaNaprawy();
        wykonawcaNaprawy.setCzasPracy(0);
        wykonawcaNaprawy.setWykonanaPraca("");
        if (taOpis.getText().isEmpty()) {
            taOpis.setPromptText("Opis jest wymagany!");
            taOpis.clear();
        }
        else if (cbKlienci.getValue() == null)
            cbKlienci.setPromptText("WYBIERZ KLIENTA");
        else if (cbSamochody.getValue() == null)
            cbSamochody.setPromptText("WYBIERZ SAMOCHÓD");
        else if (cbPracownicy.getValue() == null)
            cbPracownicy.setPromptText("WYBIERZ WYKONAWCĘ");
        else {

            String[] samochody = String.valueOf(cbSamochody.getValue()).split(" ");
            String nrRejestracyjny= samochody[2];


            List<Samochod> samochods = dataAccessObject.readAll(Samochod.class);
            for(Samochod samochod  : samochods){
                if(samochod.getNumerRejestracyjny().equals(nrRejestracyjny)){
                    naprawa.setSamochod(samochod);
                    break;
                }
            }

            List<Pracownik> pracownicy = dataAccessObject.readAll(Pracownik.class);
            for(Pracownik pracownik  : pracownicy){
                String imieNazwisko = pracownik.getImie() + " " + pracownik.getNazwisko();
                if(imieNazwisko.equals(cbPracownicy.getValue())){
                    naprawa.setPracownikOdp(pracownik);
                    wykonawcaNaprawy.setPracownik(pracownik);
                    break;
                }
            }

            naprawa.setCzasNaprawy(0);
            //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            naprawa.setData(sqlDate);

            naprawa.setOpis(taOpis.getText());
            naprawa.setPostep("Rozpoczęta.");

            naprawa = dataAccessObject.save(naprawa);
            wykonawcaNaprawy.setNaprawa(naprawa);
            dataAccessObject.save(wykonawcaNaprawy);
           //clearLabels();
            wrocDoListy();
        }
    }
}
