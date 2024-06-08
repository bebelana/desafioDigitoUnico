package com.desafio.demo.services;


import com.desafio.demo.models.Usuario;
import com.desafio.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
