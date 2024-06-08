package com.desafio.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DigitoUnicoService {
    @Autowired
    private UsuarioService usuarioService;
    private CacheCalculosService cache = new CacheCalculosService();


    public int calculaDigitoUnico(String n, int c) {
        int somaInicial = somaDigitos(n);
        int somaTotal = somaInicial * c;
        return digitoUnicoRecursivo(somaTotal);
    }

    public int somaDigitos(String n) {
        int soma = 0;
        for (char digito : n.toCharArray()) {
            soma += Character.getNumericValue(digito);
        }
        return soma;
    }

    public int digitoUnicoRecursivo(int num) {
        if (num < 10) {
            return num;
        } else {
            int novaSoma = 0;
            while (num > 0) {
                novaSoma += num % 10;
                num /= 10;
            }
            return digitoUnicoRecursivo(novaSoma);
        }
    }
}