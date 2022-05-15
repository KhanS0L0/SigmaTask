package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    @DisplayName("Basic tree structure test")
    public void struct() {
        BTree tree = new BTree();

        assertNotNull(tree.getRootNode());
        assertNotNull(tree.getRootNode().getLeftChild());
        assertNotNull(tree.getRootNode().getRightChild());

        assertNull(tree.getRootNode().getRightChild().getRightChild());
        assertNull(tree.getRootNode().getLeftChild().getLeftChild());

        assertFalse(tree.getRootNode().isLeaf());
        assertTrue(tree.getRootNode().getLeftChild().isLeaf());
        assertTrue(tree.getRootNode().getRightChild().isLeaf());

        assertEquals(tree.getRootNode().getLeftChild().getValue(), "кот");
        assertEquals(tree.getRootNode().getRightChild().getValue(), "кит");
    }

    @Test
    @DisplayName("Insert new node test")
    public void insert(){
        BTree tree = new BTree();
        tree.setCurrentNode(tree.getRootNode().getLeftChild());
        tree.insertAnimal("Ест бананы ?", "макака");
        Node tmp = tree.getRootNode().getLeftChild();

        assertNotNull(tmp);
        assertNotNull(tmp.getLeftChild());
        assertNotNull(tmp.getRightChild());

        assertFalse(tmp.isLeaf());
        assertTrue(tmp.getLeftChild().isLeaf());
        assertTrue(tmp.getRightChild().isLeaf());

        assertNull(tmp.getLeftChild().getLeftChild());
        assertNull(tmp.getRightChild().getRightChild());

        assertEquals(tmp.getValue(), "Ест бананы ?");
        assertEquals(tmp.getRightChild().getValue(), "кот");
        assertEquals(tmp.getLeftChild().getValue(), "макака");
    }
}