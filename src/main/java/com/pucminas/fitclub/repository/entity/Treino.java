package com.pucminas.fitclub.repository.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.util.List;

@Data
@Document(collection = "treinos")
public class Treino {

    @Id
    private String id;
    private String alunoId;
    private String nomeDoTreino;
    private List<String> exercicios;
}