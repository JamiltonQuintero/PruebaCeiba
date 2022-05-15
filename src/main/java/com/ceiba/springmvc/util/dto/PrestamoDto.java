package com.ceiba.springmvc.util.dto;

/*@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter*/
public class PrestamoDto {

	private String isbn; 
	private String identificacionUsuario; 
	private int tipoUsuario;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getIdentificacionUsuario() {
		return identificacionUsuario;
	}

	public void setIdentificacionUsuario(String identificacionUsuario) {
		this.identificacionUsuario = identificacionUsuario;
	}

	public int getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
}
