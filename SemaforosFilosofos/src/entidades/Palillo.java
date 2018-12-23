package entidades;

public class Palillo {

	private boolean free;
	
	public Palillo() {
		this.free = true;
	}
	
	public synchronized void coger(int quien) {
		while(!free)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		free = false;
	}
	
	public synchronized void soltar() {
		free = true;
		notifyAll();
	}
	
}
