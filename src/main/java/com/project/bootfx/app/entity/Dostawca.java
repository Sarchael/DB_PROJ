package com.project.bootfx.app.entity;

import javax.persistence.*;

@Entity
@Table(name="dostawca")
public class Dostawca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name ="nazwa")
    private String nazwa;

    @Column(name ="nip")
    private String nip;


}
