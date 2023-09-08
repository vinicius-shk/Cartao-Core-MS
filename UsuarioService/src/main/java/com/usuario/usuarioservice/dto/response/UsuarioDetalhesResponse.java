package com.usuario.usuarioservice.dto.response;

public record UsuarioDetalhesResponse(String cpf, String nome, String sobrenome, EnderecoResponse endereco) {
}
