package com.ceiba.springmvc.service.impl;

import com.ceiba.springmvc.domain.entity.Libro;
import com.ceiba.springmvc.domain.entity.Prestamo;
import com.ceiba.springmvc.domain.entity.Usuario;
import com.ceiba.springmvc.domain.repository.LibroRepository;
import com.ceiba.springmvc.service.LibroService;
import com.ceiba.springmvc.service.UsuarioService;
import com.ceiba.springmvc.util.exception.EmptyInputException;
import com.ceiba.springmvc.domain.repository.PrestamoRepository;
import com.ceiba.springmvc.domain.repository.UsuarioRepository;
import com.ceiba.springmvc.service.PrestamoService;
import com.ceiba.springmvc.util.UtilDate;
import com.ceiba.springmvc.util.dto.PrestamoDto;
import com.ceiba.springmvc.util.dto.RespuestaPrestamoDto;
import com.ceiba.springmvc.util.dto.RespuestaSolicitudPrestamoDto;
import com.ceiba.springmvc.util.enums.EEstadoLibro;
import com.ceiba.springmvc.util.enums.EEstadoPrestamo;
import com.ceiba.springmvc.util.enums.EEstadoUsuario;
import com.ceiba.springmvc.util.enums.ETipoUsuario;
import com.ceiba.springmvc.util.exception.PrestamoException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrestamoServiceImpl implements PrestamoService {

	private static final int DIAS_AFILIADO = 10;
	private static final int DIAS_EMPLEADO= 8;
	private static final int DIAS_INVITADO = 7;
	
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	LibroService libroService;

	@Autowired
	PrestamoRepository prestamoRepository;

	@Autowired
	private UtilDate utilDate;

	@Override
	public RespuestaSolicitudPrestamoDto solicitudPrestarlibro(PrestamoDto to) {
		
		Usuario usuario = usuarioService.obtenerUsuario(to.getIdentificacionUsuario());
		Libro libro =  libroService.obtenerLibro(to.getIsbn());

		if(!disponibilidadLibro(libro)) {
			throw new PrestamoException(HttpStatus.BAD_REQUEST,"El libro no se encuentra disponible");
		}

		if(esUsuarioInvitado(to.getTipoUsuario()) && obtenerPrestamosPorUsuario(usuario.getId()).isEmpty()) {
			throw new PrestamoException(HttpStatus.BAD_REQUEST,"El usuario con identificación " + usuario.getId()+ " ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo");
		}

		if(!tipoUsuarioValido(to.getTipoUsuario())) {
			throw new PrestamoException(HttpStatus.BAD_REQUEST,"Tipo de usuario no permitido en la biblioteca");
		}

		libro.cambiarEstado(EEstadoLibro.PRESTADO.getId());

		Prestamo nuevoPrestamo = new Prestamo(libro,usuario,fechaDeDevolucion(usuario), EEstadoPrestamo.ACTIVO.getId());
		RespuestaSolicitudPrestamoDto respuestaSolicitudPrestamo = new RespuestaSolicitudPrestamoDto();

		prestamoRepository.save(nuevoPrestamo);

		respuestaSolicitudPrestamo.setId(nuevoPrestamo.getId());
		respuestaSolicitudPrestamo.setFechaMaximaDevolucion( utilDate.fechaformatoDiaMesAño(nuevoPrestamo.getFechaMaximaDevolucion()));

		return respuestaSolicitudPrestamo;

	}

	@Override
	public RespuestaPrestamoDto obtenerPrestamo(Long prestamoId) {
		Prestamo prestamo = obtenerPrestamoPorId(prestamoId);
		RespuestaPrestamoDto to = new RespuestaPrestamoDto();
		to.setId(prestamo.getId());
		to.setIsbn(prestamo.getLibro().getId());
		to.setIdentificacionUsuario(prestamo.getUsuario().getId());
		to.setFechaMaximaDevolucion(utilDate.fechaformatoDiaMesAño(prestamo.getFechaMaximaDevolucion()));
		to.setTipoUsuario(prestamo.getUsuario().getTipoUsuario());
		return to;
	}

	@Override
	public List<RespuestaPrestamoDto> obtenerPrestamosUsuario(String identificacionUsuario) {
		List<RespuestaPrestamoDto> lPrestamosDto = new ArrayList<>();
		List<Prestamo> lPrestamos = obtenerPrestamosPorUsuario(identificacionUsuario);

		for (Prestamo prestamo : lPrestamos){

			RespuestaPrestamoDto to = new RespuestaPrestamoDto();
			to.setId(prestamo.getId());
			to.setIsbn(prestamo.getLibro().getId());
			to.setIdentificacionUsuario(prestamo.getUsuario().getId());
			to.setFechaMaximaDevolucion(utilDate.fechaformatoDiaMesAño(prestamo.getFechaMaximaDevolucion()));
			to.setTipoUsuario(prestamo.getUsuario().getTipoUsuario());

			lPrestamosDto.add(to);
		}

		return lPrestamosDto;
	}

	@Override
	public void entregarLibro(Long idPrestamo) {

		Prestamo prestamo = obtenerPrestamoPorId(idPrestamo);

		if(prestamo.getUsuario().getEstado() == EEstadoUsuario.PENALIZADO.getId()){
			Usuario usuario = prestamo.getUsuario();
			usuario.cambiarEstado(EEstadoUsuario.ACTIVO.getId());
			usuarioService.actualizarUsuario(usuario);
		}

		Libro libro = prestamo.getLibro();
		libro.cambiarEstado(EEstadoLibro.DISPONIBLE.getId());
		libroService.actualizarLibro(libro);

	}

	@Override
	public RespuestaPrestamoDto actualizaionParcialLibro(Long prestamoId, JsonPatch patch) {
		Prestamo prestamo = obtenerPrestamoPorId(prestamoId);

			try {
				Prestamo prestamoPatched  = applyPatchToPrestamo(patch, prestamo);
				prestamoRepository.save(prestamoPatched);

				RespuestaPrestamoDto to = new RespuestaPrestamoDto();
				to.setId(prestamo.getId());
				to.setIsbn(prestamo.getLibro().getId());
				to.setIdentificacionUsuario(prestamo.getUsuario().getId());
				to.setFechaMaximaDevolucion(utilDate.fechaformatoDiaMesAño(prestamo.getFechaMaximaDevolucion()));
				to.setTipoUsuario(prestamo.getUsuario().getTipoUsuario());
				return to;
			} catch (JsonPatchException | JsonProcessingException e) {
				throw new PrestamoException(HttpStatus.BAD_REQUEST,e.getMessage());
			}

	}

	@Override
	public void eliminarPrestamo(Long prestamoId) {
		Prestamo prestamo = obtenerPrestamoPorId(prestamoId);
		prestamoRepository.delete(prestamo);
	}

	private boolean disponibilidadLibro(Libro libro) {

		return libro.getEstado() == EEstadoLibro.DISPONIBLE.getId();

	}
	
	private boolean esUsuarioInvitado(int tipousuario) {

		return tipousuario == ETipoUsuario.INVITADO.getId();
		
	}

	private Date fechaDeDevolucion(Usuario usuario){

		if(usuario.getTipoUsuario() == ETipoUsuario.AFILIADO.getId()){
			return UtilDate.obtenerFechaSinDiasHabiles(DIAS_AFILIADO);
		} else if(usuario.getTipoUsuario() == ETipoUsuario.EMPLEADO.getId()){
			return UtilDate.obtenerFechaSinDiasHabiles(DIAS_EMPLEADO);
		} else {
			return UtilDate.obtenerFechaSinDiasHabiles(DIAS_INVITADO);
		}

	}

	private boolean tipoUsuarioValido(int tipoEmpleado) {
		return ETipoUsuario.AFILIADO.getId() == tipoEmpleado ||
				ETipoUsuario.EMPLEADO.getId() == tipoEmpleado ||
				ETipoUsuario.INVITADO.getId() == tipoEmpleado;
	}

	private List<Prestamo> obtenerPrestamosPorUsuario(String identificacionUsuario){

		return prestamoRepository.findAll().stream().filter(prestamo ->
				prestamo.getUsuario().getId().equals(identificacionUsuario) &&
						prestamo.getEstado() != EEstadoPrestamo.FINALIZADO.getId())
				.collect(Collectors.toList());
	}

	private Prestamo obtenerPrestamoPorId(Long prestamoId){
		Optional<Prestamo> prestamo = prestamoRepository.findById(prestamoId);
		if(prestamo.isPresent()){
			return prestamo.get();
		}
		throw new PrestamoException(HttpStatus.BAD_REQUEST, "El prestamo no existe");
	}

	private Prestamo applyPatchToPrestamo(
			JsonPatch patch, Prestamo targetCustomer) throws JsonPatchException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode patched = patch.apply(objectMapper.convertValue(targetCustomer, JsonNode.class));
		return objectMapper.treeToValue(patched, Prestamo.class);
	}

}
