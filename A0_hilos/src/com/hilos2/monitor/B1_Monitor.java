package com.hilos2.monitor;

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
	
	
	
	private int cont = 0;
}
