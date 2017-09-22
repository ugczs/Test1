
public class ToeplitzMatrix {
	private int rowLength;
	private int columnLength;
	private int[] row;
	private int[] column;
	private int[][] toeplitzMatrix;
	
	public ToeplitzMatrix(int rowLength, int columnLength) {
		this.rowLength = rowLength;
		this.columnLength = columnLength;
		calcToeplitzMatrix(rowLength, columnLength);
	}
	
	public ToeplitzMatrix(int[] row, int[] column) {
		this.row = row;
		this.column = column;
		fillToeplitzMatrix(row, column);
	}
	
	/**
	 * Diese Methode berechnet eine Toeplitzmatrix mit 0 oder 1 als Eintraege
	 * @return eine Toeplitzmatrix 
	 */
	public int[][] calcToeplitzMatrix(int rowLength, int columnLength) {
		int[][] t = new int[rowLength][columnLength];
		for(int a = 0; a < columnLength; a++){
			int r = flipCoin();
			for(int i = 0, j = a; i < rowLength && j < columnLength; i++, j++){
				t[i][j] = r;
			}
		}
		for(int b = 1; b < rowLength; b++){
			int r = flipCoin();
			 for(int i = b, j = 0; i < rowLength && j < columnLength; i++, j++){
				 t[i][j] = r;	
			 }
		}
		this.toeplitzMatrix = t;
		return t;
	}
	
	/**
	 * Diese Methode fuellt die Matrix auf mit angegebenen Zeilen und Spalten
	 * @return eine Toeplitzmatrix 
	 */
	public int[][] fillToeplitzMatrix(int[] row, int[] column) {
		int[][] t = new int[row.length][column.length];
		for(int a = 0; a < column.length; a++){
			int r = row[a];
			for(int i = 0, j = a; i < row.length && j < column.length; i++, j++){
				t[i][j] = r;
			}
		}
		for(int b = 1; b < row.length; b++){
			int r = column[b];
			 for(int i = b, j = 0; i < row.length && j < column.length; i++, j++){
				 t[i][j] = r;	
			 }
		}
		this.toeplitzMatrix = t;
		return t;
	}
	
	public int[] getRow() {
		for(int i = 0; i < this.toeplitzMatrix[0].length; i++) {
		this.row[i] = toeplitzMatrix[0][i];
		}
		return row;
	}

	public int[] getColumn() {
		for(int i = 0; i < this.toeplitzMatrix.length; i++) {
			this.column[i] = toeplitzMatrix[i][0];
		}
		return column;
	}

	/**
	 * Diese Methode liefert entweder 0 oder 1 per Zufall
	 * @return 0 oder 1
	 */
	public int flipCoin() {
		int c;
		if(Math.random() < 0.5) {
		    c = 0;
		}
		else {
			c = 1;
		}
		return c;
	}

	public int getRowLength() {
		return rowLength;
	}

	public int getColumnLength() {
		return columnLength;
	}

	public int[][] getToeplitzMatrix() {
		return toeplitzMatrix;
	}
}

