package com.hilos5.CyclicBarrier;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//bloque 4.3
//se lanzan hilos en una clase y se ejecutan en una clase diferente. 

//En una carrera ciclista con 100 participantes, hay 3 etapas. Antes de comenzar una etapa, hay q esperar a que lleguen todos los participantes.
//Una vez que lleguen todos los participantes, se dará una salida a la siguiente etapa.
//El ganador de la prueba, será el que mejor tiempo haga en total y tambn se requiere saber el ganador de cada una de las etapas individuales.  

public class B3_LanzaHilosEnUnaClase_y_EjecutalosEnOtraClase {
	
	//se generan 16 hilos y en estos 16 hilos se procesan 100 tareas
	public static void main(String[] args) {
		
		int participantes = 100;
		
		B3_Carrera.setCarrera(participantes);                                            			//Se usa este método para q no de error en la clase B3_carrera. inicialmente se daban los valores de tiempos y barreras en la declaracion de las variables, pero ahí les llegaba null el valor de participantes. 
		
		//Runtime runtime = Runtime.getRuntime();
		
		//int nucleos = runtime.availableProcessors();
		
		ExecutorService pool = Executors.newCachedThreadPool();                                    //newCachedThreadPool : usa este pq va a intentar reciclar siempre q pueda, pero si no hay hilos libresm va a lanzar hilos nuevos. En este caso va a lanzar 100 hilos.
		
		for(int i=0; i<participantes; i++) {
			Runnable runnable = new B3_Carrera(i/*, participantes*/);
			pool.execute(runnable);
		}
		
		pool.shutdown();
		
		while(!pool.isTerminated());
		
		double [][] tiempos = B3_Carrera.getTiempos();
		
		int idGanador0 = 0;
		double ganadorTiempo0 = tiempos[0][3];
		
		int idGanador1 = 0; 
		double ganadorTiempo1 = tiempos[0][0];
		
		int idGanador2 = 0; 
		double ganadorTiempo2 = tiempos[0][1];
		
		int idGanador3 = 0; 
		double ganadorTiempo3 = tiempos[0][2];

		
		for(int i=0; i<tiempos.length; i++) {
			
			if(tiempos[i][3] < ganadorTiempo0) {
				ganadorTiempo0 = tiempos[i][3];
				idGanador0 = i;
			}
			
			if(tiempos[i][0] < ganadorTiempo1) {
				ganadorTiempo1 = tiempos[i][0];
				idGanador1 = i;
			}
			
			if(tiempos[i][1] < ganadorTiempo2) {
				ganadorTiempo2 = tiempos[i][1];
				idGanador2 = i;
			}
			
			if(tiempos[i][2] < ganadorTiempo3) {
				ganadorTiempo3 = tiempos[i][2];
				idGanador3 = i;
			}
			
		}
		
		
		
		System.out.println("El ganador es el hilo: " + 	idGanador0);
		System.out.println("El ganador de la etapa 1 es el hilo: " + idGanador1);
		System.out.println("El ganador de la etapa 2 es el hilo: " + idGanador2);
		System.out.println("El ganador de la etapa 3 es el hilo: " + idGanador3);
	}
	
	
	
}
