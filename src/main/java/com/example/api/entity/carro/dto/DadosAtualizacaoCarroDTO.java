package com.example.api.entity.carro.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DadosAtualizacaoCarroDTO {
	
	@NotNull
	private Long id;
	private String marca;
	private String modelo;

}
