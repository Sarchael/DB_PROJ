package com.project.bootfx.app.entity;

import com.project.bootfx.app.entity.keys.ZamowionyMaterialKey;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
public class ZamowionyMaterial {

    @EmbeddedId
    ZamowionyMaterialKey id;

    @ManyToOne
    @MapsId("id_mat")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "id_mat")
    private Material material;

    @ManyToOne
    @MapsId("id_zam")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "id_zam")
    Zamowienie zamowienie;

    ZamowionyMaterial() {

    }

    public ZamowionyMaterialKey getId() {
        return id;
    }

    public void setId(ZamowionyMaterialKey id) {
        this.id = id;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        if (sameAsFormer(material))
            return;
        Material oldMaterial = this.material;
        this.material = material;
        if (oldMaterial != null)
            oldMaterial.removeZamowionyMaterial(this);
        if (material != null)
            material.addZamowionyMaterial(this);
    }

    private boolean sameAsFormer(Material newMaterial) {
        return material == null ? newMaterial == null : material.equals(newMaterial);
    }

    public void setZamowienie(Zamowienie zamowienie) {
        if (sameAsFormer(zamowienie))
            return;
        Zamowienie oldZamowienie = this.zamowienie;
        this.zamowienie = zamowienie;
        if (oldZamowienie != null)
            oldZamowienie.removeZamowionyMaterial(this);
        if (zamowienie != null)
            zamowienie.addZamowionyMaterial(this);
    }

    private boolean sameAsFormer(Zamowienie neZamowienie) {
        return zamowienie == null ? neZamowienie == null : zamowienie.equals(neZamowienie);
    }

    public Zamowienie getZamowienie() {
        return zamowienie;
    }
}
