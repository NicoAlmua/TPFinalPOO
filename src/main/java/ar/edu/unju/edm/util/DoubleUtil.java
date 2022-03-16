package ar.edu.unju.edm.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DoubleUtil {

	public static double pedirDouble() throws Exception {
		Scanner sc = new Scanner(System.in);
		double respuesta;
		try {
			respuesta = sc.nextDouble();

		} catch (InputMismatchException e) {
			throw e;
		}

		return respuesta;

	}
}
