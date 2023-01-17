package com.example.api.infra.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.api.LocadoraApplication;
import com.example.api.service.UsuarioAuthService;


@Component
public class SecurityFilter extends OncePerRequestFilter {

	private static final Logger logger = LogManager.getLogger(LocadoraApplication.class);
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioAuthService usuarioAuthService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		logger.info("Requisição realizada");
		var tokenJWT = recuperarToken(request);

		if (tokenJWT != null) {
			var subject = tokenService.getSubject(tokenJWT);
			var usuario = usuarioAuthService.loadUserByUsername(subject);
			var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		filterChain.doFilter(request, response);
	}

	private String recuperarToken(HttpServletRequest request) {
		var authHeader = request.getHeader("Authorization");

		if (authHeader != null) {
			logger.info("Possui token");
			return authHeader.replace("Bearer ", "");
		}

		logger.info("Não possui token");
		return null;
	}

}
