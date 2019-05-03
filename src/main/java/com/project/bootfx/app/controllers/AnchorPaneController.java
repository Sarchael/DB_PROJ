package com.project.bootfx.app.controllers;

import com.project.bootfx.app.dao.MiastoDAO;
import com.project.bootfx.app.dao.PracownikDAO;
import com.project.bootfx.app.dao.StanowiskoDAO;
import com.project.bootfx.app.dao.WojewodztwoDAO;
import com.project.bootfx.app.entity.Miasto;
import com.project.bootfx.app.entity.Pracownik;
import com.project.bootfx.app.entity.Stanowisko;
import com.project.bootfx.app.entity.Wojewodztwo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnchorPaneController {

    @Autowired
    private PracownikDAO pracownikDAO;

    @Autowired
    private MiastoDAO miastoDAO;

    @Autowired
    private WojewodztwoDAO wojewodztwoDAO;

    @Autowired
    private StanowiskoDAO stanowiskoDAO;

    @FXML
    private TextField tfID;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfSurname;
    @FXML
    private TextField tfCity;
    @FXML
    private Label lResults;

    @FXML
    private void create(){
        /*String name = tfName.getText();
        String surname = tfSurname.getText();
        String city = tfCity.getText();

        if(!name.isEmpty() && !surname.isEmpty() && !city.isEmpty()){
            Pracownik p = new Pracownik(name, surname, city);
            pracownikDAO.save(p);
            lResults.setText("Created succesfully!");
        }
        else{
            lResults.setText("Every field has to be filled!");
        }*/

        /*
        // TAK DZIALA ZAPIS!!! heheh juz dziala sto razy lepiej ale gdzie indziej xd
        Stanowisko stanowisko = new Stanowisko("Szef");
        Miasto miasto = new Miasto("Debica", "Debica", "Debicki");
        Wojewodztwo wojewodztwo = new Wojewodztwo("Podkarpackie");
        Pracownik pracownik = new Pracownik("Michal", "Pochec", "98022005552", "Smugowa");

        wojewodztwoDAO.save(wojewodztwo);
        miasto.setWojewodztwo(wojewodztwo);
        miastoDAO.save(miasto);
        stanowiskoDAO.save(stanowisko);
        pracownik.setMiasto(miasto);
        pracownik.setStanowisko(stanowisko);
        pracownikDAO.save(pracownik);*/

        Wojewodztwo woj = wojewodztwoDAO.getByID(1);
        List<Miasto> lista = woj.getMiasta();
        for(Miasto m : lista)
            System.out.println(m);
    }

    @FXML
    private void read(){
        /*try{
            int id = Integer.parseInt(tfID.getText());
            Pracownik p = pracownikDAO.getByID(id);
            lResults.setText(p.toString());
        }
        catch(NumberFormatException nfe){
            lResults.setText("ID has to be number!");
        }
        catch(Exception e){
            lResults.setText("No record with such ID!");
        }*/
    }

    @FXML
    private void readAll(){
       /* try {
            List<Pracownik> list = pracownikDAO.getAll();

            if(!list.isEmpty()) {
                String result = "";
                for (Pracownik p : list) {
                    result += p.toString() + "\n";
                }
                lResults.setText(result);
            }
            else{
                lResults.setText("No record in table!");
            }
        }
        catch(Exception e){
            lResults.setText("No record with such ID!");
        }*/
    }
}
