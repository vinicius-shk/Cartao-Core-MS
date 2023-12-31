package com.usuario.usuarioservice.entity;

import com.usuario.usuarioservice.dto.request.UsuarioRequest;
import com.usuario.usuarioservice.dto.response.UsuarioDependentesResponse;
import com.usuario.usuarioservice.dto.response.UsuarioDetalhesResponse;
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
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    public Usuario(UsuarioRequest dto){
        this.cpf = dto.cpf();
        this.nome = dto.nome();

    }

    public UsuarioResponse usuarioDto() {
        return new UsuarioResponse(this.cpf, this.nome);
    }

    public UsuarioDependentesResponse usuarioDependenteDto(List<String> dependentes) {
        return new UsuarioDependentesResponse(this.cpf, this.nome, dependentes);
    }

    public UsuarioDetalhesResponse usuarioDetalhesDto() {
        return new UsuarioDetalhesResponse(this.cpf, this.nome, this.endereco.enderecoDto());
    }

}
