package com.hilos10.Condition;


import java.util.Vector;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//bloque 5.3
public class B8_Buffer implements Runnable{

	private int orden, valor;
	private static Vector<Integer>elementos = new Vector<>();
	private static ReentrantLock lock = new ReentrantLock();
	private static Condition restar = lock.newCondition();
	private static Condition sumar = lock.newCondition();
	
	public B8_Buffer(int orden, int valor) {
		this.orden = orden;
		this.valor = valor;
	}
	
	
	
	@Override
	public void run() {
		
		while(true) {
			
			lock.lock();
			
			if(orden == 0) {
				
				restarElemento();
			
			} else {
			
				sumarElemento();
			
			}
			
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			lock.unlock();
		}
		
	}
	
	
	private void restarElemento() {
		
		while(elementos.isEmpty()) {
			await(restar);
		}
		
		System.out.println("Se ha extraido el elemento: " + elementos.get(0));
		elementos.remove(0);
		sumar.signal();
		await(restar);
	}
	
	private void sumarElemento() {
			while(elementos.size() > 20) {
				await(sumar);
			}
			elementos.add(valor);
			System.out.println("Se ha añadido el elemento: " + elementos.get(elementos.size() - 1));
			restar.signal();
			await(sumar);
	}
	
	private void await(Condition condition) {
		try {
			condition.await();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
