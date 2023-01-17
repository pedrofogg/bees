package com.example.api.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.entity.usuario.DadosAutenticaoUsuarioDTO;
import com.example.api.entity.usuario.Usuario;
import com.example.api.infra.security.DadosTokenJWT;
import com.example.api.infra.security.TokenService;

@RestController
@RequestMapping("/login")
public class LoginResource {
	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<?> efetuarLogin(@RequestBody @Valid DadosAutenticaoUsuarioDTO dados) {

		var authToken = new UsernamePasswordAuthenticationToken(dados.getLogin(), dados.getSenha());
		var auth = manager.authenticate(authToken);
		var tokenJWT = tokenService.gerarToken((Usuario) auth.getPrincipal());
		return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));

	}

}
