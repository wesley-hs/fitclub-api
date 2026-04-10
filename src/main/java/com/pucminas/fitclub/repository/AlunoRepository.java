package com.pucminas.fitclub.repository;

import com.pucminas.fitclub.repository.entity.Aluno;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends MongoRepository<Aluno, String> {
    //Spring cria o save, delete e findById automaticamente
}
