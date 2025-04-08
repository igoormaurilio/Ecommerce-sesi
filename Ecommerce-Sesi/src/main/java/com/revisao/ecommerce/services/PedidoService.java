package com.revisao.ecommerce.services;

import com.revisao.ecommerce.dto.PedidoDTO;
import com.revisao.ecommerce.entities.Categoria;
import com.revisao.ecommerce.entities.Pedido;
import com.revisao.ecommerce.entities.StatusDoPedido;
import com.revisao.ecommerce.entities.Usuario;
import com.revisao.ecommerce.repositories.PedidoRepository;
import com.revisao.ecommerce.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;


    public PedidoDTO inserir(PedidoDTO dto){
        Pedido pedido = new Pedido();
        pedido.setMomento(Instant.now());
        pedido.setStatus(StatusDoPedido.AGUARDANDO_PAGAMENTO);

        Usuario user = usuarioRepository.getReferenceById(dto.getClienteid());

        pedido.setCliente(user);

        pedido = pedidoRepository.save(pedido);


        return new PedidoDTO(pedido);
    }
}