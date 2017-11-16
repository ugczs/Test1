import java.math.BigInteger;

/**
 * Zufallstupel von Chameleon Hash
 * @author Yuguan Zhao
 */
public class ChameleonRandomness {
	/*Die erste Zufallszahl von Chameleon Hash*/
	private BigInteger alpha;
	/*Die zweite Zufallszahl von Chameleon Hash*/
	private BigInteger beta;
	
	public ChameleonRandomness(BigInteger alpha, BigInteger beta) {
		this.alpha = alpha;
		this.beta = beta;
	}

	public BigInteger getAlpha() {
		return alpha;
	}

	public void setAlpha(BigInteger alpha) {
		this.alpha = alpha;
	}

	public BigInteger getBeta() {
		return beta;
	}

	public void setBeta(BigInteger beta) {
		this.beta = beta;
	}
	
	
	
}
