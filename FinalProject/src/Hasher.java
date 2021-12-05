import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * this class hashes passwords using SHA-512
 *
 */
public class Hasher {
    /**
     * This method converts a string into a hashed protected password
     * @param pwd
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String hash(String pwd) throws NoSuchAlgorithmException {
        MessageDigest SHA512=MessageDigest.getInstance("SHA-512");

        byte[] bytes= SHA512.digest(pwd.getBytes());
        BigInteger val= new BigInteger(1,bytes);

        String hash= val.toString(16);
        while (hash.length() < 32) {
            hash = "0" + hash;
        }

        return hash;

    }
}
