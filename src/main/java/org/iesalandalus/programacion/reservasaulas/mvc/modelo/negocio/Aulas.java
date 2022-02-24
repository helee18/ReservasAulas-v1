package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;

public class Aulas {
	private int capacidad, tamano;
	
	Aula[] coleccionAulas;
	
	public Aulas (int capacidadColeccionAulas) {
		if (capacidadColeccionAulas <= 0)
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		
		// Creamos el array con la capacidad introducida
		coleccionAulas = new Aula[capacidadColeccionAulas];
		
		// Actualizamos los atributos capacidad y tamaño
		capacidad = capacidadColeccionAulas;
		tamano = 0;
	}
	
	public Aula[] get() {
		if (tamano == 0)
			throw new IllegalArgumentException("ERROR: La lista de reservas está vacia.");
		
		return copiaProfundaAulas(coleccionAulas);
	}
	
	private Aula[] copiaProfundaAulas(Aula[] coleccionAulasOriginal) {
		
		Aula[] coleccionCopiaAulas;
		
		coleccionCopiaAulas = new Aula[getTamano()];
		
		// recorremos todas las citas comparando
		for (int i = 0; i < coleccionAulasOriginal.length && coleccionAulasOriginal[i] != null; i++) {
			Aula aulaCopia = new Aula(coleccionAulasOriginal[i]);
			coleccionCopiaAulas[i] = aulaCopia;
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
			
			if (coleccionAulas[indice].equals(aula))// en caso de que el objeto del array que estamos consultado sea la cita introducida
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
		coleccionAulas[indice] = new Aula(aula);
		tamano++;
	}
	
	public Aula buscar (Aula aula) {
		if (aula == null)
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		
		int indice = buscarIndice(aula);
		
		// si el indice supera al tamaño, es que no lo ha encontrado y es un objeto nuevo
		if (!tamanoSuperado(indice))
			return new Aula(coleccionAulas[indice]); 
		else
			return null;
	}
	
	private void desplazarUnaPosicionHaciaIzquierda (int indice) {
		for (int i = indice; !tamanoSuperado(i); i++) { // recorrer todos los objetos hacia la derecha a partir del indice hasta legar al final
			coleccionAulas[i] = coleccionAulas[i+1]; // asignamos en cada posicion el objeto siguiente
		}
	}
	
	public void borrar (Aula aula) throws OperationNotSupportedException {
		if (aula == null) 
			throw new NullPointerException("ERROR: No se puede borrar un aula nula.");
		
		int indice = buscarIndice(aula);
		
		if (tamanoSuperado(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
		
			tamano--;
		}else {
			throw new OperationNotSupportedException("ERROR: El aula no existe");
		}
	}
	
	public String[] representar() {
		if (tamano == 0)
			throw new IllegalArgumentException("ERROR: La lista de reservas está vacia.");
		
		String[] representacion = new String[tamano];
		
		for (int i = 0; !tamanoSuperado(i); i++) {
			representacion[i] = coleccionAulas[i].toString();
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
