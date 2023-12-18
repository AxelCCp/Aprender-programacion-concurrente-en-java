package com.hilos8.TiposAtomicos;

import java.util.concurrent.atomic.AtomicInteger;

public class B6_Atomic implements Runnable{
	
	@Override
	public void run() {

		for(int i=0; i<100000; i++) {
			cont.getAndIncrement();
		}
		
	}

	
	public static int getCont() {
		return cont.get();
	}
	
	private static AtomicInteger cont = new AtomicInteger();


}
