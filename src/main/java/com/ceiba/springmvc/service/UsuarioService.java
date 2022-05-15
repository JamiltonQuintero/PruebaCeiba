package com.ceiba.springmvc.service;


import com.ceiba.springmvc.domain.entity.Usuario;
import com.ceiba.springmvc.util.dto.UsuarioDto;

public interface UsuarioService {
	
	void guardarUsuario(Usuario usuario);
	
	void actualizarUsuario(Usuario usuario);
	
	Usuario obtenerUsuario(String  identificacionUsuario);
	
	void cambiarEstadoUsuario(int  nuevoEstado, String identificacionUsuario);


}
