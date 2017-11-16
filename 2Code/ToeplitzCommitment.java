import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Diese Klasse erzeugt Toeplitz Commitment
 * @author Yuguan Zhao
 */
public class ToeplitzCommitment {
	/*Bitlaenge*/
	private int bit = 256;
	/*Zeilenlaenge*/
	private int rowLength;
	/*Spaltenlaenge*/
	private int columnLength;
	/*Zeileninhalt von Toeplitz-Matrix*/
	private int[] row;
	/*Spalteninhalt von Toeplitz-Matrix*/
	private int[] column;
	/*Nachricht in String*/
	private String message;
	/*Nachricht in Bit*/
	private String bitStringMsg;
	/*Instanz von Toeplitz-Matrix*/
	private ToeplitzMatrix tm;
	/*Toeplitz-Matrix*/
	private int[][] toeplitzMatrix;
	/*Zufallsvektor*/
	private int[][] randomVektor;
	/*b = x - toeplitzMatrix*randomVektor*/
	private int[][]	b;
	/*Int-Array von Nachricht in Bit*/
	private int[][]	x;
	/*Hash-Wert von randomVektor*/
	private String	z;
	/*Instanz von BitwiseCalculation*/
	private BitwiseCalculation bc;

	public ToeplitzCommitment(String message) {
		try{
			this.bc = new BitwiseCalculation();
			this.message = message;
			calcBitStringMsg(message);
			this.rowLength = calcMsgLength(bitStringMsg);
			this.columnLength = calcMsgLength(bitStringMsg);
			this.tm = new ToeplitzMatrix(rowLength, columnLength);
			this.toeplitzMatrix = tm.getToeplitzMatrix();
			generateRandomVektor(columnLength);
			calcX();
			calcB();
			calcZ();
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
	public ToeplitzCommitment(String message, int[] row, int[] column, int[][] randomVektor) {
		try {
			this.bc = new BitwiseCalculation();
			this.message = message;
			this.randomVektor = randomVektor;
			calcBitStringMsg(message);
			this.tm = new ToeplitzMatrix(row, column);
			this.toeplitzMatrix = tm.getToeplitzMatrix();
			calcX();
			calcB();
			calcZ();
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Can not generate toeplitz commitment with given matrix");
		}
	}
	
	/**
     * Füllt Links von String s mit 0
     * Die max. Laenge betraegt i
     */
	public String padLeftZeros(String s, int i) {
		String str = String.format("%1$" + i + "s", s).replace(' ', '0');
		return str;
	}
	
	/**
     * generiert einen Vektor mit Zeilenanzahl wie Spaltenanzahl von Toeplitzmatrix
     * und 0 oder 1 als Eintrag
     */
	public void generateRandomVektor(int rowLength) {
		int vektorRowLength = this.columnLength;
		int[][] v = new int[vektorRowLength][1];
		for(int i = 0; i < vektorRowLength; i++){
			int c = flipCoin();
			v[i][0] = c;
		}
		this.randomVektor = v;
	}
	
	/**
     * berechnet Hashwert von einem String
     * @return gibt String-Hashwert zurueck
     */
	public String calcHash(String msg) throws NoSuchAlgorithmException {
		MessageDigest m = MessageDigest.getInstance("SHA-256");
		m.reset();
		m.update(msg.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1, digest);
		String s = new String(bigInt.toString());
		return s;
	}
    
    /**
     * berechnet Bitstring von einem String
     * @return gibt Bitstring zurueck
     */
    public String calcBitStringMsg(String msg) throws NoSuchAlgorithmException {
    	String s = calcHash(msg);
    	BigInteger b = toBigInteger(s);
    	String s2 = new String(b.toString(2));
    	this.bitStringMsg = padLeftZeros(s2, bit);
    	return bitStringMsg;
    }
    
    /**
	 * Matrix Multiplikation
	 * @param a die erste Matrix 
	 * @param b die zweite Matrix
	 * @return Ergebnis
	 */
    public int[][] multiply(int[][] a, int[][] b) {
		return bc.multiplyMatrix(a, b);
	}
    
    /**
	 * Diese Methode verwandelt einen Bitstring in einen 2D Int Array um
	 * @param s ist ein Bitstring
	 */
    public int[][] bitStringToIntArray(String s){
    	String[] stringArray = s.split("");
		int[][] intArray = new int[stringArray.length][1];
		for (int i = 0; i < stringArray.length; i++) {
			intArray[i][0] = Integer.parseInt(stringArray[i]);
		}
		return intArray;
    }
    
    /**
	 * Diese Methode verwandelt einen 2D Array in einen Bitstring um
	 * @param a ist 2D Int Array
	 */
    public String intArrayToBitString(int[][] a){
    	String s = "";
    	for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				s = s + String.valueOf(a[i][j]);
		    }
		}
		return s;
 }
   
    
    /**
	 * Diese Methode verwandelt einen String zu BigInteger
	 * @param foo ist ein String
	 */
	public BigInteger toBigInteger(String foo) {
	    return new BigInteger(foo);
	}
	
