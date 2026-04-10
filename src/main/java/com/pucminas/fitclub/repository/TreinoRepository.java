package com.pucminas.fitclub.repository;

import com.pucminas.fitclub.repository.entity.Treino;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TreinoRepository extends MongoRepository<Treino, String> {
    List<Treino> findByAlunoId(String alunoId); //Busca todos os treinos de um aluno

    String id(String id);
}