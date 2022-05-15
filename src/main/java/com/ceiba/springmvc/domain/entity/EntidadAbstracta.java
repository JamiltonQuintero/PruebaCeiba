package com.ceiba.springmvc.domain.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public abstract class EntidadAbstracta implements Serializable {

	@Id
	@Column(name = "ID")
	private String id;
	@Column(name = "NOMBRE")
	private String nombre;
	@Column(name = "ESTADO")
	private int estado;
	@CreatedDate
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	@LastModifiedDate
	@Column(name = "FECHA_MODIFICACION")
	private Date fechaModificacion;
	
	public EntidadAbstracta(String id, String nombre, int estado, Date fechaCreacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
	}

	public EntidadAbstracta() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEstado() {
		return estado;
	}
	protected void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public void cambiarEstado(int nuevoEstado){

		if(validarEstado(nuevoEstado)){
			this.estado = nuevoEstado;
		}

	}

	protected abstract boolean validarEstado(int estado);

}
