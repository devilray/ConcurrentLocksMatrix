package buf;

public class Buffer {

	private int cima;
	private int capacidad;
	private int vector[];
	
	public Buffer(int size) {
		this.cima = 0;
		this.capacidad = size;
		this.vector = new int[size];
	}
	
	public synchronized int extraer() {
		while(cima == 0)
			try {
				wait();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		notifyAll();
		
		return vector[--cima];
	}
	
	public synchronized void insertar(int elemento) {
		while(cima == capacidad)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		vector[cima] = elemento;
		cima++;
		
		notifyAll();
	}
	
}
