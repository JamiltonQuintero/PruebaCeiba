package com.ceiba.springmvc.web;

import com.ceiba.springmvc.service.LibroService;
import com.ceiba.springmvc.util.dto.LibroDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/libro")
public class LibroControlador {

	private static Logger _logger = LoggerFactory.getLogger(LibroControlador.class);

	@Autowired
	LibroService libroService;

	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping(path = "/guardarLibro")
	public ResponseEntity<?> guardarLibro(@RequestBody(required = true) LibroDto to) {
		try {
			libroService.guardarLibro(to);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception ex1) {
			_logger.error(ex1.toString());
			return new ResponseEntity<>(ex1.toString(), HttpStatus.BAD_REQUEST);
		}
	}


	@CrossOrigin(origins = "*", maxAge = 3600)
	@PutMapping(path = "/actualizarLibro")
	public ResponseEntity<?> actualizarLibro(@RequestBody(required = true) LibroDto to) {
		try {
			libroService.guardarLibro(to);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception ex1) {
			_logger.error(ex1.toString());
			return new ResponseEntity<>(ex1.toString(), HttpStatus.BAD_REQUEST);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping(path = "/obtenerLibro")
	public ResponseEntity<?> obtenerLibro(@RequestParam(required = true) String isbn) {
		try {

			return new ResponseEntity<>(libroService.obtenerLibro(isbn),HttpStatus.OK);
		} catch (Exception ex1) {
			_logger.error(ex1.toString());
			return new ResponseEntity<>(ex1.toString(), HttpStatus.BAD_REQUEST);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping(path = "/cambiarEstadoLibro")
	public ResponseEntity<?> cambiarEstadoLibro(@RequestParam(required = true) int nuevoEstado, @RequestParam(required = true) String isbn) {
		try {
			libroService.cambiarEstadoLibro(nuevoEstado,isbn);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception ex1) {
			_logger.error(ex1.toString());
			return new ResponseEntity<>(ex1.toString(), HttpStatus.BAD_REQUEST);
		}
	}

}
