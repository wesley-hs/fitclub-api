package com.pucminas.fitclub.controller;

import com.pucminas.fitclub.repository.entity.Aluno;
import com.pucminas.fitclub.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    //Rota para vincular : /matriculas/vincular?alunoId=XXX&planoId=YYY
    @PutMapping("/vincular")
    public Aluno vincular(@RequestParam String alunoId, @RequestParam String planoId) {
        return matriculaService.vincularPlano(alunoId, planoId);
    }
}