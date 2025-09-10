package view;

import java.util.concurrent.Semaphore;
import controller.Triatlo;

public class Atleta {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(5);
		Triatlo[] atetlas = new Triatlo[25];

		for (int id = 1; id <= 25; id++) {
			atetlas[id - 1] = new Triatlo(id, semaforo);
			atetlas[id - 1].start();
		}

		for (int i = 0; i < 25; i++) {
			try {
				atetlas[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for (int i = 0; i < 25; i++) {
			for (int j = i + 1; j < 25; j++) {
				if (Triatlo.resultados[i] < Triatlo.resultados[j]) {
					int temp = Triatlo.resultados[i];
					Triatlo.resultados[i] = Triatlo.resultados[j];
					Triatlo.resultados[j] = temp;
				}
			}
		}

		System.out.println("Resultados finais:");
		for (int i = 0; i < 25; i++) {
			System.out.println((i + 1) + "º lugar: " + Triatlo.resultados[i]);
		}

	}

}
