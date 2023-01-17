package com.example.api.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.example.api.entity.carro.dto.DadosAtualizacaoCarroDTO;
import com.example.api.entity.carro.dto.DadosCadastroRetornoComIdDto;
import com.example.api.entity.carro.dto.DadosCarroRetornoDTO;
import com.example.api.service.CarroService;

@ExtendWith(MockitoExtension.class)
public class CarroResourceTest {

	@Mock
	private CarroService carroService;

	@InjectMocks
	private CarroResource carroResource;

	private Long id;

	@BeforeEach
	public void inicializar() {
		MockitoAnnotations.openMocks(this);
		id = 1L;
	}

	@Test
	public void deveTestarOResourceDeListarERetornar200ComUmCorpo() {
		Pageable page = PageRequest.of(0, 10);
		Page<DadosCarroRetornoDTO> carrosPaginados = new PageImpl<>(List.of(new DadosCarroRetornoDTO()));
		when(carroService.buscarTodosOsCarros(page)).thenReturn(carrosPaginados);
		var response = carroResource.listar(page);
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(carrosPaginados, response.getBody());
	}

	@Test
	public void deveTestarOResourceDeListarPorIdERetornar200ComUmCorpo() {
		DadosCadastroRetornoComIdDto carro = new DadosCadastroRetornoComIdDto();
		when(carroService.buscarCarroUnicoPorId(id)).thenReturn(carro);

		ResponseEntity<DadosCadastroRetornoComIdDto> response = carroResource.listarPorId(id);
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(carro, response.getBody());
	}

	@Test
	public void deveTestarOResourceDeAtualizarPorObjERetornar200ComUmCorpo() {
		DadosAtualizacaoCarroDTO dados = new DadosAtualizacaoCarroDTO();

		dados.setId(id);
		dados.setMarca("nome");
		dados.setModelo("modelo");
		DadosCadastroRetornoComIdDto carroAtualizado = new DadosCadastroRetornoComIdDto();

		when(carroService.atualizarCarro(dados)).thenReturn(carroAtualizado);

		ResponseEntity<DadosCadastroRetornoComIdDto> response = carroResource.atualizar(dados);
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(carroAtualizado, response.getBody());
	}

	@Test
	public void deveTestarORemoverPorIdRetornar204() {
		doNothing().when(carroService).removerPorId(id);

		ResponseEntity<?> response = carroResource.remover(id);
		assertEquals(204, response.getStatusCodeValue());
	}

	@Test
	public void deveTestarORemoverLogicamentePorIdRetornar204() {
		doNothing().when(carroService).removerLogicamentePorId(id);

		ResponseEntity<?> response = carroResource.removerLogicamente(id);
		assertEquals(204, response.getStatusCodeValue());
	}
	
    @Test
    public void deveTestarListarPaginadoPorModeloOuNomeERetornarUmPageComStatus200() {
        String dados = "nome";
        Pageable page = PageRequest.of(0, 10);
        List<DadosCarroRetornoDTO> carros = new ArrayList<>();
        Page<DadosCarroRetornoDTO> carrosPage = new PageImpl<>(carros);

        when(carroService.buscarCarroDisponivelPorModeloOuMarca(dados, page)).thenReturn(carrosPage);

        ResponseEntity<Page<DadosCarroRetornoDTO>> response = carroResource.listarCarroDisponivelPorModeloOuNome(dados, page);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(carrosPage, response.getBody());
    }
}
