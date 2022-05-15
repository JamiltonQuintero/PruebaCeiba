package com.ceiba.springmvc.service;

import com.ceiba.springmvc.util.dto.PrestamoDto;
import com.ceiba.springmvc.util.dto.RespuestaPrestamoDto;
import com.ceiba.springmvc.util.dto.RespuestaSolicitudPrestamoDto;
import com.github.fge.jsonpatch.JsonPatch;

import java.util.List;

public interface PrestamoService {

	RespuestaSolicitudPrestamoDto solicitudPrestarlibro(PrestamoDto to);

	RespuestaPrestamoDto obtenerPrestamo(Long prestamoId);

	List<RespuestaPrestamoDto> obtenerPrestamosUsuario(String identificacionUsuario);

	void entregarLibro(Long idPrestamo);

	RespuestaPrestamoDto actualizaionParcialLibro(Long prestamoId, JsonPatch patch);

	void eliminarPrestamo(Long prestamoId);

}
