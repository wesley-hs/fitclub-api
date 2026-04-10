package com.pucminas.fitclub.service;

import com.pucminas.fitclub.handler.NotFoundException;
import com.pucminas.fitclub.repository.IPlanoRepository;
import com.pucminas.fitclub.repository.entity.AvaliacaoModel;
import com.pucminas.fitclub.repository.entity.PlanoModel;
import com.pucminas.fitclub.service.dto.AvaliacaoRequestDTO;
import com.pucminas.fitclub.service.dto.PlanoModelRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class PlanoServiceTest {

    @InjectMocks
    private PlanoService service;

    @Mock
    private IPlanoRepository planoRepository;



    @Test
    void deveCriarPlano_comSucesso() {
        PlanoModelRequestDTO dto = new PlanoModelRequestDTO("Plano A", "Desc", BigDecimal.valueOf(10.0));

        PlanoModel saved = new PlanoModel();
        saved.setId("1");
        saved.setNome("Plano A");

        when(planoRepository.save(any(PlanoModel.class))).thenReturn(saved);

        PlanoModel result = service.criarPlano(dto);

        assertNotNull(result);
        assertEquals("Plano A", result.getNome());
        verify(planoRepository, times(1)).save(any());
    }

    @Test
    void deveAtualizarPlano_comSucesso() {
        String id = "1";

        PlanoModel existing = new PlanoModel();
        existing.setId(id);

        PlanoModelRequestDTO dto = new PlanoModelRequestDTO("Novo", "Nova desc", BigDecimal.valueOf(200.0));

        when(planoRepository.findById(id)).thenReturn(Optional.of(existing));
        when(planoRepository.save(any(PlanoModel.class))).thenReturn(existing);

        PlanoModel result = service.atualizarPlano(id, dto);

        assertEquals("Novo", result.getNome());
        assertEquals(BigDecimal.valueOf(200.0), result.getPreco());
        verify(planoRepository).save(existing);
    }

    @Test
    void deveRetornarException_quandoNaoEncontrado() {
        String id = "1";
        PlanoModelRequestDTO dto = new PlanoModelRequestDTO("Novo", "Desc", BigDecimal.valueOf(200.0));

        when(planoRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(NotFoundException.class,
                () -> service.atualizarPlano(id, dto));

        assertEquals("Plano não encontrado", exception.getMessage());
    }

    @Test
    void deveDeletarPlano_comSuceso() {
        String id = "1";

        doNothing().when(planoRepository).deleteById(id);

        service.deletarPlano(id);

        verify(planoRepository, times(1)).deleteById(id);
    }

    @Test
    void deveListarTodosOsPlanos_comSucesso() {
        List<PlanoModel> plans = List.of(new PlanoModel(), new PlanoModel());

        when(planoRepository.findAll()).thenReturn(plans);

        List<PlanoModel> result = service.buscarPlanos();

        assertEquals(2, result.size());
    }

    @Test
    void deveListarPlano_comSucesso() {
        String id = "1";

        PlanoModel plan = new PlanoModel();
        plan.setId(id);

        when(planoRepository.findById(id)).thenReturn(Optional.of(plan));

        PlanoModel result = service.buscarPlanosPorId(id);

        assertEquals(id, result.getId());
    }

    @Test
    void deveRetornarException_quandoPlanoPorId_naoEncontrado() {
        String id = "1";

        when(planoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> service.buscarPlanosPorId(id));
    }

    @Test
    void deveInserirAvaliacao() {

        PlanoModel plano = new PlanoModel();
        plano.setId("1");
        plano.setNome("Plano A");

        AvaliacaoRequestDTO dto = new AvaliacaoRequestDTO("user1", 5, "Ótimo");

        when(planoRepository.findById("1")).thenReturn(Optional.of(plano));
        when(planoRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        PlanoModel result = service.inserirAvaliacao("1", dto);

        assertEquals(1, result.getAvaliacoes().size());
        assertEquals("user1", result.getAvaliacoes().get(0).getUsuarioId());
    }

    @Test
    void deveInicializarListaQuandoForNull() {
        PlanoModel plano = new PlanoModel();
        plano.setId("1");
        plano.setNome("Plano A");

        AvaliacaoRequestDTO dto = new AvaliacaoRequestDTO("user1", 5, "Top");

        when(planoRepository.findById("1")).thenReturn(Optional.of(plano));
        when(planoRepository.save(any())).thenReturn(plano);

        PlanoModel result = service.inserirAvaliacao("1", dto);

        assertNotNull(result.getAvaliacoes());
        assertEquals(1, result.getAvaliacoes().size());
    }

    @Test
    void deveRetornarListaDeAvaliacoes() {

        PlanoModel plano = new PlanoModel();
        plano.setId("1");
        plano.setNome("Plano A");

        plano.setAvaliacoes(List.of(new AvaliacaoModel()));

        when(planoRepository.findById("1")).thenReturn(Optional.of(plano));

        List<AvaliacaoModel> result = service.getAvaliacaos("1");

        assertFalse(result.isEmpty());
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoHouverAvaliacoes() {

        PlanoModel plano = new PlanoModel();
        plano.setId("1");
        plano.setNome("Plano A");

        plano.setAvaliacoes(null);

        when(planoRepository.findById("1")).thenReturn(Optional.of(plano));

        List<AvaliacaoModel> result = service.getAvaliacaos("1");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
