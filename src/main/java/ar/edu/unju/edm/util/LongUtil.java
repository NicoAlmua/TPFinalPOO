package ar.edu.unju.edm.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LongUtil {

	public static long pedirLong() throws Exception {
		Scanner sc = new Scanner(System.in);
		long respuesta ;
		try {
			respuesta = sc.nextLong();
			
		}catch(InputMismatchException e) {
			throw e;
		}
		return respuesta;
	}
}
