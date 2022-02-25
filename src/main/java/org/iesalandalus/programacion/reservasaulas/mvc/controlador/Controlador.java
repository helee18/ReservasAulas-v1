package org.iesalandalus.programacion.reservasaulas.mvc.controlador;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.Modelo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.Vista;

public class Controlador {
	
	private Modelo modelo;
	private Vista vista;

	public Controlador(Modelo modelo, Vista vista) {
		if (modelo == null) {
			throw new IllegalArgumentException("ERROR: El modelo no puede ser nulo.");
		}
		
		if (vista == null) {
			throw new IllegalArgumentException("ERROR: La vista no puede ser nula.");
		}
		
		this.modelo = modelo;
		this.vista = vista;
		this.vista.setControlador(this);
	}
	
	public void comenzar() {
		vista.comenzar();
	}
	
	public void terminar() {
		System.out.println("Fin del programa.");
	}
	
	public void insertarAula(Aula aula) {
		if (aula == null)
			throw new NullPointerException("ERROR: No se puede insertar un aula nula.");
		
		try {
			modelo.insertarAula(aula);
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insertarProfesor(Profesor profesor) {
		if (profesor == null)
			throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");
		
		try {
			modelo.insertarProfesor(profesor);
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void borrarAula(Aula aula) {
		if (aula == null)
			throw new NullPointerException("ERROR: No se puede borrar un aula nula.");
		
		try {
			modelo.borrarAula(aula);
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void borrarProfesor(Profesor profesor) {
		if (profesor == null)
			throw new NullPointerException("ERROR: No se puede borrar un profesor nulo.");
		
		try {
			modelo.borrarProfesor(profesor);
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
			
	}
	
	public Aula buscarAula(Aula aula) {
		if (aula == null)
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		
		if (modelo.buscarAula(aula) == null)
			throw new IllegalArgumentException("ERROR: El aula no existe");
		
		return modelo.buscarAula(aula);
	}
	
	public Profesor buscarProfesor(Profesor profesor) {
		if (profesor == null)
			throw new NullPointerException("ERROR: No se puede buscar un profesor nulo.");
		
		if (modelo.buscarProfesor(profesor) == null)
			throw new IllegalArgumentException("ERROR: El profesor no existe");
		
		return modelo.buscarProfesor(profesor);
	}
	
	public List<String> representarAulas() {
		return modelo.representarAulas();
	}
	
	public List<String> representarProfesores() {
		return modelo.representarProfesores();
	}
	
	public List<String> representarReservas() {
		return modelo.representarReservas();
	}
	
	public void realizarReserva(Reserva reserva) {
		if (reserva == null)
			throw new NullPointerException("ERROR: No se puede realizar una reserva nula.");
		
		try {
			modelo.realizarReserva(reserva);
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void anularReserva(Reserva reserva) {
		if (reserva == null)
			throw new NullPointerException("ERROR: No se puede anular una reserva nula.");
		
		try {
			modelo.anularReserva(reserva);
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<Reserva> getReservasAula(Aula aula) {
		if (aula == null)
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		
		return modelo.getReservasAula(aula);
		
	}
	
	public List<Reserva> getReservasProfesor(Profesor profesor) {
		if (profesor == null)
			throw new NullPointerException("ERROR: No se puede buscar un profesor nulo.");
		
		return modelo.getReservasProfesor(profesor);
		
	}
	
	public List<Reserva> getReservasPremanencia(Permanencia permanencia) {
		if (permanencia == null)
			throw new NullPointerException("ERROR: No se puede buscar una permanencia nula.");
		
		return modelo.getReservasPermanencia(permanencia);
	}
	
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		if (permanencia == null)
			throw new NullPointerException("ERROR: No se puede buscar una permanencia nula.");
		if (aula == null)
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		
		return modelo.consultarDisponibilidad(aula, permanencia);
		
	}
	
	
}
