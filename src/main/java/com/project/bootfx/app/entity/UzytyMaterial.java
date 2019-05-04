package com.project.bootfx.app.entity;

import com.project.bootfx.app.entity.keys.UzytyMaterialKey;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name="uzyty_material")
public class UzytyMaterial {

    @EmbeddedId
    UzytyMaterialKey id;

    @ManyToOne
    @MapsId("id_mat")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "id_mat")
    Material material;

    @ManyToOne
    @MapsId("id_nap")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "id_nap")
    Naprawa naprawa;

    @Column(name="ilosc")
    private int ilosc;

    public UzytyMaterial() {
    }

    public UzytyMaterial(int ilosc) {
        this.ilosc = ilosc;
    }

    public UzytyMaterialKey getId() {
        return id;
    }

    public void setId(UzytyMaterialKey id) {
        this.id = id;
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
            oldMaterial.removeUzytyMaterial(this);
        if(material!=null)
            material.addUzytyMaterial(this);
    }

    public Naprawa getNaprawa() {
        return naprawa;
    }

    public void setNaprawa(Naprawa naprawa) {
        if(sameAsFormer(naprawa))
            return;
        Naprawa oldNaprawa = this.naprawa;
        this.naprawa = naprawa;
        if(oldNaprawa!=null)
            oldNaprawa.removeUzytyMaterial(this);
        if(naprawa!=null)
            naprawa.addUzytyMaterial(this);
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    private boolean sameAsFormer(Material newMaterial) {
        return material==null? newMaterial == null : material.equals(newMaterial);
    }

    private boolean sameAsFormer(Naprawa newNaprawa) {
        return naprawa==null? newNaprawa == null : naprawa.equals(newNaprawa);
    }

    @Override
    public String toString() {
        return "UzytyMaterial{" +
                "id=" + id.toString() +
                ", ilosc=" + ilosc +
                '}';
    }
}
