package com.example.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.api.repository.UsuarioRepository;

public class UsuarioAuthServiceTest {

	@InjectMocks
	private UsuarioAuthService service;

	@Mock
	private UsuarioRepository usuarioRepository;
	
	@BeforeEach
	public void inicializar() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void deveriaBuscarUmUsuarioPeloUsername() {
		UserDetails userDetailsClass = Mockito.mock(UserDetails.class);
		when(usuarioRepository.findByLogin(Mockito.any())).thenReturn(userDetailsClass);
		var userDetails = service.loadUserByUsername(Mockito.any());
		assertThat(userDetailsClass).isEqualTo(userDetails);
	}
	
	
}
