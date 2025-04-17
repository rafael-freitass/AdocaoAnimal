package br.edu.ifpr.model;

import jakarta.persistence.*;

@Entity
public class AnimalModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "nome_animal", nullable = false, length = 100)
    private String nome;

    @Column(nullable = false)
    private int idade;

    @Column(nullable = false, length = 50)
    private String raca;

    @Column(nullable = false, length = 1)
    private char sexo;

    @Column(nullable = false, length = 50)
    private String vacina;

    @Column(nullable = false, length = 50)
    private String doenca;

    @Column(nullable = false)
    private boolean disponivelAdocao;

    @Column(nullable = false)
    private boolean castrado;
}
