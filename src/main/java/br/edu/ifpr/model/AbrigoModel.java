package br.edu.ifpr.model;

import jakarta.persistence.*;

@Entity
public class AbrigoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "nome_abrigo", nullable = false, length = 100)
    private String nome;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    private EnderecoModel endereco;
}
