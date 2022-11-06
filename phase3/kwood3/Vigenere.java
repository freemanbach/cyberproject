public class Vigenere {

    /**
    * Makes the key the same length as the message.
    */
    public String generateKey(String message, String key) {
        if (key.length() > message.length())
            return key;

        int x = message.length();
        int i = 0;

        while (key.length() != message.length()) {
            if (i == x)
                i = 0;
            
            key += key.toLowerCase().charAt(i);
            i++;
        }
        return key;
    }

    /**
    * Takes the original message along with a key and encrypts it
    */
    public String cipherText(String message, String key) {
        String generatedKey = generateKey(message, key);
        String cipherText = "";
        String upperMessage = message.toUpperCase();
        String upperKey = generatedKey.toUpperCase();

        for (int i = 0; i < upperMessage.length(); i++) {

            int cipherChar = (upperMessage.charAt(i) + upperKey.charAt(i)) % 26;
            cipherChar += 'a';
            cipherText+=(char)(cipherChar);
        }
        return cipherText;
    }

    /**
    * Takes the cipher text along with the key and decrypts it back to the original text
    */
    public String plainText(String cipherText, String key) {
        String generatedKey = generateKey(cipherText, key);
        String plainText = "";
        String upperCipherText = cipherText.toUpperCase();
        String upperKey = generatedKey.toUpperCase();

        for (int i = 0; i < upperCipherText.length(); i++) {
            int plainChar = (upperCipherText.charAt(i) - upperKey.charAt(i) + 26) % 26;
            plainChar += 'a';
            plainText+=(char)(plainChar);
        }

        return plainText;
    }
}