package com.project.bootfx.app.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="klient")
public class Klient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name ="imie")
    private String imie;

    @Column(name="nazwisko")
    private String nazwisko;

    @Column(name="pesel")
    private String pesel;

    @Column(name="ulica")
    private String ulica;

    @Column(name ="nip")
    private String nip;

    @Column(name = "rabat")
    private int rabat;

    @ManyToOne
    @JoinColumn(name ="id_miasta")
    private Miasto miasto;

    @OneToMany(mappedBy = "klient", fetch = FetchType.EAGER)
    private List<Samochod> samochody;

    public Klient() {
    }

    public Klient(String imie, String nazwisko, String pesel, String ulica, String nip, int rabat) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.ulica = ulica;
        this.nip = nip;
        this.rabat = rabat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public int getRabat() {
        return rabat;
    }

    public void setRabat(int rabat) {
        this.rabat = rabat;
    }

    public Miasto getMiasto() {
        return miasto;
    }

    public void setMiasto(Miasto miasto) {
        if(sameAsFormer(miasto))
            return;
        Miasto oldMiasto = this.miasto;
        this.miasto = miasto;
        if(oldMiasto!=null)
            oldMiasto.removeKlient(this);
        if(miasto!=null)
            miasto.addKlient(this);
    }

    private boolean sameAsFormer(Miasto newMiasto) {
        return miasto==null? newMiasto == null : miasto.equals(newMiasto);
    }

    public List<Samochod> getSamochod() {
        return new ArrayList<Samochod>(samochody);
    }

    public void addSamochod(Samochod samochod){
        if(samochody == null){
            samochody = new ArrayList<>();
            samochody.add(samochod);
            samochod.setKlient(this);
            return;
        }
        if(samochody.contains(samochod))
            return;
        samochody.add(samochod);
        samochod.setKlient(this);
    }

    public void removeSamochod(Samochod samochod){
        if(!samochody.contains(samochod))
            return;
        samochody.remove(samochod);
        samochod.setKlient(this);
    }

    @Override
    public String toString() {
        return "Klient{" +
                "id=" + id +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", pesel='" + pesel + '\'' +
                ", ulica='" + ulica + '\'' +
                ", nip='" + nip + '\'' +
                ", rabat=" + rabat +
                ", miasto=" + miasto +
                '}';
    }
}
