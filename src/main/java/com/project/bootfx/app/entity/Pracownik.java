package com.project.bootfx.app.entity;

import javax.persistence.*;

@Entity
@Table(name="pracownik")
public class Pracownik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "imie")
    private String imie;

    @Column(name="nazwisko")
    private String nazwisko;

    @Column(name="pesel")
    private String pesel;

    @Column(name="ulica")
    private String ulica;

    @ManyToOne
    @JoinColumn(name="id_miasta")
    private Miasto miasto;

    @ManyToOne
    @JoinColumn(name="id_stan")
    private Stanowisko stanowisko;

    public Pracownik() {
    }

    public Pracownik(String imie, String nazwisko, String pesel, String ulica) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.ulica = ulica;
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

    public Miasto getMiasto() {
        return miasto;
    }

    public void setMiasto(Miasto miasto) {
        if(sameAsFormer(miasto))
            return;
        Miasto oldMiasto = this.miasto;
        this.miasto = miasto;
        if(oldMiasto!=null)
            oldMiasto.removePracownik(this);
        if(miasto!=null)
            miasto.addPracownik(this);
    }

    private boolean sameAsFormer(Miasto newMiasto) {
        return miasto==null? newMiasto == null : miasto.equals(newMiasto);
    }

    public Stanowisko getStanowisko() {
        return stanowisko;
    }

    public void setStanowisko(Stanowisko stanowisko) {
        if(sameAsFormer(stanowisko))
            return;
        Stanowisko oldStanowisko = this.stanowisko;
        this.stanowisko = stanowisko;
        if(oldStanowisko!=null)
            oldStanowisko.removePracownik(this);
        if(stanowisko!=null)
            stanowisko.addPracownik(this);
    }

    private boolean sameAsFormer(Stanowisko newStanowisko) {
        return stanowisko==null? newStanowisko == null : stanowisko.equals(newStanowisko);
    }

    @Override
    public String toString() {
        return "Pracownik{" +
                "id=" + id +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", pesel='" + pesel + '\'' +
                ", ulica='" + ulica + '\'' +
                ", miasto=" + miasto +
                ", stanowisko=" + stanowisko +
                '}';
    }
}
