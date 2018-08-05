package com.utilitybelt.test.algorithmics;

import static org.junit.Assert.assertArrayEquals;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.utilitybelt.algorithmics.Sort;
import com.utilitybelt.test.algorithmics.TesterHelpers.ComparableNumber;
import com.utilitybelt.test.algorithmics.TesterHelpers.TestNumber;

public class MergeSortTest {
	
	private int[] intArr;
	private float[] floatArr;
	private double[] doubleArr;
	private short[] shortArr;
	private long[] longArr;
	private ComparableNumber[] comparArr;
	private TestNumber[] numberArr;
	
	private int[] intArrSorted;
	private float[] floatArrSorted;
	private double[] doubleArrSorted;
	private short[] shortArrSorted;
	private long[] longArrSorted;
	private ComparableNumber[] comparArrSorted;
	private TestNumber[] numberArrSorted;


	@Before
	public void setUp() throws Exception {
		//Size of arrays
		int k = 1000;

		intArr = new int[k];
		floatArr = new float[k];
		doubleArr = new double[k];
		shortArr = new short[k];
		longArr = new long[k];
		comparArr = new ComparableNumber[k];
		numberArr = new TestNumber[k];

		intArrSorted = new int[k];
		floatArrSorted = new float[k];
		doubleArrSorted = new double[k];
		shortArrSorted = new short[k];
		longArrSorted = new long[k];
		comparArrSorted = new ComparableNumber[k];
		numberArrSorted = new TestNumber[k];
		
		List<Integer> perm = new LinkedList<>();
		for(int idx = 0; idx < k; idx++) {
			perm.add(idx);
		}
		
		Collections.shuffle(perm);
		
		for(int idx = 0; idx < k; idx++) {
			intArr[perm.get(idx)] = idx;
			floatArr[perm.get(idx)] = idx;
			doubleArr[perm.get(idx)] = idx;
			shortArr[perm.get(idx)] = (short)idx;
			longArr[perm.get(idx)] = idx;
			comparArr[perm.get(idx)] = new ComparableNumber(idx);
			numberArr[perm.get(idx)] = new TestNumber(idx);
			
			intArrSorted[idx] = idx;
			floatArrSorted[idx] = idx;
			doubleArrSorted[idx] = idx;
			shortArrSorted[idx] = (short)idx;
			longArrSorted[idx] = idx;
			comparArrSorted[idx] = new ComparableNumber(idx);
			numberArrSorted[idx] = new TestNumber(idx);
			
		}
		
	}

	@Test
	public void test() {
		Sort.mergeSort(intArr);
		Sort.mergeSort(floatArr);
		Sort.mergeSort(doubleArr);
		Sort.mergeSort(shortArr);
		Sort.mergeSort(longArr);
		Sort.mergeSort(comparArr);
		Sort.mergeSort(numberArr, new Comparator<TestNumber>() {

			@Override
			public int compare(TestNumber o1, TestNumber o2) {
				if(o1.getI() < o2.getI())
					return -1;
				if(o1.getI() > o2.getI())
					return 1;
				return 0;
				
			}
			
		});
		
		assertArrayEquals(intArr, intArrSorted);
		assertArrayEquals(floatArr, floatArrSorted, 0.0001f);
		assertArrayEquals(doubleArr, doubleArrSorted, 0.0001);
		assertArrayEquals(shortArr, shortArrSorted);
		assertArrayEquals(longArr, longArrSorted);
		assertArrayEquals(comparArr, comparArrSorted);
		assertArrayEquals(numberArr, numberArrSorted);
		
	}

}
