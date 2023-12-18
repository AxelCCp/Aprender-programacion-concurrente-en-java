package com.hilos2.monitor;

//Bloque 4.1
public class B1_Principal implements Runnable{

	public static void main(String[] args) {
		
		Runtime runtime = Runtime.getRuntime();
		int nucleos = runtime.availableProcessors();
		Thread[]hilos = new Thread[nucleos];
		
		for(int i=0; i<hilos.length; i++) {
			Runnable runnable = new B1_Principal();
			hilos[i] = new Thread(runnable);
			hilos[i].start();
		}
		
		for(int i=0; i<hilos.length; i++) {
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	@Override
	public void run() {
	
		int cont = monitor.inc();     
		System.out.println(cont);
		
	}
	
	
	private static B1_Monitor monitor = new B1_Monitor();											//debe ser static, para que monitor se use en cada uno de los hilos.

}
