package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public class Vista {
	private static final String ERROR="ERROR", NOMBRE_VALIDO="Helena Gutierrez", CORREO_VALIDO="helena@gutierrez.com";
	
	private Controlador controlador;
	
	public Vista() {
		Opcion.setVista(this);
	}
	
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
		
		controlador.comenzar();
	}
	
	public void comenzar()
	{
		int ordinalOpcion;
		do{
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOrdinal(ordinalOpcion);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}
	
	public void salir() {
		controlador.terminar();
		
	}
	
	public void insertarAula() {
		Aula aula;
		
		aula = Consola.leerAula();
		
		controlador.insertarAula(aula);
		
	}
	
	public void borrarAula() {
		Aula aula;
		
		aula = Consola.leerAula();
		
		controlador.borrarAula(aula);
		
	}
	
	public void buscarAula() {
		Aula aula;
		
		aula = Consola.leerAula();
				
		controlador.buscarAula(aula);
		
	}
	
	public void listarAulas() {
		
		controlador.representarAulas();
		
	}
	
	public void insertarProfesor() {
		Profesor profesor;
		
		profesor = Consola.leerProfesor();
		
		controlador.insertarProfesor(profesor);
		
	}
	
	public void borrarProfesor() {
		Profesor profesor;
		
		profesor = Consola.leerProfesor();
		
		controlador.borrarProfesor(profesor);
	}
	
	public void buscarProfesor() {
		Profesor profesor;
	
		profesor = Consola.leerProfesor();
		
		controlador.buscarProfesor(profesor);
		
	}
	
	public void listarProfesores() {
		
		controlador.representarProfesores();
	}
	
	public void realizarReserva() {
		Reserva reserva;
		Profesor profesor;
		Aula aula;
		Permanencia permanencia;
		
		profesor = Consola.leerProfesor();
		aula = Consola.leerAula();
		permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
		
		if (controlador.buscarAula(aula) != null) { 
			throw new IllegalArgumentException("ERROR: No se puede realizar una reserva si el aula un no existe.");
		} else if (controlador.buscarProfesor(profesor) != null) {
			throw new IllegalArgumentException("ERROR: No se puede realizar una reserva si el profesor un no existe.");
		} else {
			reserva = new Reserva(profesor, aula, permanencia);
			controlador.realizarReserva(reserva);
		}
		
	}
	
	private Reserva leerReserva(Profesor profesor) {
		if (profesor == null)
			throw new NullPointerException(ERROR);
		
		Aula aula;
		Permanencia permanencia;
		
		aula = Consola.leerAula();
		permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
		
		return new Reserva(profesor, aula, permanencia);
		
	}
	
	public void anularReserva() {
		Profesor profesor;
		
		profesor = Consola.leerProfesor();

		controlador.anularReserva(leerReserva(profesor));
		
	}
	
	public void listarReservas() {
		controlador.representarReservas();
		
	}
	
	public void listarReservasAula() {
		controlador.getReservasAula(Consola.leerAula());
	}
	
	public void listarReservasProfesor() {
		controlador.getReservasProfesor(Consola.leerProfesor());
		
	}
	
	public void listarReservasPermanencia() {
		Permanencia permanencia;
		
		permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
		
		controlador.getReservasPremanencia(permanencia);
		
	}
	
	public void consultarDisponibilidad() {
		Aula aula;
		Permanencia permanencia;
		
		aula = Consola.leerAula();
		
		if (controlador.buscarAula(aula) != null) { 
			throw new IllegalArgumentException("ERROR: No se puede realizar una reserva si el aula un no existe.");
		} else {
			permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
			controlador.consultarDisponibilidad(aula, permanencia);
		}
		
		
	}
}
