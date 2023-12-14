package com.company.dsa.tests;

import com.company.dsa.BinarySearchTree;
import com.company.dsa.BinarySearchTreeImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeImplTests {
    private BinarySearchTree<Integer> testTree;

    @Test
    public void constructor_Should_CreateTreeWithSingleNode() {
        // Arrange, Act
        testTree = new BinarySearchTreeImpl<>(5);

        // Assert
        Assertions.assertEquals(5, testTree.getRootValue());
        Assertions.assertNull(testTree.getLeftTree());
        Assertions.assertNull(testTree.getRightTree());
    }

    @Test
    public void insert_Should_AddLeftNodeCorrectly_When_TreeHasRootOnly() {
        // Arrange
        testTree = new BinarySearchTreeImpl<>(5);

        // Act
        testTree.insert(4);

        // Assert
        Assertions.assertEquals(4, testTree.getLeftTree().getRootValue());
    }

    @Test
    public void insert_Should_AddRightNodeCorrectly_When_TreeHasRootOnly() {
        //Arrange
        testTree = new BinarySearchTreeImpl<>(5);

        // Act
        testTree.insert(7);

        // Assert
        Assertions.assertEquals(7, testTree.getRightTree().getRootValue());
    }

    @Test
    public void insert_Should_AddLeftNodeCorrectly_When_TreeHasMultipleNodes() {
        // Arrange
        prepareTestTree();

        // Act
        testTree.insert(25);

        // Assert
        Assertions.assertEquals(25, testTree.getLeftTree().getLeftTree().getRightTree().getRootValue());
    }

    @Test
    public void insert_should_AddRightNodeCorrectly_When_TreeHasMultipleNodes() {
        // Arrange
        prepareTestTree();

        // Act
        testTree.insert(65);

        // Assert
        Assertions.assertEquals(65, testTree.getRightTree().getLeftTree().getRightTree().getRootValue());
    }

    @Test
    public void search_Should_ReturnFalse_When_SingleNotMatchingNode() {
        // Act
        testTree = new BinarySearchTreeImpl<>(5);

        // Act, Assert
        Assertions.assertFalse(testTree.search(50));
    }

    @Test
    public void search_Should_ReturnFalse_When_NodeDoesNotExist() {
        // Arrange
        prepareTestTree();

        // Act, Assert
        Assertions.assertFalse(testTree.search(5));
    }

    @Test
    public void search_Should_ReturnTrue_When_SingleMatchingNode() {
        // Arrange
        testTree = new BinarySearchTreeImpl<>(5);

        // Act, Assert
        Assertions.assertTrue(testTree.search(5));
    }

    @Test
    public void search_Should_ReturnTrue_When_TreeHasMultipleNodes() {
        prepareTestTree();

        // Act, Assert
        Assertions.assertTrue(testTree.search(60));
    }


    @Test
    public void preOrder_Should_ReturnElementsInRightSequence() {
        // Arrange
        prepareTestTree();
        Integer[] expected = {50, 30, 20, 40, 70, 60, 80, 72, 71};

        // Act
        List<Integer> result = testTree.preOrder();

        // Assert
        Assertions.assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void inOrder_Should_ReturnElementsInRightSequence() {
        // Arrange
        prepareTestTree();
        Integer[] expected = {20, 30, 40, 50, 60, 70, 71, 72, 80};

        // Act
        List<Integer> result = testTree.inOrder();

        // Assert
        Assertions.assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void postOrder_Should_ReturnElementsInRightSequence() {
        // Arrange
        prepareTestTree();
        Integer[] expected = {20, 40, 30, 60, 71, 72, 80, 70, 50};

        // Act
        List<Integer> result = testTree.postOrder();

        // Assert
        Assertions.assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void bfs_Should_ReturnElementsInRightSequence() {
        // Arrange
        prepareTestTree();
        Integer[] expected = {50, 30, 70, 20, 40, 60, 80, 72, 71};

        // Act
        List<Integer> result = testTree.bfs();

        // Assert
        Assertions.assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void height_Should_ReturnZero_When_TreeHasRootOnly() {
        // Arrange
        testTree = new BinarySearchTreeImpl<>(5);

        // Act & Assert
        Assertions.assertEquals(0, testTree.height());
    }

    @Test
    public void height_Should_ReturnCorrectHeight_When_TreeHasMultipleNodes() {
        // Arrange
        prepareTestTree();

        // Act & Assert
        Assertions.assertEquals(4, testTree.height());
    }

    // Uncomment when implementing remove method
    @Test
    public void remove_Should_ReturnFalse_When_SingleNotMatchingItem() {
        // Arrange
        testTree = new BinarySearchTreeImpl<>(5);

        // act, Assert
        Assertions.assertFalse(testTree.remove(6));
    }

    @Test
    public void remove_Should_ReplaceRoot_When_ValueToBeRemovedIsInRoot() {
        // Arrange
        prepareTestTree();
        List<Integer> treeValuesAfterRemove = List.of(20, 30, 40, 60, 70, 71, 72, 80);

        // Act
        testTree.remove(50);

        // Assert
        Assertions.assertIterableEquals(treeValuesAfterRemove, inOrder(testTree));
    }

    @Test
    public void remove_Should_MaintainCorrectOrdering_When_ValuePresentAndHasNoChildren() {
        // Arrange
        prepareTestTree();
        testTree.insert(42);
        List<Integer> treeValuesAfterRemove = List.of(20, 30, 40, 50, 60, 70, 71, 72, 80);

        // Act
        testTree.remove(42);

        // Assert
        Assertions.assertIterableEquals(treeValuesAfterRemove, inOrder(testTree));
    }

    @Test
    public void remove_Should_MaintainCorrectOrdering_When_ValuePresentAndHasRightChildOnly() {
        // Arrange
        prepareTestTree();
        testTree.insert(42);
        List<Integer> treeValuesAfterRemove = List.of(20, 30, 42, 50, 60, 70, 71, 72, 80);

        // Act
        testTree.remove(40);

        // Assert
        Assertions.assertIterableEquals(treeValuesAfterRemove, inOrder(testTree));
    }

    @Test
    public void remove_Should_MaintainCorrectOrdering_When_ValuePresentAndHasLeftChildOnly() {
        // Arrange
        prepareTestTree();
        testTree.insert(39);
        List<Integer> treeValuesAfterRemove = List.of(20, 30, 39, 50, 60, 70, 71, 72, 80);

        // Act
        testTree.remove(40);

        // Assert
        Assertions.assertIterableEquals(treeValuesAfterRemove, inOrder(testTree));
    }

    @Test
    public void remove_Should_MaintainCorrectOrdering_WhenValuePresentAndHasBothChildren() {
        // Arrange
        prepareTestTree();
        List<Integer> treeValuesAfterRemove = List.of(20, 30, 40, 50, 60, 71, 72, 80);

        // Act
        testTree.remove(70);

        // Assert
        Assertions.assertIterableEquals(treeValuesAfterRemove, inOrder(testTree));
    }

    private void prepareTestTree() {
        testTree = new BinarySearchTreeImpl<>(50);
        testTree.insert(30);
        testTree.insert(20);
        testTree.insert(40);
        testTree.insert(70);
        testTree.insert(60);
        testTree.insert(80);
        testTree.insert(72);
        testTree.insert(71);
    }

    private List<Integer> inOrder(BinarySearchTree<Integer> tree){
        List<Integer> result = new ArrayList<>();
        if (tree.getLeftTree() != null) {
            result.addAll(tree.getLeftTree().inOrder());
        }
        result.add(testTree.getRootValue());
        if (tree.getRightTree() != null) {
            result.addAll(tree.getRightTree().inOrder());
        }
        return result;
    }
}