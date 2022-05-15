package com.ceiba.springmvc.domain.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PRESTAMO")

public class Prestamo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "LIBRO_ID", referencedColumnName = "ID")
	private Libro libro;
	@ManyToOne
    @JoinColumn(name="USUARIO_ID", nullable=false)
	private Usuario usuario;
	@Column(name = "FECHA_MAXIMA_DEVOLUCION")
	private Date fechaMaximaDevolucion;

	@Column(name = "ESTADO")
	private int estado;

	public Prestamo(Libro libro, Usuario usuario, Date fechaMaximaDevolucion, int estado) {
		this.libro = libro;
		this.usuario = usuario;
		this.fechaMaximaDevolucion = fechaMaximaDevolucion;
		this.estado = estado;
	}

	public Prestamo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFechaMaximaDevolucion() {
		return fechaMaximaDevolucion;
	}

	public void setFechaMaximaDevolucion(Date fechaMaximaDevolucion) {
		this.fechaMaximaDevolucion = fechaMaximaDevolucion;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
}
