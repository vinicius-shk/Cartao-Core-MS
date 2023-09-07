package com.cartao.cartaoservice.controller;


import com.cartao.cartaoservice.dto.request.CartaoRequest;
import com.cartao.cartaoservice.dto.response.CartaoResponse;
import com.cartao.cartaoservice.service.CartaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/cartao")
@Slf4j
public class CartaoController {
    private final CartaoService cartaoService;

    public CartaoController(CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }

    @PostMapping(path = "", produces = "application/json")
    public CartaoResponse adicionarCartao(@RequestBody CartaoRequest body) {

        return this.cartaoService.execute(body);
    }
}

