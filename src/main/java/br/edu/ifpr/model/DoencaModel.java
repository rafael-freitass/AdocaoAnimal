package br.edu.ifpr.model;

import jakarta.persistence.*;

@Entity
@Table(name = "doencas")
public class DoencaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_doenca;

    @Column(nullable = false)
    private String nome;

    // Getters e Setters
    public Long getId_doenca() {
        return id_doenca;
    }

    public void setId_doenca(Long id_doenca) {
        this.id_doenca = id_doenca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
