package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;

public class Aulas {
	private int capacidad, tamano;
	
	List<Aula> coleccionAulas;
	
	public Aulas (int capacidadColeccionAulas) {
		if (capacidadColeccionAulas <= 0)
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		
		// Creamos el array con la capacidad introducida
		coleccionAulas = new ArrayList<Aula>(capacidadColeccionAulas);
		
		// Actualizamos los atributos capacidad y tamaño
		capacidad = capacidadColeccionAulas;
		tamano = 0;
	}
	
	public List<Aula> getAulas() {
		if (tamano == 0)
			throw new IllegalArgumentException("ERROR: La lista de reservas está vacia.");
		
		return copiaProfundaAulas(coleccionAulas);
	}
	
	private List<Aula> copiaProfundaAulas(List<Aula> coleccionAulasOriginal) {
		
		List<Aula> coleccionCopiaAulas;
		
		coleccionCopiaAulas = new ArrayList<Aula>(getTamano());
		
		// recorremos todas las aulas comparando
		Iterator<Aula> it = coleccionAulasOriginal.iterator();
		while(it.hasNext()) {
			Aula aulaCopia = new Aula(it.next());
			coleccionCopiaAulas.add(aulaCopia);
		}
		return coleccionCopiaAulas;
		
	}
	
	private boolean tamanoSuperado(int indice) {
		boolean superado = false;
		
		// comprobamos que el indice no supere el tamaño
		if (indice >= (tamano))
			superado = true;
		
		return superado;
	}
	
	private boolean capacidadSuperada(int indice) {
		boolean superada = false;
		
		// comprobamos que el indice no supere la capacidad
		if (indice >= (capacidad))
			superada = true;
		
		return superada;
	}
	
	private int buscarIndice (Aula aula) {
		if (aula == null)
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		
		int indice = 0;
		boolean aulaEncontrada = false;
		while (!tamanoSuperado(indice) && !aulaEncontrada) {
			
			if (coleccionAulas.get(indice).equals(aula))// en caso de que el objeto del array que estamos consultado sea la cita introducida
				aulaEncontrada = true;
			else
				indice++;
			
		}
		
		return indice;
	}
	
	public void insertar (Aula aula) throws OperationNotSupportedException {
		if (aula == null) 
			throw new NullPointerException("ERROR: No se puede insertar un aula nula.");
		
		int indice = buscarIndice(aula);
		
		if (capacidadSuperada(indice))
			throw new OperationNotSupportedException("ERROR: No se aceptan más aulas.");
		
		if (buscar(aula) != null)
			throw new OperationNotSupportedException("ERROR: El aula ya existe");
		
		if (capacidadSuperada(buscarIndice(aula)))
			throw new IllegalArgumentException("ERROR: No se pueden insertar mas aulas.");
		
		// insertamos la nueva cita y actualizamos el tamaño
		coleccionAulas.add(new Aula(aula));
		tamano++;
	}
	
	public Aula buscar (Aula aula) {
		if (aula == null)
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		
		int indice = buscarIndice(aula);
		
		// si el indice supera al tamaño, es que no lo ha encontrado y es un objeto nuevo
		if (!tamanoSuperado(indice))
			return new Aula(coleccionAulas.get(indice)); 
		else
			return null;
	}
	
	public void borrar (Aula aula) throws OperationNotSupportedException {
		if (aula == null) 
			throw new NullPointerException("ERROR: No se puede borrar un aula nula.");
		
		int indice = buscarIndice(aula);
		
		if (tamanoSuperado(indice)) {
			coleccionAulas.remove(indice);
		
			tamano--;
		}else {
			throw new OperationNotSupportedException("ERROR: El aula no existe");
		}
	}
	
	public List<String> representar() {
		if (tamano == 0)
			throw new IllegalArgumentException("ERROR: La lista de reservas está vacia.");
		
		List<String> representacion = new ArrayList<String>(tamano);
		
		Iterator<Aula> it = coleccionAulas.iterator();
			representacion.add(it.next().toString());		
	
		return representacion;
	}
	
	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}
	

}
