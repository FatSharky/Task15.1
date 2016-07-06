package by.training.task15.controller;

import java.util.ArrayList;
import java.util.List;

import by.training.task15.domain.Matrix;

public class ThreadController {

	Matrix x1 = new Matrix();
	Matrix x2 = new Matrix();
	Matrix result = new Matrix();
	int numberOfThreads = -1;
	private List<ThreadCalc> threads = new ArrayList<ThreadCalc>();

	public void setMatrixes(int[][] x1, int[][] x2) {
		this.x1.setMas(x1);
		this.x2.setMas(x2);
	}

	public void setNumberOfThreads(int numberOfThreads) {
		this.numberOfThreads = numberOfThreads;
	}

	public Matrix count() throws InterruptedException {
		if (numberOfThreads < 0 || numberOfThreads > x1.getLength()) {
			numberOfThreads = x1.getLength();
		}
		int len1 = x1.getLength();
		int len2 = x2.getLength();
		int wid1 = x1.getWidth();
		int wid2 = x2.getWidth();
		result.setMas(new int[len1][wid2]);
		if (wid1 == len2) {
			ThreadCalc thread = null;
			for (int i = 0; i < numberOfThreads; i++) {
				thread = new ThreadCalc(x1, x2, i);
				threads.add(thread);
				thread.start();
			}
			for (Thread t : threads) {
				t.join();
			}
			return result;
		} else {

			return null;
		}
	}

	public class ThreadCalc extends Thread {
		Matrix x1;
		Matrix x2;
		int number;
		int generalNumber;

		public ThreadCalc(Matrix x1, Matrix x2, int number) {
			this.x1 = x1;
			this.x2 = x2;
			this.number = number;
			generalNumber = numberOfThreads;
		}

		@Override
		public void run() {
			int len1 = x1.getLength();
			int wid2 = x2.getWidth();
			for (int i = 0; i < len1; i++) {
				for (int j = 0; j < wid2; j++) {
					if (i % generalNumber == number) {
						count(x1.getRow(i), x2.getColumn(j), i, j);
					}
				}
			}
		}

		public void count(int[] row, int[] column, int rowNumber, int columnNumber) {
			int n = row.length;
			int res = 0;
			for (int k = 0; k < n; k++) {
				res += row[k] * column[k];
			}
			result.setElement(res, rowNumber, columnNumber);
		}

	}

}
