package com.ceiba.springmvc.domain.entity;

import com.ceiba.springmvc.util.enums.EEstadoLibro;
import com.ceiba.springmvc.util.exception.UsuarioException;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "LIBRO")
@Polymorphism(type = PolymorphismType.EXPLICIT)
public class Libro extends EntidadAbstracta{
	

	public Libro(String nombre, int estado, Date fechaCreacion, String isbn) {
		super(isbn, nombre, estado, fechaCreacion);

	}

	public Libro() {
		super();
	}

	@Override
	protected boolean validarEstado(int estado) {
		if(EEstadoLibro.DISPONIBLE.getId() == estado ||
				EEstadoLibro.PRESTADO.getId() == estado ||
				EEstadoLibro.ARCHIVADO.getId() == estado ||
				EEstadoLibro.DETERIORADO.getId() == estado){

			return true;

		}

		throw new UsuarioException(HttpStatus.BAD_REQUEST,"Estado de libro invalido");

	}
}
