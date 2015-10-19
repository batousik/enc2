package uk.ac.standrews.cs.cs3302.practical1.io;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 130017964 on 10/13/15.
 * Simple File reader
 */
public class FileReader {
    public static final String RESOURCES_PATH = "uk/ac/standrews/cs/cs3302/practical1/resources/";

    /**
     * Reads in a file
     *
     * @param path relative path from project root
     * @return file body
     * @throws IOException
     */
    public String getFileFromJar(String path) throws IOException {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(path);
        return IOUtils.toString(in, "UTF-8");
    }

    /**
     * Reads in file using absolute path
     *
     * @param path absolute path to file
     * @return String file contents
     * @throws IOException
     */
    public String getFileFromAbs(String path) throws IOException {
        File file = new File(path);
        return IOUtils.toString(new FileInputStream(file), "UTF-8");
    }
}
