package com.ceiba.springmvc.web;

import com.ceiba.springmvc.service.UsuarioService;
import com.ceiba.springmvc.util.dto.UsuarioDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioControlador {
	private static Logger _logger = LoggerFactory.getLogger(UsuarioControlador.class);
	@Autowired
	UsuarioService usuarioService;

	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping(path = "/guardarUsuario")
	public ResponseEntity<?> guardarUsuario(@RequestBody(required = true) UsuarioDto to) {

			usuarioService.guardarUsuario(to);
			return new ResponseEntity<>(HttpStatus.OK);

	}


	@CrossOrigin(origins = "*", maxAge = 3600)
	@PutMapping(path = "/actualizarUsuario")
	public ResponseEntity<?> actualizarUsuario(@RequestBody(required = true) UsuarioDto to) {
		usuarioService.guardarUsuario(to);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping(path = "/obtenerUsuario")
	public ResponseEntity<?> obtenerUsuario(@RequestParam(required = true) String identificacionUsuario) {

		return new ResponseEntity<>(usuarioService.obtenerUsuario(identificacionUsuario),HttpStatus.OK);

	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping("/{identificacionUsuario}/cambiarEstado/{nuevoEstado}")
	public ResponseEntity<?> cambiarEstadoUsuario(@PathVariable(required = true) String identificacionUsuario, @PathVariable(required = true) int nuevoEstado) {
		try {
			usuarioService.cambiarEstadoUsuario(nuevoEstado,identificacionUsuario);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception ex1) {
			_logger.error(ex1.toString());
			return new ResponseEntity<>(ex1.toString(), HttpStatus.BAD_REQUEST);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping(path = "/obtenerPresmosUsuario")
	public ResponseEntity<?> obtenerPresmosUsuario(@RequestParam(required = true) String identificacionUsuario) {
		try {
			return new ResponseEntity<>(usuarioService.obtenerUsuario(identificacionUsuario),HttpStatus.OK);
		} catch (Exception ex1) {
			_logger.error(ex1.toString());
			return new ResponseEntity<>(ex1.toString(), HttpStatus.BAD_REQUEST);
		}
	}

}
