package com.hilos9.ReentrantLock;

import com.hilos8.TiposAtomicos.B6_Atomic;
import com.hilos8.TiposAtomicos.B6_Synchro;

//Bloque 5.2
public class B7_Principal {

public static void main(String[] args) {
		
		Runtime runtime = Runtime.getRuntime();
		
		int nucleos = runtime.availableProcessors();
		
	//Synchro
		
		Thread[] vec = new Thread[nucleos];
		
		lanzarSynchro(vec);
		
		esperarHilos(vec);
		
		System.out.println(B6_Synchro.getCont());
		
	//Atomic
		
		vec = new Thread[nucleos];
		
		lanzarAtomic(vec);
		
		esperarHilos(vec);
		
		System.out.println(B6_Atomic.getCont());
		
	//ReentrantLock
		
		vec = new Thread[nucleos];
		
		lanzarReentrant(vec);
		
		esperarHilos(vec);
		
		System.out.println(B7_Reentrant.getCont());
		
		
	}
	
	
	private static void lanzarSynchro(Thread[]vec) {
		
		for(int i=0; i<vec.length; i++) {
			Runnable run = new B6_Synchro();
			vec[i] = new Thread(run);
			vec[i].start();	
		}
		
	}
	
	
	private static void lanzarAtomic(Thread[]vec) {
		
		for(int i=0; i<vec.length; i++) {
			Runnable run = new B6_Atomic();
			vec[i] = new  Thread(run);
			vec[i].start();	
		}
		
	}
	
	
	private static void lanzarReentrant(Thread[]vec) {
		
		for(int i=0; i<vec.length; i++) {
			Runnable run = new B7_Reentrant();
			vec[i] = new Thread(run);
			vec[i].start();	
		}
		
	}
	
	
	private static void esperarHilos(Thread[]vec) {
		
		try {
			for(int i=0; i<vec.length; i++) {
				vec[i].join();
			}
		} catch (Exception e) {
			
		}
	}
	
}
