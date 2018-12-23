package entidades;

import buf.Buffer;

public class Productor extends Thread {

	private Buffer b;
	private int e;
	
	public Productor(Buffer b, int e) {
		this.b = b;
		this.e = e;
	}
	
	@Override
	public void run() {
		
		try {
			b.insertar(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("He puesto el elemento en el buffer.");
		return;

	}

}
