package com.project.bootfx.app.entity;

import com.project.bootfx.app.entity.keys.WykonawcaNaprawyKey;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name="wykonawca_naprawy")
public class WykonawcaNaprawy {

    @EmbeddedId
    private WykonawcaNaprawyKey id;

    @ManyToOne
    @MapsId("id_prac")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "id_prac")
    private Pracownik pracownik;

    @ManyToOne
    @MapsId("id_nap")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name="id_nap")
    private Naprawa naprawa;

    @Column(name = "czas_pracy")
    private int czasPracy;

    @Column(name = "wykonana_praca")
    private String wykonanaPraca;

    public WykonawcaNaprawy() {
        id = new WykonawcaNaprawyKey();
    }

    public WykonawcaNaprawy(int czasPracy, String wykonanaPraca) {
        id = new WykonawcaNaprawyKey();
        this.czasPracy = czasPracy;
        this.wykonanaPraca = wykonanaPraca;
    }

    public WykonawcaNaprawyKey getId() {
        return id;
    }

    public void setId(WykonawcaNaprawyKey id) {
        this.id = id;
    }

    public Pracownik getPracownik() {
        return pracownik;
    }

    public void setPracownik(Pracownik pracownik) {
        if(sameAsFormer(pracownik))
            return;
        Pracownik oldPracownik = this.pracownik;
        this.pracownik = pracownik;
        if(oldPracownik!=null)
            oldPracownik.removeListaNapraw(this);
        if(pracownik!=null) {
            pracownik.addListaNapraw(this);
            id.setIdPrac(pracownik.getId()); // IMPORTANT CHANGE!!!!
        }
    }

    private boolean sameAsFormer(Pracownik newPracownik) {
        return pracownik==null? newPracownik == null : pracownik.equals(newPracownik);
    }

    public Naprawa getNaprawa() {
        return naprawa;
    }

    public void setNaprawa(Naprawa naprawa) {
        if(sameAsFormer2(naprawa))
            return;
        Naprawa oldNaprawa = this.naprawa;
        this.naprawa = naprawa;
        if(oldNaprawa!=null)
            oldNaprawa.removeListaPracownikow(this);
        if(naprawa!=null) {
            naprawa.addListaPracownikow(this);
            id.setIdNap(naprawa.getId()); // IMPORTANT CHANGE!!!!
        }
    }

    private boolean sameAsFormer2(Naprawa newNaprawa) {
        return naprawa==null? newNaprawa == null : naprawa.equals(newNaprawa);
    }

    public int getCzasPracy() {
        return czasPracy;
    }

    public void setCzasPracy(int czasPracy) {
        this.czasPracy = czasPracy;
    }

    public String getWykonanaPraca() {
        return wykonanaPraca;
    }

    public void setWykonanaPraca(String wykonanaPraca) {
        this.wykonanaPraca = wykonanaPraca;
    }

    @Override
    public String toString() {
        return "WykonawcaNaprawy{" +
                "id=" + id +
                ", pracownik=" + pracownik +
                ", naprawa=" + naprawa +
                ", czasPracy=" + czasPracy +
                ", wykonanaPraca='" + wykonanaPraca + '\'' +
                '}';
    }
}
