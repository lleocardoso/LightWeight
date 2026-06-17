package com.LightWeight.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

    //Entidade que representa a tabela 'usuarios' no banco de dados
@Data
@EqualsAndHashCode(exclude = {"treinos"})
@ToString(exclude = {"treinos"})
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false)
    private Integer idade;

    @Column(nullable = false, unique = true, length = 254)
    private String email;

    @Column(nullable = false)
    private String senha;

    //Relacionamento 1 . n: se o usuário for deletado, todos os seus treinos associados também serão (CascadeType.ALL)
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Treino> treinos = new ArrayList<>();

}
