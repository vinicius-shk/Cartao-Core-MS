package com.usuario.usuarioservice.entity;

import com.usuario.usuarioservice.dto.request.EnderecoRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "endereco")
@NoArgsConstructor
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cep;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String complemento;
    private String numero;

    public Endereco(EnderecoRequest dto) {
        this.bairro = dto.bairro();
        this.cep = dto.cep();
        this.cidade = dto.cidade();
        this.estado = dto.estado();
        this.complemento = dto.complemento();
        this.numero = dto.numero();
        this.rua = dto.rua();
    }
}
