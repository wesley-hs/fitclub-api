package com.pucminas.fitclub.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
public class TreinoRequestDTO {
    private String alunoId;
    private String nomeDoTreino;
    private List<String> exercicios;
}
