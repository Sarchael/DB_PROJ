package com.project.bootfx.app.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "naprawa")
public class Naprawa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "opis")
    private String opis;

    @Column(name = "czas_naprawy")
    private int czasNaprawy;

    @Column(name = "data")
    private Date data;

    @Column(name = "postep")
    private String postep;

    @ManyToOne
    @JoinColumn(name = "id_sam")
    private Samochod samochod;

    @ManyToOne
    @JoinColumn(name = "id_prac_odp")
    private Pracownik pracownikOdp;

    @OneToMany(mappedBy = "naprawa")
    private List<UzytyMaterial> uzyteMaterialy;

    @OneToMany(mappedBy = "naprawa")
    private List<WykonawcaNaprawy> listaPracownikow;

    public Naprawa() {
    }

    public Naprawa(String opis, int czasNaprawy, Date data, String postep) {
        this.opis = opis;
        this.czasNaprawy = czasNaprawy;
        this.data = data;
        this.postep = postep;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getCzasNaprawy() {
        return czasNaprawy;
    }

    public void setCzasNaprawy(int czasNaprawy) {
        this.czasNaprawy = czasNaprawy;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getPostep() {
        return postep;
    }

    public void setPostep(String postep) {
        this.postep = postep;
    }

    public Samochod getSamochod() {
        return samochod;
    }

    public void setSamochod(Samochod samochod) {
        if(sameAsFormer(samochod))
            return;
        Samochod oldSamochod = this.samochod;
        this.samochod = samochod;
        if(oldSamochod!=null)
            oldSamochod.removeNaprawa(this);
        if(samochod!=null)
            samochod.addNaprawa(this);
    }

    private boolean sameAsFormer(Samochod newSamochod) {
        return samochod==null? newSamochod == null : samochod.equals(newSamochod);
    }

    public Pracownik getPracownikOdp() {
        return pracownikOdp;
    }

    public void setPracownikOdp(Pracownik pracownikOdp) {
        if(sameAsFormer2(pracownikOdp))
            return;
        Pracownik oldPracownikOdp = this.pracownikOdp;
        this.pracownikOdp = pracownikOdp;
        if(oldPracownikOdp!=null)
            oldPracownikOdp.removeNaprawaOdp(this);
        if(pracownikOdp!=null)
            pracownikOdp.addNaprawaOdp(this);
    }

    private boolean sameAsFormer2(Pracownik newPracownikOdp) {
        return pracownikOdp==null? newPracownikOdp == null : pracownikOdp.equals(newPracownikOdp);
    }

    public List<UzytyMaterial> getUzyteMaterialy() {
        return new ArrayList<UzytyMaterial>(uzyteMaterialy);
    }

    public void addUzytyMaterial(UzytyMaterial uzytyMaterial){
        if(uzyteMaterialy == null){
            uzyteMaterialy = new ArrayList<>();
            uzyteMaterialy.add(uzytyMaterial);
            uzytyMaterial.setNaprawa(this);
            return;
        }
        if(uzyteMaterialy.contains(uzytyMaterial))
            return;
        uzyteMaterialy.add(uzytyMaterial);
        uzytyMaterial.setNaprawa(this);
    }

    public void removeUzytyMaterial(UzytyMaterial uzytyMaterial){
        if(!uzyteMaterialy.contains(uzytyMaterial))
            return;
        uzyteMaterialy.remove(uzytyMaterial);
        uzytyMaterial.setNaprawa(null);
    }

    public void addListaPracownikow(WykonawcaNaprawy wykonawcaNaprawy){
        if(listaPracownikow == null){
            listaPracownikow = new ArrayList<>();
            listaPracownikow.add(wykonawcaNaprawy);
            wykonawcaNaprawy.setNaprawa(this);
            return;
        }
        if(listaPracownikow.contains(wykonawcaNaprawy))
            return;
        listaPracownikow.add(wykonawcaNaprawy);
        wykonawcaNaprawy.setNaprawa(this);
    }

    public void removeListaPracownikow(WykonawcaNaprawy wykonawcaNaprawy){
        if(!listaPracownikow.contains(wykonawcaNaprawy))
            return;
        listaPracownikow.remove(wykonawcaNaprawy);
        wykonawcaNaprawy.setNaprawa(null);
    }

    @Override
    public String toString() {
        return "Naprawa{" +
                "id=" + id +
                ", opis='" + opis + '\'' +
                ", czasNaprawy=" + czasNaprawy +
                ", data=" + data +
                ", postep='" + postep + '\'' +
                ", samochod=" + samochod +
                ", pracownikOdp=" + pracownikOdp +
                '}';
    }
}
