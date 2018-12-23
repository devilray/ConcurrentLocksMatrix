package main;

import buf.Buffer;
import entidades.Consumidor;
import entidades.Productor;

public class Programa {

	static Buffer buffer = new Buffer(3);
	
	public static void main(String[] args) {
		
		for (int i = 0; i <= 5; i++)
			new Productor(buffer, i).start();
		for (int j = 0; j <= 5; j++)
			new Consumidor(buffer, j).start();
		System.out.println("Fin del hilo principal.");

	}

}
