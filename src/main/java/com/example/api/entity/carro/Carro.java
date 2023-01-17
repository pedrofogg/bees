package com.example.api.entity.carro;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;

import com.example.api.entity.carro.dto.DadosCadastroCarroDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "carros")
@Entity(name = "Carro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Carro {

	public Carro(@Valid DadosCadastroCarroDTO dados) {
		this.marca = dados.getMarca();
		this.modelo = dados.getModelo();
		this.disponivel = true;
	}

//	public void atualizarInformacoes(@Valid DadosAtualizacaoCarroDTO dados) {
//		this.
//	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String marca;
	private String modelo;
	private Boolean disponivel;

}
