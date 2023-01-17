package com.example.api.entity.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.example.api.entity.usuario.Usuario;

public class UsuariosTest {

	@Test
	public void aoInstanciarOsObjetosDeUsuarioEChamarGettersSetterDeveFuncionar() {
		Usuario usuario = new Usuario();
		usuario.setId(1);
		usuario.setLogin("test");
		usuario.setSenha("test");

		Usuario usuario2 = new Usuario(1, "test2", "test3");

		assertEquals(1, usuario.getId());
		assertEquals("test", usuario.getLogin());
		assertEquals("test", usuario.getPassword());
		assertEquals("test2", usuario2.getUsername());
		Collection<? extends GrantedAuthority> authorities = usuario.getAuthorities();
		assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_USER")));
		assertTrue(usuario.isAccountNonExpired());
		assertTrue(usuario.isAccountNonLocked());
		assertTrue(usuario.isCredentialsNonExpired());
		assertTrue(usuario.isEnabled());

	}
}
