import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

/**
 * BLOWFISH Algorithm used for cryptography
 *
 *Blowfish is a symmetric-key which provide a good encryption rate in software however the aes receives more attention
 *Blowfish is a general-purpose algorithm that is intended as an alternative to DES
 *The algorithm is hereby placed in the public domain, and can be freely used by anyone."[3]
 *
 * @Author Rafal Ubermanowicz
 */
@SuppressWarnings("DuplicatedCode")
public class Blowfish {
    /**
     * Object which is Encrypting the entered text after choosing encryption.
     *
     * @param word it is the in main initialized String which you want to encrypt
     * @param key the secret key value which you have to enter when decrypting the ciphered test
     * @return returns the encrypted text to be printed out in the encryptDecrypt function
     */
    public static String encrypt(String word, String key) {
        try {
            byte[] keyData = (key).getBytes();
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyData, "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] text = cipher.doFinal(word.getBytes());
            return new String(Base64.getEncoder().encode(text));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Object which is Decrypting the entered text after choosing decryption.
     *
     * @param word it is the in main initialized String which you want to encrypt
     * @param key the secret key which was used to encrypt the text should be the same here
     * @return returns the decrypted text
     */
    public static String decrypt(String word, String key) {
        try {
            byte[] keyData = (key).getBytes();
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyData, "Blowfish/CFB/NoPadding");
            Cipher cipher = Cipher.getInstance("Blowfish/CFB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] text = cipher.doFinal(java.util.Base64.getDecoder().decode(word));
            return new String(text);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * The main object used to initialize and declare the variables
     */
    public static void encryptDecryptBLOWFISH() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        String text;

        System.out.println("Do you want to 1.Encrypt or 2.Decrypt?");
        choice = scanner.nextInt();

        System.out.println("Please Enter Your secret Key ( size = 8 )");
        String key = scanner.next();


        if (choice == 1) {
            System.out.println("Enter the Word you want to Encrypt");
            text = scanner.next();
            System.out.println("Encrypted Word is: " + encrypt(text, key));
        } else if (choice == 2) {
            System.out.println("Enter the Word you want to Decrypt");
            text = scanner.next();
            System.out.println("Decrypted Word is: " + decrypt(text, key));
        } else
            System.out.println("You chose a Wrong Number ");

        System.out.println("Do you want to continue? Enter 1, if not enter any other number");
        if (scanner.nextInt() == 1 ) {
            encryptDecryptBLOWFISH();
        }
    }
}
