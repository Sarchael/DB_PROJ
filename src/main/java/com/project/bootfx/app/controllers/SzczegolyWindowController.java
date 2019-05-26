package com.project.bootfx.app.controllers;

import com.project.bootfx.app.properties.interfaces.PropertiesProvider;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Map;

/*
    NOTE: not a normal JavaFX @Component, so it is not connected with any .fxml file
          maybe change it in future?
 */
public class SzczegolyWindowController {

    public static void display(PropertiesProvider propertiesProvider)
    {
        Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Szczegóły");

        Map<String, StringProperty> properties = propertiesProvider.provideProperties();
        String labelText = "\n";

        for (Map.Entry<String, StringProperty> entry : properties.entrySet())
            labelText += entry.getKey() + ": " + entry.getValue().get() + "\n";

        Label label1= new Label(labelText);


        Button button1= new Button("Zamknij");


        button1.setOnAction(e -> popupwindow.close());



        VBox layout= new VBox(10);


        layout.getChildren().addAll(label1, button1);

        layout.setAlignment(Pos.CENTER);

        Scene scene1= new Scene(layout, 250, 125);

        popupwindow.setScene(scene1);

        popupwindow.showAndWait();
    }
}
