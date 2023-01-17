package com.example.api.service;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api.LocadoraApplication;
import com.example.api.config.exception.LocadoraException;
import com.example.api.entity.carro.Carro;
import com.example.api.entity.carro.dto.DadosAtualizacaoCarroDTO;
import com.example.api.entity.carro.dto.DadosCadastroCarroDTO;
import com.example.api.entity.carro.dto.DadosCadastroRetornoComIdDto;
import com.example.api.entity.carro.dto.DadosCarroRetornoDTO;
import com.example.api.repository.CarroRepository;
import com.example.api.util.FunctionUtil;

@Service
public class CarroService {

	private static final Logger logger = LogManager.getLogger(LocadoraApplication.class);

	@Autowired
	private CarroRepository carroRepository;

	public Page<DadosCarroRetornoDTO> buscarTodosOsCarros(Pageable page)  {
		try {
			logger.info("Buscando o  carro");
			return carroRepository.findAll(page).map(DadosCarroRetornoDTO::new);
		} catch (Exception e) {
			logger.fatal("Ocorreu algum erro fatal ao buscar todos os carros");
			throw new LocadoraException();
		}
	}

	@Transactional
	public DadosCadastroRetornoComIdDto salvarCarro(@Valid DadosCadastroCarroDTO dados) {
		try {
			logger.info("Salvando carro");
			var carro = new Carro(dados);
			return new DadosCadastroRetornoComIdDto(carroRepository.save(carro));
		} catch (Exception e) {
			logger.fatal("Ocorreu algum erro fatal ao salvar o carro");
			throw new LocadoraException();
		}
	}

	@Transactional
	public DadosCadastroRetornoComIdDto buscarCarroUnicoPorId(Long id) {
		try {
			logger.info("Buscar carro por id");
			return new DadosCadastroRetornoComIdDto(carroRepository.getById(id));
		} catch (Exception e) {
			logger.fatal("Ocorreu algum erro fatal ao buscar um carro por id");
			throw new LocadoraException();
		}
	}

	@Transactional
	public DadosCadastroRetornoComIdDto atualizarCarro(@Valid DadosAtualizacaoCarroDTO dados) {
		try {
			logger.info("Atualizando carro");
			var carroDados = carroRepository.getById(dados.getId());
			BeanUtils.copyProperties(dados, carroDados, FunctionUtil.buscarCamposNulos(dados));
			return new DadosCadastroRetornoComIdDto(carroRepository.save(carroDados));
		} catch (Exception e) {
			logger.fatal("Ocorreu algum erro fatal ao atualizar o carro");
			throw new LocadoraException();
		}
	}

	@Transactional
	public void removerPorId(Long id) {
		try {
			logger.info("Removendo carro");
			carroRepository.deleteById(id);
		} catch (Exception e) {
			logger.fatal("Ocorreu algum erro fatal ao remover o carro");
			throw new LocadoraException();
		}
	}

	public Page<DadosCarroRetornoDTO> buscarCarroDisponivelPorModeloOuMarca(String dados, Pageable page) {
		try {
			logger.info("Buscando carros disponivel por modelo ou nome");
			return carroRepository.findAllByMarcaOrModeloAndDisponivelTrue(dados, dados, page)
					.map(DadosCarroRetornoDTO::new);
		} catch (Exception e) {
			logger.fatal("Ocorreu algum erro fatal ao buscar carro disponivel por modelo ou nome");
			throw new LocadoraException();
		}
	}

	@Transactional
	public void removerLogicamentePorId(Long id) {
		try {
			logger.info("Removendo carro logicamente");
			var carro = carroRepository.getById(id);
			carro.setDisponivel(false);
			carroRepository.save(carro);
		} catch (Exception e) {
			logger.fatal("Ocorreu algum erro fatal ao remover carro logicamente");
			throw new LocadoraException("Erro com mensagem personalizada");
		}
	}

}
