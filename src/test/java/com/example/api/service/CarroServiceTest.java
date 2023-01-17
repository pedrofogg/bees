package com.example.api.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.api.config.exception.LocadoraException;
import com.example.api.entity.carro.Carro;
import com.example.api.entity.carro.dto.DadosAtualizacaoCarroDTO;
import com.example.api.entity.carro.dto.DadosCadastroCarroDTO;
import com.example.api.repository.CarroRepository;

public class CarroServiceTest {

	@InjectMocks
	private CarroService service;

	@Mock
	private CarroRepository carroRepository;

	private Carro carro;

	@BeforeEach
	public void inicializar() {
		MockitoAnnotations.openMocks(this);
		carro = new Carro(1l, "Nome", "Modelo", true);
	}

	@Test
	public void deveriaListarTodosOsCarros() {
		Page<Carro> page = new PageImpl<>(Arrays.asList(carro));
		Pageable pageable = PageRequest.of(0, 10);
		when(carroRepository.findAll(pageable)).thenReturn(page);
		var pageCarros = service.buscarTodosOsCarros(pageable);
		assertNotNull(pageCarros.getContent().get(0).getModelo());
	}

	@Test
	public void deveriaTentarListarTodosCarrosMasDarErroESoltarExcecao() {
		Pageable pages = PageRequest.of(0, 10);
		when(carroRepository.findAll(pages)).thenThrow(new RuntimeException());
		Assertions.assertThrows(LocadoraException.class, () -> service.buscarTodosOsCarros(pages));
	}

	@Test
	public void deveriaSalvarUmNovoCarro() {
		when(carroRepository.save(Mockito.any())).thenReturn(carro);
		var carroSalvo = service.salvarCarro(new DadosCadastroCarroDTO("nome", "modelo"));
		assertNotNull(carroSalvo);
	}

	@Test
	public void deveriaTentarSalvarUmCarroMasDarErroESoltarExcecao() {
		when(carroRepository.save(Mockito.any())).thenThrow(new RuntimeException());
		Assertions.assertThrows(LocadoraException.class,
				() -> service.salvarCarro(new DadosCadastroCarroDTO("nome", "modelo")));
	}

	@Test
	public void deveriaListarUmCarroPorId() {
		when(carroRepository.getById(Mockito.any())).thenReturn(carro);
		var carroBuscado = service.buscarCarroUnicoPorId(Mockito.any());
		assertNotNull(carroBuscado);
	}

	@Test
	public void deveriaTentarListarUmCarroPorIdMasDarErroESoltarExcecao() {
		when(carroRepository.getById(Mockito.any())).thenThrow(new RuntimeException());
		Assertions.assertThrows(LocadoraException.class, () -> service.buscarCarroUnicoPorId(Mockito.any()));
	}

	@Test
	public void deveriaAtualizarUmCarroPorObjeto() {
		when(carroRepository.getById(Mockito.any())).thenReturn(carro);
		when(carroRepository.save(Mockito.any())).thenReturn(carro);
		carro = new Carro();
		service.atualizarCarro(new DadosAtualizacaoCarroDTO(1l, "nome", "modelo"));
		var carroAtualizado = service.buscarCarroUnicoPorId(Mockito.any());
		assertNotNull(carroAtualizado);
	}

	@Test
	public void deveriaTentarAtualizarUmCarroPorObjetoMasDarErroESoltarExcecao() {
		when(carroRepository.getById(Mockito.anyLong())).thenThrow(new RuntimeException());
		Assertions.assertThrows(LocadoraException.class, () -> service
				.atualizarCarro(new DadosAtualizacaoCarroDTO(Mockito.any(), Mockito.any(), Mockito.any())));
	}

	@Test
	public void deveriaRemoverUmCarro() {
		Long id = 1L;
		service.removerPorId(id);
		verify(carroRepository).deleteById(id);
	}

	@Test
	public void deveriaTentarRemoverUmCarroMasDarErroESoltarExcecao() {
		Long id = 1L;
		Mockito.doThrow(new LocadoraException()).when(carroRepository).deleteById(id);
		Assertions.assertThrows(LocadoraException.class, () -> service.removerPorId(id));
	}

	@Test
	public void deveriaBuscarUmCarroDisponivelPorModeloOuNome() {
		Page<Carro> page = new PageImpl<>(Arrays.asList(carro));
		Pageable pageable = PageRequest.of(0, 10);
		when(carroRepository.findAllByMarcaOrModeloAndDisponivelTrue(Mockito.any(), Mockito.any(), Mockito.any()))
				.thenReturn(page);
		var carrosEncontrados = service.buscarCarroDisponivelPorModeloOuMarca("Teste", pageable);
		assertNotNull(carrosEncontrados);

	}

	@Test
	public void deveriaBuscarUmCarroDisponivelPorModeloOuNomeMasDarErroESoltarExcecao() {
		when(carroRepository.findAllByMarcaOrModeloAndDisponivelTrue(Mockito.any(), Mockito.any(), Mockito.any()))
				.thenThrow(new RuntimeException());
		Assertions.assertThrows(LocadoraException.class,
				() -> service.buscarCarroDisponivelPorModeloOuMarca(Mockito.any(), Mockito.any()));
	}

	@Test
	public void deveriaRemoverUmCarroLogicamente() {
		Long id = 1L;
		when(carroRepository.getById(Mockito.any())).thenReturn(carro);
		when(carroRepository.save(Mockito.any())).thenReturn(carro);
		service.removerLogicamentePorId(id);
	}

	@Test
	public void deveriaRemoverUmCarroLogicamenteMasDarErroESoltarExcecao() {
		when(carroRepository.getById(Mockito.any())).thenThrow(new RuntimeException());
		Assertions.assertThrows(LocadoraException.class, () -> service.removerLogicamentePorId(1l));
	}

}
