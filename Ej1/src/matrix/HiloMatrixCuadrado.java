package matrix;

import static main.Programa.*;

import java.util.Random;

public class HiloMatrixCuadrado implements Runnable {

	private int[][] m = new int[DIM_MATRIX][DIM_MATRIX];
	
	public HiloMatrixCuadrado() {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				this.m[i][j] = new Random().nextInt(9) + 1;
			}
		}
	}
	
	@Override
	public void run() {
		System.out.println("Comienzo del Hilo que MULTIPLICA matrices: \n");
		
		//calculamos el producto de nuestra matriz aleatoria
		int [][] result = new int[DIM_MATRIX][DIM_MATRIX];
		result = cuadradoMatrices(m, m);
		
		//lo imprimimos por pantalla la matriz resultante del producto
		for (int i = 0; i < CANT_THREADS; i++) {
			System.out.println("Matriz Nº " + (i + 1) + " para multiplicar");
			//begin Critic Section (SC) (busy)
			show(m);
			//end CS (free)
			System.out.println("Hilo Nº " + (i + 1) + " producto. M * M");
			//begin CS (busy)
			show(result);
			//end CS (free)
			System.out.println("Fin del hilo Nº " + (i + 1) + " de producto");
		}
	}

}
