package com.cartao.cartaoservice.dto.request;

import lombok.Getter;
import lombok.Setter;
import com.cartao.cartaoservice.dto.TipoCartao;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CartaoRequest {

    private String identificador;

    private String nome;

    private TipoCartao tipoCartao;

    private List<String> dependentes;

}






