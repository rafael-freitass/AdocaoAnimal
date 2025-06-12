package br.edu.ifpr.model;

import jakarta.persistence.*;

@Entity
@Table(name="animais")
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

    @Column(nullable = false, length = 200)
    private String vacina;

    @Column(length = 200)
    private String doenca;

    @Column(nullable = false)
    private boolean disponivelAdocao = true;

    @Column(nullable = false)
    private boolean castrado;

    @Column(length = 500)
    private String descricao;

    @Column(length = 200)
    private String foto;

    // Getters e Setters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getVacina() {
        return vacina;
    }

    public void setVacina(String vacina) {
        this.vacina = vacina;
    }

    public String getDoenca() {
        return doenca;
    }

    public void setDoenca(String doenca) {
        this.doenca = doenca;
    }

    public boolean isDisponivelAdocao() {
        return disponivelAdocao;
    }

    public void setDisponivelAdocao(boolean disponivelAdocao) {
        this.disponivelAdocao = disponivelAdocao;
    }

    public boolean isCastrado() {
        return castrado;
    }

    public void setCastrado(boolean castrado) {
        this.castrado = castrado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}