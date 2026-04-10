package com.pucminas.fitclub.service;

import com.pucminas.fitclub.repository.IPlanoRepository;
import com.pucminas.fitclub.repository.entity.Aluno;
import com.pucminas.fitclub.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatriculaService {

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private IPlanoRepository iPlanoRepository;

    public Aluno vincularPlano(String alunoId, String planoId) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado."));

        //Busca o plano
        iPlanoRepository.findById(planoId)
                        .orElseThrow(() -> new RuntimeException("Plano não encontrado."));

        aluno.setPlanoId(planoId);
        return alunoRepository.save(aluno);
    }
}