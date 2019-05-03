package com.project.bootfx.app.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="stanowisko")
public class Stanowisko {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="nazwa")
    private String nazwa;

    @OneToMany(mappedBy = "stanowisko", fetch = FetchType.EAGER)
    private List<Pracownik> pracownicy;

    public Stanowisko() {
    }

    public Stanowisko(String nazwa) {
        this.nazwa = nazwa;
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

    public List<Pracownik> getPracownicy() {
        return new ArrayList<Pracownik>(pracownicy);
    }


    public void addPracownik(Pracownik pracownik){
        if(pracownicy == null){
            pracownicy = new ArrayList<>();
            pracownicy.add(pracownik);
            pracownik.setStanowisko(this);
            return;
        }
        if(pracownicy.contains(pracownik))
            return;
        pracownicy.add(pracownik);
        pracownik.setStanowisko(this);
    }

    public void removePracownik(Pracownik pracownik){
        if(!pracownicy.contains(pracownik))
            return;
        pracownicy.remove(pracownik);
        pracownik.setStanowisko(null);
    }

    @Override
    public String toString() {
        return "Stanowisko{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                '}';
    }
}
