package com.hilos;

import java.util.Random;

//bloque 3.2
public class A8_CondicionDeGuardaWaitNotifyall implements Runnable {
	
	public  A8_CondicionDeGuardaWaitNotifyall(int id) {
		this.id = id;	
	}
	
	public static void main(String[] args) {
		
		Runtime runtime = Runtime.getRuntime();
		
		int nucleos = runtime.availableProcessors();
		
		Thread[]hilos = new Thread[nucleos];
		
		
		for(int i=0; i<hilos.length; i++) {
			
			Runnable runnable = new A8_CondicionDeGuardaWaitNotifyall(i);           
			
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
		
		System.out.println("Soy el hilo principal...");
	}

	
	//Dentro del run se quiere ordenar a los hilos segun su id. --- LA COLA DE synchronized ES DIFERENTE A LA COLA DEL cerrojo.
	@Override
	public void run() {
		
		//cola del synchronized --- los hilos del synchronized se despiertan automaticamente al salir un hilo del synchronized.
		synchronized(cerrojo){
			
			//si no eres el hilo que se está esperando, te mandamos a la cola del cerrojo --- a los hilos del cerro hay q despertarlos cuando sale un hilo del synchronized.
			while(id != cont) {															  //debe ser un while y no un if, ya que los hilos que duermen en el cerrojo, despiertan donde se les puso el wait(), entonces con el while, se vuelve a evaluar si es el turno del hilo o no. 					
				try {
					cerrojo.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			System.out.println("Soy el hilo: " + id);
			cont++;
			cerrojo.notifyAll();                                                          //se despierta a los hilos de la cola del cerrojo.
		}
		
	}

	
	private int id;
	private static Random cerrojo = new Random();	//el cerrojo puede ser de cualquier tipo.
	private static int cont;

}
