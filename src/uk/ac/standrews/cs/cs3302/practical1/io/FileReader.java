package uk.ac.standrews.cs.cs3302.practical1.io;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 130017964 on 10/18/15.
 */
public class FileReader {
    public static final String RESOURCES_PATH = "uk/ac/standrews/cs/cs3302/practical1/resources/";

    /**
     * Reads in a file
     *
     * @param path relative path from project root
     * @return file body
     */
    public String getFile(String path) throws IOException {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(path);
        String string = IOUtils.toString(in, "UTF-8");
        return string;
    }
}
