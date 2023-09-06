package com.usuario.usuarioservice.dto.response;

import java.util.List;

public record UsuarioResponse(String cpf, String nome, String sobrenome, List<String> dependentes) {
}
