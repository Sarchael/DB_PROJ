package com.project.bootfx.app.entity;

import javax.persistence.*;

@Entity
@Table(name="uzytkownik")
public class Uzytkownik {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="login")
    private String login;

    @Column(name="haslo")
    private String haslo;

    @Column(name="rola")
    private String rola;

    public Uzytkownik() {
    }

    public Uzytkownik(String login, String haslo, String rola) {
        this.login = login;
        this.haslo = haslo;
        this.rola = rola;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getRola() {
        return rola;
    }

    public void setRola(String rola) {
        this.rola = rola;
    }

    @Override
    public String toString() {
        return "Uzytkownik{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", haslo='" + haslo + '\'' +
                ", rola='" + rola + '\'' +
                '}';
    }
}
