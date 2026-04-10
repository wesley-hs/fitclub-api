package com.pucminas.fitclub.controller;

import com.pucminas.fitclub.repository.entity.PlanoModel;
import com.pucminas.fitclub.service.PlanoService;
import com.pucminas.fitclub.service.dto.PlanoModelRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/planos")
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class PlanoAdminController {

    private final PlanoService service;

    @PostMapping
    public PlanoModel create(@RequestBody PlanoModelRequestDTO dto) {
        return service.criarPlano(dto);
    }

    @PutMapping("/{id}")
    public PlanoModel update(@PathVariable String id,
                             @RequestBody PlanoModelRequestDTO dto) {
        return service.atualizarPlano(id, dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        service.deletarPlano(id);
    }

}
