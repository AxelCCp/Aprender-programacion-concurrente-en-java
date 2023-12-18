package com.hilos1;

import java.util.Random;

//Realizar la multiplicacion de todos los elementos de un vector de enteros por 10.

public class A4_Solucion_Indeterminismo extends Thread{

	public static void main(String[] args) {
		
		Random random = new Random(System.nanoTime());
		
		for(int i=0; i<vec.length; i++) {
			vec[i] = random.nextInt(10);
		}
		
		A4_Solucion_Indeterminismo h1 = new A4_Solucion_Indeterminismo(0, 4);
		A4_Solucion_Indeterminismo h2 = new A4_Solucion_Indeterminismo(4, 8);
		
		h1.start();
		h2.start();
		
		//no va a continuar el hilo principal hasta q hayan terminado los hilos secundarios.
		try {
			h1.join();
			h2.join();
		}catch (Exception e) {
			
		}
		
		for(int i=0; i<vec.length; i++) {
			System.out.print(vec[i] + " ");
		}

	}

	
	// hilo 1: ini=0 y fin=4  ---  hilo 1: ini=4 y fin=8
	// 0 1 2 3     4 5 6 7  8
	// ------- <4  ------- <8
	public void run() {
		for(int i=ini; i<fin; i++) {
			vec[i] *= 10;
		}
	}
	
	
	
	public A4_Solucion_Indeterminismo(int ini, int fin) {
		this.ini = ini;
		this.fin = fin;
	}
	
	
	private static int tam = 8;
	private static int [] vec = new int[tam];
	private int ini, fin;
	
	
}
