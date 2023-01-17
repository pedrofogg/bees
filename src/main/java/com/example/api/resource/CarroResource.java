package com.example.api.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.api.entity.carro.dto.DadosAtualizacaoCarroDTO;
import com.example.api.entity.carro.dto.DadosCadastroCarroDTO;
import com.example.api.entity.carro.dto.DadosCadastroRetornoComIdDto;
import com.example.api.entity.carro.dto.DadosCarroRetornoDTO;
import com.example.api.service.CarroService;

@RestController
@RequestMapping("/carros")
public class CarroResource {

	@Autowired
	private CarroService carroService;

	@GetMapping
	public ResponseEntity<Page<DadosCarroRetornoDTO>> listar(
			@PageableDefault(size = 10, sort = { "nome" }) Pageable page) {
		var carrosPaginados = carroService.buscarTodosOsCarros(page);
		return ResponseEntity.ok(carrosPaginados);
	}

	@PostMapping
	public ResponseEntity<DadosCadastroRetornoComIdDto> cadastrar(@RequestBody @Valid DadosCadastroCarroDTO dados,
			UriComponentsBuilder uriBuilder) {
		var carroSalvo = carroService.salvarCarro(dados);
		var uri = uriBuilder.path("/carros/{id}").buildAndExpand(carroSalvo.getId()).toUri();
		return ResponseEntity.created(uri).body(carroSalvo);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DadosCadastroRetornoComIdDto> listarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(carroService.buscarCarroUnicoPorId(id));
	}

	@PutMapping
	public ResponseEntity<DadosCadastroRetornoComIdDto> atualizar(@RequestBody @Valid DadosAtualizacaoCarroDTO dados) {
		return ResponseEntity.ok(carroService.atualizarCarro(dados));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		carroService.removerPorId(id);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/logic/{id}")
	public ResponseEntity<?> removerLogicamente(@PathVariable Long id)  {
		carroService.removerLogicamentePorId(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/infos/{dados}")
	public ResponseEntity<Page<DadosCarroRetornoDTO>> listarCarroDisponivelPorModeloOuNome(@PathVariable String dados,
			@PageableDefault(size = 10, sort = { "nome" }) Pageable page) {
		return ResponseEntity.ok(carroService.buscarCarroDisponivelPorModeloOuMarca(dados, page));
	}

}
