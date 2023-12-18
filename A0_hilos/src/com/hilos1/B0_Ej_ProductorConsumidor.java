package com.hilos1;

//Bloque 3.4
//Consumidor : si hay tarta le resto 1 a la variable.
//Consumidor : si no hay despierta al cocinero y me duermo.
//Cocinero : me duermo esperando a que me llamen.
//Cocinero : se me llaman produzco 10 trozos de tarta y me duermo. 


public class B0_Ej_ProductorConsumidor implements Runnable{

	public B0_Ej_ProductorConsumidor(boolean consumidor) {
		this.consumidor = consumidor;
	}
	
	public static void main(String[] args) {
		
		int numHilos = 11;																		//10 consumidores y 1 productor.
		Thread [] hilos  = new Thread[numHilos];
		
		for(int i=0; i<hilos.length; i++) {
			Runnable runnable = null;
			if(i != 0) {
				runnable = new B0_Ej_ProductorConsumidor(true);
			} else {
				runnable = new B0_Ej_ProductorConsumidor(false);
			}
			
			hilos[i] = new Thread(runnable);
			hilos[i].start();
		}
		
		for(int i=0; i<hilos.length; i++) {
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	@Override
	public void run() {
		
		while(true) {
			if(consumidor) {
				this.consumiendo();
			} else {
				this.cocinando();
			}
		}
		
	}
	
	
	private void consumiendo() {
		
		synchronized (lock) {
			
			if(tarta > 0) {
				
				tarta--;																	//si hay tartas, el consumidor se come una.
				System.out.println("Quedan " + tarta + " porciones de tarta.");
				
				try {
					Thread.sleep(1000);														//cada vez que un consumidor consuma una tarta, detenemos el hilo secundario por un segundo.
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else {
				lock.notifyAll(); 															//despertamos a todos los hilos, para despertar al cocinero. se hace estto pq no se puede despertar al hilo del cocinero en concreto. Al despertar a todos los hilos, en el bucle while  volvera a aparecer el cocienero q se dara cuenta a no hay tartas y volverá a producir.
				try {
					lock.wait();												            //como este no es el cocinero, lo echamos a dormir, así se elimina almenos un hilo.	
				} catch (InterruptedException e) {
					e.printStackTrace();
				}  				
			}
		}
		
	}

	private void cocinando() {
	
		synchronized (lock) {
			
			if(tarta == 0) {
				tarta = 10;
				System.out.println("Soy el cocinero y quedan: " + tarta + ".");
				lock.notifyAll();                                                          //cuando se ejecuta el cocinero, despierta a todos los demás.
			}
			
			try {
				lock.wait();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	private boolean consumidor;
	private static int tarta = 0;
	private static Object lock = new  Object();
}
