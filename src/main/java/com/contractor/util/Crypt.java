package com.contractor.util;

import org.mindrot.jbcrypt.BCrypt;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * BCrypt library integration class. Code for library https://github.com/jeremyh/jBCrypt library.
 */
public class Crypt {

    /**
     * @param plainText String to hash.
     * @return String hashed string.
     */
    public static String hashIt(String plainText) {
        return BCrypt.hashpw(plainText, BCrypt.gensalt(12));
    }

    /**
     * Checks if two hashes match, based on BCrypt implementation
     *
     * @param plainText String un-encrypted string.
     * @param hashed    String hash to compare.
     * @return boolean true if matched.
     */
    public static boolean hashMatches(String plainText, String hashed) {
        return BCrypt.checkpw(plainText, hashed);
    }

    /**
     * @return String random
     */
    public static String generateToken() {
        SecureRandom sr = new SecureRandom();
        return new BigInteger(130, sr).toString(32);
    }
}
