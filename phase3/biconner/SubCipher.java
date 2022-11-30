
/**
 * Author: Blake Conner
 * Substitution Cipher
 * Generates a random alphabet as a cipher key and replaces 
 * the plaintext characters with the new cipher alphabet characters 
 * at the position they are in the alphabet
 */
import java.util.*;

public class SubCipher {

    //generates a random cipher alphabet
    public char[] cipherAlphabet(String alpha){
        Random rand = new Random(); 
        char[] cipherAlphabet = new char[25];
        for(int i = 0; i < alpha.length() - 1; i++){
            int int_random = rand.nextInt(26);
            cipherAlphabet[i] = alpha.charAt(int_random);
        }
        return cipherAlphabet;
    }

    //encodes a plain text string with the new cipher alphabet
    public String encode (String plainText){
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        char[] key = cipherAlphabet(alpha);
        
        char[] output = new char[plainText.length()];

        for(int i = 0; i < plainText.length() - 1; i++){
            if (plainText.charAt(i) == ' '){
                output[i] = ' ';
            }else {
                int position = alpha.indexOf(plainText.charAt(i));
                output[i] = key[position];
            }
        }
        return output.toString();
    }


}