package entidades;

import buf.Buffer;

public class Consumidor extends Thread {

	private int e;
	private Buffer b;
	
	public Consumidor(Buffer b, int e) {
		this.b = b;
		this.e = e;
	}
	
	@Override
	public void run() {
		
		try {
			e = b.extraer();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return;

	}

}
