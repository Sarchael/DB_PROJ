package com.project.bootfx.app.properties;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class KlientFX {
    private final StringProperty imie;
    private final StringProperty nazwisko;
    private final StringProperty pesel;
    private final StringProperty ulica;

    public KlientFX(String imie, String nazwisko, String pesel, String ulica) {
        this.imie = new SimpleStringProperty(imie);
        this.nazwisko = new SimpleStringProperty(nazwisko);
        this.pesel = new SimpleStringProperty(pesel);
        this.ulica = new SimpleStringProperty(ulica);
    }

    public String getImie() {
        return imie.get();
    }

    public void setImie(String imie) {
        this.imie.set(imie);
    }

    public StringProperty imieProperty() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko.get();
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko.set(nazwisko);
    }

    public StringProperty nazwiskoProperty() {
        return nazwisko;
    }

    public String getPesel() {
        return pesel.get();
    }

    public void setPesel(String pesel) {
        this.pesel.set(pesel);
    }

    public StringProperty peselProperty() {
        return pesel;
    }

    public String getUlica() {
        return ulica.get();
    }

    public void setUlica(String ulica) {
        this.ulica.set(ulica);
    }

    public StringProperty ulicaProperty() {
        return ulica;
    }
}