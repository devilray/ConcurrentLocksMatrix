package main;

import java.util.concurrent.locks.ReentrantLock;

import matrix.HiloMatrixCuadrado;
import matrix.HiloMatrixSuma;

/**
 * Programa principal que ejecuta los dos procesos para sumar y potencia.
 *
 * Supongamos dos hilos con la siguiente funcionalidad: uno se dedica a generar una
matriz A de valores enteros de tama�o 3x3, calcular el cuadrado de dicha matriz (A2) e
imprimir en pantalla:
A x A

a1 a2 a3
a4 a5 a6
a7 a8 a9

x

a1 a2 a3
a4 a5 a6
a7 a8 a9

A^2
r1 r2 r3
r4 r5 r6
r7 r8 r9

El otro hilo hace lo mismo pero en lugar de calcular A2, calcula A+A y por lo tanto
imprime en pantalla:

A + A

a1 a2 a3
a4 a5 a6
a7 a8 a9

+

a1 a2 a3
a4 a5 a6
a7 a8 a9


2A
r1 r2 r3
r4 r5 r6
r7 r8 r9


La actividad anterior la repite cada hilo 10 veces. Desarrollar un programa concurrente
en Java que resuelva el problema anterior. Para resolver la sincronizaci�n necesaria entre los
hilos se usar�n los cerrojos de la clase ReentrantLock.

 * @author Juan Jos� Mar�n Peralta
 * @version 2.0
 */
public class Programa {
	
	
	//CONSTANTS
	/**
	 * Constante que hace referencia a las dimensiones que tendr�n nuestras matrices 
	 * cuadradas. En este caso ser�n de 3 x 3.
	 */
	public static final int DIM_MATRIX = 3;
	
	/**
	 * 
	 */
	public static final int CANT_THREADS = 10;
	
	
	//GLOBAL VARIABLES IN SHARED MEMORY
	/**
	 * 
	 */
	public static ReentrantLock cerrojo = new ReentrantLock();
	
	/**
	 * M�todo global que realiza la suma entre dos matrices que se la pasan por par�metro y 
	 * devuelve un matriz resultado de la suma.
	 * @param a Primera matriz a sumar.
	 * @param b Segunda matriz a sumar.
	 * @return Matriz c resultado de sumar a y b.
	 */
	public static int[][] sumaMatrices(int[][] a, int[][] b) {
		int[][] c = new int[DIM_MATRIX][DIM_MATRIX];
		
		if((a.length == b.length) && (a[0].length == b[0].length)) {
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a[i].length; j++) {
					c[i][j] = a[i][j] + b[i][j];
				}
			}
		} else 
			throw new IllegalArgumentException("ERROR. Las longitudes de alguna de las matrices son incorrectas.");
		
		return c;
	}
	
	/**
	 * M�todo global que realiza el producto entre dos matrices que se la pasan por par�metro y 
	 * devuelve un matriz resultado de la multiplicaci�n.
	 * @param a Primera matriz a multiplicar.
	 * @param b Segunda matriz a multiplicar.
	 * @return Matriz c resultado de multiplicar a y b.
	 */
	public static int[][] cuadradoMatrices(int[][] a, int[][] b) {
		int[][] c = new int[DIM_MATRIX][DIM_MATRIX];
		
		if((a.length == b.length) && (a[0].length == b[0].length)) {
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a[i].length; j++) {
					c[i][j] = a[i][j] * b[i][j];
				}
			}
		} else
			throw new IllegalArgumentException("ERROR. Las longitudes de alguna de las matrices son incorrectas.");
		
		return c;
	}
	
	/**
	 * M�todo global que muestra por pantalla de forma concurrente, una matriz que se le pasa por par�metro.
	 * @param matriz Matriz a imprimir por pantalla.
	 */
	public static void show(int[][] matriz) {
		try {
			cerrojo.lock();
			
			for (int i = 0; i < matriz.length; i++) {
				for (int j = 0; j < matriz[i].length; j++) {
					System.out.println("{" + matriz[i][j] + "}" + " ");
				}
				System.out.println("");
			}
			System.out.println("");
		} finally {
			cerrojo.unlock();
		}
	}

	public static void main(String[] args) {
	
		HiloMatrixSuma a = new HiloMatrixSuma();
		HiloMatrixCuadrado b = new HiloMatrixCuadrado();
		Thread hiloCuadrado = new Thread(a);
		Thread hiloSuma = new Thread(b);
		
		hiloCuadrado.start();
		hiloSuma.start();
		
		try {
			hiloCuadrado.join();
			hiloSuma.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
