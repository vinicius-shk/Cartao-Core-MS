package com.cartao.cartaoservice.dto.request;

import com.cartao.cartaoservice.dto.TipoCartao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriarNovoCartaoRequest {
    private String nomeTitular;
    private TipoCartao tipoCartao;

}