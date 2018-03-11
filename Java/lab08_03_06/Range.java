package lab08_03_06;

import java.util.Iterator;

public class Range implements Iterable<Integer> {

	private final int start, end;

	// ranges from start (inclusive) to end (exclusive)
	public Range(int start, int end) {
	    // completare
		this.start = start;
		this.end = end;
	}

	// ranges from 0 (inclusive) to end (exclusive)
	public Range(int end) {
	    // completare
		start = 0;
		this.end = end;
	}

	@Override
	public Iterator<Integer> iterator() {
	  // completare usando la classe RangeIterator
		
		
		return new RangeIterator(start,end);
		
		
		/*
		Iterator<Integer> it = new Iterator<Integer>() {

            private int currentIndex = start;

            @Override
            public boolean hasNext() {
                return currentIndex < end;
            }

            @Override
            public Integer next() {
                return currentIndex++;
            }

        };
        return it;
        */
	}

}
