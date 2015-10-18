package uk.ac.standrews.cs.cs3302.practical1.encoder;

import uk.ac.standrews.cs.cs3302.practical1.datastructure.HTree;
import uk.ac.standrews.cs.cs3302.practical1.exceptions.TreeOverflowException;
import uk.ac.standrews.cs.cs3302.practical1.io.FileReader;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by 130017964 on 10/18/15.
 */
public class HuffmanStringEncoder {
    private FileReader fr;
    private String data;
    private String[] dataArr;
    private HTree huffmanTree;
    private int alphabetSize;
    private int e;
    private int r;
    private String encodedText = "";
    private HashMap<String, Integer> mapOfChars;

    public HuffmanStringEncoder(String fileName, int alphabetSize) {
        try {
            this.mapOfChars = new HashMap<>();
            fillInAlphabetMap();
            this.alphabetSize = alphabetSize;
            this.fr = new FileReader();
            this.data = this.fr.getFile(FileReader.RESOURCES_PATH + fileName);
            this.dataArr = this.data.split("");
            this.huffmanTree = new HTree(95);
            this.solveEncEquation();
        } catch (IOException e) {
            System.err.println("NO FILE FOUND");
            e.printStackTrace();
        } catch (TreeOverflowException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates map to get chars position (k) from map of chars
     * Symbol S(k) is encoded as follows:
     * 1<=k<=2r as (e+1) bit binary representation
     * k>2r as e bit binary representation of k-r-1
     */
    private void fillInAlphabetMap() {
        for (int i = 32; i <= 126; i++) {
            mapOfChars.put(String.valueOf(Character.toChars(i)), i - 31);
        }
    }

    /**
     * solves for e and r such that
     * 2^e + r = alphabetSize and 0 <= r <= 2^e
     */
    private boolean solveEncEquation() {
        for (int i = 0; i < alphabetSize; i++) {
            r = i;
            e = (int) (Math.log(alphabetSize - i) / Math.log(2));
            if (Math.pow(2, e) + r == alphabetSize)
                return true;
        }
        return false;
    }

    public void encode() throws TreeOverflowException {
        for (int i = 0; i < dataArr.length; i++) {
            String s = dataArr[i];
            if (s == null || s.length() == 0)
                continue;
            if (huffmanTree.symbolExist(s)) {
                encodedText += huffmanTree.getCodeRepr(s);
                System.out.println(i + " " + huffmanTree.getCodeRepr(s));
            } else {
                // this will send the current code for the NYT node first and the UTF8-encoded character afterwards
                encodedText += getCodeForNewChar(s);
            }
            huffmanTree.acceptSymbol(s);
        }
    }

    /**
     * Symbol S(k) is encoded as follows:
     * 1<=k<=2r as (e+1) bit binary representation
     * k>2r as e bit binary representation of k-r-1
     *
     * @return code representation of a char
     */

    public String getCodeForNewChar(String s) {
        int k = this.mapOfChars.get(s);
        int numOfBits;
        String codeForNewChar;
        if (1 <= k && k <= 2 * r) {
            numOfBits = e + 1;
            codeForNewChar = Integer.toBinaryString(k - 1);
            while (codeForNewChar.length() < numOfBits)
                codeForNewChar = 0 + codeForNewChar;
        } else {
            numOfBits = e;
            codeForNewChar = Integer.toBinaryString(k - r - 1);
            while (codeForNewChar.length() < numOfBits)
                codeForNewChar = 0 + codeForNewChar;
        }
        return codeForNewChar;
    }

    public String getEncodedText() {
        return encodedText;
    }
}
