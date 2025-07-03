package br.edu.ifpr.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vacinas")
public class VacinaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_vacina;

    @Column(nullable = false)
    private String nome;

    // Getters e Setters
    public Long getId_vacina() {
        return id_vacina;
    }

    public void setId_vacina(Long id_vacina) {
        this.id_vacina = id_vacina;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
