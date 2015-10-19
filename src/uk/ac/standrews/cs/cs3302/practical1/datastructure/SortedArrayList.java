package uk.ac.standrews.cs.cs3302.practical1.datastructure;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by 130017964 on 10/13/15.
 * Arraylist that keeps values sorted using insertion sort
 */
public class SortedArrayList<T> extends ArrayList<T> {
    @SuppressWarnings("unchecked")
    public void addSorted(T value) {
        add(value);
        Comparable<T> cmp = (Comparable<T>) value;
        for (int i = size() - 1; i > 0 && cmp.compareTo(get(i - 1)) < 0; i--)
            Collections.swap(this, i, i - 1);
    }
}
