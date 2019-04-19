package Controllers;

import DBControll.DBControll;
import Entities.Pracownik;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AnchorPaneController {

    DBControll database;

    @FXML
    private Button bCreate;
    @FXML
    private Button bRead;
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
            database.create(name, surname, city);
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
            Pracownik p = database.singleRead(id);
            lResults.setText(p.toString());
        }
        catch(NumberFormatException nfe){
            lResults.setText("ID has to be number!");
        }
        catch(Exception e){
            lResults.setText("No record with such ID!");
        }
    }
}
