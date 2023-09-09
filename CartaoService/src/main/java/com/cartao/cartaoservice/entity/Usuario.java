package com.cartao.cartaoservice.entity;

import com.cartao.cartaoservice.dto.request.UsuarioRabbitMQEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "usuario")
@NoArgsConstructor
public class Usuario {
    @Id
    private String identificador;
    private String nome;

    public Usuario(UsuarioRabbitMQEvent dto){
        this.identificador = dto.getIdentificador();
        this.nome = dto.getNome();

    }

}