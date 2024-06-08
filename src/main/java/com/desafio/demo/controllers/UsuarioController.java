package com.desafio.demo.controllers;



import com.desafio.demo.models.Usuario;
import com.desafio.demo.services.CacheCalculosService;
import com.desafio.demo.services.DigitoUnicoService;
import com.desafio.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public Usuario criaUsuario(@RequestBody Usuario Usuario) {
        return usuarioService.salva(Usuario);
    }

    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable int id, @RequestBody Usuario Usuario) {
        Usuario.setId(id);
        return usuarioService.salva(Usuario);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable int id) {
        usuarioService.deletaUsuarioId(id);
    }


}
   