package com.desafio.demo.controllers;

import com.desafio.demo.services.DigitoUnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculo")
public class CalculaDigitoUnicoController {

    @Autowired
    private DigitoUnicoService digitoUnicoService;

    @PostMapping
    public ResponseEntity<Integer> calcularDigitoUnico(@RequestParam String n, @RequestParam int c) {
        return ResponseEntity.ok(digitoUnicoService.calculaDigitoUnico(n, c));
    }
}
