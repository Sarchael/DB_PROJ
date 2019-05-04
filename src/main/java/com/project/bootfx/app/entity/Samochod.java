package com.project.bootfx.app.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "samochod", fetch = FetchType.EAGER)
    private List<Naprawa> naprawy;

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
        if(sameAsFormer(model))
            return;
        Model oldModel = this.model;
        this.model = model;
        if(oldModel!=null)
            oldModel.removeSamochod(this);
        if(model!=null)
            model.addSamochod(this);
    }

    private boolean sameAsFormer(Model newModel) {
        return model==null? newModel == null : model.equals(newModel);
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        if(sameAsFormer2(klient))
            return;
        Klient oldKlient = this.klient;
        this.klient = klient;
        if(oldKlient!=null)
            oldKlient.removeSamochod(this);
        if(klient!=null)
            klient.addSamochod(this);
    }

    private boolean sameAsFormer2(Klient newKlient) {
        return klient==null? newKlient == null : klient.equals(newKlient);
    }

    public List<Naprawa> getNaprawy() {
        return naprawy;
    }

    public void addNaprawa(Naprawa naprawa){
        if(naprawy == null){
            naprawy = new ArrayList<>();
            naprawy.add(naprawa);
            naprawa.setSamochod(this);
            return;
        }
        if(naprawy.contains(naprawa))
            return;
        naprawy.add(naprawa);
        naprawa.setSamochod(this);
    }

    public void removeNaprawa(Naprawa naprawa){
        if(!naprawy.contains(naprawa))
            return;
        naprawy.remove(naprawa);
        naprawa.setSamochod(null);
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
