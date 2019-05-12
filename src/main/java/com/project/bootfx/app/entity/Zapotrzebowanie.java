package com.project.bootfx.app.entity;

import javax.persistence.*;

@Entity
@Table(name="zapotrzebowanie")
public class Zapotrzebowanie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="ilosc")
    private int ilosc;

    @ManyToOne
    @JoinColumn(name="id_mat")
    private Material material;

    public Zapotrzebowanie() {
    }

    public Zapotrzebowanie(int ilosc, String status) {
        this.ilosc = ilosc;
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

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        if(sameAsFormer(material))
            return;
        Material oldMaterial = this.material;
        this.material = material;
        if(oldMaterial!=null)
            oldMaterial.removeZapotrzebowanie(this);
        if(material!=null)
            material.addZapotrzebowanie(this);
    }

    private boolean sameAsFormer(Material newMaterial) {
        return material==null? newMaterial == null : material.equals(newMaterial);
    }

    @Override
    public String toString() {
        return "Zapotrzebowanie{" +
                "id=" + id +
                ", ilosc='" + ilosc + '\'' +
                ", material=" + material +
                '}';
    }
}
