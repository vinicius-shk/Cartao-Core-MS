package com.cartao.cartaoservice.entity;

import com.cartao.cartaoservice.dto.TipoCartao;
import com.cartao.cartaoservice.dto.request.CartaoRequest;
import com.cartao.cartaoservice.dto.request.CriarNovoCartaoRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

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

    public Cartao(CartaoRequest dto, String codigoSeguranca, String numeroCartao, Usuario user) {
        LocalDate dataAtual = LocalDate.now();
        this.nomeTitular = dto.getNome();
        this.tipoCartao = dto.getTipoCartao();
        this.usuario = user;
        this.idContaBanco = UUID.randomUUID().toString();
        this.vencimentoCartao = dataAtual.plusYears(5);
        this.codigoSeguranca = codigoSeguranca;
        this.numeroCartao = numeroCartao;

    }



}