package com.hilos;


public class A3_Indeterminismo extends Thread{

	public static void main(String[] args) {
		
		A3_Indeterminismo[]vector = new A3_Indeterminismo[1000];
		
		for(int i=0; i<vector.length; i++) {
			vector[i] = new A3_Indeterminismo();
			vector[i].start();
		}
	
		try {
			for(int i=0; i<vector.length; i++) {
				vector[i].join();
			}
		}catch (Exception e) {
			
		}
		
		System.out.println("Contador: " + cont);
	}

	//indeterminismo - sección critica
	//Aquí se produce indeterminismo ya que nunca llega al valor esperado. Debería se 1000 hilos * 1000 = 1.000.000 = cont 
	//por los tanto esta es una sección critica.
	
	public void run() {
		for(int i=0; i<1000; i++) {
			cont++;
		}
	}
	
	
	
	private static int cont = 0;
}

