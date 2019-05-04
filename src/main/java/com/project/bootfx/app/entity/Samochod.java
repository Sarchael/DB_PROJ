package com.project.bootfx.app.entity;

import javax.persistence.*;

@Entity
@Table(name="samochod")
public class Samochod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="nr_rej")
    private String numerRejestracyjny;

    @Column(name="vin")
    private String vin;

    @Column(name="rok_prod")
    private int rokProdukcji;

    @Column(name = "przebieg")
    private int przebieg;

    @ManyToOne
    @JoinColumn(name = "id_mod")
    private Model model;

    @ManyToOne
    @JoinColumn(name="id_kl")
    private Klient klient;

    public Samochod() {
    }

    public Samochod(String numerRejestracyjny, String vin, int rokProdukcji, int przebieg) {
        this.numerRejestracyjny = numerRejestracyjny;
        this.vin = vin;
        this.rokProdukcji = rokProdukcji;
        this.przebieg = przebieg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumerRejestracyjny() {
        return numerRejestracyjny;
    }

    public void setNumerRejestracyjny(String numerRejestracyjny) {
        this.numerRejestracyjny = numerRejestracyjny;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public int getRokProdukcji() {
        return rokProdukcji;
    }

    public void setRokProdukcji(int rokProdukcji) {
        this.rokProdukcji = rokProdukcji;
    }

    public int getPrzebieg() {
        return przebieg;
    }

    public void setPrzebieg(int przebieg) {
        this.przebieg = przebieg;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    @Override
    public String toString() {
        return "Samochod{" +
                "id=" + id +
                ", numerRejestracyjny='" + numerRejestracyjny + '\'' +
                ", vin='" + vin + '\'' +
                ", rokProdukcji=" + rokProdukcji +
                ", przebieg=" + przebieg +
                ", model=" + model +
                ", klient=" + klient +
                '}';
    }
}
