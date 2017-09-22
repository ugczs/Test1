import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import static java.nio.charset.StandardCharsets.UTF_8;


public class TestRsa {
	private Cipher cipher;
	
	public TestRsa() throws NoSuchAlgorithmException, NoSuchPaddingException {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		this.cipher = Cipher.getInstance("RSA/NONE/OAEPWithSHA1AndMGF1Padding");
	}
	
	
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
	
	public static String decrypt(String cipherText, PrivateKey privateKey) throws Exception {
	    byte[] bytes = Base64.getDecoder().decode(cipherText);
	    Cipher decriptCipher = Cipher.getInstance("RSA");
	    decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);
	    return new String(decriptCipher.doFinal(bytes), UTF_8);
	}
	
	public String sign(String plainText, PrivateKey privateKey) throws Exception {
	    Signature privateSignature = Signature.getInstance("SHA1withRSAandMGF1");
	    privateSignature.initSign(privateKey);
	    privateSignature.update(plainText.getBytes(UTF_8));
	    byte[] signature = privateSignature.sign();
	    return Base64.getEncoder().encodeToString(signature);
	}
	
	
	public PublicKey getPublic(String pubKey) throws Exception {
	    byte[] publicBytes = Base64.getDecoder().decode(pubKey);
	    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
	    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	    return keyFactory.generatePublic(keySpec);
	}
	
	public boolean veriffy(String signature, String msg, PublicKey key) throws Exception {
	    byte[] sign = Base64.getDecoder().decode(signature);
	    byte[] dat = Base64.getDecoder().decode(msg);
	    Signature rsaVerify = Signature.getInstance("SHA1withRSAandMGF1");
	    rsaVerify.initVerify(key);
	    rsaVerify.update(dat);
	    return rsaVerify.verify(sign);
	}
	
	public String siggn(String plainText, PrivateKey privateKey) throws Exception {
		byte[] dat = Base64.getDecoder().decode(plainText);
	    Signature privateSignature = Signature.getInstance("SHA1withRSAandMGF1");
	    privateSignature.initSign(privateKey);
	    privateSignature.update(dat);
	    byte[] signature = privateSignature.sign();
	    return Base64.getEncoder().encodeToString(signature);
	}
	
	public boolean verify(String plainText, String signature, PublicKey publicKey) throws Exception {
	    Signature publicSignature = Signature.getInstance("SHA1withRSAandMGF1");
	    publicSignature.initVerify(publicKey);
	    publicSignature.update(plainText.getBytes(UTF_8));
	    byte[] signatureBytes = Base64.getDecoder().decode(signature);
	    return publicSignature.verify(signatureBytes);
	}
	
}