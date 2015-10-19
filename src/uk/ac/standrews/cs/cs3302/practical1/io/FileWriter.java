package uk.ac.standrews.cs.cs3302.practical1.io;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by 130017964 on 10/13/15.
 * Simple file writer
 */
public class FileWriter {
    /**
     * Simple procedure to write to file
     *
     * @param fileName file name to write to
     * @param data     what to write to file
     * @param mode     "append" or "overwrite", default is overwrite
     * @throws IOException
     */
    public void writeToFile(String fileName, String data, String mode) throws IOException {
        File file;
        switch (mode) {
            case "append":
                file = new File(fileName);
                FileUtils.writeStringToFile(file, data, Charset.defaultCharset(), true);
                break;
            default:
            case "overwrite":
                file = new File(fileName);
                FileUtils.writeStringToFile(file, data, Charset.defaultCharset(), false);
                break;
        }

    }
}
