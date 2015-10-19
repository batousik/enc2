package uk.ac.standrews.cs.cs3302.practical1.encoder;

import uk.ac.standrews.cs.cs3302.practical1.io.FileReader;

import java.io.IOException;

/**
 * Created by 130017964 on 10/13/15.
 * Encoder class
 */
public class HuffmanStringEncoder extends HuffmanStringCoder {
    private String data;
    private String[] dataArr;

    public HuffmanStringEncoder(String fileName, String encodedText) {
        super(encodedText);
        try {
            FileReader fr = new FileReader();
            this.data = fr.getFileFromJar(FileReader.RESOURCES_PATH + fileName);
            this.dataArr = this.data.split("");
        } catch (IOException e) {
            System.err.println("NO FILE FOUND");
            e.printStackTrace();
        }
    }

    public HuffmanStringEncoder(String absPath, int ignored) {
        super("");
        try {
            FileReader fr = new FileReader();
            this.data = fr.getFileFromAbs(absPath);
            this.dataArr = this.data.split("");
        } catch (IOException e) {
            System.err.println("NO FILE FOUND");
            e.printStackTrace();
        }
    }

    public HuffmanStringEncoder(String data) {
        super("");
        this.data = data;
        this.dataArr = this.data.split("");
    }

    public void encode() throws Exception {
        for (int i = 0; i < dataArr.length; i++) {
            String s = dataArr[i];
            if (s == null || s.length() == 0)
                continue;
            if (huffmanTree.symbolExist(s)) {
                encodedData += huffmanTree.getCodeRepr(s);
            } else {
                // this will send the current code for the NYT node first and the UTF8-encoded character afterwards
                encodedData += huffmanTree.getNYTCode();
                encodedData += getCodeForNewChar(s);
            }
            System.out.println(i + "[" + s + "]:" + encodedData);
            huffmanTree.acceptSymbol(s);
        }
    }

    public String getEncodedText() {
        return encodedData;
    }
}
