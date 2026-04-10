package com.pucminas.fitclub.service.dto;

import java.math.BigDecimal;

public record PlanoModelRequestDTO(
        String nome,
        String descricao,
        BigDecimal preco
        ) {
}
