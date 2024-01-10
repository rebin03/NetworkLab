import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;
import java.util.Scanner;

public class RSATest {

    public static void main(String[] args) {
        try {
            // Generate an RSA key pair
            KeyPair keyPair = generateKeyPair();

            // Get input from the user
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a message to encrypt: ");
            String originalMessage = scanner.nextLine();

            // Encrypt the message using the public key
            String encryptedMessage = encrypt(originalMessage, keyPair.getPublic());
            System.out.println("Encrypted Message: " + encryptedMessage);

            // Decrypt the message using the private key
            String decryptedMessage = decrypt(encryptedMessage, keyPair.getPrivate());
            System.out.println("Decrypted Message: " + decryptedMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // You can adjust the key size (e.g., 1024, 2048, or 4096 bits)
        return keyPairGenerator.generateKeyPair();
    }

    private static String encrypt(String plaintext, PublicKey publicKey) throws Exception {
        byte[] plaintextBytes = plaintext.getBytes();
        javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("RSA");
        cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(plaintextBytes);
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    private static String decrypt(String ciphertext, PrivateKey privateKey) throws Exception {
        byte[] ciphertextBytes = Base64.getDecoder().decode(ciphertext);
        javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("RSA");
        cipher.init(javax.crypto.Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(ciphertextBytes);
        return new String(decryptedBytes);
    }
}

