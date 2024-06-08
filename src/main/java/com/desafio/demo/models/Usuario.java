package com.desafio.demo.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Usuario {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DigitoUnico> resultados;

    public Usuario(){

    }

    public Usuario(String nome, String email, List<DigitoUnico> listaResultado){
        super();
        this.nome = nome;
        this.email = email;
        this.resultados = listaResultado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<DigitoUnico> getResultados() {
        return resultados;
    }

    public void setResultados(List<DigitoUnico> listaResultado) {
        this.resultados = listaResultado;
    }

}

