package com.project.bootfx.app.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Entity
@Table(name = "model")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "marka")
    private String marka;

    @Column(name = "model")
    private String model;

    @Column(name = "wersja")
    private String wersja;

    public Model() {
    }

    public Model(String marka, String model, String wersja) {
        this.marka = marka;
        this.model = model;
        this.wersja = wersja;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getWersja() {
        return wersja;
    }

    public void setWersja(String wersja) {
        this.wersja = wersja;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", marka='" + marka + '\'' +
                ", model='" + model + '\'' +
                ", wersja='" + wersja + '\'' +
                '}';
    }
}
