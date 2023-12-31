package com.hilos1;

import java.util.Random;

//bloque 3.3 - parte 2.
public class A9_InterbloqueoEstrategiaBuffer_EstrategiaBuffer implements Runnable{

	
	public A9_InterbloqueoEstrategiaBuffer_EstrategiaBuffer(int id) {
		this.id = id;
	}
	
	public static void main(String[] args) {
	
		Runtime runtime = Runtime.getRuntime();
		
		int nucleos = runtime.availableProcessors();
		
		Thread[]hilos = new Thread[nucleos];
		
		for(int i=0; i<hilos.length; i++) {
			
			Runnable runnable = new A9_InterbloqueoEstrategiaBuffer_EstrategiaBuffer(i);           
			
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
		
		
		System.out.println("Soy el hilo: " + cont);
		
	}
	
	
	//este código es más eficiente q el anterior. En el For se hace el trabajo pesado, donde todos los hilos iteran 20000 veces al mismo tiempo.
	//luego en el syncronized los hilos entran uno a la vez y solo hacen una asignacion.
	@Override
	public void run() {
		
		for(int i=0; i<20000; i++) {
			cont_private++;
		}
		
		synchronized (cerrojoA) {
			cont += cont_private;
		}
		
	}
	
	
	
	
	private int id;
	private static Random cerrojoA = new Random();													//los cerrojos pueden ser de cualquier tipo.
	private static Thread cerrojoB = new Thread();
	private int cont_private = 0;
	private static int cont = 0;
	
	
	
}
