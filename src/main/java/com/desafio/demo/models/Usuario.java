package com.desafio.demo.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "nome", length = 2048)
    private String nome;

    @Column(name = "email", length = 2048)
    private String email;

    @Column(nullable = false, length = 2048)
    private String chavePublica;

    @Column(nullable = false, length = 2048)
    private String chavePrivada;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DigitoUnico> resultados;

    public Usuario(){
        this.resultados = new ArrayList<>();
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

    public void adicionarResultado(DigitoUnico resultado) {
        this.resultados.add(resultado);
    }

    public String getChavePublica() {
        return chavePublica;
    }

    public void setChavePublica(String chavePublica) {
        this.chavePublica = chavePublica;
    }

    public String getChavePrivada() {
        return chavePrivada;
    }

    public void setChavePrivada(String chavePrivada) {
        this.chavePrivada = chavePrivada;
    }

}

