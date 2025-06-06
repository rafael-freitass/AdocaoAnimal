package br.edu.ifpr.model;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name="adocoes")
public class AdocaoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(nullable = false)
    private Date dataAdocao;

    @ManyToOne
    @JoinColumn(name = "id_animal", nullable = false)
    private AnimalModel animal;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private UsuarioModel cliente;

    @Column(length = 500)
    private String observacoes;

    // Getters e Setters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getDataAdocao() {
        return dataAdocao;
    }

    public void setDataAdocao(Date dataAdocao) {
        this.dataAdocao = dataAdocao;
    }

    public AnimalModel getAnimal() {
        return animal;
    }

    public void setAnimal(AnimalModel animal) {
        this.animal = animal;
    }

    public UsuarioModel getCliente() {
        return cliente;
    }

    public void setCliente(UsuarioModel cliente) {
        this.cliente = cliente;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}