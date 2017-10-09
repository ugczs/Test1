
public class CommitmentSet {
	private int[] row;
	private int[] column;
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
