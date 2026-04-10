package com.pucminas.fitclub.controller;

import com.pucminas.fitclub.repository.entity.AvaliacaoModel;
import com.pucminas.fitclub.repository.entity.PlanoModel;
import com.pucminas.fitclub.service.PlanoService;
import com.pucminas.fitclub.service.dto.AvaliacaoRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("planos")
@AllArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class PlanoController {


    private final PlanoService service;

    @GetMapping
    public List<PlanoModel> findAll() {
        return service.buscarPlanos();
    }

    @GetMapping("/{id}")
    public PlanoModel findById(@PathVariable String id) {
        return service.buscarPlanosPorId(id);
    }

    @PostMapping("/{id}/reviews")
    public PlanoModel addReview(@PathVariable String id,
                                @RequestBody AvaliacaoRequestDTO dto) {
        return service.inserirAvaliacao(id, dto);
    }

    @GetMapping("/{id}/reviews")
    public List<AvaliacaoModel> getReviews(@PathVariable String id) {
        return service.getAvaliacaos(id);
    }

}
