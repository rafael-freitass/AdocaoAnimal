package br.edu.ifpr.model;

import br.edu.ifpr.model.enums.Role;
import jakarta.persistence.*;
import jdk.jfr.Name;

@Entity
@Table(name="usuarios")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int ID;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(unique = true, nullable = false, length = 11)
    private String cpf;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(nullable = false)
    private String senha;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    private EnderecoModel endereco;

    @Enumerated(EnumType.STRING)
    @Column(name = "id_role",nullable = false)
    private Role role;

    public Role getRole() {
        return role;
    }
}
