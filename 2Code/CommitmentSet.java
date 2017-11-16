/**
 * Set um Toeplitzmatrix wiederherzustellen
 * @author Yuguan Zhao
 */
public class CommitmentSet {
	/*Zeile von Toeplitz-Matrix*/
	private int[] row;
	/*Spalte von Toeplitz-Matrix*/
	private int[] column;
	/*Zufallsvektor*/
	private int[][] randomVektor;
	
	public CommitmentSet(int[] row, int[] column, int[][] randomVektor) {
		this.row = row;
		this.column = column;
		this.randomVektor = randomVektor;
	}

	public int[] getRow() {
		return row;
	}

	public void setRow(int[] row) {
		this.row = row;
	}

	public int[] getColumn() {
		return column;
	}

	public void setColumn(int[] column) {
		this.column = column;
	}

	public int[][] getRandomVektor() {
		return randomVektor;
	}

	public void setRandomVektor(int[][] randomVektor) {
		this.randomVektor = randomVektor;
	}
	
}
