package com.ceiba.springmvc.service;


import com.ceiba.springmvc.domain.entity.Libro;
import com.ceiba.springmvc.util.dto.LibroDto;

public interface LibroService {

	void guardarLibro(Libro libro);
	
	void actualizarLibro(Libro  libro);

	Libro obtenerLibro(String  Id);
	
	void cambiarEstadoLibro(int  nuevoEstado,String id);
		
}
