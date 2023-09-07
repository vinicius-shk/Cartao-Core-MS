package com.usuario.usuarioservice.entity;

import com.usuario.usuarioservice.dto.request.UsuarioRequest;
import com.usuario.usuarioservice.dto.response.UsuarioResponse;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "usuario")
@NoArgsConstructor
public class Usuario {
    @Id
    private String cpf;
    private String nome;
    private String sobrenome;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    public Usuario(UsuarioRequest dto){
        this.cpf = dto.cpf();
        this.nome = dto.nome();
        this.sobrenome = dto.sobrenome();

    }

    public UsuarioResponse usuarioDto(List<String> dependentes) {
        return new UsuarioResponse(this.cpf, this.nome, this.sobrenome, dependentes);
    }

}
