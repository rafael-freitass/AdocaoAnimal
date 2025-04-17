package br.edu.ifpr.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
public class AdocaoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(nullable = false)
    private Date dataAdocao;
}
