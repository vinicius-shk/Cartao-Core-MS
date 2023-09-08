package com.usuario.usuarioservice.dto.response;

import com.usuario.usuarioservice.dto.TipoCartao;
import com.usuario.usuarioservice.dto.request.UsuarioRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class UsuarioRabbitMQEvent {
        private String identificador;
        private String nome;
        private TipoCartao tipoCartao;
        private List<String> dependentes;

        public UsuarioRabbitMQEvent(UsuarioRequest body) {
            this.identificador = body.cpf();
            this.nome = body.nome();
            this.tipoCartao = body.tipoCartao();
            this.dependentes = body.dependentes();
        }
}
