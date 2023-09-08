package com.cartao.cartaoservice.controller;


import com.cartao.cartaoservice.dto.request.CartaoRequest;
import com.cartao.cartaoservice.dto.response.CartaoResponse;
import com.cartao.cartaoservice.service.CartaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cartao")
@Slf4j
public class CartaoController {
    private final CartaoService cartaoService;

    public CartaoController(CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }

    @PostMapping(path = "", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public CartaoResponse adicionarCartao(@RequestBody CartaoRequest body) {
        return this.cartaoService.execute(body);
    }

    @GetMapping("/{id}")
    public CartaoResponse getOne(@PathVariable String id) {
        return cartaoService.getOne(id);
    }

    @GetMapping
    public List<CartaoResponse> getAll(
            @RequestParam( value = "filter", defaultValue = "") String filter) {
        return cartaoService.getAll();
    }

    @PutMapping("/{id}")
    public CartaoResponse edit(@RequestBody CartaoRequest body, @PathVariable String id) {
        return cartaoService.edit(body, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        cartaoService.delete(id);
    }
}

