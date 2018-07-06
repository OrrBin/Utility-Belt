package algorithms;

import java.util.Comparator;

public class Sort {

	//*******************************************************
	//	Merge-Sort
	//*******************************************************

	public static void mergeSort(int[] array) {

	}

	public static void mergeSort(float[] array) {

	}

	public static void mergeSort(double[] array) {

	}

	public static void mergeSort(short[] array) {

	}

	public static void mergeSort(long[] array) {

	}

	public static <T extends Comparable<?>> void mergeSort(T[] array) {

	}

	public static <T> void mergeSort(T[] array, Comparator<T> comperator) {

	}

	//*******************************************************
	//	Quick-Sort
	//*******************************************************

	public static void quickSort(int[] array) {

	}

	public static void quickSort(float[] array) {

	}

	public static void quickSort(double[] array) {

	}

	public static void quickSort(short[] array) {

	}

	public static void quickSort(long[] array) {

	}

	public static <T extends Comparable<?>> void quickSort(T[] array) {

	}

	public static <T> void quickSort(T[] array, Comparator<T> comperator) {

	}

	//*******************************************************
	//	Insertion-Sort
	//*******************************************************

	/**
	 * Sorting an array of integers according to the <i>Insertion-Sort algorithm</i>
	 * after calling this function the given array will be sorted in ascending order
	 * 
	 * @param array - int array to be sorted
	 */
	public static void insertionSort(int[] array) {
		if(array.length <= 1)
			return;
		int size = array.length;
		int key;
		for(int i = 1; i < size; i++) {
			key = array[i];
			int j = i - 1;
			while(j>=0 && key < array[j]) {
				array[j + 1] = array[j];
				j -= 1;
			}
			array[j + 1] = key;
		}
	}

	/**
	 * Sorting an array of floats according to the <i>Insertion-Sort algorithm</i>
	 * after calling this function the given array will be sorted in ascending order
	 * 
	 * @param array - float array to be sorted
	 */
	public static void insertionSort(float[] array) {
		if(array.length <= 1)
			return;
		int size = array.length;
		float key;
		for(int i = 1; i < size; i++) {
			key = array[i];
			int j = i - 1;
			while(j>=0 && key < array[j]) {
				array[j + 1] = array[j];
				j -= 1;
			}
			array[j + 1] = key;
		}
	}

	/**
	 * Sorting an array of doubles according to the <i>Insertion-Sort algorithm</i>
	 * after calling this function the given array will be sorted in ascending order
	 * 
	 * @param array - double array to be sorted
	 */
	public static void insertionSort(double[] array) {
		if(array.length <= 1)
			return;
		int size = array.length;
		double key;
		for(int i = 1; i < size; i++) {
			key = array[i];
			int j = i - 1;
			while(j>=0 && key < array[j]) {
				array[j + 1] = array[j];
				j -= 1;
			}
			array[j + 1] = key;
		}
	}

	/**
	 * Sorting an array of short according to the <i>Insertion-Sort algorithm</i>
	 * after calling this function the given array will be sorted in ascending order
	 * 
	 * @param array - short array to be sorted
	 */
	public static void insertionSort(short[] array) {
		if(array.length <= 1)
			return;
		int size = array.length;
		short key;
		for(int i = 1; i < size; i++) {
			key = array[i];
			int j = i - 1;
			while(j>=0 && key < array[j]) {
				array[j + 1] = array[j];
				j -= 1;
			}
			array[j + 1] = key;
		}
	}

	/**
	 * Sorting an array of longs according to the <i>Insertion-Sort algorithm</i>
	 * after calling this function the given array will be sorted in ascending order
	 * 
	 * @param array - long array to be sorted
	 */
	public static void insertionSort(long[] array) {
		if(array.length <= 1)
			return;
		int size = array.length;
		long key;
		for(int i = 1; i < size; i++) {
			key = array[i];
			int j = i - 1;
			while(j>=0 && key < array[j]) {
				array[j + 1] = array[j];
				j -= 1;
			}
			array[j + 1] = key;
		}
	}

	/**
	 * Sorting an array of Comparables according to the <i>Insertion-Sort algorithm</i>
	 * after calling this function the given array will be sorted in ascending order
	 * 
	 * @param array - int array to be sorted
	 */
	public static <T extends Comparable<T>> void insertionSort(T[] array) {
		if(array.length <= 1)
			return;
		int size = array.length;
		T key;
		for(int i = 1; i < size; i++) {
			key = array[i];
			int j = i - 1;
			while(j>=0 && key.compareTo(array[j]) == -1) {
				array[j + 1] = array[j];
				j -= 1;
			}
			array[j + 1] = key;
		}
	}

	/**
	 * Sorting an array of integers according to the <i>Insertion-Sort algorithm</i>
	 * after calling this function the given array will be sorted in ascending order
	 * 
	 * @param array - int array to be sorted
	 */
	public static <T> void insertionSort(T[] array, Comparator<T> comperator) {
		if(array.length <= 1)
			return;
		int size = array.length;
		T key;
		for(int i = 1; i < size; i++) {
			key = array[i];
			int j = i - 1;
			while(j>=0 && comperator.compare(key, array[j]) == -1) {
				array[j + 1] = array[j];
				j -= 1;
			}
			array[j + 1] = key;
		}
	}
}
