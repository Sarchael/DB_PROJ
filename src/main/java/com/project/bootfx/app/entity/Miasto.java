package com.project.bootfx.app.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="miasto")
public class Miasto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="nazwa")
    private String nazwa;

    @Column(name="gmina")
    private String gmina;

    @Column(name="powiat")
    private String powiat;

    @ManyToOne
    @JoinColumn(name="id_woj")
    private Wojewodztwo wojewodztwo;

    @OneToMany(mappedBy = "miasto", fetch = FetchType.EAGER)
    private List<Pracownik> pracownicy;

    public Miasto() {
    }

    public Miasto(String nazwa, String gmina, String powiat) {
        this.nazwa = nazwa;
        this.gmina = gmina;
        this.powiat = powiat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getGmina() {
        return gmina;
    }

    public void setGmina(String gmina) {
        this.gmina = gmina;
    }

    public String getPowiat() {
        return powiat;
    }

    public void setPowiat(String powiat) {
        this.powiat = powiat;
    }

    public Wojewodztwo getWojewodztwo() {
        return wojewodztwo;
    }

    public void setWojewodztwo(Wojewodztwo wojewodztwo) {
        if(sameAsFormer(wojewodztwo))
            return;
        Wojewodztwo oldWojewodztwo = this.wojewodztwo;
        this.wojewodztwo = wojewodztwo;
        if(oldWojewodztwo!=null)
            oldWojewodztwo.removeMiasto(this);
        if(wojewodztwo!=null)
            wojewodztwo.addMiasto(this);
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
