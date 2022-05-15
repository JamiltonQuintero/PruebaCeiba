package com.ceiba.springmvc.util.dto;

/*@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
*/
public class UsuarioDto {
	
	private String identificacionUsuario;
	
	private String nombre;

	private int tipoUsuario;

	public String getIdentificacionUsuario() {
		return identificacionUsuario;
	}

	public void setIdentificacionUsuario(String identificacionUsuario) {
		this.identificacionUsuario = identificacionUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
}
