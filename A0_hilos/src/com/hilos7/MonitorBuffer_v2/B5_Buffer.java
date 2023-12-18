package com.hilos7.MonitorBuffer_v2;

import java.util.Random;
import java.util.Vector;

//BLOQUE 4.4
//CLASE MONITOR
public class B5_Buffer {

	private Random random = new Random(System.nanoTime());                 //genera numeros aleatorios
	
	
	private int tam = 10;													//atributo para generar buffer - tamaño máximo de 10.
	private int pos = -1;													//atributo para generar buffer - atributo q marca la posicion en la q nos encontramos. (-1) : es posición marca q el vector está vacío.
	private Vector<Integer>cola = new Vector<>();							//atributo para generar buffer - un vector es una pila.
	
	
	//método del consumidor:
	public synchronized int leer() {
		
		int elemento = -1;
		
		while(pos < 0) {			                      // V2 - se cambia el if por while para q sea una condicion de guarda.(min 20)   -   //si el vector esta vacío, no hay nada que leer. por lo tanto manda a dormir a los hilos consumidores del verctor. 
			
			try {
				System.out.println("El vector está vacío y el consumidor se va a dormir.");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}		
		
		//es codigo se va a ejecutar solo cuando pos >= 0.
		elemento = cola.get(pos);										
		cola.remove(pos);
		pos--;
		
		
		return elemento;
	}
	
	
	//método del productor:
	public synchronized void escribir() {
		
		pos++;
		
		if(pos >= tam){														//aquí el vector se encuentra completo.
			System.out.println("El vector está lleno");
			pos--;                                                          //esto es para mantener la posision en el ultimo indice del vector.
		
		} else {															//si no estamos en el último indice del vector, se le agrega un elemento.
			cola.add(generar());                                            //agrega int random.
			notifyAll();													//si se a generado un nuevo elemento y los consumidores están dormidos, se despiertan.
		}
	}
	
	
	public synchronized int generar() {										//devuelve los numeros aleatorios q se van a meter dentro del buffer.
		return random.nextInt(10);
	}
	
}
