package com.cartao.cartaoservice.dto.response;

import com.cartao.cartaoservice.dto.TipoCartao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartaoResponse {
    private String numeroCartao;
    private String nomeTitularCartao;

    private TipoCartao tipoCartao;

    private String nomeTitularConta;
}
