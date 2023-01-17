package com.example.api.entity.usuario;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DadosAutenticaoUsuarioDTO {

	@NotBlank
	private String login;
	@NotBlank
	private String senha;

}
