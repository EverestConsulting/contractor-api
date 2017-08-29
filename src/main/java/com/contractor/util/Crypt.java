package com.contractor.util;

import org.mindrot.jbcrypt.BCrypt;

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
     * @param plainText String un-encrypted string.
     * @param hashed String hash to compare.
     * @return boolean true if matched.
     */
    public static boolean hashMatches(String plainText, String hashed) {
        return BCrypt.checkpw(plainText, hashed);
    }

}
