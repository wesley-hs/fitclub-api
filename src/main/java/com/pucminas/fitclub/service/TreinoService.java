package com.pucminas.fitclub.service;

import com.pucminas.fitclub.repository.entity.Treino;
import com.pucminas.fitclub.repository.TreinoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TreinoService {

    @Autowired
    private TreinoRepository treinoRepository;

    public Treino salvarTreino(Treino treino) {
        return treinoRepository.save(treino);
    }

    public List<Treino> listarTreinoPorAluno(String alunoId) {
        return treinoRepository.findByAlunoId(alunoId);
    }

    public List<Treino> listarTreinosPorAluno(String alunoId) {
        return treinoRepository.findByAlunoId(alunoId);
    }

    public void deletar(String id) {
        treinoRepository.deleteById(id);
    }

    public Treino atulizarTreino(String id, Treino novosDados) {
        // 1 Busca o treino atual no banco pelo ID
        return treinoRepository.findById(id).map(treinoExistente -> {
            // 2 Atualiza os campos
            treinoExistente.setNomeDoTreino(novosDados.getNomeDoTreino());
            treinoExistente.setExercicios(novosDados.getExercicios());

            // 3 Salva a versão atualizada
            return treinoRepository.save(treinoExistente);
        }).orElseThrow(() -> new RuntimeException("Treino não encontrado com o ID"));
    }
}