package org.example;

import lombok.Data;

import java.util.Scanner;

/*
* Бинарное дерево поиска
* Содержит в себе логику работы приложения (поиск животного / добавление животного)
* */
@Data
public class BTree {

    private Node rootNode;
    private Scanner sc;
    private Node currentNode;

    /*
    * Создание дерева по умолчанию
    * животные - "кот" и "кит"
    * вопрос - "живет на суше?"
    * */
    public BTree() {
        this.sc = new Scanner(System.in);
        this.rootNode = new Node("Живет на суше?", null);
        Node cat = new Node("кот", rootNode);
        Node whale = new Node("кит", rootNode);
        this.rootNode.setChildren(cat, whale);
        this.currentNode = rootNode;
    }

    /*
    * При добавлении нового животного, мы должны так же добавить новый вопрос на место предыдущего животного и добавить само животное
    * */
    public void insertAnimal(String question, String answer){
        String tmp = currentNode.getValue();
        currentNode.setValue(question);

        Node left = new Node(answer, currentNode);
        Node right = new Node(tmp, currentNode);

        currentNode.setChildren(left, right);
    }

    public void startGame(BTree tree){
        System.out.println("Загадай животное, а я попробую угадать...");
        while(true){
            boolean answer = askQuestion(currentNode);
            if(answer && !currentNode.isLeaf()){
                //если ответили на вопрос да и у узла еще есть дочерние элементы, меняем currentNode и продолжаем угадывать
                currentNode = currentNode.getLeftChild();
            }else if(answer && currentNode.isLeaf()){
                //если ответили на вопрос да и у узла нет дочерних элементов, значит мы отгадали животное, предлагаем сыграть еще раз
                System.out.println("Сыграем еще раз ?");
                if(sc.nextLine().trim().equals("да")) {
                    currentNode = rootNode;
                    startGame(tree);
                } else break;
            }else if(!answer && !currentNode.isLeaf()){
                //если ответили на вопрос нет и у узла еще есть дочерние элементы, меняем currentNode и продолжаем угадывать
                currentNode = currentNode.getRightChild();
            }else if(!answer && currentNode.isLeaf()){
                //если ответили на вопрос нет и у узла нет дочерних элементов, значит мы не отгадали животное, добавляем животное и предлагаем сыграть еще раз
                System.out.println("Какое животное ты загадал ?");
                String animal = sc.nextLine();
                System.out.println("Чем " + animal + " отличается от " + currentNode.getValue());
                String question = sc.nextLine();
                tree.insertAnimal(question, animal);

                System.out.println("Сыграем еще раз ?");
                if(sc.nextLine().trim().equals("да")) {
                    currentNode = rootNode;
                    startGame(tree);
                } else break;
            }
        }
        System.out.println("Спасибо, что сыграли. Пока пока");
        System.exit(0);
    }

    public boolean askQuestion(Node node){
        if(node.isLeaf()){
            System.out.println("Вы загадали " + node.getValue() + "?");
        }else{
            System.out.println(node.getValue());
        }
        return sc.nextLine().trim().equals("да");
    }
}