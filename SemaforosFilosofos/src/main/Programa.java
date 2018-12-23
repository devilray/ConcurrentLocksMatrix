package main;

import entidades.Filosofo;
import entidades.Palillo;

public class Programa {
	
	private Filosofo f[] = new Filosofo[5];
	private Palillo palillos[] = new Palillo[5];
	private Contador cont;
	private int cantFilosofos = 5;
	
	public Programa() {
		cont = new Contador(cantFilosofos - 1);
		
		for (int i = 0; i < cantFilosofos; i++) {
			palillos[i] = new Palillo();
		}
		
		for (int i = 0; i < cantFilosofos; i++) {
			f[i] = new Filosofo(i, cont, palillos[i], palillos[(i + 1) % cantFilosofos]);
			f[i].start();
		}
		
		for (int i = 0; i < cantFilosofos; i++) {
			try {
				f[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		
		new Programa();

	}

}
