package com.project.bootfx.app.entity.keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UzytyMaterialKey implements Serializable {

    @Column(name = "id_mat")
    Long materialId;

    @Column(name = "id_nap")
    Long naprawaId;

    public UzytyMaterialKey() {
    }

    public UzytyMaterialKey(Long materialId, Long naprawaId) {
        this.materialId = materialId;
        this.naprawaId = naprawaId;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Long getNaprawaId() {
        return naprawaId;
    }

    public void setNaprawaId(Long naprawaId) {
        this.naprawaId = naprawaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UzytyMaterialKey that = (UzytyMaterialKey) o;
        return materialId.equals(that.materialId) &&
                naprawaId.equals(that.naprawaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(materialId, naprawaId);
    }
}
