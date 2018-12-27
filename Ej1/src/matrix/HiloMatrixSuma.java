package matrix;

import static main.Programa.*;

import java.util.Random;

public class HiloMatrixSuma implements Runnable {

	private int[][] m = new int[DIM_MATRIX][DIM_MATRIX];
	
	public HiloMatrixSuma() {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				this.m[i][j] = new Random().nextInt(9) + 1;
			}
		}
	}
	
	@Override
	public void run() {
		System.out.println("Comienzo del Hilo que SUMA matrices: \n");
		
		int[][] result = new int[DIM_MATRIX][DIM_MATRIX];
		result = sumaMatrices(m,m);
		
		for (int i = 0; i < CANT_THREADS; i++) {
			System.out.println("Matriz Nº " + (i + 1) + " para sumar");
			show(m);
			System.out.println("Hilo Nº " + (i + 1) + " suma. M + M");
			show(result);
			System.out.println("Fin del hilo Nº " + (i + 1) + " de sumar.");
		}
	}

}
