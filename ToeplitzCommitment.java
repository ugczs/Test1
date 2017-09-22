import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class ToeplitzCommitment {
	private int bit = 128;
	private int rowLength;
	private int columnLength;
	private String message;
	private String bitStringMsg;
	private ToeplitzMatrix tm;
	private int[][] toeplitzMatrix;
	private int[][] randomVektor;
	private int[][]	b;
	private int[][]	x;
	private int[][]	y;
	private String	z;
	private BitwiseCalculation bc = new BitwiseCalculation();

	public ToeplitzCommitment(String message) {
		try{
			this.message = message;
			calcBitStringMsg(message);
			this.rowLength = calcMsgLength(bitStringMsg);
			this.columnLength = calcMsgLength(bitStringMsg);
			this.tm = new ToeplitzMatrix(rowLength, columnLength);
			this.toeplitzMatrix = tm.getToeplitzMatrix();
			generateRandomVektor(columnLength);
			calcB();
			calcX();
			hashWithMatrix(this.x);
			calcZ();
		}
		catch(Exception ex) {
			System.out.println(ex);
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
		MessageDigest m = MessageDigest.getInstance("MD5");
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
		int[][] m = bitStringToIntArray(bitStringMsg);
		int[][] temp = bc.multiplyMatrix(toeplitzMatrix, randomVektor);
		int[][] b =  bc.substractVektor(m, temp);
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
	 * Diese Methode berechnet einen Hash mit der Funktion
	 * f(x) = Ax + b = y
	 * @return y ist der Hashwert
	 */
	public void hashWithMatrix(int[][] x) {
		int[][] temp = bc.multiplyMatrix(toeplitzMatrix, x);
		int[][] y = bc.addVektor(temp, b);
		this.y = y;
	}
	
	/**
	 * Diese Methode berechnet einen MD5-Hash mit Eingabe y
	 * Ergebnis wird dann in z gespeichert.
	 */
	public void calcZ() {
		String s = intArrayToBitString(y);
		try {
			String s2 = calcHash(s);
			String s3 = padLeftZeros(s2, bit);
			this.z = s3;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public String commitment() {
		return "z is" + this.z + "ToeplitzRow is" + arrayToString(tm.getRow()) + 
				"ToeplitzColumn is" + arrayToString(tm.getColumn()) +
				"b is" + matrixToString(this.b);
	}

	public int[][] getToeplitzMatrix() {
		return toeplitzMatrix;
	}
}
