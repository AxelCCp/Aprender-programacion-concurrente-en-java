package com.hilos9.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class B7_Reentrant implements Runnable {

	@Override
	public void run() {
		
		cerrojo.lock();              											//se consigue una parte de código que se va a ejecutar de forma atomica.
		
		/*if(condicion) {
			cerrojo.unlock();                                                   //Reentrant permite usar condiciones para cerrar un seccion del run que se ejecuta en todos los hilos secundarios.
		}*/
		
		for(int i=0; i<100000; i++) {
			cont++;
		}
		
		cerrojo.unlock();
	}
	
	
	public static int  getCont() {
		return cont;
	}
	
	private static ReentrantLock cerrojo = new ReentrantLock();
	private static int cont;
	private boolean condicion;

}
