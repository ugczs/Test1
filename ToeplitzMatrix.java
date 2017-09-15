
public class ToeplitzMatrix {
	private int rowLength;
	private int columnLength;
	private int[][] toeplitzMatrix;
	
	public ToeplitzMatrix(int rowLength, int columnLength) {
		this.rowLength = rowLength;
		this.columnLength = columnLength;
		calcToeplitzMatrix(rowLength, columnLength);
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

