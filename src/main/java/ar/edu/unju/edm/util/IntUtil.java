package ar.edu.unju.edm.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IntUtil {

	public static int pedirInt() throws Exception {
		Scanner sc = new Scanner(System.in);
		int respuesta ;
		try {
			respuesta = sc.nextInt();
			
		}catch(InputMismatchException e) {
			throw e;
		}
		return respuesta;
	}
}
