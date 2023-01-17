package com.example.api.entity.carro.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosCadastroCarroDTO {

	@NotBlank 
	private String marca;
	@NotBlank 
	private String modelo;
}
