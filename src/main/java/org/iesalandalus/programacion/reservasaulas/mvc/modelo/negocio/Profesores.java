package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;

public class Profesores {

	private int capacidad, tamano;
	
	Profesor[] coleccionProfesores;
	
	public Profesores (int capacidadColeccionProfesores) {
		if (capacidadColeccionProfesores <= 0)
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		
		// Creamos el array con la capacidad introducida
		coleccionProfesores = new Profesor[capacidadColeccionProfesores];
		
		// Actualizamos los atributos capacidad y tamaño
		capacidad = capacidadColeccionProfesores;
		tamano = 0;
	}
	
	public Profesor[] get() {
		if (tamano == 0)
			throw new IllegalArgumentException("ERROR: La lista de profesores está vacia.");
		
		return copiaProfundaProfesores(coleccionProfesores);
	}
	
	private Profesor[] copiaProfundaProfesores(Profesor[] coleccionProfesoresOriginal) {
		
		Profesor[] coleccionCopiaProfesores;
		
		coleccionCopiaProfesores = new Profesor[getTamano()];
		
		// recorremos todas las citas comparando
		for (int i = 0; i < coleccionProfesoresOriginal.length && coleccionProfesoresOriginal[i] != null; i++) {
			Profesor profesorCopia = new Profesor(coleccionProfesoresOriginal[i]);
			coleccionCopiaProfesores[i] = profesorCopia;
		}
		
		return coleccionCopiaProfesores;
		
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
	
	private int buscarIndice (Profesor profesor) {
		if (profesor == null)
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		
		int indice = 0;
		boolean aulaEncontrada = false;
		while (!tamanoSuperado(indice) && !aulaEncontrada) {
			
			if (coleccionProfesores[indice].equals(profesor))// en caso de que el objeto del array que estamos consultado sea la cita introducida
				aulaEncontrada = true;
			else
				indice++;
			
		}
		
		return indice;
	}
	
	public void insertar (Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) 
			throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");
		
		int indice = buscarIndice(profesor);
		
		if (capacidadSuperada(indice))
			throw new OperationNotSupportedException("ERROR: No se aceptan más profesores.");
		
		if (buscar(profesor) != null)
			throw new OperationNotSupportedException("ERROR: El profesor ya existe");
		
		if (capacidadSuperada(buscarIndice(profesor)))
			throw new IllegalArgumentException("ERROR: No se pueden insertar mas profesores.");
		
		// insertamos la nueva cita y actualizamos el tamaño
		coleccionProfesores[indice] = new Profesor(profesor);
		tamano++;
	}
	
	public Profesor buscar (Profesor profesor) {
		if (profesor == null)
			throw new NullPointerException("ERROR: No se puede buscar un profesor nulo.");
		
		int indice = buscarIndice(profesor);
		
		// si el indice supera al tamaño, es que no lo ha encontrado y es un objeto nuevo
		if (!tamanoSuperado(indice))
			return new Profesor(coleccionProfesores[indice]); 
		else
			return null;
	}
	
	private void desplazarUnaPosicionHaciaIzquierda (int indice) {
		for (int i = indice; !tamanoSuperado(i); i++) { // recorrer todos los objetos hacia la derecha a partir del indice hasta legar al final
			coleccionProfesores[i] = coleccionProfesores[i+1]; // asignamos en cada posicion el objeto siguiente
		}
	}
	
	public void borrar (Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) 
			throw new NullPointerException("ERROR: No se puede borrar un profesor nulo.");
		
		int indice = buscarIndice(profesor);
		
		if (tamanoSuperado(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
		
			tamano--;
		}else {
			throw new OperationNotSupportedException("ERROR: El profesor no existe");
		}
	}
	
	public String[] representar() {
		if (tamano == 0)
			throw new IllegalArgumentException("ERROR: La lista de reservas está vacia.");
		
		String[] representacion = new String[tamano];
		
		for (int i = 0; !tamanoSuperado(i); i++) {
			representacion[i] = coleccionProfesores[i].toString();
		}
		
	
		return representacion;
	}
	
	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}
}
