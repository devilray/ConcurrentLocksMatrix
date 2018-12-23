package main;

public class Contador {

	private int cont;
	private int tope;
	
	public Contador(int tope) {
		this.cont = 0;
		this.tope = tope;
	}
	
	public void inc() {
		while(cont == tope)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		cont++;
	}
	
	public synchronized void dec() {
		cont--;
		notifyAll();
	}
	
	public synchronized int getContador() {
		return cont;
	}
	
}
