package com.ceiba.springmvc.service.impl;

import com.ceiba.springmvc.domain.entity.Usuario;
import com.ceiba.springmvc.domain.repository.UsuarioRepository;
import com.ceiba.springmvc.service.UsuarioService;
import com.ceiba.springmvc.util.UtilDate;
import com.ceiba.springmvc.util.dto.UsuarioDto;
import com.ceiba.springmvc.util.enums.EEstadoUsuario;
import com.ceiba.springmvc.util.exception.UsuarioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	 @Autowired
	 UsuarioRepository usuarioRepository;

	@Autowired
	private UtilDate utilDate;

	@Override
	public void guardarUsuario(Usuario usuario) {

		Optional<Usuario> usuarioExistente = usuarioRepository.findById(usuario.getId());

		if (usuarioExistente.isPresent()){
			throw new UsuarioException(HttpStatus.NOT_FOUND, "El usuario no existe");
		}

		Usuario usuarioNuevo = new Usuario(usuario.getId(),usuario.getNombre(), EEstadoUsuario.ACTIVO.getId(), utilDate.obtenerFechaCompletaActual(),
				usuario.getTipoUsuario());
		usuarioRepository.save(usuarioNuevo);
		
	}

	@Override
	public void actualizarUsuario(Usuario usuario) {
		Usuario usuarioActualizado = obtenerUsuarioPorId(usuario.getId());
		usuarioActualizado.setNombre(usuario.getNombre());
		usuarioActualizado.setTipoUsuario(usuario.getTipoUsuario());
		usuarioRepository.save(usuarioActualizado);
		
	}

	@Override
	public Usuario obtenerUsuario(String identificacionUsuario) {

		return obtenerUsuarioPorId(identificacionUsuario);

	}

	@Override
	public void cambiarEstadoUsuario(int nuevoEstado, String identificacionUsuario) {

			Usuario usuario = obtenerUsuarioPorId(identificacionUsuario);
			usuario.cambiarEstado(nuevoEstado);
			usuarioRepository.save(usuario);

	}

	private Usuario obtenerUsuarioPorId(String id){
		Optional<Usuario> usuario = usuarioRepository.findById(id);

		if (usuario.isPresent()){
			return usuario.get();
		}

		throw new UsuarioException(HttpStatus.NOT_FOUND, "El usuario no existe");
	}

}
