package com.cartao.cartaoservice.consumer;

import com.cartao.cartaoservice.dto.request.UsuarioRabbitMQEvent;
import com.cartao.cartaoservice.service.CartaoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UsuarioCriadoConsumer {
    private final CartaoService cartaoService;

    public UsuarioCriadoConsumer(CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }

    @RabbitListener(queues = "usuarios.v1.criar-usuario")
    public void aoCriarUsuario(UsuarioRabbitMQEvent event) {
        cartaoService.execute(event);
    }
}
