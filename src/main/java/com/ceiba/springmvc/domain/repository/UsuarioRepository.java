package com.ceiba.springmvc.domain.repository;

import com.ceiba.springmvc.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{

}
