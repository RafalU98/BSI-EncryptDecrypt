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
 * @version 1.0 Build 1 December 13, 2020.
*/
public class Main {
    public static void main(String[] args) throws InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException {

        System.out.println("Welcome to the Encryption and Decryption Program");

        System.out.println("""
                Choose which Algorithm you want to use:
                1. DES ( Data Encryption Standard )
                2. AES ( Advanced Encryption Standard )
                3. RC5 ( Rivest Cipher 5 )
                """
        );
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                DES.encryptDecryptDES();
            case 2:
                AES.DecrypteEncrypte();
            case 3:
                //RC5.encryptDecryptRC5();
            default:
                System.out.println("You have entered a Wrong NUMBER, please restart the program and try again");
        }


    }
}
