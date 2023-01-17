package com.example.api.entity.carro.dto;

import com.example.api.entity.carro.Carro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DadosCarroRetornoDTO {

	public DadosCarroRetornoDTO(Carro carro) {
		this.marca = carro.getMarca();
		this.modelo = carro.getModelo();
		this.disponivel = carro.getDisponivel();
	}

	private String marca;
	private String modelo;
	private Boolean disponivel;

}
