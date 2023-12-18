package com.hilos11.ConditionReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class B9_Aeropuerto implements Runnable{
	
	private B9_Avion avion;
	private static int contDespegar = 0;
	
	private static ReentrantLock lock = new ReentrantLock();
	private static Condition despegar = lock.newCondition();
	private static  Condition aparcamiento = lock.newCondition();
	
	public B9_Aeropuerto(B9_Avion avion) {
		this.avion = avion;
	}

	@Override
	public void run() {
	
		int intencion = avion.getIntencion();
	
		while(true) {
			lock.lock();
			
			switch(intencion) {
				case 0:
					intencion = avion.getNextIntencion(); 																//intencion 1 
					System.out.println("El avion " + avion.getId() + " está volando");
					contDespegar--;
					aparcamiento.signalAll();
					break;
					
				case 1:
					intencion = avion.getNextIntencion();																//intencion 2
					System.out.println("El avion " + avion.getId() + " va a aterrizar");
					await(aparcamiento);
					break;
					
				case 4:
					intencion = avion.getNextIntencion();																//intencion 0
					
					while(contDespegar >= 2) {
						
						await(aparcamiento);
						System.out.println("El avion " + avion.getId() + " va a pasar a la cola del despegue");
						contDespegar++;
						await(despegar);
						break;
					}
					
				default:
					intencion = avion.getNextIntencion();																//intencion 3 o 4
					System.out.println("El avion " + avion.getId() + " está en el parking...");
					
			}
			
			
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			lock.unlock();
		}
		
	}
	
	private void await(Condition condition) {
		
		try {
			condition.await();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