	/**
	 * Diese Methode verwandelt einen BigInteger zu String
	 * @param foo ist ein BigInteger
	 */
	public String fromBigInteger(BigInteger foo) {
	    return new String(foo.toString());
	}
    
    
    public int calcMsgLength(String msg) {
    	int s = msg.length();
    	return s;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getRowLength() {
		return rowLength;
	}

	public void setRowLength(int rowLength) {
		this.rowLength = rowLength;
	}

	public int getColumnLength() {
		return columnLength;
	}

	public void setColumnLength(int columnLength) {
		this.columnLength = columnLength;
	}

	public String getBitStringMsg() {
		return bitStringMsg;
	}

	public void setBitStringMsg(String bitStringMsg) {
		this.bitStringMsg = bitStringMsg;
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

	public int[][] getRandomVektor() {
		return randomVektor;
	}
	
	public void calcB() {
		int[][] temp = bc.multiplyMatrix(toeplitzMatrix, randomVektor);
		int[][] b =  bc.subtractVektor(this.x, temp);
		this.b = b;
	}
	
	public void calcX() throws NoSuchAlgorithmException {
			int[][] x = bitStringToIntArray(bitStringMsg);
			this.x = x;
	}
	
	public int getBit() {
		return bit;
	}

	public void setBit(int bit) {
		this.bit = bit;
	}
	
	
	
	/**
	 * Diese Methode berechnet einen Hash z mit dem Random-Vektor
	 * z ist Teil von Commitment-Wert
	 */
	public void calcZ() {
		String s = intArrayToBitString(this.randomVektor);
		try {
			String s2 = calcHash(s);
			String s3 = padLeftZeros(s2, bit);
			this.z = s3;
		} catch (NoSuchAlgorithmException e) {
			System.err.println("Wrong z value!");
		}
	}
	
	public String getZ() {
		return z;
	}
	
	/**
	 * Diese Methode transformiert eine Matrix in String
	 */
	public String matrixToString(int[][] intArray){
		String result = "";
		for (int i = 0; i < intArray.length; i++) {
			for (int j = 0; j < intArray[i].length; j++) {
				result += "" + intArray[i][j] ;
		    }
		}
		return result;
	}
	
	/**
	 * Diese Methode transformiert einen Array in String
	 */
	public String arrayToString(int[] intArray){
		String result = "";
		for (int i = 0; i < intArray.length; i++) {
				result += "" + intArray[i] ;
		}
		return result;
	}
	
	/**
	 * Diese Methode gibt eine Matrix aus
	 */
	public void printMatrix(int[][] intArray) {
		for (int i = 0; i < intArray.length; i++) {
			for (int j = 0; j < intArray[i].length; j++) {
		        System.out.print(intArray[i][j] + " ");
		    }
		    System.out.println();
		}
	}

	public int[][] getToeplitzMatrix() {
		return toeplitzMatrix;
	}
	
	/**
	 * Diese Methode gibt die erste Zeile von Toeplitzmatrix aus
	 * @return Int-Array
	 */
	public int[] getRow() {
		this.row = new int[toeplitzMatrix[0].length];
		for(int i = 0; i < this.toeplitzMatrix[0].length; i++) {
			this.row[i] = toeplitzMatrix[0][i];
		}
		return row;
	}
	
	/**
	 * Diese Methode gibt die erste Spalte von Toeplitzmatrix aus
	 * @return Int-Array
	 */
	public int[] getColumn() {
		this.column = new int[this.toeplitzMatrix.length];
		for(int i = 0; i < this.toeplitzMatrix.length; i++) {
			this.column[i] = toeplitzMatrix[i][0];
		}
		return column;
	}
	
	/**
	 * Diese Methode gibt den Commitment-Wert zurueck,
	 * diese besteht aus z-Wert, b-Wert, Zeilen- und
	 * Spaltenwert aus Toeplitz-Matrix. #Zeichen dient
	 * zur Trennung
	 * @return Commitment-wert
	 */
	public String getCommitmentValue() {
		String b = matrixToString(this.b);
		String row = arrayToString(getRow());
		String column = arrayToString(getColumn());
		String s3 = this.z + "#" + b + "#" + row + "#" + column;
		return s3;
	}
	
}
