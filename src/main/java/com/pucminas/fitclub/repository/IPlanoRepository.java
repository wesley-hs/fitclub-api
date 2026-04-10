package com.pucminas.fitclub.repository;

import com.pucminas.fitclub.repository.entity.PlanoModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IPlanoRepository extends MongoRepository<PlanoModel, String> {
}
