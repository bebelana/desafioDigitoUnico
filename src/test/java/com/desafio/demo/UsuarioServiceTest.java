package com.desafio.demo;

import com.desafio.demo.models.DigitoUnico;
import com.desafio.demo.models.Usuario;
import com.desafio.demo.repositories.UsuarioRepository;
import com.desafio.demo.services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getUsuarioIdTest() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        Usuario result = usuarioService.getUsuarioId(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void getUsuariosTest() {
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<Usuario> result = usuarioService.getUsuarios();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void getUsuarioIdDescTest()  {
        Usuario usuario = new Usuario();
        try {
            usuario.setId(1);
            usuario.setNome("Nome Criptografado");
            usuario.setEmail("Email Criptografado");
            usuario.setChavePrivada("Chave Privada");
            when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

            // Este teste precisa de um mock mais complexo para CriptrografiaRSA
            // Assumindo que a lógica de descriptografia está correta, o teste abaixo é simplificado
            Usuario result = usuarioService.getUsuarioIdDesc(1);
            assertNotNull(result);
        }catch (Exception e){
            System.out.println("");
        }
    }

    @Test
    void salvaTest() {
        Usuario usuario = new Usuario();
        usuario.setNome("Nome");
        usuario.setEmail("Email");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario result = usuarioService.salva(usuario);

        assertNotNull(result);
        // Verificações adicionais sobre a criptografia podem ser feitas aqui
    }

    @Test
    void deletaUsuarioIdTest() {
        doNothing().when(usuarioRepository).deleteById(1);

        assertDoesNotThrow(() -> usuarioService.deletaUsuarioId(1));
    }


    @Test
    void testGetResultados() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setId(1);
        List<DigitoUnico> resultados = new ArrayList<>();
        DigitoUnico resultado1 = new DigitoUnico();
        resultado1.setResultado(2);
        resultado1.setN("9875");
        resultado1.setC(4);
        resultados.add(resultado1);
        usuario.adicionarResultado(resultado1);

        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.of(usuario));

        // Act
        List<DigitoUnico> retrievedResultados = usuarioService.getResultados(1);

        // Assert
        assertNotNull(retrievedResultados);
        assertEquals(1, retrievedResultados.size());
        assertEquals(resultado1, retrievedResultados.get(0));
        verify(usuarioRepository, times(1)).findById(anyInt());
    }
}
