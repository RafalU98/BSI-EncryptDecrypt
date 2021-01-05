
import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Scanner;

/**
 * RSA (Rivest–Shamir–Adleman) Algorithm Cryptography
 *
 * It is a public-private key cryptology that is one of the oldest and widely used system to secure data transmission.
 * In a public-key cryptology, the encryption key is public and distinct from the decryption key, which is kept secret (private).
 * Messages can be encrypted by anyone, via the public key, but can only be decoded by someone who knows the prime
 * numbers also called as private key.
 * RSA is a relatively slow algorithm. Because of this, it is not commonly used to directly encrypt user data.
 *
 * @Author Rafał Ubermanowicz
 */
public class RSA {

    private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgFGVfrY4jQSoZQWWygZ83roKXWD4YeT2x2p41dGkPixe73rT2IW04glagN2vgoZoHuOPqa5and6kAmK2ujmCHu6D1auJhE2tXP+yLkpSiYMQucDKmCsWMnW9XlC5K7OSL77TXXcfvTvyZcjObEz6LIBRzs6+FqpFbUO9SJEfh6wIDAQAB";
    private static String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKAUZV+tjiNBKhlBZbKBnzeugpdYPhh5PbHanjV0aQ+LF7vetPYhbTiCVqA3a+Chmge44+prlqd3qQCYra6OYIe7oPVq4mETa1c/7IuSlKJgxC5wMqYKxYydb1eULkrs5IvvtNddx+9O/JlyM5sTPosgFHOzr4WqkVtQ71IkR+HrAgMBAAECgYAkQLo8kteP0GAyXAcmCAkA2Tql/8wASuTX9ITD4lsws/VqDKO64hMUKyBnJGX/91kkypCDNF5oCsdxZSJgV8owViYWZPnbvEcNqLtqgs7nj1UHuX9S5yYIPGN/mHL6OJJ7sosOd6rqdpg6JRRkAKUV+tmN/7Gh0+GFXM+ug6mgwQJBAO9/+CWpCAVoGxCA+YsTMb82fTOmGYMkZOAfQsvIV2v6DC8eJrSa+c0yCOTa3tirlCkhBfB08f8U2iEPS+Gu3bECQQCrG7O0gYmFL2RX1O+37ovyyHTbst4s4xbLW4jLzbSoimL235lCdIC+fllEEP96wPAiqo6dzmdH8KsGmVozsVRbAkB0ME8AZjp/9Pt8TDXD5LHzo8mlruUdnCBcIo5TMoRG2+3hRe1dHPonNCjgbdZCoyqjsWOiPfnQ2Brigvs7J4xhAkBGRiZUKC92x7QKbqXVgN9xYuq7oIanIM0nz/wq190uq0dh5Qtow7hshC/dSK3kmIEHe8z++tpoLWvQVgM538apAkBoSNfaTkDZhFavuiVl6L8cWCoDcJBItip8wKQhXwHp0O3HLg10OEd14M58ooNfpgt+8D8/8/2OOFaR0HzA+2Dm";

    /**
     * Getting the publicKey and making it public
     *
     * @param base64PublicKey Public key based on 64 bits
     * @return parameter that returns the public key needed for Encryption
     */
    public static PublicKey getPublicKey(String base64PublicKey){
        PublicKey publicKey = null;
        try{
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    /**
     * Getting the privateKey and making it private
     *
     * @param base64PrivateKey Public key based on 64 bits
     * @return parameter that returns the private key needed for decryption
     */
    public static PrivateKey getPrivateKey(String base64PrivateKey){
        PrivateKey privateKey = null;
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            assert keyFactory != null;
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return privateKey;
    }

    /**
     * Module that is encrypting with the RSA Algorithm with the use of Public Key
     *
     * @param data the data string that you want to encrypt ( you enter it in the main function)
     * @param publicKey the public key which is then used to encrypt the data
     * @return returns the encrypted string
     */
    public static byte[] encrypt(String data, String publicKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
        return cipher.doFinal(data.getBytes());
    }


    /**
     * Module that is decrypting with the RSA Algorithm with the use of Private KeY
     *
     * @param data the data which is encrypted, you get it from encrypt function
     * @param privateKey the private key which is used to decrypt a public encrypted text
     * @return returns the decrypted string
     */
    public static String decrypt(byte[] data, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(data));
    }


    public static String decrypt(String data, String base64PrivateKey) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        return decrypt(Base64.getDecoder().decode(data.getBytes()), getPrivateKey(base64PrivateKey));
    }

    /**
     * Method that is converting our String to a SHA1 converted HASHCODE using the messageDigest library
     *
     * SHA1  or secure hash algorithm 1 is a has function the produces a message digest of 16bit
     *
     * @param input the input you get which is the word, or phrase you want to hash with md5
     * @return returns the hashed with md5 text
     */
    public static String SHA1(String input)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger bigInteger = new BigInteger(1, messageDigest);
            String hashtext = bigInteger.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method that is generating a Salt
     *
     * Salt is random data that is used as an additional input to a one way function that hashes data or passwords
     * @return returns the random generated data called salt
     */
    private static byte[] Salt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[6];
        random.nextBytes(salt);
        return salt;
    }

    /**
     * Hash method that is hashing the password with the random generated salt
     *
     * @param password the string which you want to to hash once again with salt
     * @param salt takes the  salt string hash which has been generated in the method Salt
     * @return returns the encoded md
     */
    private static byte[] Hash(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        return factory.generateSecret(spec).getEncoded();
    }

    /**
     * Main Function which asks for your desired text to encrypt,
     * also controls the rest of the class.
     */
    public static void encryptDecryptRSA()
    {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter The String You want to Encrypt: ");
            String textToEncrypt = scanner.nextLine();
            System.out.println("Public Key: " + publicKey);
            System.out.println("Private Key: " + privateKey);
            String encryptedString = Base64.getEncoder().encodeToString(encrypt(textToEncrypt, publicKey));
            System.out.println("Encrypted String: " + encryptedString);
            String decryptedString = RSA.decrypt(encryptedString, privateKey);
            System.out.println("Decrypted String: " + decryptedString);

            System.out.println();
            System.out.println("HashCode SHA1 for decrypted String: " + SHA1(textToEncrypt));
            Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
            byte[] bSalt = Salt();
            String strSalt = encoder.encodeToString(bSalt);
            System.out.println("Salt: " + strSalt);
            System.out.println("String to be hashed: " + SHA1(textToEncrypt) + strSalt + " ,String:  " + SHA1(textToEncrypt) + ", Salt: " + strSalt );
            String strHash = encoder.encodeToString(Hash(SHA1(textToEncrypt), bSalt));
            System.out.println("Hashed value: " + strHash);

            System.out.println("Original String: " + decryptedString);

        } catch (NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException | NoSuchPaddingException | InvalidKeySpecException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("Do you want to continue? Enter 1, if not enter any other number");
        if (scanner.nextInt() == 1 ) {
            encryptDecryptRSA();
        }
    }
}

