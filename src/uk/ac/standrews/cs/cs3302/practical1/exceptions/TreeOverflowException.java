package uk.ac.standrews.cs.cs3302.practical1.exceptions;

/**
 * Created by 130017964 on 10/13/15.
 * Custom Exception class
 */
public class TreeOverflowException extends Exception {
    public TreeOverflowException() {
        super("TreeOverflowException, alphabet size is smaller than different values supplied");
    }
}
