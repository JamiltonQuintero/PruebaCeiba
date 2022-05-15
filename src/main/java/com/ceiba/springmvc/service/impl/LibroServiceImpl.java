package com.ceiba.springmvc.service.impl;

import com.ceiba.springmvc.domain.entity.Libro;
import com.ceiba.springmvc.domain.entity.Usuario;
import com.ceiba.springmvc.domain.repository.LibroRepository;
import com.ceiba.springmvc.service.LibroService;
import com.ceiba.springmvc.util.UtilDate;
import com.ceiba.springmvc.util.dto.LibroDto;
import com.ceiba.springmvc.util.enums.EEstadoLibro;
import com.ceiba.springmvc.util.exception.UsuarioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibroServiceImpl implements LibroService {

	@Autowired
	LibroRepository libroRepository;

	@Autowired
	private UtilDate utilDate;

	@Override
	public void guardarLibro(Libro to) {
		Libro libro = new Libro(to.getNombre(),EEstadoLibro.DISPONIBLE.getId(),utilDate.obtenerFechaCompletaActual(),to.getId());
		libroRepository.save(libro);
	}

	@Override
	public void actualizarLibro(Libro to) {

		Libro libro = obtenerLibroPorId(to.getId());
		libro.setNombre(to.getNombre());
		libro.setId(to.getId());
		libroRepository.save(libro);

	}

	@Override
	public Libro obtenerLibro(String id) {

		return obtenerLibroPorId(id);

	}

	@Override
	public void cambiarEstadoLibro(int nuevoEstado,String id) {

		Libro libro = obtenerLibroPorId(id);
		libro.cambiarEstado(nuevoEstado);
		libroRepository.save(libro);

	}

	private Libro obtenerLibroPorId(String id){
		Optional<Libro> libro = libroRepository.findById(id);

		if (libro.isPresent()){
			return libro.get();
		}

		throw new UsuarioException(HttpStatus.NOT_FOUND, "El libro no existe");
	}

	
}
