package uk.ac.standrews.cs.cs3302.practical1.encoder;

import uk.ac.standrews.cs.cs3302.practical1.datastructure.HNode;
import uk.ac.standrews.cs.cs3302.practical1.exceptions.TreeOverflowException;
import uk.ac.standrews.cs.cs3302.practical1.io.FileWriter;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by 130017964 on 10/13/15.
 * Huffman Decoder class
 */
public class HuffmanStringDecoder extends HuffmanStringCoder {
    private static String outputFile;
    private FileWriter fw;
    private String decodedData;
    private Queue<String> inputDataQueue;
    private String bufferCodeData;
    private int encodedSize;
    private int decodedSize;
    private double compressionRatio;

    public HuffmanStringDecoder(String encodedData, String outputFile) {
        super(encodedData);
        HuffmanStringDecoder.outputFile = outputFile;
        this.fw = new FileWriter();
        this.decodedData = "";
        this.bufferCodeData = "";
        this.inputDataQueue = new LinkedList<>();
        for (int i = 0; i < this.encodedData.length(); i++) {
            this.inputDataQueue.add(String.valueOf(this.encodedData.charAt(i)));
        }
        this.encodedSize = this.inputDataQueue.size();
    }

    public String getDecodedData() {
        return decodedData;
    }

    public void decode() throws TreeOverflowException, IOException {
        while (!inputDataQueue.isEmpty()) {
            this.bufferCodeData += this.inputDataQueue.remove();
            String decodedChar = this.huffmanTree.getNodeWithPath(bufferCodeData);
            if (decodedChar == null || decodedChar.equals(HNode.INNER))
                continue;
            if (decodedChar.equals(HNode.NYT) || decodedChar.equals(HNode.ROOT)) {
                if (decodedChar.equals(HNode.ROOT)) {
                    Queue<String> temp = new LinkedList<>();
                    temp.add(bufferCodeData);
                    temp.addAll(this.inputDataQueue);
                    this.inputDataQueue = temp;
                }
                this.bufferCodeData = "";
                for (int i = 0; i < e; i++) {
                    this.bufferCodeData += this.inputDataQueue.remove();
                }
                int binaryVal = Integer.parseInt(this.bufferCodeData, 2);
                if (binaryVal < r) {
                    this.bufferCodeData += this.inputDataQueue.remove();
                    binaryVal = Integer.parseInt(this.bufferCodeData, 2);
                    decodedChar = this.mapOfPosToChars.get(binaryVal + 1);
                } else {
                    decodedChar = this.mapOfPosToChars.get(binaryVal + 1 + r);
                }
            }
            nextSymbol(decodedChar);
        }
        decodedSize = decodedData.toCharArray().length * 7;
        compressionRatio = decodedSize / encodedSize;
        String info = "";
        info += "ENCODED SIZE: " + encodedSize + "\n";
        info += "DECODED SIZE: " + decodedSize + "\n";
        info += "COMPRESSION : " + compressionRatio + "\n";
        info += "TREE        : " + this.huffmanTree.getStringRepresentation() + "\n";
        info += "DATA        : \n";
        this.fw.writeToFile(outputFile, info, "overwrite");
        this.fw.writeToFile(outputFile, this.decodedData, "append");
    }

    private void nextSymbol(String decodedChar) throws TreeOverflowException {
        this.bufferCodeData = "";
        this.huffmanTree.acceptSymbol(decodedChar);
        this.decodedData += decodedChar;
    }
}
