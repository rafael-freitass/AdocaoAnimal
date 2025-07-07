package br.edu.ifpr.model;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name="adocoes")
public class AdocaoModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adocao")
    private Integer ID;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_adocao", nullable = false)
    private Date dataAdocao = new Date();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_animal", nullable = false)
    private AnimalModel animal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioModel cliente;

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

}