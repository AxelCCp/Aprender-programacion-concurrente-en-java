package com.hilos1;

import java.util.Random;

//bloque 3.3 - parte 1.
//ESTE ES UN EJEMPLO DE CERROJOS CRUZADOS - NUNCA SE DEBEN CRUZAR LO CERROJOS YA QUE SE PRODUCE INTERBLOQUEO - NO USAR ESTE CÓDIGO.
public class A9_InterbloqueoEstrategiaBuffer_Ej_CerrojosCruzados implements Runnable{
	
	public A9_InterbloqueoEstrategiaBuffer_Ej_CerrojosCruzados(int id) {
		this.id = id;
	}
	
	public static void main(String[] args) {
	
		Runtime runtime = Runtime.getRuntime();
		
		int nucleos = runtime.availableProcessors();
		
		Thread[]hilos = new Thread[nucleos];
		
		for(int i=0; i<hilos.length; i++) {
			
			Runnable runnable = new A9_InterbloqueoEstrategiaBuffer_Ej_CerrojosCruzados(i);           
			
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
	
	
	//los hilos pares van por un lado y los impares por otro lado.
	@Override
	public void run() {
		
		if(id%2 == 0) {
			synchronized (cerrojoA) {
				mostrarA();
			}
		} else {
			synchronized (cerrojoB) {
				mostrarB();
			}
		}
		
	}
	
	
	private void mostrarA() {
		synchronized (cerrojoB) {
			System.out.println("Soy el hilo: " + id);
		}
	}
	
	private void mostrarB() {
		synchronized (cerrojoA) {
			System.out.println("Soy el hilo: " + id);
		}
	}
	
	private int id;
	private static Random cerrojoA = new Random();													//los cerrojos pueden ser de cualquier tipo.
	private static Thread cerrojoB = new Thread();
	

}
