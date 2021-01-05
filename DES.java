import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

/**
 * DES Algorithm used for Decryption and Encryption of Strings
 *
 * DES os a block cipher algorithm that takes a fixed length-string and transforms it through series of operations into
 * a ciphertext, DES has the block size of 64Bits. DES uses a key to customize the transformation and only if you know
 * the secret key, you will be able to decrypt the ciphertext properly.
 *
 * @Author Rafal Ubermanowicz
 */
@SuppressWarnings("DuplicatedCode")
public class DES {
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
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyData, "DES");
            Cipher cipher = Cipher.getInstance("DES");
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
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyData, "DES/CBC/PKCS5PADDING");
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5PADDING");
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
    public static void encryptDecryptDES() {
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
            encryptDecryptDES();
        }
    }
}
