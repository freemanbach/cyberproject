import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Random;

public final class Playfair {

    private Playfair() {
        throw new AssertionError();
    }

    /**
     *  Uses the playfair cipher to convert plain text to cipher text
     * 
     * @param plaintext The inputted text to be converted
     * @param keyPhrase The phrase that generates the key block
     * @return Returns the encrypted text using the playfair cipher
     */
    public static String playfairEncrypt(String plaintext, String keyPhrase) {
        if(plaintext.isBlank() || plaintext.isEmpty()) {
            return "";
        }

        String[] keyBlock = generateKeyBlock(keyPhrase);
        String[] blocks = fillX(chunkText(plaintext));
        String rawCipherText = "";

        for(String block : blocks) {
            //
            // blockLocation[0] = character 1 (row,column)
            // blockLocation[1] = character 2 (row,column)
            //
            int[][] blockLocation = findBlockLocation(keyBlock, block);
            int ch1Row = blockLocation[0][0];
            int ch1Col = blockLocation[0][1];
            int ch2Row = blockLocation[1][0];
            int ch2Col = blockLocation[1][1];


            if(ch1Row == ch2Row) { //same row
                ch1Col++;
                ch2Col++;
                if(ch1Col >= keyBlock.length) {
                    ch1Col = 0;
                }
                if(ch2Col >= keyBlock.length) {
                    ch2Col = 0;
                }
            } else if(ch1Col == ch2Col) { //same column
                ch1Row++;
                ch2Row++;
                if(ch1Row >= keyBlock.length) {
                    ch1Row = 0;
                }
                if(ch2Row >= keyBlock.length) {
                    ch2Row = 0;
                }
            } else { //other cases
                ch1Col = blockLocation[1][1];
                ch2Col = blockLocation[0][1];
            }

            rawCipherText += keyBlock[ch1Row].charAt(ch1Col) + "" + keyBlock[ch2Row].charAt(ch2Col);
        }

        return formatText(rawCipherText);
    }

    /**
     * Uses the playfair cipher to convert cipher text to plain text
     * 
     * @param ciphertext The inputted text to be converted
     * @param keyphrase The phrase that generates the key block
     * @return Returns the decrypted text using the playfair cipher
     */
    public static String playfairDecrypt(String ciphertext, String keyphrase) {
        if(ciphertext.isBlank() || ciphertext.isEmpty()) {
            return "";
        }
        String[] keyBlock = generateKeyBlock(keyphrase);
        String[] blocks = chunkText(ciphertext);
        String rawPlainText = "";

        for(String block : blocks) {
             //
            // blockLocation[0] = character 1 (row,column)
            // blockLocation[1] = character 2 (row,column)
            //
            int[][] blockLocation = findBlockLocation(keyBlock, block);
            int ch1Row = blockLocation[0][0];
            int ch1Col = blockLocation[0][1];
            int ch2Row = blockLocation[1][0];
            int ch2Col = blockLocation[1][1];

            if(ch1Row == ch2Row) { //same row
                ch1Col--;
                ch2Col--;
                if(ch1Col < 0) {
                    ch1Col = keyBlock.length - 1;
                }
                if(ch2Col < 0) {
                    ch2Col = keyBlock.length - 1;
                }
            } else if(ch1Col == ch2Col) { //same column
                ch1Row--;
                ch2Row--;
                if(ch1Row < 0) {
                    ch1Row = keyBlock.length - 1;
                }
                if(ch2Col < 0) {
                    ch2Row = keyBlock.length - 1;
                }
            } else { //other cases
                ch1Col = blockLocation[1][1];
                ch2Col = blockLocation[0][1];
            }
            rawPlainText +=  keyBlock[ch1Row].charAt(ch1Col) + "" + keyBlock[ch2Row].charAt(ch2Col);
        }

        return rawPlainText;
    }

    /**
     * Generates a random key phrase of two words from the words.txt file
     * <b>NOTE:</b> A words.txt file must be included for this to work
     * 
     * @return Returns two randomly generated words.
     */
    public static String generateKeyPhrase() {
        Random r = new Random();
        LinkedList<String> words = null;
        try {
            words = FileUtil.readFile("words.txt");
        } catch(FileNotFoundException e) {
            System.err.println( e.getMessage() + "Please ensure that the 'words.txt' file exist.");
        }
        if(words == null) {
            return "";
        }
        return words.get(r.nextInt(words.size())) + " " + words.get(r.nextInt(words.size()));
    }

