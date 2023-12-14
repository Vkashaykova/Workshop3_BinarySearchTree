package com.company.dsa;

import java.util.List;

public interface BinarySearchTree<E extends Comparable<E>> {
    
    E getRootValue();

    BinarySearchTree<E> getLeftTree();

    BinarySearchTree<E> getRightTree();

    void insert(E element);
    
    boolean search(E element);
    
    List<E> inOrder();
    
    List<E> postOrder();
    
    List<E> preOrder();
    
    List<E> bfs();
    
    int height();
    
    // Advanced task: implement remove method. To test, uncomment the commented tests in BinaryTreeImplTests
   boolean remove(E value);
}
