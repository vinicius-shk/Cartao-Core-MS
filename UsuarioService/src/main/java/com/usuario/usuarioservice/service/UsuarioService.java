package com.usuario.usuarioservice.service;

import com.usuario.usuarioservice.dto.request.UsuarioRequest;
import com.usuario.usuarioservice.dto.response.UsuarioResponse;
import com.usuario.usuarioservice.entity.Endereco;
import com.usuario.usuarioservice.entity.Usuario;
import com.usuario.usuarioservice.repository.EnderecoRepository;
import com.usuario.usuarioservice.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;

    public UsuarioResponse cadastrarUsuario(UsuarioRequest dto) {
        Usuario usuario = new Usuario(dto);
        Endereco endereco = enderecoRepository.save(new Endereco(dto.endereco()));
        usuario.setEndereco(endereco);
        usuarioRepository.save(usuario);

        // TODO inicio da saga enviando dados do usuario e lista de dependentes

        return usuario.usuarioDto(dto.dependentes());
    }

    public UsuarioResponse cadastrarDependentes(UsuarioRequest dto) {
        if (!dto.dependentes().isEmpty()) {
            // TODO logica de mensagem para orquestrador
        }
        return new UsuarioResponse(null, null, null, new ArrayList<>());
    }
}
