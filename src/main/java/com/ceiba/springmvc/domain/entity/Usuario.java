package com.ceiba.springmvc.domain.entity;

import com.ceiba.springmvc.util.enums.EEstadoUsuario;
import com.ceiba.springmvc.util.exception.UsuarioException;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "USUARIO")
@Polymorphism(type = PolymorphismType.EXPLICIT)

public class Usuario extends EntidadAbstracta {

	@Column(name = "TIPO_USUARIO")
	private int tipoUsuario;

	public Usuario(String identificacionUsuario, String nombre, int estado, Date fechaCreacion, int tipoUsuario) {
		super(identificacionUsuario,nombre, estado, fechaCreacion);

		this.tipoUsuario = tipoUsuario;
	}

	public Usuario() {
		super();
	}

	public int getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	@Override
	protected boolean validarEstado(int estado) {
		if(EEstadoUsuario.ACTIVO.getId() == estado ||
				EEstadoUsuario.INACTIVO.getId() == estado ||
				EEstadoUsuario.PENALIZADO.getId() == estado ||
				EEstadoUsuario.MAXIMO_LIBROS_PRESTADOS.getId() == estado){

			return true;
		}

		throw new UsuarioException(HttpStatus.BAD_REQUEST,"Estado de usuario invalido");

	}


}
