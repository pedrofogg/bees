package com.example.api.graphql.resolver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.api.LocadoraApplication;
import com.example.api.entity.carro.dto.DadosCadastroCarroDTO;
import com.example.api.entity.carro.dto.DadosCadastroRetornoComIdDto;
import com.example.api.service.CarroService;

@Component
public class Mutation implements GraphQLMutationResolver {

	private static final Logger logger = LogManager.getLogger(LocadoraApplication.class);
	
	@Autowired
	private CarroService carroService;

	public DadosCadastroRetornoComIdDto salvarCarro(String marca, String modelo) {
		logger.info("Salvando carro via Mutation - GraphQL");
		var carro = carroService.salvarCarro(new DadosCadastroCarroDTO(marca, modelo));
		return carro;
	}

}
