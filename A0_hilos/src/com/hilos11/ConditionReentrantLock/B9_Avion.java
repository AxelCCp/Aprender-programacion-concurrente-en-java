package com.hilos11.ConditionReentrantLock;

//intencion = 0  => despegar el avion
//intencion = 1 => esta volando
//intencion != 0 o 1 => esta aparcado.
//ultima intencion posible  es 4.

public class B9_Avion {
	
	private int id;
	
	private static int intencion; 				
	
	public B9_Avion(int id, int intencion) {
		this.id = id;
		B9_Avion.intencion = intencion;
	}
	
	
	public int getIntencion() {
		return intencion;
	}
	
	public int getId() {
		return id;
	}
	
	public int getNextIntencion() {
		intencion = ++intencion % 5;
		return intencion;
	}
	

	
}
