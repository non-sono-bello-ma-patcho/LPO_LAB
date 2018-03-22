package labo08_03_06;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MapIteratorTest {

	public static void main(String[] args) {
		List<String> l = new LinkedList<String>(Arrays.asList(new String[] { "one", "two", "three" }));
		Iterator<String> it = new MapIterator<String,String>(new UppercaseFunction(), l.iterator());
		while (it.hasNext()) // print ONE \n TWO \n THREE
			System.out.println(it.next());
	}

}
