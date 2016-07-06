package by.training.task15.domain;

import java.util.Arrays;

public class Matrix {

	private int[][] mas;
	private int rows;
	private int cols;

	public Matrix() {
		this.rows = 0;
		this.cols = 0;
		mas = new int[cols][rows];
	}

	public Matrix(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		mas = new int[cols][rows];
	}

	public double getValue(int row, int col) {
		// TODO exception array index out of bounds
		return mas[row][col];
	}

	public int[] getColumn(int index) {
		int length = mas.length;
		int[] matr = new int[length];
		for (int i = 0; i < length; i++) {
			if (index < mas[i].length) {
				matr[i] = mas[i][index];
			} else {
				return null;
			}
		}
		return matr;
	}

	public int[] getRow(int index) {
		if (index < mas.length) {
			return mas[index];
		} else {
			return null;
		}
	}

	public int getLength() {
		return mas.length;
	}

	public int getWidth() {
		return mas[0].length;
	}

	public void setValue(int row, int col, int value) {
		// TODO exception array index out of bounds
		mas[row][col] = value;
	}

	public void setElement(int element, int row, int colomn) {
		mas[row][colomn] = element;
	}

	public void reset(int newRows, int newCols) {
		this.rows = newRows;
		this.cols = newCols;
		this.mas = new int[cols][rows];
	}

	public int[][] getMas() {
		return mas;
	}

	public void setMas(int[][] mas) {
		this.mas = mas;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cols;
		result = prime * result + Arrays.deepHashCode(mas);
		result = prime * result + rows;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matrix other = (Matrix) obj;
		if (cols != other.cols)
			return false;
		if (!Arrays.deepEquals(mas, other.mas))
			return false;
		if (rows != other.rows)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Matrix [mas=" + Arrays.toString(mas) + ", rows=" + rows + ", cols=" + cols + "]";
	}

}
