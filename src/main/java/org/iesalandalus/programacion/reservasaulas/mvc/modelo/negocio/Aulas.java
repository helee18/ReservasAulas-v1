package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;

public class Aulas {
	
	List<Aula> coleccionAulas;
	
	public Aulas () {
		coleccionAulas = new ArrayList<Aula>();
	}
	
	public Aulas(Aulas aulasOriginal) {
		if (aulasOriginal == null)
			throw new NullPointerException("ERROR: No se pueden copiar aulas nulas.");
		
		if (aulasOriginal.getNumAulas() == 0)
			this.coleccionAulas = new ArrayList<Aula>();
		else 
			this.coleccionAulas = copiaProfundaAulas(aulasOriginal.getAulas());
	}
	
	public List<Aula> getAulas() {
		return copiaProfundaAulas(coleccionAulas);
	}
	
	private List<Aula> copiaProfundaAulas(List<Aula> coleccionAulasOriginal) {
		
		List<Aula> coleccionCopiaAulas;
		
		coleccionCopiaAulas = new ArrayList<Aula>(getNumAulas());
		
		// recorremos todas las aulas comparando
		Iterator<Aula> it = coleccionAulasOriginal.iterator();
		while(it.hasNext()) {
			Aula aulaCopia = new Aula(it.next());
			coleccionCopiaAulas.add(aulaCopia);
		}
		return coleccionCopiaAulas;
		
	}
	
	public void insertar (Aula aula) throws OperationNotSupportedException {
		if (aula == null) 
			throw new NullPointerException("ERROR: No se puede insertar un aula nula.");
		
		if (buscar(aula) != null)
			throw new OperationNotSupportedException("ERROR: Ya existe un aula con ese nombre.");
		
		// insertamos la nueva cita y actualizamos el tamaño
		coleccionAulas.add(new Aula(aula));
	}
	
	public Aula buscar (Aula aula) {
		if (aula == null)
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		
		// si el indice supera al tamaño, es que no lo ha encontrado y es un objeto nuevo
		if (coleccionAulas.contains(aula))
			return new Aula(aula); 
		else
			return null;
	}
	
	public void borrar (Aula aula) throws OperationNotSupportedException {
		if (aula == null) 
			throw new NullPointerException("ERROR: No se puede borrar un aula nula.");
		
		if (buscar(aula) != null) 			
			coleccionAulas.remove(aula);
			
		else
			throw new OperationNotSupportedException("ERROR: No existe ningún aula con ese nombre.");
	}
	
	public List<String> representar() {
		if (getNumAulas() == 0)
			throw new IllegalArgumentException("ERROR: La lista de aulas está vacia.");
		
		List<String> representacion = new ArrayList<String>(getNumAulas());
		
		Iterator<Aula> it = coleccionAulas.iterator();
			representacion.add(it.next().toString());		
	
		return representacion;
	}

	public int getNumAulas() {
		return coleccionAulas.size();
	}
	

}
