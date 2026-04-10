package com.pucminas.fitclub.repository.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoModel {

    private String usuarioId;
    private Integer nota;
    private String comentario;
    private LocalDateTime createdAt;
}
