import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;
import static java.nio.charset.StandardCharsets.UTF_8;


public class RsaSig {
	public KeyPair generateKeyPair() throws Exception {
	    java.security.KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
	    generator.initialize(2048, new SecureRandom());
	    KeyPair pair = generator.generateKeyPair();
	    return pair;
	}
	
	public String encrypt(String plainText, PublicKey publicKey) throws Exception {
	    Cipher encryptCipher = Cipher.getInstance("RSA");
	    encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
	    byte[] cipherText = encryptCipher.doFinal(plainText.getBytes(UTF_8));
	    return Base64.getEncoder().encodeToString(cipherText);
	}
	
	public String decrypt(String cipherText, PrivateKey privateKey) throws Exception {
	    byte[] bytes = Base64.getDecoder().decode(cipherText);
	    Cipher decriptCipher = Cipher.getInstance("RSA");
	    decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);
	    return new String(decriptCipher.doFinal(bytes), UTF_8);
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