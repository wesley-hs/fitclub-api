package com.pucminas.fitclub.service;

import com.pucminas.fitclub.handler.NotFoundException;
import com.pucminas.fitclub.repository.entity.AvaliacaoModel;
import com.pucminas.fitclub.repository.entity.PlanoModel;
import com.pucminas.fitclub.repository.IPlanoRepository;
import com.pucminas.fitclub.service.dto.AvaliacaoRequestDTO;
import com.pucminas.fitclub.service.dto.PlanoModelRequestDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class PlanoService {

    private final IPlanoRepository repository;


    public PlanoModel criarPlano(PlanoModelRequestDTO dto) {
        PlanoModel plan = new PlanoModel();
        plan.setNome(dto.nome());
        plan.setDescricao(dto.descricao());
        plan.setPreco(dto.preco());
        return repository.save(plan);
    }


    public PlanoModel atualizarPlano(String id, PlanoModelRequestDTO dto) {
        PlanoModel plan = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Plano não encontrado"));

        plan.setNome(dto.nome());
        plan.setDescricao(dto.descricao());
        plan.setPreco(dto.preco());

        return repository.save(plan);
    }


    public void deletarPlano(String id) {
        repository.deleteById(id);
    }


    public List<PlanoModel> buscarPlanos() {
        return repository.findAll();
    }


    public PlanoModel buscarPlanosPorId(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Plano %s não encontrado", id)));

    }

    public PlanoModel inserirAvaliacao(String planId, AvaliacaoRequestDTO dto) {
        PlanoModel plan = buscarPlanosPorId(planId);

        if (plan.getAvaliacoes() == null) {
            plan.setAvaliacoes(new ArrayList<>());
        }

        AvaliacaoModel review = new AvaliacaoModel();
        review.setUsuarioId(dto.usuarioId());
        review.setNota(dto.nota());
        review.setComentario(dto.comentario());
        review.setCreatedAt(LocalDateTime.now());

        plan.getAvaliacoes().add(review);

        return repository.save(plan);
    }

    public List<AvaliacaoModel> getAvaliacaos(String planId) {
        return Optional.ofNullable(buscarPlanosPorId(planId).getAvaliacoes())
                .orElse(Collections.emptyList());
    }
}
