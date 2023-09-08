package com.usuario.usuarioservice.dto.response;

import java.util.List;

public record UsuarioDependentesResponse(String cpf, String nome, List<String> dependentes) {
}
