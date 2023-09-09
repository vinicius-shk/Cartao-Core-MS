package com.usuario.usuarioservice.dto.request;

public record EnderecoRequest(
        String cep,
        String rua,
        String bairro,
        String cidade,
        String estado,
        String complemento,
        String numero
) {
}
