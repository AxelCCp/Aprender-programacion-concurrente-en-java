package com.hilos10.Condition;

import java.util.Random;


//bloque 5.3
public class B8_Principal {
	
	
	public static void main(String[] args) {

		Random random = new Random(System.nanoTime());
		
		Thread[]vec = new Thread[30];
		
		for(int i=0; i<vec.length; i++) {
			
			int orden = random.nextInt(2);
			
			Runnable runnable = new B8_Buffer(orden, i);
			vec[i] = new Thread(runnable);
			vec[i].start();
			
		}
		
		try {
			 for(int i=0; i<vec.length; i++) {
				 vec[i].join();
			 }
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	

}
