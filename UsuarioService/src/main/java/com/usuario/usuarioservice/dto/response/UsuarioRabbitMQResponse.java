package com.usuario.usuarioservice.dto.response;

import com.usuario.usuarioservice.dto.TipoCartao;

import java.util.List;

public record UsuarioRabbitMQResponse(
        String identificador,
        String nome,
        TipoCartao tipoCartao,
        List<String> dependentes
) {
}
