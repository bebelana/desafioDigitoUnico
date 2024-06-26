package com.desafio.demo.controllers;



import com.desafio.demo.models.DigitoUnico;
import com.desafio.demo.models.Usuario;
import com.desafio.demo.services.CacheCalculosService;
import com.desafio.demo.services.DigitoUnicoService;
import com.desafio.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService ;
    @Autowired
    private DigitoUnicoService digitoUnicoService ;
    @Autowired
    private final CacheCalculosService cacheCalculosService = new CacheCalculosService();

    @GetMapping("/{id}")
    public Usuario getUsuarioId(@PathVariable int id) {
        return usuarioService.getUsuarioId(id);
    }
    @GetMapping("/{id}/descriptografado")
    public Usuario getUsuarioIdDescrip(@PathVariable int id) throws Exception {
        return usuarioService.getUsuarioIdDesc(id);
    }

    @GetMapping("/")
    public  List<Usuario> getUsuarios(){
        return usuarioService.getUsuarios();
    }

    @PostMapping
    public Usuario criaUsuario(@RequestBody Usuario Usuario) {
        try {
            return usuarioService.salva(Usuario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable int id, @RequestBody Usuario Usuario) {
        try {
            Usuario.setId(id);
            return usuarioService.salva(Usuario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable int id) {
        usuarioService.deletaUsuarioId(id);
    }


    @PostMapping("/{id}/calcular")
    public ResponseEntity<Usuario> calcularDigitoUnico(@PathVariable int id, @RequestParam String n, @RequestParam int c) {
        int resultado = digitoUnicoService.calculaDigitoUnico(n, c);
        DigitoUnico digitoUnicoResultado = new DigitoUnico();
        digitoUnicoResultado.setN(n);
        digitoUnicoResultado.setC(c);
        digitoUnicoResultado.setResultado(resultado);

        Usuario usuarioAtualizado = usuarioService.adicionarResultado(id, digitoUnicoResultado);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @GetMapping("/{id}/resultados")
    public ResponseEntity<List<DigitoUnico>> getResultados(@PathVariable int id) {
        return ResponseEntity.ok(usuarioService.getResultados(id));
    }
}
   