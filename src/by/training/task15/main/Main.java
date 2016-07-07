package by.training.task15.main;

import by.training.task15.constant.Constant;
import by.training.task15.service.MatrixGenerator;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		final int[][] firstMatrix = new int[Constant.FIRST_MATRIX_ROWS][Constant.FIRST_MATRIX_COLS];
		final int[][] secondMatrix = new int[Constant.SECOND_MATRIX_ROWS][Constant.SECOND_MATRIX_COLS];

		MatrixGenerator.randomMatrix(firstMatrix);
		MatrixGenerator.randomMatrix(secondMatrix);

		final int[][] resultMatrixMT = MatrixGenerator.multiplyMatrixMT(firstMatrix, secondMatrix,
				Runtime.getRuntime().availableProcessors());

		final int[][] resultMatrix = MatrixGenerator.multiplyMatrix(firstMatrix, secondMatrix);

		for (int row = 0; row < Constant.FIRST_MATRIX_ROWS; ++row) {
			for (int col = 0; col < Constant.SECOND_MATRIX_COLS; ++col) {
				if (resultMatrixMT[row][col] != resultMatrix[row][col]) {
					System.out.println("Error in multithreaded calculation!");
					return;
				}
			}
		}

		MatrixGenerator.printAllMatrix(Constant.PATH, firstMatrix, secondMatrix, resultMatrixMT);
	}
}
