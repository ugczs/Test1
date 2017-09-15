/**
 * berechnet bitweise Ergebnisse mit Eingaben von 0 oder 1
 * @author Yuguan
 */
public class BitwiseCalculation {
	
	public int add(int a, int b) {
		int solution = 999;
		if(a == 0 && b == 0) {
			solution = 0;
		}
		else if(a==1 && b==0) {
			solution = 1;
		}
		else if(a==0 && b==1) {
			solution = 1;
		}
		else if(a==1 && b==1){
			solution = 0;
		}
		return solution;
	}
	
	public int substract(int a, int b) {
		int solution = 999;
		if(a==0 && b==0) {
			solution = 0;
		}
		else if(a==1 && b==0) {
			solution = 1;
		}
		else if(a==0 && b==1) {
			solution = 1;
		}
		else if(a==1 && b==1){
			solution = 0;
		}
		return solution;
	}
	
	public int multiply(int a, int b) {
		int solution = 999;
		if(a==0 && b==0) {
			solution = 0;
		}
		else if(a==1 && b==0) {
			solution = 0;
		}
		else if(a==0 && b==1) {
			solution = 0;
		}
		else if(a==1 && b==1){
			solution = 1;
		}
		return solution;
	}
	
	/**
	 * Matrix Multiplikation
	 * @param a die erste Matrix 
	 * @param b die zweite Matrix
	 * @return Ergebnis
	 */
    public int[][] multiplyMatrix(int[][] a, int[][] b) {
		int[][] solution = null;
		if (a[0].length == b.length) {
			int aRow = a.length;
			int aCol = a[0].length;
			int bCol = b[0].length;
			solution = new int[aRow][bCol];
			for (int i = 0; i < aRow; i++) {
				for (int j = 0; j < bCol; j++) {
					solution[i][j] = 0;
					for (int k = 0; k < aCol; k++) {
					  solution[i][j] =  add(solution[i][j], multiply(a[i][k], b[k][j])) ;
					}
				}
			}
		} 
		else {
			int col = a[0].length;
			int row = a.length;
			solution = new int[row][col];
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a[0].length; j++) {
					solution[i][j] = 0;
				}
			}
		}
		return solution;
	}
	
    /**
	 * Vektoraddition
	 * @param a der erste Vektor
	 * @param b der zweite Vektor
	 * @return Ergebnis
	 */
    public int[][] addVektor(int[][] a, int[][] b) {
    	int row = a.length;
    	int col = a[0].length;
    	int[][] c = new int[row][col];
    	if(b.length != row || b[0].length != col) {
    		System.err.println("Vektor without same size by addVektor");
    	}
    	else{
    		for (int i = 0; i < row; i++) {
    			for (int j = 0; j < col; j++) {
                c[i][j] = add(a[i][j],b[i][j]);
    			}
            }
        }
    
        return c;
    }
	
    /**
   	 * Vektorsubstraktion
   	 * @param a der erste Vektor
   	 * @param b der zweite Vektor
   	 * @return Ergebnis
   	 */
       public int[][] substractVektor(int[][] a, int[][] b) {
       	int row = a.length;
       	int col = a[0].length;
       	int[][] c = new int[row][col];
       	if(b.length != row || b[0].length != col) {
    		System.err.println("Vektor without same size by substractVektor");
    	}
       	else {
           for (int i = 0; i < row; i++) {
               for (int j = 0; j < col; j++) {
                   c[i][j] = substract(a[i][j],b[i][j]);
               }
           }
       	}
       	return c;
      }

}
