package Controllers;

import DBControll.DBControll;
import Entities.Pracownik;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;

public class AnchorPaneController {

    DBControll database;

    @FXML
    private Button bCreate;
    @FXML
    private Button bRead;
    @FXML
    private Button bReadAll;
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
    private void initialize(){
        database = new DBControll();
    }

    @FXML
    private void create(){
        String name = tfName.getText();
        String surname = tfSurname.getText();
        String city = tfCity.getText();

        if(!name.isEmpty() && !surname.isEmpty() && !city.isEmpty()){
            Pracownik p = new Pracownik(name, surname, city);
            database.create(p);
            lResults.setText("Created succesfully!");
        }
        else{
            lResults.setText("Every field has to be filled!");
        }
    }

    @FXML
    private void read(){
        try{
            int id = Integer.parseInt(tfID.getText());
            Pracownik p = database.singleReadByID(Pracownik.class, id);
            lResults.setText(p.toString());
        }
        catch(NumberFormatException nfe){
            lResults.setText("ID has to be number!");
        }
        catch(Exception e){
            lResults.setText("No record with such ID!");
        }
    }

    @FXML
    private void readAll(){
        try {
            List<Pracownik> list = database.readAll(Pracownik.class);

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
        }
    }
}
