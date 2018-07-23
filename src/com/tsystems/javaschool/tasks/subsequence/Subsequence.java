package com.tsystems.javaschool.tasks.subsequence;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")
    public boolean find(List x, List y) {
	// TODO: Implement the logic here
	boolean Subsequence = false;

	if (x == null || y == null) {
	    throw new IllegalArgumentException();
	}

	if (x.isEmpty()) {
	    Subsequence = true;
	} else {
	    ListIterator<String> iterX = x.listIterator();
	    Iterator<String> iterY = y.iterator();

	    while (iterY.hasNext()) {
		if (iterY.next() == x.get(iterX.nextIndex())) {
		    iterX.next();
		}
		
		if (!iterX.hasNext()) {
		    Subsequence = true;
		    break;
		}
	    }
	}
	return Subsequence;
    }
}
