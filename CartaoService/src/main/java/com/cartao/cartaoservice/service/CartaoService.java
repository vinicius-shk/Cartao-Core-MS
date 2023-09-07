package com.cartao.cartaoservice.service;

import com.cartao.cartaoservice.dto.TipoCartao;
import com.cartao.cartaoservice.dto.request.CartaoRequest;
import com.cartao.cartaoservice.dto.response.CartaoResponse;
import com.cartao.cartaoservice.dto.response.DependentesResponse;
import com.cartao.cartaoservice.entity.Cartao;
import com.cartao.cartaoservice.entity.Usuario;
import com.cartao.cartaoservice.repository.CartaoRepository;
import com.cartao.cartaoservice.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class CartaoService {

    private final CartaoRepository cartaoRepository;
    private final UsuarioRepository usuarioRepository;
    private static Random random;

    public CartaoResponse execute(CartaoRequest cadastroRequest) {

        boolean exists = usuarioRepository.existsById(cadastroRequest.getIdentificador());
        if (!exists) {
            Usuario usuario = new Usuario(cadastroRequest);
            usuarioRepository.save(usuario);
        }
        Usuario usuario = usuarioRepository.findById(cadastroRequest.getIdentificador())
                .orElseThrow();

        Cartao cartao = new Cartao(cadastroRequest ,gerarNumeroAleatorio(3), gerarNumeroAleatorio(12), usuario);
        cartaoRepository.save(cartao);

        List<DependentesResponse> listaDependentes = new ArrayList<>();

        if (!cadastroRequest.getDependentes().isEmpty()){
            listaDependentes =  cadastrarDependentes(cadastroRequest.getDependentes(), usuario);
        }
        return new CartaoResponse(cartao.getNumeroCartao(), cartao.getNomeTitular(), cartao.getTipoCartao(), usuario.getNome(), listaDependentes);
    }

    public List<DependentesResponse> cadastrarDependentes(List<String> dependentes, Usuario usuario) {

        List<Cartao> listaCartoes = new ArrayList<>();
        List<DependentesResponse> listaResposta = new ArrayList<>();

        for (String dependente : dependentes) {
            Cartao cartao = new Cartao(dependente ,gerarNumeroAleatorio(3), gerarNumeroAleatorio(12), usuario);
            listaCartoes.add(cartao);
            listaResposta.add(new DependentesResponse(cartao.getNumeroCartao(), cartao.getNomeTitular(),
                    cartao.getTipoCartao()));
        }
        cartaoRepository.saveAll(listaCartoes);
        return listaResposta;
    }


    private String gerarNumeroAleatorio(int length) {

        final int tamanho = length <= 0 ? 1 : length;
        IntStream stream = getRandom().ints(tamanho, 0, 9);
        StringBuilder builder = new StringBuilder();
        stream.forEachOrdered(builder::append);
        return builder.toString();
    }

    private static Random getRandom() {
        if (Objects.isNull(random)) {
            random = new Random();
        }
        return random;
    }
}


