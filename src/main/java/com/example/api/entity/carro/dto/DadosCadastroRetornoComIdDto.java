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
public class DadosCadastroRetornoComIdDto {

	public DadosCadastroRetornoComIdDto(Carro carro) {
		this.id = carro.getId();
		this.marca = carro.getMarca();
		this.modelo = carro.getModelo();
		this.disponivel = carro.getDisponivel();
	}

	private Long id;
	private String marca;
	private String modelo;
	private Boolean disponivel;

}
