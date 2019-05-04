package com.project.bootfx.app.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="naprawa")
public class Naprawa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "opis")
    private String opis;

    @Column(name="czas_naprawy")
    private int czasNaprawy;

    @Column(name = "data")
    private Date data;

    @Column(name = "postep")
    private String postep;

    private Samochod samochod;

    private Pracownik pracownik;
}
