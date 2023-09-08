package com.usuario.usuarioservice.dto.response;

public record EnderecoResponse(
        String cep,
        String rua,
        String bairro,
        String cidade,
        String estado,
        String complemento,
        String numero
) {
}
