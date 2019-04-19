package Entities;

import javax.persistence.*;

@Entity
@Table(name="pracownicy")
public class Pracownik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "imie")
    private String imie;

    @Column(name="nazwisko")
    private String nazwisko;

    @Column(name="miasto")
    private String miasto;


    public Pracownik(){}

    public Pracownik(String imie, String nazwisko, String miasto) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.miasto = miasto;
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

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    @Override
    public String toString() {
        return "[ ID: " + id + ", IMIE: " + imie + ", NAZWISKO: " + nazwisko + ", MIASTO: " + miasto + " ]";
    }
}
