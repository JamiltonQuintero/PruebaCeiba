package com.ceiba.springmvc.util.dto;

/*@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter*/
public class LibroDto {
		
	private String isbn;

	private String nombre;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
