package by.training.task15.main;

import by.training.task15.controller.ThreadController;
import by.training.task15.domain.Matrix;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		int[][] x1 = { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 11, 12, 13, 14, 15 }, { 16, 17, 18, 19, 20 },
				{ 21, 22, 23, 24, 25 } };
		int[][] x2 = { { 26, 27, 28, 29, 30 }, { 31, 32, 33, 34, 35 }, { 36, 37, 38, 39, 40 }, { 41, 42, 43, 44, 45 },
				{ 46, 47, 48, 49, 50 } };
		ThreadController controller = new ThreadController();
		controller.setMatrixes(x1, x2);
		controller.setNumberOfThreads(2);
		Matrix result = controller.count();
		if (result != null) {
			int[][] c = result.getMas();
			for (int[] i : c) {
				for (int r : i) {
					System.out.print(r + " ");
				}
				System.out.println("");
			}
		}
	}

}
