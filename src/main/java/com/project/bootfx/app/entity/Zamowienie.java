package com.project.bootfx.app.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="zamowienie")
public class Zamowienie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="ilosc")
    private int ilosc;

    @Column(name="status")
    private String status;

    @ManyToOne
    @JoinColumn(name="id_dost")
    private Dostawca dostawca;

    //@OneToMany(mappedBy = "amowienie", fetch = FetchType.EAGER)
    //private List<Pracownik> pracownicy;

    public Zamowienie() {
    }

    public Zamowienie(int ilosc, String status) {
        this.ilosc = ilosc;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Dostawca getDostawca() {
        return dostawca;
    }

    public void setDostawca(Dostawca dostawca) {
        if(sameAsFormer(dostawca))
            return;
        Dostawca oldDostawca = this.dostawca;
        this.dostawca = dostawca;
        if(oldDostawca!=null)
            oldDostawca.remove(this);
        if(dostawca!=null)
            dostawca.addMiasto(this);
    }

    private boolean sameAsFormer(Wojewodztwo newWojewodztwo) {
        return wojewodztwo==null? newWojewodztwo == null : wojewodztwo.equals(newWojewodztwo);
    }

    public List<Pracownik> getPracownicy() {
        return new ArrayList<Pracownik>(pracownicy);
    }

    public void addPracownik(Pracownik pracownik){
        if(pracownicy == null){
            pracownicy = new ArrayList<>();
            pracownicy.add(pracownik);
            pracownik.setMiasto(this);
            return;
        }
        if(pracownicy.contains(pracownik))
            return;
        pracownicy.add(pracownik);
        pracownik.setMiasto(this);
    }

    public void removePracownik(Pracownik pracownik){
        if(!pracownicy.contains(pracownik))
            return;
        pracownicy.remove(pracownik);
        pracownik.setMiasto(null);
    }

    @Override
    public String toString() {
        return "Miasto{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", gmina='" + gmina + '\'' +
                ", powiat='" + powiat + '\'' +
                ", wojewodztwo=" + wojewodztwo +
                '}';
    }
}
