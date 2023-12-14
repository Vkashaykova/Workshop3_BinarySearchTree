package com.company.dsa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinarySearchTreeImpl<E extends Comparable<E>> implements BinarySearchTree<E> {
    private E value;
    private BinarySearchTreeImpl<E> left;
    private BinarySearchTreeImpl<E> right;

    public BinarySearchTreeImpl(E value) {
        this.value = value;
        left = null;
        right = null;
    }


    @Override
    public E getRootValue() {
        return value;
    }

    @Override
    public BinarySearchTree<E> getLeftTree() {
        return left;
    }

    @Override
    public BinarySearchTree<E> getRightTree() {
        return right;
    }

    @Override
    public void insert(E value) {
        if (value.compareTo(this.value) < 0) {
            if (left == null) {
                left = new BinarySearchTreeImpl<>(value);
            } else {
                left.insert(value);
            }
        } else {
            if (right == null) {
                right = new BinarySearchTreeImpl<>(value);
            } else {
                right.insert(value);
            }

        }
    }

    @Override
    public boolean search(E value) {
        return searchNode(this, value) != null;
    }

    private BinarySearchTreeImpl<E> searchNode(BinarySearchTreeImpl<E> node, E value) {
        if (node == null || node.value == null) {
            return null;
        }
        int comparison = value.compareTo(node.value);
        if (comparison == 0) {
            return node;
        } else if (comparison < 0 && left != null) {
            return searchNode(node.left, value);
        } else if (comparison > 0 && right != null) {
            return searchNode(node.right, value);
        }
        return null;
    }

    @Override
    public List<E> inOrder() {
        List<E> result = new ArrayList<>();
        if (left != null) {
            result.addAll(left.inOrder());
        }
        result.add(value);
        if (right != null) {
            result.addAll(right.inOrder());
        }
        return result;

    }

    @Override
    public List<E> postOrder() {
        List<E> result = new ArrayList<>();
        if (left != null) {
            result.addAll(left.postOrder());
        }
        if (right != null) {
            result.addAll(right.postOrder());
        }
        result.add(value);
        return result;


    }

    @Override
    public List<E> preOrder() {
        List<E> result = new ArrayList<>();
        result.add(value);
        if (left != null) {
            result.addAll(left.preOrder());
        }
        if (right != null) {
            result.addAll(right.preOrder());
        }

        return result;
    }

    @Override
    public List<E> bfs() {
        List<E> result = new ArrayList<>();
        Queue<BinarySearchTreeImpl<E>> queue = new LinkedList<>();
        if (this.value != null) {
            queue.offer(this);
        }
        while (!queue.isEmpty()) {
            BinarySearchTreeImpl<E> current = queue.poll();
            result.add(current.value);
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
        return result;
    }

    @Override
    public int height() {
        if (this.value == null) {
            return 0;
        }
        return calculateHeight(this) - 1;
    }

    private int calculateHeight(BinarySearchTreeImpl<E> node) {

        if (node == null) {
            return 0;
        } else {
            int leftHeight = calculateHeight(node.left);
            int rightHeight = calculateHeight(node.right);

            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    @Override
    public boolean remove(E value) {
        BinarySearchTreeImpl<E> parent = null;
        BinarySearchTreeImpl<E> needToRemove = find(value);
        if (needToRemove == null) {
            return false;
        }
        if (needToRemove != this) {
            parent = findParent(value, this);
        }
        removeNode(needToRemove, parent);
        return true;
    }

    private void removeNode(BinarySearchTreeImpl<E> node, BinarySearchTreeImpl<E> parent) {
        if (node.left != null && node.right != null) {
            BinarySearchTreeImpl<E> child = findMin(node.right);
            parent = findParent(child.value, this);
            node.value = child.value;

            removeNode(child, parent);

        } else {
            BinarySearchTreeImpl<E> child2 = (node.left != null) ? node.left : node.right;
            if (parent == null) {
                this.value = child2.value;
                this.left = child2.left;
                this.right = child2.right;
            } else if (parent.left == node) {
                parent.left = child2;
            } else {
                parent.right = child2;
            }
        }
    }

    private BinarySearchTreeImpl<E> findMin(BinarySearchTreeImpl<E> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private BinarySearchTreeImpl<E> find(E value) {
        return searchNode(this, value);
    }

    private BinarySearchTreeImpl<E> findParent(E value, BinarySearchTreeImpl<E> node) {
        if (node == null || node.value == null) {
            return null;
        }
        if ((node.left != null && node.left.value.equals(value)) || (node.right != null && node.right.value.equals(value))) {
            return node;
        } else if (value.compareTo(node.value) < 0) {
            return findParent(value, node.left);
        } else {
            return findParent(value, node.right);
        }
    }
}

