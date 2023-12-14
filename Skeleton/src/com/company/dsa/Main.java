package com.company.dsa;

public class Main {
    
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTreeImpl<>(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);
        tree.insert(72);
        tree.insert(71);
    tree.remove(40);
    tree.inOrder();
        System.out.println(tree);
    }
    
}
