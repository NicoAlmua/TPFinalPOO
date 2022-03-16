package ar.edu.unju.edm.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FechaUtil {
	
	public static LocalDate convertirStringLocalDate(String fecha) throws Exception {
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fechaLocalDate;
		try {
			fechaLocalDate = LocalDate.parse(fecha,formato);
		}catch(DateTimeParseException d) {
			throw new Exception("La fecha ingresada no tiene formato de fecha");
		}
		return fechaLocalDate;
	}
	
	public static String convertirLocalDateString(LocalDate fechaLocalDate) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String fechaString = formato.format(fechaLocalDate);
		return fechaString;
	}
	
	public static int calcularAntiguedad(LocalDate fechaIngreso) {
		LocalDate ahora = LocalDate.now();
		Period periodo = Period.between(fechaIngreso, ahora);
		return periodo.getYears();
	}
}
