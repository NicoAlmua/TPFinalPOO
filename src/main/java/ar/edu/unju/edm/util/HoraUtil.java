package ar.edu.unju.edm.util;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Scanner;

public class HoraUtil {

/*public static LocalTime convertirStringLocalDate(String hora) throws Exception {
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("hh:mm");
		LocalTime horaLocalDate;
		try {
			horaLocalDate = LocalTime.parse(hora,formato);
		}catch(DateTimeParseException d) {
			throw new Exception("La hora ingresada no tiene formato de hora");
		}
		return horaLocalDate;
	}
}*/

public static LocalTime convertirStringLocalDate() throws Exception {
	
	LocalTime horaLocalDate;
	Scanner sc = new Scanner(System.in);
	String hora = sc.next();
	try {
		horaLocalDate = LocalTime.parse(hora, DateTimeFormatter.ofPattern("HH:mm"));
	}catch(DateTimeParseException d) {
		throw new Exception("La hora ingresada no tiene formato de hora");
	}
	return horaLocalDate;
}
}
