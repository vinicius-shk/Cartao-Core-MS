package com.cartao.cartaoservice.exceptions;

import lombok.Getter;

@Getter
public class NotFoundException {
   private final String entityNotFound;

    public NotFoundException(String entityNotFound) {
        this.entityNotFound = entityNotFound;
    }

}
