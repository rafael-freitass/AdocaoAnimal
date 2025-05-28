package br.edu.ifpr.model;

import jakarta.persistence.*;

@Entity
@Table(name="enderecos")
public class EnderecoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private int ID;

    @Column(nullable = false, length = 100)
    private String rua;

    @Column(nullable = false, length = 100)
    private String bairro;

    @Column(nullable = false, length = 100)
    private String numero;

    @Column(nullable = false, length = 100)
    private String cidade;

    @Column(nullable = false, length = 100)
    private String cep;
}
