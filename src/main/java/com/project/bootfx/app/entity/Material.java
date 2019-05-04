package com.project.bootfx.app.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "material")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nazwa")
    private String nazwa;

    @Column(name = "ilosc")
    private int ilosc;

    @Column(name = "cena_jedn")
    private int cena_jedn;

    @OneToMany(mappedBy = "material", fetch = FetchType.EAGER)
    private List<Zapotrzebowanie> zapotrzebowania;

    @OneToMany(mappedBy = "material", fetch = FetchType.EAGER)
    private List<ZamowionyMaterial> zamowioneMaterialy;

    Material() {

    }

    Material(int id, String nazwa, int ilosc, int cena_jedn) {
        this.id = id;
        this.ilosc = ilosc;
        this.nazwa = nazwa;
        this.cena_jedn = cena_jedn;
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

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public int getCena_jedn() {
        return cena_jedn;
    }

    public void setCena_jedn(int cena_jedn) {
        this.cena_jedn = cena_jedn;
    }

    public List<ZamowionyMaterial> getZamowioneMaterialy() {
        return new ArrayList<ZamowionyMaterial>(zamowioneMaterialy);
    }

    public void addZamowionyMaterial(ZamowionyMaterial zamowionyMaterial) {
        if (zamowioneMaterialy == null) {
            zamowioneMaterialy = new ArrayList<>();
            zamowioneMaterialy.add(zamowionyMaterial);
            zamowionyMaterial.setMaterial(this);
            return;
        }
        if (zamowioneMaterialy.contains(zamowionyMaterial))
            return;
        zamowioneMaterialy.add(zamowionyMaterial);
        zamowionyMaterial.setMaterial(this);
    }

    public void removeZamowionyMaterial(ZamowionyMaterial zamowionyMaterial) {
        if (!zamowioneMaterialy.contains(zamowionyMaterial))
            return;
        zamowioneMaterialy.remove(zamowionyMaterial);
        zamowionyMaterial.setMaterial(null);
    }

    public List<Zapotrzebowanie> getZapotrzebowania() {
        return new ArrayList<Zapotrzebowanie>(zapotrzebowania);
    }

    public void addZapotrzebowanie(Zapotrzebowanie zapotrzebowanie) {
        if (zapotrzebowania == null) {
            zapotrzebowania = new ArrayList<>();
            zapotrzebowania.add(zapotrzebowanie);
            zapotrzebowanie.setMaterial(this);
            return;
        }
        if (zapotrzebowania.contains(zapotrzebowanie))
            return;
        zapotrzebowania.add(zapotrzebowanie);
        zapotrzebowanie.setMaterial(this);
    }

    public void removeZapotrzebowanie(Zapotrzebowanie zapotrzebowanie) {
        if (!zapotrzebowania.contains(zapotrzebowanie))
            return;
        zapotrzebowania.remove(zapotrzebowanie);
        zapotrzebowanie.setMaterial(null);
    }

    @Override
    public String toString() {
        return "Material{" +
                "id= " + id
                + ", nazwa= " + nazwa
                + ", ilosc" + ilosc
                + ", cena_jedn" + cena_jedn;
    }
}
