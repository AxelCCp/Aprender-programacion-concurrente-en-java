package com.hilos3.monitor;

//Bloque 4.1
//un monitor provee exclusion mutua a cada uno de los métoodos. provee exclusion mutua a todos los métodos con la palabra synchronized. o sea un hilo a la vez.
//una de las caracteristicas de los monitores es que cuando un hilo entra en un método, no se puede usar ningun otro método por ningun otro hilo.
public class B1_Monitor {

	public synchronized int inc() {                               //no es necesario un cerrojo, ya que "monitor" en B1_Principal actua como cerrojo.
		
		for(int i=0; i<20000; i++) {
			cont++;
		}
		
		return cont;
		
	}
	
	
	public synchronized void ordenar(int id) {
		
		while(id != order) {												//condicion de guarda, valida si el orden es diferente al esperado.
			try {
				this.wait();                                                //se usa la clase como cerrojo y si el hilo no cumple la considion, se manda a dormir.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Soy el hilo: " + id);
		order++;
		this.notifyAll();                                                   //se despierta a todos los hilos de la cola del wait()
	}
	
	
	
	private int cont = 0;
	private int order = 0;
}
