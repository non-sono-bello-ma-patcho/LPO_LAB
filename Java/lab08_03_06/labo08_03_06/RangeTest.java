package labo08_03_06;

import java.util.Iterator;

public class RangeTest {

	public static void main(String[] args) {
		Range r = new Range(2);
		Iterator<Integer> it1 = r.iterator();
		// test with while, prints 0 0 \n 0 1 \n 1 0 \n 1 1 \n		
		while (it1.hasNext()) {
			int x = it1.next(); // unboxing conversion
			Iterator<Integer> it2 = r.iterator();
			while (it2.hasNext())
				System.out.println(x + " " + it2.next()); // string conversion
		}
		// test with enhanced-for, prints 0 0 \n 0 1 \n 1 0 \n 1 1 \n
		for (int x : r) // unboxing conversion
			for (int y : r) // unboxing conversion
				System.out.println(x + " " + y); // string conversion
		r = new Range(1, 3);
		// test with enhanced-for, prints 1 1 \n 1 2 \n 2 1 \n 2 2 \n
		for (int x : r)
			for (int y : r)
				System.out.println(x + " " + y);
	}
}
