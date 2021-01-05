import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * Decryption and Encryption Algorithms for BSI classes PJATK Gdańsk
 *
 * @Authors Rafał Ubermanowicz and Jakub Wiśniewski
 * @version 1.8 Build 2 January 5, 2020.
*/
public class Main {
    public static void main(String[] args) throws InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException {

        System.out.println("Welcome to the Encryption and Decryption Program");

        System.out.println("""
                Choose which Algorithm you want to use:
                1. DES ( Data Encryption Standard )
                2. AES ( Advanced Encryption Standard )
                3. Blowfish algorithm
                4. RSA (Rivest–Shamir–Adleman) 
                5. Diffie Hellman Merkle
                """
        );
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                DES.encryptDecryptDES();
                break;
            case 2:
                AES.DecrypteEncrypte();
                break;
            case 3:
                Blowfish.encryptDecryptBLOWFISH();
                break;
            case 4:
                RSA.encryptDecryptRSA();
                break;
            case 5:
                DHM.();
                break;
        }


    }
}

