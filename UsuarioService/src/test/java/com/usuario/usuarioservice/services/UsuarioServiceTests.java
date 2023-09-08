package com.usuario.usuarioservice.services;

import com.usuario.usuarioservice.dto.request.EnderecoRequest;
import com.usuario.usuarioservice.dto.request.UsuarioRequest;
import com.usuario.usuarioservice.entity.Endereco;
import com.usuario.usuarioservice.entity.Usuario;
import com.usuario.usuarioservice.repository.EnderecoRepository;
import com.usuario.usuarioservice.repository.UsuarioRepository;
import com.usuario.usuarioservice.service.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTests {
    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    public void deveriaCadastrarUmNovoUsuarioComSucesso() {
        UsuarioRequest usuarioRequest = Mockito.mock(UsuarioRequest.class);
        Usuario usuario = Mockito.mock(Usuario.class);
        EnderecoRequest enderecoRequest = Mockito.mock(EnderecoRequest.class);
        Endereco endereco = Mockito.mock(Endereco.class);

        Mockito.when(usuarioRequest.cpf()).thenReturn("000.000.000-00");
        Mockito.when(usuarioRequest.nome()).thenReturn("Vini");
        Mockito.when(usuarioRequest.endereco()).thenReturn(enderecoRequest);
        Mockito.when(usuarioRequest.dependentes()).thenReturn(List.of("Joao"));
        Mockito.when(enderecoRepository.save(Mockito.any(Endereco.class))).thenReturn(endereco);
        Mockito.when(usuarioRepository.save(Mockito.any(Usuario.class))).thenReturn(usuario);

        usuarioService.cadastrarUsuario(usuarioRequest);

        ArgumentCaptor<Usuario> usuarioArgumentCaptor = ArgumentCaptor.forClass(Usuario.class);
        Mockito.verify(usuarioRepository, Mockito.times(1))
                .save(usuarioArgumentCaptor.capture());

        Usuario usuarioSalvo = usuarioArgumentCaptor.getValue();
        Assertions.assertEquals("Vini", usuarioSalvo.getNome());
        Assertions.assertEquals("000.000.000-00", usuarioSalvo.getCpf());
    }
}
