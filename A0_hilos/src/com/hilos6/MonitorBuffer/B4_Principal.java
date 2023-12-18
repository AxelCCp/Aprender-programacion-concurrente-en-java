package com.hilos6.MonitorBuffer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class B4_Principal implements Runnable{
	
	private int id;
	private static B4_Buffer buf = new B4_Buffer();										//monitor
	
	
	public B4_Principal(int id) {
		this.id = id;
	}


	@Override
	public void run() {
		
		if(id==0) {																		//id==0 : quiere decir q estamos frente a un consumidor.
			
			int elemento;
			
			while(true) {
				elemento = buf.leer();
				if(elemento != -1) {
					System.out.println("El elemento extraido del buffer es: " + elemento);
				}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		} else {
			
			while(true) {
				buf.escribir();
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	
	public static void main(String[]args) {
		
		Runtime runtime = Runtime.getRuntime();
		int nucleos = runtime.availableProcessors();
		ExecutorService poolLector = Executors.newFixedThreadPool(nucleos); 
		
		for(int i=0; i<nucleos;i++) {
			Runnable runnable = new B4_Principal(0);
			poolLector.execute(runnable);
		}
		
		poolLector.shutdown();

		ExecutorService poolEscritor = Executors.newFixedThreadPool(2); 
		
		for(int i=0; i<2;i++) {
			Runnable runnable = new B4_Principal(1);
			poolEscritor.execute(runnable);
		}
		
		poolEscritor.shutdown();
		while(!poolEscritor.isTerminated());
		
	}
}
