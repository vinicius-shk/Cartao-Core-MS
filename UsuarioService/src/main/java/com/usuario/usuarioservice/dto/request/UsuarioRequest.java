package com.usuario.usuarioservice.dto.request;

import java.util.List;

public record UsuarioRequest(String cpf, String nome, String sobrenome, List<String> dependentes) {
}
