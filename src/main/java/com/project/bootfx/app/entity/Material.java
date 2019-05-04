package com.project.bootfx.app.entity;

import javax.persistence.*;

@Entity
@Table(name = "material")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name ="nazwa")
    private String nazwa;

    @Column(name ="ilosc")
    private int ilosc;

    @Column(name ="cena_jedn")
    private int cena_jedn;

    Material(){

    }

    Material(int id, String nazwa, int ilosc, int cena_jedn){
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

    @Override
    public String toString(){
        return "Dostawca{" +
                "id= " + id
                +", nazwa= " + nazwa
                +", ilosc" + ilosc
                +", cena_jedn" + cena_jedn;
    }
}
