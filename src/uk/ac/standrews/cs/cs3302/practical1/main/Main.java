package uk.ac.standrews.cs.cs3302.practical1.main;

import uk.ac.standrews.cs.cs3302.practical1.encoder.HuffmanStringDecoder;
import uk.ac.standrews.cs.cs3302.practical1.encoder.HuffmanStringEncoder;

/**
 * Created by 130017964 on 10/11/15.
 * Main class for executable jar
 */
public class Main {
    public static void main(String[] args) throws Exception {
//        String[] arr = new String[95];
//        for (int i = HuffmanStringCoder.ALPHABET_ASCII_START_CODE; i <= HuffmanStringCoder.ALPHABET_ASCII_END_CODE; i++) {
//            arr[i - 32] = String.valueOf(Character.toChars(i));
//        }
//        Random r = new Random();
//        System.out.println();
//        for (int i = 0; i < 400; i++) {
//            int R = r.nextInt(95);
//            System.out.print(arr[R]);
//        }
//        System.out.println();

        HuffmanStringDecoder hd;
        HuffmanStringEncoder he;
        if (args.length == 0) {
            String data = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCD" +
                    "EFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|" +
                    "}~AAAAAAAAAAA";
            for (int i = 0; i < 100; i++) {
                data += "AAAAAAAAAAAAAAAAA";
            }
            he = new HuffmanStringEncoder(data);
            he.encode();
            hd = new HuffmanStringDecoder(he.getEncodedText(), "out-" + System.nanoTime() + ".txt");
            hd.decode();
        } else if (args.length == 1) {
            String in = args[0];
            he = new HuffmanStringEncoder(in, 0);
            he.encode();
            hd = new HuffmanStringDecoder(he.getEncodedText(), "out-" + System.nanoTime() + ".txt");
            hd.decode();
        }
    }
}