package com.usuario.usuarioservice.controller;

import com.usuario.usuarioservice.dto.request.UsuarioRequest;
import com.usuario.usuarioservice.dto.response.UsuarioResponse;
import com.usuario.usuarioservice.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping(path = "", produces = "application/json")
    public UsuarioResponse cadastrarUsuario(@RequestBody UsuarioRequest body) {
        return usuarioService.cadastrarUsuario(body);
    }

    @PostMapping(path = "/dependentes", produces = "application/json")
    public ResponseEntity<UsuarioResponse> cadastrarDependentes(@RequestBody UsuarioRequest body) {
        UsuarioResponse usuario = usuarioService.cadastrarDependentes(body);
        if (usuario.dependentes().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.cadastrarDependentes(body));
    }

}
