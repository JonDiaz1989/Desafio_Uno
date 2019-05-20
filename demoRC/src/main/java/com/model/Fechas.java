package com.model;

import java.util.List;

public class Fechas {
	String id;
	String fechaCreacion;
	String fechaFin;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	@SuppressWarnings("rawtypes")
	private List fechas;
	@SuppressWarnings("rawtypes")
	public List getFechas() {
		return fechas;
	}

	public void setFechas(@SuppressWarnings("rawtypes") List fechas) {
		this.fechas = fechas;
	}

	

}
