package lab08_03_06;

import java.util.Iterator;

public class RangeIterator implements Iterator<Integer>{

	private int currentIndex;
	private int end;
	
	public RangeIterator(int index, int end){
		this.currentIndex = index;
		this.end = end;
	}
	
	public boolean hasNext() {
        return currentIndex < end;
    }
	
	public Integer next() {
        return currentIndex++;
    }
	
}

