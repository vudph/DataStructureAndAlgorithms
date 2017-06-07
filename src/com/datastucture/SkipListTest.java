package com.datastucture;

import java.util.Comparator;
import java.util.Iterator;

public class SkipListTest {

	public static void main(String[] args) {
		SkipList<Integer> skipList = new SkipList<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        skipList.add(1);
        skipList.add(5);
        skipList.add(2);
        skipList.add(3);
        skipList.uglyPrint();
        skipList.add(4);
        skipList.add(6);
        skipList.add(2);
        skipList.add(3);
//        skipList.uglyPrint();
        Iterator<Integer> iterator = skipList.iterator();
        while(iterator.hasNext()) {
        	System.out.print(iterator.next() + " ");
        }
	}

}
