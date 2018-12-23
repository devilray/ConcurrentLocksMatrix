package entidades;

import main.Contador;

public class Filosofo extends Thread {

	public static final int OPERACIONES = 10;
	
	private int whoami = 0;
	private Palillo left, right;
	private Contador cont;
	
	public Filosofo(int whoami, Contador cont, Palillo left, Palillo right) {
		this.whoami = whoami;
		this.left = left;
		this.right = right;
		this.cont = cont;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < OPERACIONES; i++) {
			System.out.println("Filósofo " + whoami + " pensando.");
			cont.inc();
			right.coger(whoami);
			left.coger(whoami);
			System.out.println("Filósofo " + whoami + " comiendo.");
			right.soltar();
			left.soltar();
			cont.dec();
		}
	}
	
}
