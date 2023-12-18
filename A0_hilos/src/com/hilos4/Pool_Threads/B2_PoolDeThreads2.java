package com.hilos4.Pool_Threads;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Bloque 4.2 - Codigo que construye una matriz de 20mil x 20mil, y mide el tiempo que demora en crear 20mil hilos. Donde cada hilo construye una fila.
//se usa: newFixedThreadPool() - newCachedThreadPool() - newSingleThreadExecutor() 

public class B2_PoolDeThreads2 implements Runnable{
	
	
	public B2_PoolDeThreads2(int fila) {
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
									   //
		                               
	    //ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());		//156.123 milisegundos	...  //(numero de nucleos logicos del pc)
	    //ExecutorService pool = Executors.newCachedThreadPool();                                               //373.039 milisegundos  ...
	    ExecutorService pool = Executors.newSingleThreadExecutor();                                             //168.498 milisegundos 
	    
		for(int i=0; i<tam; i++) {
			Runnable runnable = new B2_PoolDeThreads2(i);
			pool.execute(runnable);                                                                                   //ejecuta el runnable
		}
		
		
		pool.shutdown();                              															      //con los pool de threads, no existen los join(), para esperar y luego que parta de nuevo el hilo principal. ShutDown() : determina los hilos de pool que se iran apagando una vez que terminen sus tareas. no puedes agregar más hilos despues del shutdown. 
		while(!pool.isTerminated());  				                                                                  //se usa una condicion de guarda. isTerminated() : devuelve true si todas las tareas han terminado, siguiendo un shutdown.
			
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
