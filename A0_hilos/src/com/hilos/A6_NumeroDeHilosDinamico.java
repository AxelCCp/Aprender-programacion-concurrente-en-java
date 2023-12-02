package com.hilos;

import java.util.Random;

//Bloque 2.4
//Código que se adapta a la cantidad de nucleos q tenga una CPU
public class A6_NumeroDeHilosDinamico extends Thread{

	public static void main(String[] args) {
		
		//-----------------------------------------------------
		Runtime runtime = Runtime.getRuntime();
		int nucleos = runtime.availableProcessors();								//Nucleos lógicos.
		//System.out.println(nucleos);
		//-----------------------------------------------------
		
		Random random = new Random(System.nanoTime());
		
		double tiempoInicio, tiempoFinal;
		
		for(int i=0; i<matriz.length; i++) {
			for(int j=0; j<matriz[0].length; j++) {
				matriz[i][j] = random.nextInt(10);
			}
		}
		
		Thread[]hilos = new Thread[nucleos];
		
		int rango = tam/nucleos;
		int start = 0;
		int finish = rango;
		
		tiempoInicio = System.nanoTime();	
		
		for(int i=0; i<nucleos; i++) {
			
			//si es diferente a la última iteración.
			if(i != nucleos - 1) {

				hilos[i] = new A6_NumeroDeHilosDinamico(start, finish);
				hilos[i].start();
				start = finish;
				finish += rango;
			
			} else {
				//esto es para solucionar bug que se produce cuando la cantidad de nucleos no es par, ya que el rango es int. 
				hilos[i] = new A6_NumeroDeHilosDinamico(start, tam);
				hilos[i].start();
				
			}
			
		}
		
		for(int i=0; i<nucleos; i++) {
			
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		tiempoFinal = System.nanoTime() - tiempoInicio;
		
		for(int i=0; i<matriz.length; i++) {
			for(int j=0; j<matriz[0].length; j++) {
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println("Tiempo transcurrido: " + tiempoFinal/1000000 + " milisegundos");
		
	}
	
	
	public A6_NumeroDeHilosDinamico(int inicio, int fin){
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
