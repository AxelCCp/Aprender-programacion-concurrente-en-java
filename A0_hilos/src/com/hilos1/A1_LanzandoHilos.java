package com.hilos1;

public class A1_LanzandoHilos extends Thread{

	public static void main(String[]RR) {
		A1_LanzandoHilos h1 = new A1_LanzandoHilos(1);				//para generar un hilo, hay que instanciar un obj de la clase thread.
		A1_LanzandoHilos h2 = new A1_LanzandoHilos(2);
		A1_LanzandoHilos h3 = new A1_LanzandoHilos(3);
		
		h1.start();
		h2.start();
		h3.start();
		
		System.out.println("Soy el hilo principal");
		
		
	}
	
	public A1_LanzandoHilos(int id) {
		this.id = id;
	}
	
	//los hilo creados ejecutan el metodo run
	public void run() {
		System.out.println("Soy el hilo: " + id);
	}
	
	private int id;
	
}
