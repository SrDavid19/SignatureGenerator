package com.cardinal.generator.signature;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Base64;

@SpringBootApplication
public class SignatureApplication {

	public static void main(String[] args) {
		String demoApiKey = "13f1fd1b-ab2d-4c1f-8e2c-ca61878f2a44";
        long currentTimeMillis = System.currentTimeMillis();

        String signature = currentTimeMillis + demoApiKey;
        String hashedSignatureSha256 = sha256(signature);
		String hashedSignatureSha512 = sha512(signature);

		//System.out.println("Milliseconds Since Epoch: " + currentTimeMillis);
		//System.out.println("Demo Api Key: " + demoApiKey);
        //System.out.println("Signature: " + signature);
		System.out.println("Base64 Hashed Signature SHA256: " + Base64.getEncoder().encodeToString(hashedSignatureSha256.getBytes()));
		System.out.println("Base64 Hashed Signature SHA512: " + Base64.getEncoder().encodeToString(hashedSignatureSha512.getBytes()));
		
	}

	public static String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(input.getBytes());

            byte[] byteData = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

	public static String sha512(String input){
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(input.getBytes());
	
			byte[] byteData = md.digest();
	
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
	
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error: " + e.getMessage());
			return null;
		}
	}

}
