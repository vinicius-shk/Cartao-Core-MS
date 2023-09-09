package com.usuario.usuarioservice.service;

import com.usuario.usuarioservice.dto.request.UsuarioRequest;
import com.usuario.usuarioservice.dto.response.UsuarioDependentesResponse;
import com.usuario.usuarioservice.dto.response.UsuarioResponse;
import com.usuario.usuarioservice.entity.Endereco;
import com.usuario.usuarioservice.entity.Usuario;
import com.usuario.usuarioservice.repository.EnderecoRepository;
import com.usuario.usuarioservice.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;

    public UsuarioDependentesResponse cadastrarUsuario(UsuarioRequest dto) {
        Usuario usuario = new Usuario(dto);
        Endereco endereco = enderecoRepository.save(new Endereco(dto.endereco()));
        usuario.setEndereco(endereco);
        usuarioRepository.save(usuario);

        return usuario.usuarioDependenteDto(dto.dependentes());
    }

    public List<UsuarioResponse> buscarTodos() {
        return usuarioRepository.findAll().stream().map(Usuario::usuarioDto).toList();
    }

    public Optional<Usuario> buscarPorId(String id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> atualizarUsuario(UsuarioRequest dto, String id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            Endereco enderecoAtualizado = new Endereco(dto.endereco());
            usuario.get().setEndereco(enderecoAtualizado);
            usuarioRepository.save(usuario.get());
            return usuario;
        }
        return Optional.empty();
    }

    public Optional<Usuario> deletarUsuario(String id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            usuarioRepository.delete(usuario.get());
            return usuario;
        }
        return Optional.empty();
    }
}
