package com.pucminas.fitclub.repository.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "planos")
public class PlanoModel {

    @Id
    private String id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private List<AvaliacaoModel> avaliacoes;
}
