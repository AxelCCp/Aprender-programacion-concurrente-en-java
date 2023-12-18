package com.hilos8.TiposAtomicos;

public class B6_Synchro implements Runnable{

	@Override
	public void run() {
		synchronized (cerrojo) {
			for(int i=0; i<100000; i++) {
				cont++;
			}
		}
	}

	
	public static int getCont() {
		return cont;
	}
	
	private static int cont = 0;
	private static Object cerrojo = new Object();
	
}
