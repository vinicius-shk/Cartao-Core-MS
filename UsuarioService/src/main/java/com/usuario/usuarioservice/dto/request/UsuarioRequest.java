package com.usuario.usuarioservice.dto.request;

import com.usuario.usuarioservice.dto.TipoCartao;

import java.util.List;

public record UsuarioRequest(String cpf,
                             String nome,
                             List<String> dependentes,
                             TipoCartao tipoCartao,
                             EnderecoRequest endereco
) {
}
