import java.security.*;
import java.util.Base64;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Signaturklasse mit RSA und SHA256
 */
public class RsaSig {
	public KeyPair generateKeyPair() throws Exception {
	    java.security.KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
	    generator.initialize(2048, new SecureRandom());
	    KeyPair pair = generator.generateKeyPair();
	    return pair;
	}
	
	public String sign(String plainText, PrivateKey privateKey) throws Exception {
	    Signature privateSignature = Signature.getInstance("SHA256withRSA");
	    privateSignature.initSign(privateKey);
	    privateSignature.update(plainText.getBytes(UTF_8));
	    byte[] signature = privateSignature.sign();
	    return Base64.getEncoder().encodeToString(signature);
	}
	
	public  boolean verify(String plainText, String signature, PublicKey publicKey) throws Exception {
	    Signature publicSignature = Signature.getInstance("SHA256withRSA");
	    publicSignature.initVerify(publicKey);
	    publicSignature.update(plainText.getBytes(UTF_8));
	    byte[] signatureBytes = Base64.getDecoder().decode(signature);
	    return publicSignature.verify(signatureBytes);
	}
  
}