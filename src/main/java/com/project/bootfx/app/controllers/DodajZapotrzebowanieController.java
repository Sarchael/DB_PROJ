package com.project.bootfx.app.controllers;

import com.project.bootfx.app.dao.DataAccessObject;
import com.project.bootfx.app.entity.Material;
import com.project.bootfx.app.entity.Pracownik;
import com.project.bootfx.app.entity.Zapotrzebowanie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;


@Component
public class DodajZapotrzebowanieController {

    @Autowired
    private DataAccessObject dataAccessObject;

    @FXML
    private TextField tfIlosc;

    @FXML
    private ComboBox cbMaterial;

    @FXML
    public void initialize(){
        List<String> nazwaMaterialu = new ArrayList<>();
        List<Material> materialy = dataAccessObject.readAll(Material.class);
        for (Material material : materialy)
            nazwaMaterialu.add(material.getNazwa());

        ObservableList<String> fxNazwy = FXCollections.observableList(nazwaMaterialu);
        cbMaterial.setItems(fxNazwy);
    }

    public void onZatwierdz(ActionEvent actionEvent) {
        if(cbMaterial.getValue() == null)
            cbMaterial.setPromptText("WYBIERZ MATERIAL");
        else if(!tfIlosc.getText().matches("[\\d]{1,4}")) {
            tfIlosc.setPromptText("WPISZ POPRAWNA ILOSC");
            tfIlosc.clear();
        }
        else{
            List<Material> materialy = dataAccessObject.readAll(Material.class);
            Zapotrzebowanie zapotrzebowanie = new Zapotrzebowanie();
            for(Material material : materialy){
                if(material.getNazwa().equals(String.valueOf(cbMaterial.getValue()))) {
                    zapotrzebowanie.setMaterial(material);
                    break;
                }
            }

            zapotrzebowanie.setIlosc(Integer.parseInt(tfIlosc.getText()));
            dataAccessObject.save(zapotrzebowanie);
            cbMaterial.setValue(null);
            cbMaterial.setPromptText("Materiał");
            tfIlosc.clear();
            tfIlosc.setPromptText("Ilość");
        }
    }
}
