package com.pucminas.fitclub.service.dto;

public record AvaliacaoRequestDTO(
         String usuarioId,
         Integer nota,
         String comentario
) {
}
