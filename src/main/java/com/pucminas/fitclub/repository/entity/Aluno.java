package com.pucminas.fitclub.repository.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "alunos")
public class Aluno {
    @Id
    private String id;
    private String nome;
    private String cpf;
    private String planoId;//Onde vincula o Aluno ao Plano
}