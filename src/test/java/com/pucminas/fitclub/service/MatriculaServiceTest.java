package com.pucminas.fitclub.service;

import com.pucminas.fitclub.repository.IPlanoRepository;
import com.pucminas.fitclub.repository.AlunoRepository;
import com.pucminas.fitclub.repository.entity.Aluno;
import com.pucminas.fitclub.repository.entity.PlanoModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MatriculaServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private IPlanoRepository planoRepository;

    @InjectMocks
    private MatriculaService matriculaService;

    @Test
    void deveVincularPlanoAoAluno_comSucesso() {
        // CENÁRIO
        String alunoId = "1";
        String planoId = "999";
        Aluno alunoFake = new Aluno();
        alunoFake.setId(alunoId);

        // Mocks para o cenário de sucesso
        when(alunoRepository.findById(alunoId)).thenReturn(Optional.of(alunoFake));
        // Simulamos que o plano existe para o teste de sucesso passar
        when(planoRepository.findById(planoId)).thenReturn(Optional.of(new PlanoModel()));
        when(alunoRepository.save(any(Aluno.class))).thenAnswer(i -> i.getArguments()[0]);

        // AÇÃO
        Aluno resultado = matriculaService.vincularPlano(alunoId, planoId);

        // VERIFICAÇÃO
        assertNotNull(resultado);
        assertEquals(planoId, resultado.getPlanoId());
        verify(alunoRepository, times(1)).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoPlanoNaoExistir() {
        // CENÁRIO
        String alunoId = "1";
        String planoId = "999";

        when(alunoRepository.findById(alunoId)).thenReturn(Optional.of(new Aluno()));
        when(planoRepository.findById(planoId)).thenReturn(Optional.empty());

        // AÇÃO & VERIFICAÇÃO
        assertThrows(RuntimeException.class, () -> {
            matriculaService.vincularPlano(alunoId, planoId);
        });

        verify(alunoRepository, never()).save(any(Aluno.class));
    }
}