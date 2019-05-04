package com.project.bootfx.app.entity.keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class WykonawcaNaprawyKey implements Serializable {

    @Column(name="id_nap")
    private int idNap;

    @Column(name="id_prac")
    private int idPrac;

    public WykonawcaNaprawyKey() {
    }

    public int getIdNap() {
        return idNap;
    }

    public void setIdNap(int idNap) {
        this.idNap = idNap;
    }

    public int getIdPrac() {
        return idPrac;
    }

    public void setIdPrac(int idPrac) {
        this.idPrac = idPrac;
    }

    @Override
    public String toString() {
        return "WykonawcaNaprawyKey{" +
                "idNap=" + idNap +
                ", idPrac=" + idPrac +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WykonawcaNaprawyKey that = (WykonawcaNaprawyKey) o;
        return idNap == that.idNap &&
                idPrac == that.idPrac;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNap, idPrac);
    }
}
