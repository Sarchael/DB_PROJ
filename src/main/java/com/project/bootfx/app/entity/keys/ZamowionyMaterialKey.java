package com.project.bootfx.app.entity.keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ZamowionyMaterialKey implements Serializable {

    @Column(name = "id_mat")
    private int id_mat;

    @Column(name = "id_zam")
    private int id_zam;

    public int getId_mat() {
        return id_mat;
    }

    public void setId_mat(int id_mat) {
        this.id_mat = id_mat;
    }

    public int getId_zam() {
        return id_zam;
    }

    public void setId_zam(int id_zam) {
        this.id_zam = id_zam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZamowionyMaterialKey that = (ZamowionyMaterialKey) o;
        return id_mat == that.id_mat &&
                id_zam == that.id_zam;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_mat, id_zam);
    }
}
