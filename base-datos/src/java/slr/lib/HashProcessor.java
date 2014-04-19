/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slr.lib;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aaron
 */
public class HashProcessor {
    MessageDigest prc;
    public HashProcessor(String algorithm) throws NoSuchAlgorithmException{
        prc = MessageDigest.getInstance(algorithm);
    }

    public String hash(String message){
        String res = "";
        prc.reset();
        try {
            prc.update(message.getBytes("UTF-8"));
            for(byte i: prc.digest()){
                res += String.format("%1$02x", i);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(HashProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public static String hash(String message, String algorithm) throws NoSuchAlgorithmException{
        MessageDigest sprc = MessageDigest.getInstance(algorithm);
        String res = "";
        sprc.reset();
        try {
            sprc.update(message.getBytes("UTF-8"));
            for(byte i: sprc.digest()){
                res += String.format("%1$02x", i);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(HashProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public static String generateSalt(int length){
        byte[] arr = new byte[length];
        String res = "";
        SecureRandom rnd = new SecureRandom();
        rnd.nextBytes(arr);
        for(byte i: arr){
            res += String.format("%1$02x", i);
        }
        return res;
    }
}
