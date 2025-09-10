package controller;

import java.util.concurrent.Semaphore;

public class Triatlo extends Thread {
	
	private int id;
	private Semaphore semaforo;
	private static int pontChegadaCorrida = 260;
	private int pontCorrida;
	private static int pontChegadaCiclismo = 260;
	private int pontCiclismo;
	private int pontTotal;
    public static int[] resultados = new int[25];
	
	public Triatlo(int id, Semaphore semaforo) {
		this.id = id;
		this.semaforo = semaforo;
	}
	
	public void run() {
		corrida();
		
		try {
			semaforo.acquire();
			tiro();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
		ciclismo();
		
		total();
	}

	private void corrida() {
		int distCorrida = 0;
		int distTotal = 3000;
		while (distCorrida <= distTotal) {
			distCorrida += (int)((Math.random() *6 ) + 20);
			try {
				sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
		try {
			semaforoCorrida.acquire();
			pontChegadaCorrida -= 10;
			pontCorrida = pontChegadaCorrida;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforoCorrida.release();
		}	
	}

	private int pontTiro = 0;
	private void tiro() {
		for (int i = 1; i < 4; i++) {
			pontTiro += (int)(Math.random() * 11);
		}
	}

	private void ciclismo() {
		int distCiclismo = 0;
		int distTotalC = 5000;
		while (distCiclismo <= distTotalC) {
			distCiclismo += (int)((Math.random() *11 ) + 30);
			try {
				sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			semaforoCiclismo.acquire();
			pontChegadaCiclismo -= 10;
			pontCiclismo = pontChegadaCiclismo;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforoCiclismo.release();
		}
	}
	
	private void total() {
		pontTotal = pontCorrida + pontTiro + pontCiclismo;
		resultados[id-1] = pontTotal;
		//System.out.println("#"+id+" pontua "+pontTotal);
	}
	
}