    private static String[] fillX(String[] blocks) {
        for(int i = 0; i < blocks.length; i++) {
            if(blocks[i].length() < 2) {
                blocks[i] += 'X';
            } else if(blocks[i].charAt(0) == blocks[i].charAt(1)) {
                blocks[i] = "" + blocks[i].charAt(0) + 'X';
                blocks = offsetBlocks(i + 1 , blocks[i].charAt(0), blocks);
            }
        }

        return blocks;
    }

    private static String[] offsetBlocks(int index, char ch, String[] blocks) {
        while(index < blocks.length) {
            if(blocks[index].length() < 2) {
                char temp = blocks[index].charAt(0);
                blocks[index] = "" + ch + temp;
                ch = ' ';
            } else {
                char tempLeft = blocks[index].charAt(0);
                char tempRight = blocks[index].charAt(1);
                blocks[index] = "" + ch + tempLeft;
                ch = tempRight;
            }
            index++;
        }

        if(ch != ' ') {
            String[] temp = new String[blocks.length + 1];
            for(int i = 0; i < blocks.length; i++) {
                temp[i] = blocks[i];
            }

            temp[temp.length-1] = "";
            temp[temp.length-1] += ch;
            return temp;
        } 

        return blocks;
    } 

    private static int[][] findBlockLocation(String[] keyBlock, String block) {
        int[][] location = new int[2][2];
        
        int chIndex = 0;
        for(char c : block.toCharArray()) {
            for(int i = 0; i < keyBlock.length; i++) {
                for(int j = 0; j < keyBlock[i].length(); j++) {
                    if(keyBlock[i].charAt(j) == c) {
                        location[chIndex][0] = i; //rows
                        location[chIndex][1] = j; //columns
                        chIndex++;
                    }
                }
            }
        }

        return location;
    }

    private static String[] generateKeyBlock(String phrase) {
        LinkedList<Character> letters = playfairAlphabet();
        LinkedList<Character> phraseToChar = phraseToChar(phrase);
        String[] block = new String[5];
        
        for(int i = 0; i < block.length; i++) {
            block[i] = "";
            if(!phraseToChar.isEmpty()) {
                int j = 0;
                while(!phraseToChar.isEmpty() && j < block.length) {
                    char ch = phraseToChar.pop();
                    if(letters.contains(ch)) {
                        block[i] += ch; 
                        letters.removeFirstOccurrence(ch);
                        j++;
                    }
                }
            } 
            if(phraseToChar.isEmpty() && !letters.isEmpty()) {
                int size = letters.size();
                    for(int j = 0; j < size && block[i].length() < 5; j++) {
                    block[i] += letters.pop();
                }
            }
        }

        return block;
    }

    private static String[] chunkText(String text) {
        LinkedList<Character> textToChar = phraseToChar(text);
        int len = textToChar.size() % 2 == 0 ? textToChar.size() : textToChar.size() + 1;
        String[] blocks = new String[len / 2];

        int i = 0, j = 0;
        for(char c : textToChar) {
            if(blocks[i] == null) {
                blocks[i] = "";
            }
            blocks[i] += c;


            j++;
            if(j != 0 && j % 2 == 0) {
                i++;
            }
        }

        return blocks;
    }

    private static String formatText(String text) {
        String formatted = "";

        int i = 0;
        for(char c : text.toCharArray()) {
            formatted += c;
            i++;
            if(i != 0 && i % 5 == 0) {
                formatted += ' ';
                i = 0;
            }
        }
        return formatted;
    }

    private static LinkedList<Character> playfairAlphabet() {
        LinkedList<Character> letters = new LinkedList<>();

        for(char c = 'A'; c <= 'Z'; c++) {
            if(c != 'J') {
                letters.add(c);
            }
        }

        return letters;
    }

    private static LinkedList<Character> phraseToChar(String phrase) {
        LinkedList<Character> phraseToChar = new LinkedList<>();

        phrase = phrase.trim().toUpperCase();
        for(char c : phrase.toCharArray()){
            if(c >= 'A' && c <= 'Z') {
                if(c == 'J') {
                    phraseToChar.add('I');
                } else {
                    phraseToChar.add(c);
                }
            }
        }

        return phraseToChar;
    }

}