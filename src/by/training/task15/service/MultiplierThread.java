package by.training.task15.service;

public class MultiplierThread extends Thread {
	private final int[][] firstMatrix;
	private final int[][] secondMatrix;
	private final int[][] resultMatrix;
	private final int firstIndex;
	private final int lastIndex;
	private final int sumLength;

	public MultiplierThread(final int[][] firstMatrix, final int[][] secondMatrix, final int[][] resultMatrix,
			final int firstIndex, final int lastIndex) {
		this.firstMatrix = firstMatrix;
		this.secondMatrix = secondMatrix;
		this.resultMatrix = resultMatrix;
		this.firstIndex = firstIndex;
		this.lastIndex = lastIndex;

		sumLength = secondMatrix.length;
	}

	@Override
	public void run() {
		double startTime = System.currentTimeMillis();
		System.out.println(
				"Thread " + getName() + " started. Calculating cells from " + firstIndex + " to " + lastIndex + "...");

		final int colCount = secondMatrix[0].length;
		for (int index = firstIndex; index < lastIndex; ++index)
			calcValue(index / colCount, index % colCount);
		double timeSpent = System.currentTimeMillis() - startTime;
		System.out.println("Thread " + getName() + " finished. " + "Time Spent: " + timeSpent + " milisec");

	}

	private void calcValue(final int row, final int col) {
		int sum = 0;
		for (int i = 0; i < sumLength; ++i)
			sum += firstMatrix[row][i] * secondMatrix[i][col];
		resultMatrix[row][col] = sum;
	}
}
