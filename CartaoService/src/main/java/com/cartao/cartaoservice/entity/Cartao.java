package com.cartao.cartaoservice.entity;

import com.cartao.cartaoservice.dto.TipoCartao;
import com.cartao.cartaoservice.dto.request.UsuarioRabbitMQEvent;
import com.cartao.cartaoservice.dto.response.CartaoResponse;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "cartao")
@NoArgsConstructor
public class Cartao {
    @Id
    private String numeroCartao;

    private String nomeTitular;
    private LocalDate vencimentoCartao;
    private String codigoSeguranca;
    private TipoCartao tipoCartao;
    private String idContaBanco;
    private Boolean dependente = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "usuarioIdentificador")
    private Usuario usuario;

    public Cartao(UsuarioRabbitMQEvent dto, String codigoSeguranca, String numeroCartao, Usuario user) {
        LocalDate dataAtual = LocalDate.now();
        this.nomeTitular = dto.getNome();
        this.tipoCartao = dto.getTipoCartao();
        this.usuario = user;
        this.dependente = Boolean.TRUE;
        this.idContaBanco = UUID.randomUUID().toString();
        this.vencimentoCartao = dataAtual.plusYears(5);
        this.codigoSeguranca = codigoSeguranca;
        this.numeroCartao = numeroCartao;
    }

    public Cartao(String dependente, String codigoSeguranca, String numeroCartao, Usuario user) {
        LocalDate dataAtual = LocalDate.now();
        this.nomeTitular = dependente;
        this.tipoCartao = TipoCartao.PRATA;
        this.usuario = user;
        this.idContaBanco = UUID.randomUUID().toString();
        this.vencimentoCartao = dataAtual.plusYears(5);
        this.codigoSeguranca = codigoSeguranca;
        this.numeroCartao = numeroCartao;
    }

    public CartaoResponse dtoResponse(){
        return new CartaoResponse(this.numeroCartao,this.nomeTitular, this.tipoCartao, this.usuario.getNome(), null);

    }

}