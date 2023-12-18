package com.hilos7.MonitorBuffer_v2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class B5_Principal implements Runnable{
	
	private int id;
	private static B5_Buffer buf = new B5_Buffer();										//monitor
	
	
	public B5_Principal(int id) {
		this.id = id;
	}


	@Override
	public void run() {
		
		if(id==0) {																		//id==0 : quiere decir q estamos frente a un consumidor.
			
			int elemento;
			
			while(true) {
				
				elemento = buf.leer();
		
				System.out.println("El elemento extraido del buffer es: " + elemento);
				
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
			Runnable runnable = new B5_Principal(0);
			poolLector.execute(runnable);
		}
		
		poolLector.shutdown();

		ExecutorService poolEscritor = Executors.newFixedThreadPool(2); 
		
		for(int i=0; i<2;i++) {
			Runnable runnable = new B5_Principal(1);
			poolEscritor.execute(runnable);
		}
		
		poolEscritor.shutdown();
		while(!poolEscritor.isTerminated());
		
	}
}
