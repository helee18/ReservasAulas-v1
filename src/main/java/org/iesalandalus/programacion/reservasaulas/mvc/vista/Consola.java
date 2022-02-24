package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private Consola() {
		
	}
	
	public static void mostrarCabecera(String cabecera) {
		System.out.printf("%n%s%n", cabecera);
		String cadena = "%0" + cabecera.length() + "d%n";
		System.out.println(String.format(cadena, 0).replace("0", "-"));
	}
	
	public static void mostrarMenu() {
		mostrarCabecera("RESERVA AULAS");
		
		for (Opcion opcion: Opcion.values()) {
            System.out.println(opcion); 
		}
	}
	
	public static int elegirOpcion() {
		int opcion;
		
		do {
			System.out.println("Elige una opción: ");
			opcion = Entrada.entero();
		} while (!Opcion.esOrdinalValido(opcion));
		
		return opcion;
	}
	
	public static String leerNombreAula() {
		String nombre;
		
		do {
			
			System.out.println("Introduce el nombre del aula: ");
			nombre = Entrada.cadena();
			
		} while (nombre.trim().isEmpty() || nombre.trim() == null);
		
		return nombre;
	}
	
	public static Aula leerAula() {
		Aula aula;
		
		aula = new Aula(leerNombreAula()); //HACER TRY CATCH EN EL MAIN
		
		return new Aula(aula);
	}
	
	public static String leerNombreProfesor() {
		String nombre;
		
		do {
			
			System.out.println("Introduce el nombre del profesor: ");
			nombre = Entrada.cadena();
			
		} while (nombre.trim().isEmpty() || nombre.trim() == null);
		
		return nombre;
	}
	
	public static Profesor leerProfesor() {
		Profesor profesor;
		
		String correo, telefono;
		
		System.out.println("Introduce el correo del profesor: ");
		correo = Entrada.cadena();
		
		System.out.println("Introduce el telefono del profesor: ");
		telefono = Entrada.cadena();
		
		profesor = new Profesor(leerNombreProfesor(), correo, telefono); //HACER TRY CATCH EN EL MAIN
		
		return new Profesor(profesor);
	}
	
	public static Tramo leerTramo() {
		Tramo[] tramo = Tramo.values();
		
		int tramoElegido;
		
		do {
			
			System.out.println("Elige un tramo (0- Mañana, 1- Tarde: ");
			tramoElegido = Entrada.entero();
			
		} while (tramoElegido < 0 || tramoElegido > 1) ;

		return tramo[tramoElegido];
	}
	
	public static LocalDate leerDia() {
		
		System.out.println("Introduce una fecha para la reserva: ");
		
		String dia;
		boolean diaCorrecto = false;
		do {
			System.out.println("Introduce una fecha para la reserva (dd/mm/aaaa): ");
			dia = Entrada.cadena();
			try {
				LocalDate.parse(dia, FORMATO_DIA);
				diaCorrecto = true;
			} catch (DateTimeParseException e) {
				diaCorrecto = false;
			}
		} while (!diaCorrecto);
		
		LocalDate fecha=LocalDate.parse(dia, FORMATO_DIA);
		
		return fecha;
		
	}
	
}
