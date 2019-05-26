package com.project.bootfx.app.properties;

import com.project.bootfx.app.properties.interfaces.PropertiesProvider;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.HashMap;
import java.util.Map;

public class SamochodFX implements PropertiesProvider {
    private final StringProperty numerRejestracyjny;
    private final StringProperty rokProdukcji;
    private final StringProperty marka;
    private final StringProperty model;

    public SamochodFX(String numerRejestracyjny, String rokProdukcji, String marka, String model) {
        this.numerRejestracyjny = new SimpleStringProperty(numerRejestracyjny);
        this.rokProdukcji = new SimpleStringProperty(rokProdukcji);
        this.marka = new SimpleStringProperty(marka);
        this.model =new SimpleStringProperty(model);
    }

    public String getNumerRejestracyjny() {
        return numerRejestracyjny.get();
    }

    public StringProperty numerRejestracyjnyProperty() {
        return numerRejestracyjny;
    }

    public void setNumerRejestracyjny(String numerRejestracyjny) {
        this.numerRejestracyjny.set(numerRejestracyjny);
    }

    public String getRokProdukcji() {
        return rokProdukcji.get();
    }

    public StringProperty rokProdukcjiProperty() {
        return rokProdukcji;
    }

    public void setRokProdukcji(String rokProdukcji) {
        this.rokProdukcji.set(rokProdukcji);
    }

    public String getMarka() {
        return marka.get();
    }

    public StringProperty markaProperty() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka.set(marka);
    }

    public String getModel() {
        return model.get();
    }

    public StringProperty modelProperty() {
        return model;
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    @Override
    public Map<String, StringProperty> provideProperties() {
        Map<String, StringProperty> properties = new HashMap<>();

        properties.put("Numer rej", numerRejestracyjny);
        properties.put("Rok produkcji", rokProdukcji);
        properties.put("Marka", marka);
        properties.put("Model", model);

        return properties;
    }
}
