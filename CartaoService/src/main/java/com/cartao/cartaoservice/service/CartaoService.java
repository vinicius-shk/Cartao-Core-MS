package com.cartao.cartaoservice.service;

import com.cartao.cartaoservice.dto.TipoCartao;
import com.cartao.cartaoservice.dto.request.CartaoRequest;
import com.cartao.cartaoservice.dto.response.CartaoResponse;
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

        // Usuariosuario usuario = usuarioRepository.findById(cadastroRequest.getIdentificador())
        //  .orElseThrow();
        Usuario usuario = new Usuario(cadastroRequest);
        usuarioRepository.save(usuario);

        Cartao cartao = new Cartao(cadastroRequest ,gerarNumeroAleatorio(3), gerarNumeroAleatorio(12), usuario);
        cartaoRepository.save(cartao);
        return new CartaoResponse(cartao.getNumeroCartao(), cartao.getNomeTitular(),
                cartao.getTipoCartao(), usuario.getNome());
    }

    public List<CartaoResponse> cadastrarDependentes(CartaoRequest cadastroRequest) {
        Usuario usuario = usuarioRepository.findById(cadastroRequest.getIdentificador())
                .orElseThrow();

        List<Cartao> listaCartoes = new ArrayList<>();
        List<CartaoResponse> listaResposta = new ArrayList<>();

        for (String dependente : cadastroRequest.getDependentes()) {
            Cartao cartao = new Cartao(cadastroRequest ,gerarNumeroAleatorio(3), gerarNumeroAleatorio(12), usuario);
            cartao.setDependente(true);
            cartao.setTipoCartao(TipoCartao.PRATA);
            cartao.setNomeTitular(dependente);
            listaCartoes.add(cartao);
            listaResposta.add(new CartaoResponse(cartao.getNumeroCartao(), cartao.getNomeTitular(),
                    cartao.getTipoCartao(), usuario.getNome()));
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


