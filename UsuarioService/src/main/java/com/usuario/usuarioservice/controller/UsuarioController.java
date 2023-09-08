package com.usuario.usuarioservice.controller;

import com.usuario.usuarioservice.dto.request.UsuarioRequest;
import com.usuario.usuarioservice.dto.response.UsuarioDependentesResponse;
import com.usuario.usuarioservice.dto.response.UsuarioDetalhesResponse;
import com.usuario.usuarioservice.dto.response.UsuarioRabbitMQEvent;
import com.usuario.usuarioservice.dto.response.UsuarioResponse;
import com.usuario.usuarioservice.entity.Usuario;
import com.usuario.usuarioservice.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final RabbitTemplate rabbitTemplate;
    private static final String ROUTING_KEY = "usuarios.v1.criar-usuario";

    @PostMapping(path = "", produces = "application/json")
    public UsuarioDependentesResponse cadastrarUsuario(@RequestBody UsuarioRequest body) {
        UsuarioDependentesResponse usuarioDependentesResponse = usuarioService.cadastrarUsuario(body);

        // Mensageria para RabbitMQ com fluxo
        UsuarioRabbitMQEvent event = new UsuarioRabbitMQEvent(body);
        rabbitTemplate.convertAndSend(ROUTING_KEY, event);
        return usuarioDependentesResponse;
    }

    @GetMapping(path = "", produces = "application/json")
    public List<UsuarioResponse> buscarTodos() {
        return usuarioService.buscarTodos();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<UsuarioDetalhesResponse> buscarPorId(@PathVariable String id) {
        Optional<Usuario> usuario = usuarioService.buscarPorId(id);
        return usuario.map(value -> ResponseEntity.status(HttpStatus.OK).body(value.usuarioDetalhesDto()))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<UsuarioDetalhesResponse> atualizarUsuario(@RequestBody UsuarioRequest body,
                                                                    @PathVariable String id) {
        Optional<Usuario> usuario = usuarioService.atualizarUsuario(body, id);
        return usuario.map(value -> ResponseEntity.status(HttpStatus.OK).body(value.usuarioDetalhesDto()))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<UsuarioDetalhesResponse> deletarUsuario(@PathVariable String id) {
        Optional<Usuario> usuario = usuarioService.deletarUsuario(id);
        return usuario.map(value -> ResponseEntity.status(HttpStatus.OK).body(value.usuarioDetalhesDto()))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
