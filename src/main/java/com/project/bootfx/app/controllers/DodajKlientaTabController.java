package com.project.bootfx.app.controllers;

import com.project.bootfx.app.dao.IDataAccessObject;
import com.project.bootfx.app.entity.Klient;
import com.project.bootfx.app.entity.Miasto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DodajKlientaTabController {

    @Autowired
    private IDataAccessObject dataAccessObject;

    @FXML
    private Button bDodaj;

    @FXML
    private TextField tfImie;

    @FXML
    private TextField tfNazwisko;

    @FXML
    private TextField tfPesel;

    @FXML
    private TextField tfUlica;

    @FXML
    private TextField tfNip;

    @FXML
    private ComboBox cbMiasto;

    @FXML
    public void dodajKlienta() {
        Klient klient = new Klient();
        if(!tfImie.getText().matches("[\\p{Lu}\\p{Ll}]{1,20}"))
            tfImie.setPromptText("BŁĘDNE IMIĘ!");
        else if(!tfNazwisko.getText().matches("[\\p{Lu}\\p{Ll}][\\p{Lu}\\p{Ll}-]{1,20}[\\p{Lu}\\p{Ll}]"))
            tfNazwisko.setPromptText("BŁĘDNE NAZWISKO!");
        else if(tfPesel.getText().isEmpty())
            tfPesel.setPromptText("BŁĘDNY PESEL!");
        else if(tfUlica.getText().isEmpty())
            tfUlica.setPromptText("BŁĘDNA ULICA!");
        else if(cbMiasto.getValue() == null)
            cbMiasto.setPromptText("WYBIERZ MIASTO");
        else{
            klient.setImie(tfImie.getText());
            klient.setNazwisko(tfNazwisko.getText());
            klient.setUlica(tfUlica.getText());
            klient.setPesel(tfPesel.getText());
            klient.setNip(tfNip.getText());
            List<Miasto> miasta = dataAccessObject.readAll(Miasto.class);
            String nazwaWybranegoMiasta = String.valueOf(cbMiasto.getValue());
            for(Miasto miasto: miasta)
                if(miasto.getNazwa().equals(nazwaWybranegoMiasta)){
                    klient.setMiasto(miasto);
                    break;
                }

            dataAccessObject.save(klient);
            clearLabels();
        }
    }

    @FXML
    public void initialize(){
        List<String> nazwyMiast = new ArrayList<String>();
        List<Miasto> miasta = dataAccessObject.readAll(Miasto.class);
        for(Miasto miasto: miasta)
            nazwyMiast.add(miasto.getNazwa());

        ObservableList<String> fxNazwyMiast = FXCollections.observableList(nazwyMiast);
        cbMiasto.setItems(fxNazwyMiast);
        //bDodaj.getStyleClass().add("button");
    }

    private void clearLabels(){
        tfImie.clear();
        tfNazwisko.clear();
        tfUlica.clear();
        tfPesel.clear();
        tfNip.clear();
    }
}
