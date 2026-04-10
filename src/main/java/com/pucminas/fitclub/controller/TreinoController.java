package com.pucminas.fitclub.controller;

import com.pucminas.fitclub.dto.TreinoRequestDTO;
import com.pucminas.fitclub.repository.entity.Treino;
import com.pucminas.fitclub.service.TreinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/treinos")
public class TreinoController {

    @Autowired
    private TreinoService treinoService;

    @PostMapping
    public ResponseEntity<Treino> criarTreino(@RequestBody TreinoRequestDTO dto) {
        Treino treino = new Treino();
        treino.setAlunoId(dto.getAlunoId());
        treino.setNomeDoTreino(dto.getNomeDoTreino());
        treino.setExercicios(dto.getExercicios());

        Treino novoTreino = treinoService.salvarTreino(treino);
        return ResponseEntity.status(201).body(novoTreino);
    }

    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<Treino>> buscarPorAluno(@PathVariable String alunoId) {
        return ResponseEntity.ok(treinoService.listarTreinosPorAluno(alunoId));
    }
}