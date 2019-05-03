package com.project.bootfx.app.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="wojewodztwo")
public class Wojewodztwo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="nazwa")
    private String nazwa;

    @OneToMany(mappedBy = "wojewodztwo", fetch = FetchType.EAGER)
    private List<Miasto> miasta;

    public Wojewodztwo() {
    }

    public Wojewodztwo(String nazwa) {
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

    public List<Miasto> getMiasta() {
        return new ArrayList<Miasto>(miasta);
    }

    public void addMiasto(Miasto miasto){
        if(miasta == null){
            miasta = new ArrayList<>();
            miasta.add(miasto);
            miasto.setWojewodztwo(this);
            return;
        }
        if(miasta.contains(miasto))
            return;
        miasta.add(miasto);
        miasto.setWojewodztwo(this);
    }

    public void removeMiasto(Miasto miasto){
        if(!miasta.contains(miasto))
            return;
        miasta.remove(miasto);
        miasto.setWojewodztwo(null);
    }

    @Override
    public String toString() {
        return "Wojewodztwo{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                '}';
    }
}
