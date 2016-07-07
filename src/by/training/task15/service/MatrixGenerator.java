package by.training.task15.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import by.training.task15.constant.Constant;

public class MatrixGenerator {

	public static void randomMatrix(final int[][] matrix) {
		final Random random = new Random();

		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[row].length; ++col) {
				matrix[row][col] = random.nextInt(Constant.INT_FOR_RANDOM);
			}
		}
	}

	public static void printMatrix(final FileWriter fileWriter, final int[][] matrix) throws IOException {
		boolean hasNegative = false;
		int maxValue = 0;

		for (final int[] row : matrix) {
			for (final int element : row) {
				int temp = element;
				if (element < 0) {
					hasNegative = true;
					temp = -temp;
				}
				if (temp > maxValue) {
					maxValue = temp;
				}
			}
		}

		int len = Integer.toString(maxValue).length() + 1;

		if (hasNegative) {
			len++;
		}
		String formatString = Constant.SYMBOL_PERCENT + len + Constant.SYMBOL_D;

		for (final int[] row : matrix) {
			for (final int element : row)
				fileWriter.write(String.format(formatString, element));

			fileWriter.write(Constant.NEW_ROW_SYMBOL);
		}
	}

	public static void printAllMatrix(final String fileName, final int[][] firstMatrix, final int[][] secondMatrix,
			final int[][] resultMatrix) {
		try (FileWriter fileWriter = new FileWriter(fileName, false)) {
			fileWriter.write("First matrix:\n");
			printMatrix(fileWriter, firstMatrix);

			fileWriter.write("\nSecond matrix:\n");
			printMatrix(fileWriter, secondMatrix);

			fileWriter.write("\nResult matrix:\n");
			printMatrix(fileWriter, resultMatrix);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int[][] multiplyMatrix(final int[][] firstMatrix, final int[][] secondMatrix) {
		final int rowCount = firstMatrix.length;
		final int colCount = secondMatrix[0].length;
		final int sumLength = secondMatrix.length;
		final int[][] result = new int[rowCount][colCount];
		double startTime = System.currentTimeMillis();

		for (int row = 0; row < rowCount; ++row) {
			for (int col = 0; col < colCount; ++col) {

				int sum = 0;
				for (int i = 0; i < sumLength; ++i) {
					sum += firstMatrix[row][i] * secondMatrix[i][col];
				}
				result[row][col] = sum;
			}

		}
		double timeSpent = System.currentTimeMillis() - startTime;
		System.out.println("Without Thread " + "Time Spent: " + timeSpent + " milisec");
		return result;
	}

	public static int[][] multiplyMatrixMT(final int[][] firstMatrix, final int[][] secondMatrix, int threadCount) {
		assert threadCount > 0;

		final int rowCount = firstMatrix.length;
		final int colCount = secondMatrix[0].length;
		final int[][] result = new int[rowCount][colCount];

		final int cellsForThread = (rowCount * colCount) / threadCount;
		int firstIndex = 0;
		final MultiplierThread[] multiplierThreads = new MultiplierThread[threadCount];

		for (int threadIndex = threadCount - 1; threadIndex >= 0; --threadIndex) {
			int lastIndex = firstIndex + cellsForThread;
			if (threadIndex == 0) {

				lastIndex = rowCount * colCount;
			}
			multiplierThreads[threadIndex] = new MultiplierThread(firstMatrix, secondMatrix, result, firstIndex,
					lastIndex);
			multiplierThreads[threadIndex].start();
			firstIndex = lastIndex;
		}

		try {
			for (final MultiplierThread multiplierThread : multiplierThreads)
				multiplierThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return result;
	}
}
