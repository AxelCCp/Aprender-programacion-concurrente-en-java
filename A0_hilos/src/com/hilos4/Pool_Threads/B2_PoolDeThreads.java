package com.hilos4.Pool_Threads;

import java.util.Random;

//Bloque 4.2 - Codigo que construye una matriz de 20mil x 20mil, y mide el tiempo que demora en crear 20mil hilos. Donde cada hilo construye una fila.

public class B2_PoolDeThreads implements Runnable{
	
	
	public B2_PoolDeThreads(int fila) {
		this.fila = fila;
	}


	public static void main(String[] args) {
		
		Random random = new Random(System.nanoTime());
		
		double tiempo_inicio, tiempo_final;
		
		for(int i=0; i<matriz.length; i++) {
			for(int j=0; j<matriz[0].length; j++) {
				matriz[i][j] = random.nextInt(10);
			}
		}
		
		
		tiempo_inicio = System.nanoTime();
		
		Thread[]hilos = new Thread[tam];
		
		for(int i=0; i<hilos.length; i++) {
			Runnable runnable = new B2_PoolDeThreads(i);
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
		
		tiempo_final = System.nanoTime() - tiempo_inicio;
		
		System.out.println((tiempo_final/1000000) + "milisegundos.");
		
		/*for(int i=0; i<matriz.length; i++) {
			for(int j=0; j<matriz[0].length; j++) {
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println();
		}*/
		
	}

	
	@Override
	public void run() {
		for(int i=0; i<tam ; i++) {
			matriz[fila][i] *= 10;              //cada hilo se va a encargar de su fila.
		}
		
	}
	
	
	private static int tam = 20000;
	private static int [][] matriz = new int [tam][tam];
	private int fila;                                                            //no es estatico pq cada uno de los hilos va a tener un atributo fila diferente.
	
	
}
