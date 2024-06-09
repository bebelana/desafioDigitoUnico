package com.desafio.demo.services;


import com.desafio.demo.models.DigitoUnico;
import com.desafio.demo.repositories.UsuarioRepository;
import com.desafio.demo.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import static com.desafio.demo.services.CriptrografiaRSA.decodePrivateKey;
import static com.desafio.demo.services.CriptrografiaRSA.decrypt;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;


    @Autowired
    private CacheCalculosService cacheCalculo;

    public  Usuario getUsuarioId(int id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        usuario.setChavePrivada(null);
       return usuario;
    }

    public  List<Usuario> getUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioIdDesc(int id) throws Exception {

        Usuario retorno = usuarioRepository.findById(id).orElse(null);

        try {
            Usuario usu  = new Usuario();
            usu.setNome( decrypt(retorno.getNome(), decodePrivateKey( retorno.getChavePrivada())));
            usu.setEmail( decrypt(retorno.getEmail(), decodePrivateKey(retorno.getChavePrivada())));

            return  usu;
        }catch (Exception e){
            throw e;
        }
    }

    public Usuario salva(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getEmail() == null) {
            throw new IllegalArgumentException("Nome e email não podem ser nulos");
        }
        try {
            // Gera par de chaves
            KeyPair keyPair = CriptrografiaRSA.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            // Codifica e define as chaves
            usuario.setChavePublica(CriptrografiaRSA.encodePublicKey(publicKey));
            usuario.setChavePrivada(CriptrografiaRSA.encodePrivateKey(privateKey));

            // Criptografa e define nome e email
            usuario.setNome(setCriptografia(usuario.getNome(), publicKey));
            usuario.setEmail(setCriptografia(usuario.getEmail(), publicKey));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return usuarioRepository.save(usuario);
    }

    private String setCriptografia(String data, PublicKey key) throws Exception {
        if (data == null || key == null) {
            throw new IllegalArgumentException("Data e chave não podem ser nulos");
        }
        return CriptrografiaRSA.encrypt(data, key);
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

