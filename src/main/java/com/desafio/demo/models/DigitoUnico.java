package com.desafio.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DigitoUnico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String n;
    private int c;
    private int resultado;

    public DigitoUnico(String n, int c, int resultado) {
        this.n = n;
        this.c = c;
        this.resultado = resultado;
    }

    // Métodos getters e setters
    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }
}
