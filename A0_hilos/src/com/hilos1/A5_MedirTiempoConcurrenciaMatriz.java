package com.hilos1;

import java.util.Random;

//Bloque 2.3
public class A5_MedirTiempoConcurrenciaMatriz extends Thread{


	public static void main(String[] args) {
		
		Random random = new Random(System.nanoTime());
		
		double tiempoInicio, tiempoFinal;
		
		for(int i=0; i<matriz.length; i++) {
			for(int j=0; j<matriz[0].length; j++) {
				matriz[i][j] = random.nextInt(10);
			}
		}
		
		tiempoInicio = System.nanoTime();													  //hora en nanosegundos.
		
		A5_MedirTiempoConcurrenciaMatriz h1 = new A5_MedirTiempoConcurrenciaMatriz(0,400);		//(0,2);      //toma las filas 0 y 1 
		A5_MedirTiempoConcurrenciaMatriz h2 = new A5_MedirTiempoConcurrenciaMatriz(400,800);	//(2,4);      //toma las filas 2 y 3
		
		h1.start();
		h2.start();
		
		try {
			h1.join();
			h2.join();
		} catch (Exception e) {}
		
		tiempoFinal = System.nanoTime() - tiempoInicio;
		
		for(int i=0; i<matriz.length; i++) {
			for(int j=0; j<matriz[0].length; j++) {
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println("Tiempo transcurrido: " + tiempoFinal/1000000 + " milisegundos");
	}
	
	
	public A5_MedirTiempoConcurrenciaMatriz(int inicio, int fin){
		this.inicio = inicio;
		this.fin = fin;
	}
	
	public void run() {
		for(int i=inicio; i<fin; i++) {
			for(int j=0; j<matriz[0].length; j++) {
				matriz[i][j] *= 10;
			}
		}
	}
	
	
	private static int tam = 800;	//4;
	private static int [][]matriz = new int[tam][tam];
	private int inicio, fin;
	
	
}
