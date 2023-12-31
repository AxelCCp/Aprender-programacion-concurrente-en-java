package com.hilos1;


//Bloque 3.1 - generando hilos con runnable:

public class A7_Runnable_Syncronized implements Runnable{

	/* Código para generar 1 hilo
	 *  
	public static void main(String[] args) {
		
		Runnable runnable = new A7_Runnable_Syncronized();
		Thread hilo1 = new Thread(runnable);
		hilo1.start();
		
		try {
			hilo1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	
	
	/* Código para generar hilos con un vector 
	 */
	public static void main(String[] args) {
		
		Runtime runtime = Runtime.getRuntime();
		
		int nucleos = runtime.availableProcessors();
		
		Thread[]hilos = new Thread[nucleos];
		
		for(int i=0; i<hilos.length; i++) {
			
			//hilos[i] = new Thread(new A7_Runnable_Syncronized());				//manera 1
			
			Runnable runnable = new A7_Runnable_Syncronized();                  //manera 2
			hilos[i] = new Thread(runnable);                                    //manera 2
			
			hilos[i].start();	
		}
		
		for(int i=0; i<hilos.length; i++) {
			
			try {
				hilos[i].join();
			} catch (InterruptedException e) {		
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
		}
		
		System.out.println(cont);
	}
	
	
	@Override
	public void run() {
		
		//Con este código, los hilos se despiertan 20000 veces para ingresar al sincronized. Lo que no está muy bien.
		/*for(int i=0; i<20000;i++) {
			synchronized (object) {												//se va preguntando si hay un hilo ejecutando, si no hay nadie dentro del syncronized, peude entrar un hilo. Si hay un hilo dentro, los demas hilos se quedan en la cola, hasta q se libera el syncronized. Cuando se libera el sincronized, entra el hilo más rápido al sincronized y se vuelve a cerrar.
				cont++;
			}
		}*/
		
		
		//con este código entre el 1er hilo e itera 20000 veces, luego el 2do hace lo mismo y así sucesivamente. Esté código está mejor.
		synchronized (object) {												
			for(int i=0; i<20000;i++) {
				cont++;
			}
		}
		
	}
	
	
	private static int cont = 0; 
	private static Object object = new Object();								//este es el cerrojo del sincronized, siempre debe ser estatico, para q los hilos lo vean y no se produzca indeterminismo.
	
	

}
