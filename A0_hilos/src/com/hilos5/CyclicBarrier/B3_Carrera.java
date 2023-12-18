package com.hilos5.CyclicBarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//bloque 4.3
//CyclicBarrier : es una barrera que contiene a los hilos. minuto 13.00 (Explicacion).

public class B3_Carrera implements Runnable{

	
	public B3_Carrera(int id/*, int participantes*/) {
		this.id = id;
		//this.participantes = participantes;
	}

	
	
	@Override
	public void run() {
		etapa(0);
		etapa(1);
		etapa(2);
		
		tiempos[id][3] = tiempos[id][0] + tiempos[id][1] + tiempos[id][2];		
	}

	
	
	public static double[][] getTiempos() {
		return tiempos;
	}
	
	
	public static void setCarrera(int participantes) {
		B3_Carrera.tiempos = new double[participantes][4];
		B3_Carrera.barrera = new CyclicBarrier(participantes);
	}
	
	
	private void etapa(int numEtapa) {
		
		inicio = System.nanoTime();
		
		
		//tiempo que demora la tarea...
		try {
			Thread.sleep(random.nextInt(100) + 100);   										//va a tardar unos 100 milisegundos + 100.  random.nextInt(100): Esto da un n° aleatorio entre 0 y 99.	
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//...tiempo que demora la tarea
		
		
		//se registra el 1er participante en el 1er espacio de la matriz.  
		total = System.nanoTime() - inicio;
		tiempos[id][numEtapa] = total;
		
		try {
			                                                // --> nunca se debe tener un hilo esperando y resetear la barrera en ese momento. es por esto que puso Thread.sleep(random.nextInt(100) + 100);  min 29.
			barrera.await();                              								   //aqui el hilo (participante), va a preguntar si puede pasar a la siguiente etapa, y si se ha completado la cantidad de hilos "100" que se le pasaron por parametro del constructor, ahí va a dejar pasar a todos los hilos. 
			barrera.reset();                                                               // como la barrera está dentro de un método, para generar la barrera al fin de cada etapa, hay que resetear la barrera, para q impida el paso de los hilos a la siguiente etapa. con esto, cuando la barrera se haya terminado, se resetea. min 28.
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
	
	
	private int id;
	//private static int participantes;
	private double inicio, total;
	private Random random = new Random(System.nanoTime());
	private static double [][] tiempos;														//se declara ua matriz estatica donde se va a registrar el tiempo que le toma a cada hilo ejecutar cada etapa. es estática pq va a ser compartida por cada hilo.
	private static CyclicBarrier barrera;													//el CyclicBarrier va a dejar pasar a los hilos (participantes) a la siguiente etapa cuando hallan llegado los 100 a la barrera.

}
