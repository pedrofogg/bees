package com.example.api.graphql.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.api.entity.carro.Carro;
import com.example.api.repository.CarroRepository;

@Component
public class Query implements GraphQLQueryResolver {
	private CarroRepository carroRepository;

	@Autowired
	public Query(CarroRepository carroRepository) {
		this.carroRepository = carroRepository;
	}

	public Iterable<Carro> findAllCarros() {
		return carroRepository.findAll();
	}

	public long countCarros() {
		return carroRepository.count();
	}

}
