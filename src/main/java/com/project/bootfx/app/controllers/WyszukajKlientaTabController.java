package com.project.bootfx.app.controllers;


import com.project.bootfx.app.dao.IDataAccessObject;
import com.project.bootfx.app.entity.Klient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    public void initialize(){
        //bWyszukajWszystkich.getStyleClass().add("button");
    }

    @FXML
    public void wynikiWyszukiwania(){
        lWynikiWyszukiwania.setText("");
        List<Klient> klienci = dataAccessObject.readAll(Klient.class);
        for(Klient k : klienci){
            lWynikiWyszukiwania.setText(lWynikiWyszukiwania.getText().toString() + k.toString() + "\n");
        }
    }
}
