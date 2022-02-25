package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public class Reservas {

	List<Reserva> coleccionReservas;
	
	public Reservas () {
		coleccionReservas = new ArrayList<Reserva>();
	}
	
	public Reservas(Reservas reservasOriginal) {
		if (reservasOriginal == null)
			throw new NullPointerException("ERROR: No se pueden copiar reservas nulas.");
		
		if (reservasOriginal.getNumReservas() == 0)
			this.coleccionReservas = new ArrayList<Reserva>();
		else 
			this.coleccionReservas = copiaProfundaReservas(reservasOriginal.getReservas());
	}
	
	public List<Reserva> getReservas() {
		return copiaProfundaReservas(coleccionReservas);
	}
	
	private List<Reserva> copiaProfundaReservas(List<Reserva> coleccionReservasOriginal) {
		
		List<Reserva> coleccionCopiaReservas;
		
		coleccionCopiaReservas = new ArrayList<Reserva>(getNumReservas());
		
		// recorremos todas las reservas comparando
		Iterator<Reserva> it = coleccionReservasOriginal.iterator();
		while(it.hasNext()) {
			Reserva reservaCopia = new Reserva(it.next());
			coleccionCopiaReservas.add(reservaCopia);
		}
		return coleccionCopiaReservas;
		
	}
	
	public void insertar (Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) 
			throw new NullPointerException("ERROR: No se puede realizar una reserva nula.");
		
		if (buscar(reserva) != null)
			throw new OperationNotSupportedException("ERROR: La reserva ya existe.");
		
		// insertamos la nueva cita y actualizamos el tamaño
		coleccionReservas.add(new Reserva(reserva));
	}
	
	public Reserva buscar (Reserva reserva) {
		if (reserva == null)
			throw new NullPointerException("ERROR: No se puede buscar un reserva nula.");
		
		// si el indice supera al tamaño, es que no lo ha encontrado y es un objeto nuevo
		if (coleccionReservas.contains(reserva))
			return new Reserva(reserva); 
		else
			return null;
	}
	
	public void borrar (Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) 
			throw new NullPointerException("ERROR: No se puede anular una reserva nula.");
		
		if (buscar(reserva) != null) 			
			coleccionReservas.remove(reserva);
			
		else
			throw new OperationNotSupportedException("ERROR: La reserva a anular no existe.");
	}
	
	public List<String> representar() {
		if (getNumReservas() == 0)
			throw new IllegalArgumentException("ERROR: La lista de reservas está vacia.");
		
		List<String> representacion = new ArrayList<String>();
		
		for (Reserva r : coleccionReservas)
			representacion.add(r.toString());
	
		return representacion;
	}

	public int getNumReservas() {
		return coleccionReservas.size();
	}
	
	public List<Reserva> getReservasProfesor (Profesor profesor) {
		if (profesor ==  null)
			throw new NullPointerException ("ERROR: No se puede buscar una reserva con profesor nulo.");
		
		List<Reserva> coleccionReservasProfesor;
		
		coleccionReservasProfesor = new ArrayList<Reserva>(getNumReservas());
		
		for (Reserva i: coleccionReservas) {
			Reserva reserva = new Reserva(i);
			
			if (profesor.equals(i.getProfesor())) {
				coleccionReservasProfesor.add(reserva);
			}
		}
		
		return coleccionReservasProfesor;
	}
	
	public List<Reserva> getReservasAula (Aula aula) {
		if (aula ==  null)
			throw new NullPointerException ("ERROR: No se puede buscar una reserva con aula nula.");
		
		List<Reserva> coleccionReservasAula;
		
		coleccionReservasAula = new ArrayList<Reserva>(getNumReservas());
		
		for (Reserva i: coleccionReservas) {
			Reserva reserva = new Reserva(i);
			
			if (aula.equals(i.getAula())) {
				coleccionReservasAula.add(reserva);
			}
		}
		
		return coleccionReservasAula;
	}
	
	public List<Reserva> getReservasPermanencia(Permanencia permanencia) {
		if (permanencia ==  null)
			throw new NullPointerException ("ERROR: No se puede buscar una reserva con permanencia nula.");
		
		List<Reserva> coleccionReservasPermanencia;
		
		coleccionReservasPermanencia = new ArrayList<Reserva>(getNumReservas());
		
		for (Reserva i: coleccionReservas) {
			Reserva reserva = new Reserva(i);
			
			if (permanencia.equals(i.getPermanencia())) {
				coleccionReservasPermanencia.add(reserva);
			}
		}
		
		return coleccionReservasPermanencia; 
	}
	
	public boolean consultarDisponibilidad (Aula aula, Permanencia permanencia) {
		boolean disponible = true;
		
		if (aula == null)
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de un aula nula.");

		if (permanencia == null)
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de una permanencia nula.");
		
		for(Reserva i: coleccionReservas) {
			
			if(i.getAula().equals(aula) && i.getPermanencia().equals(permanencia))
				disponible = false;
		}
		
		return disponible;
			
	}
}
