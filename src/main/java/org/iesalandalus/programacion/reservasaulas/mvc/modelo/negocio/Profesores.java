package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public class Profesores {
	
	private List<Profesor> coleccionProfesores;
	
	public Profesores () {
		coleccionProfesores = new ArrayList<Profesor>();
	}
	
	public Profesores(Profesores profesoresOriginal) {
		if (profesoresOriginal == null)
			throw new NullPointerException("ERROR: No se pueden copiar profesores nulos.");
		
		if (profesoresOriginal.getNumProfesores() == 0)
			this.coleccionProfesores = new ArrayList<Profesor>();
		else 
			this.coleccionProfesores = copiaProfundaProfesores(profesoresOriginal.getProfesores());
	}
	
	public List<Profesor> getProfesores() {
		return copiaProfundaProfesores(coleccionProfesores);
	}
	
	private List<Profesor> copiaProfundaProfesores(List<Profesor> coleccionProfesoresOriginal) {
		
		List<Profesor> coleccionCopiaProfesores;
		
		coleccionCopiaProfesores = new ArrayList<Profesor>(getNumProfesores());
		
		// recorremos todas las profesores comparando
		Iterator<Profesor> it = coleccionProfesoresOriginal.iterator();
		while(it.hasNext()) {
			Profesor profesorCopia = new Profesor(it.next());
			coleccionCopiaProfesores.add(profesorCopia);
		}
		return coleccionCopiaProfesores;
		
	}
	
	public void insertar (Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) 
			throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");
		
		if (buscar(profesor) != null)
			throw new OperationNotSupportedException("ERROR: Ya existe un profesor con ese nombre.");
		
		// insertamos la nueva cita y actualizamos el tamaño
		coleccionProfesores.add(new Profesor(profesor));
	}
	
	public Profesor buscar (Profesor profesor) {
		if (profesor == null)
			throw new NullPointerException("ERROR: No se puede buscar un profesor nulo.");
		
		// si el indice supera al tamaño, es que no lo ha encontrado y es un objeto nuevo
		if (coleccionProfesores.contains(profesor))
			return new Profesor(profesor); 
		else
			return null;
	}
	
	public void borrar (Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) 
			throw new NullPointerException("ERROR: No se puede borrar un profesor nulo.");
		
		if (buscar(profesor) != null) 		
			coleccionProfesores.remove(profesor);
			
		else
			throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese nombre.");
	}
	
	public List<String> representar() {
		if (getNumProfesores() == 0)
			throw new IllegalArgumentException("ERROR: La lista de profesores está vacia.");
		
		List<String> representacion = new ArrayList<String>();
		
		for (Profesor p : coleccionProfesores)
			representacion.add(p.toString());	
	
		return representacion;
	}

	public int getNumProfesores() {
		return coleccionProfesores.size();
	}
	

}
