package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.example.api.entity.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	UserDetails findByLogin(String login);

}
