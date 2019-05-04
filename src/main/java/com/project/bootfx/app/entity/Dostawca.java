package com.project.bootfx.app.entity;

import javax.persistence.*;

@Entity
@Table(name="dostawca")
public class Dostawca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name ="nazwa")
    private String nazwa;

    @Column(name ="nip")
    private String nip;

    @Column(name ="nr_tel")
    private String nr_tel;

    @ManyToOne
    @JoinColumn(name ="id_miasta")
    private Miasto miasto;

    Dostawca(){

    }

    Dostawca(String nazwa, String nip, String nr_tel){
        this.nazwa = nazwa;
        this.nip = nip;
        this.nr_tel = nr_tel;
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

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNr_tel() {
        return nr_tel;
    }

    public void setNr_tel(String nr_tel) {
        this.nr_tel = nr_tel;
    }

    public Miasto getMiasto() {
        return miasto;
    }

    public void setMiasto(Miasto miasto) {
        this.miasto = miasto;
    }

    @Override
    public String toString(){
        return "Dostawca{" +
                "id= " + id
                +", nazwa= " + nazwa;
    }
}
