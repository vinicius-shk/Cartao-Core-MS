package com.cartao.cartaoservice.dto.request;

import lombok.*;
import com.cartao.cartaoservice.dto.TipoCartao;

import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class UsuarioRabbitMQEvent {

    private String identificador;

    private String nome;

    private TipoCartao tipoCartao;

    private List<String> dependentes;

}






