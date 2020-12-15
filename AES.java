
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES- algorithm encryption and decryption
 *
 * @Authors Rafal Ubermanowicz and Jakub Wisniewski
 */
class AES {

    private static final String ALG = "AES";
    private static byte[] keyValue;

    /**
     * @param key a seed to randomize encrypte
     */
    public static byte[] AES(String key) {
        keyValue = (key).getBytes();
        return keyValue;
    }

    /**
     * data object was encrypted here
     * @param Data data to encrypt
     * @return return encrypted data
     *
     */
    public static String encrypt(String Data,byte[] keyValue) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        Key key = generateKey(keyValue);
        Cipher c = Cipher.getInstance(ALG);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        byte[] encodedBytes = Base64.getEncoder().encode(encVal);
        String encryptedValue = new String(encodedBytes);
        return encryptedValue;
    }

    /**
     * encryptedData object was decrypted here
     * @param encryptedData encrypted data to decrypte
     * @return return decrypted data
     *
     */
    public static String decrypt(String encryptedData,byte[] keyValue) throws BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {

        Key key = generateKey(keyValue);
        Cipher c = Cipher.getInstance(ALG);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decorderValue = Base64.getDecoder().decode(encryptedData);
        byte[] decValue = c.doFinal(decorderValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;

    }

    private static Key generateKey(byte[] keyValue) {

        Key key = new SecretKeySpec(keyValue, ALG);
        return key;
    }

    public static void DecrypteEncrypte() throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        Scanner scanner = new Scanner(System.in);
        int choice;
        String text;

        System.out.println("Do you want to 1.Encrypt or 2.Decrypt?");
        choice = scanner.nextInt();

        byte[] aes = AES("lv39eptlvuhaggsr");

        if (choice == 1) {
            System.out.println("Enter the Text you want to Encrypt");
            text = scanner.next();
            System.out.println("Encrypted Text is: " + encrypt(text,aes));
        } else if (choice == 2) {
            System.out.println("Enter the Text you want to Decrypt");
            text = scanner.next();
            System.out.println("Decrypted Text is: " + decrypt(text,aes));
        } else
            System.out.println("You chose a Wrong Number ");
    }
}
