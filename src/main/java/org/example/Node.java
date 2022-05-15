package org.example;

import lombok.Data;

/*
 * Узел в бинарном дереве
 */
@Data
public class Node {

    /*
    * value - хранит либо вопрос, ответ на который да / нет, либо название животного
    * */
    private String value;

    /*
    * parent - указатель на родителя
    * */
    private Node parent;

    /*
    * leftChild - указатель на левого дочернего узла
    * если ответ на вопрос (value) - да, то leftChild указывает на следующий узел, который нам нужен
    * */
    private Node leftChild;

    /*
     * rightChild - указатель на правого ребенка
     * если ответ на вопрос (value) - нет, то leftChild указывает на следующий узел, который нам нужен
     * */
    private Node rightChild;

    /*
    * leaf - флаг, является ли узел листом (узел без дочерних узлов)
    * если узел является листом, значит он содержит ответ на вопрос(value - название животного)
    * в ином случае, он должен содержать вопрос(value - вопрос) и варианты ответа (left/rightChild)
    * */
    private boolean leaf;

    public Node() {}

    public Node(String value, Node parent) {
        this.parent = parent;
        this.value = value;
        this.leaf = true;
    }

    public void setChildren(Node left, Node right){
        this.setLeftChild(left);
        this.setRightChild(right);
        this.leaf = false;
    }
}
