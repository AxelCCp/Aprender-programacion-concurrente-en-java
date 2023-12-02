package com.hilos;

public class A2_Join_HilosDesdeVector extends Thread{
	
	
	public A2_Join_HilosDesdeVector(int id) {
		this.id = id;
	}

	
	public static void main(String[] args) {
		
		A2_Join_HilosDesdeVector[]vector = new A2_Join_HilosDesdeVector[5];
		
		//El hilo principal lanza los hilos secundarios
		for(int i=0; i<vector.length; i++) {
			vector[i] = new A2_Join_HilosDesdeVector(i+1);
			vector[i].start();
			
			//No puede ir aquí el join(), ya q se pierde el paralelismo. Esto pasa pq es for va determinado por el hilo principal y los hilos se esperarian consecutivamente a que ejecuten el run().
			/*
			try {
				vector[i].join();
			}catch (Exception e) {
				
			}
			*/
		}
		
		//Esperar a que los hilos secundarios terminen su tarea antes de seguir el hilo principal.
		try {
			for(int i=0; i<vector.length; i++) {
				vector[i].join();
			}
		}catch (Exception e) {
			
		}
		
				
		System.out.println("Soy el hilo principal..");
	}
	
	
	public void run() {
		System.out.println("Soy el hilo: " + id);
	}
	
	private int id;
}
