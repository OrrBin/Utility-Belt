package com.utilitybelt.dataStructures.trees.binaryTrees;

public interface BinaryTree<T,K> {

	public T get(K key);

	public T insert(K key, T value);
	
	public T delete(K key);
	
}
