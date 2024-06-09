package com.desafio.demo.services;


import com.desafio.demo.models.DigitoUnico;
import com.desafio.demo.models.Usuario;
import com.desafio.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;


    @Autowired
    private CacheCalculosService cacheCalculo;

    public  Usuario getUsuarioId(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public  Usuario salva(Usuario usuario) {
        return  usuarioRepository.save(usuario);
    }

    public  void deletaUsuarioId(int id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario adicionarResultado(int id, DigitoUnico resultado) {
        Usuario usuario = getUsuarioId(id);
        usuario.getResultados().add(resultado);
        return usuarioRepository.save(usuario);
    }

    public List<DigitoUnico> getResultados(int id) {
        Usuario usuario = getUsuarioId(id);
        return usuario.getResultados();
    }

}
