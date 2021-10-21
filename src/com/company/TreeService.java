package com.company;

import com.company.entity.BinaryTreeNode;
import com.company.entity.BinaryTreeNodeWithParent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeService {

    BinaryTreeNode root;

    public boolean insert(int value) {
        BinaryTreeNode newNode = new BinaryTreeNode(value);
        if (root == null) {
            root = newNode;
            System.out.println("insert true for " + value);
            return true;
        }
        BinaryTreeNode existValue = search(value);
        BinaryTreeNode parent;
        BinaryTreeNode current;
        current = root;

        if (existValue != null) {
            System.out.println("Element is exist");
            return false;
        }

        while (true) {

            if (value > current.getValue()) {
                parent = current.getRight();
                if (parent == null) {
                    current.setRight(newNode);
                    System.out.println("insert true for " + value);
                    return true;
                } else {
                    current = parent;
                }
            } else {
                parent = current.getLeft();
                if (parent == null) {
                    current.setLeft(newNode);
                    System.out.println("insert true for " + value);
                    return true;
                } else {
                    current = parent;
                }
            }
        }
    }

    public void delete(Integer deleteValue) {
        BinaryTreeNode parentLeft = null;
        BinaryTreeNode parentRight = null;
        if (root == null) {
            return;
        }
        BinaryTreeNode current = root;

        //поиск элемента в дереве
        while (deleteValue != current.getValue()) {
            parentLeft = null;
            parentRight = null;
            if (deleteValue > current.getValue()) {
                parentRight = current;
                current = current.getRight();
            } else {
                parentLeft = current;
                current = current.getLeft();
            }

            if (current == null) {
                return;
            }
        }

        // 1. нет потомков
        if(DeleteNodeNoChildren(parentLeft, parentRight, current)){
            System.out.println("Delete successful " + deleteValue);
            return;
        }

        // 2. всего 1 потомок
        if(DeleteTreeWithOneChildren(parentLeft, parentRight, current)){
            System.out.println("Delete successful for value " + deleteValue);
            return;
        }

        //2 потомка
        //проверка потомков
        // поиск преемника
        //подстановка преемника на место предка
        BinaryTreeNode leftChildren = current.getLeft();
        BinaryTreeNode rightChildren = current.getRight();

        //1 вариант если у левого потомка нет правого потомка
        if(leftChildren.getRight() == null){
            if (parentLeft != null){
                parentLeft.setLeft(leftChildren);
                leftChildren.setRight(rightChildren);
            }else if(parentRight != null){
                parentRight.setRight(leftChildren);
                leftChildren.setRight(rightChildren);
            }else{
                root = leftChildren;
                leftChildren.setRight(rightChildren);
            }
            return;
        }

        //2 если у левого потомка есть правый потомок
        //find max по дереву leftChildren
        var maxLeftChWithParent = GetMaxByBinaryTreeNode(leftChildren);
        BinaryTreeNode parent = maxLeftChWithParent.getParent();
        BinaryTreeNode treeNode = maxLeftChWithParent.getTreeNode();

        if(treeNode.getLeft() == null){
            parent.setRight(null);
            treeNode.setRight(rightChildren);
            treeNode.setLeft(leftChildren);
            //todo не работает удаление с 2 потомками в верхних уровнях

        }else{
            BinaryTreeNode leftMaxLeftCh = treeNode.getLeft();
            parent.setRight(leftMaxLeftCh);
        }
    }

    private BinaryTreeNodeWithParent GetMaxByBinaryTreeNode(BinaryTreeNode treeNode){
        BinaryTreeNode current = treeNode;
        BinaryTreeNode next = null;
        BinaryTreeNode parent = treeNode;

        while(current != null){
//            parent = current;
            parent = next;
            next = current;

            current = current.getRight();
        }

        return new BinaryTreeNodeWithParent(next, parent);
    }

    private boolean DeleteTreeWithOneChildren(BinaryTreeNode parentLeft, BinaryTreeNode parentRight, BinaryTreeNode current) {
        if(current.getLeft() == null && current.getRight() != null
                || current.getLeft() != null && current.getRight() == null){

           if(parentLeft != null){
               if(current.getLeft() != null){
                   parentLeft.setLeft(current.getLeft());
               }else{
                   parentLeft.setLeft(current.getRight());
               }
           }else if(parentRight != null){
               if(current.getLeft() != null){
                   parentRight.setRight(current.getLeft());
               }else{
                   parentRight.setRight(current.getRight());
               }
           }else{
               if(current.getLeft() != null){
                   root = current.getLeft();
               }else{
                   root = current.getRight();
               }
           }
           return true;
        }
        return false;
    }

    private boolean DeleteNodeNoChildren(BinaryTreeNode parentLeft, BinaryTreeNode parentRight, BinaryTreeNode current) {
        if(current.getRight() == null && current.getLeft() == null){
            if(parentLeft != null){
                parentLeft.setLeft(null);
            }else{
                parentRight.setRight(null);
            }
            return true;
        }
        return false;
    }

    public BinaryTreeNode search(Integer searchValue) {
        System.out.println("Start binary tree search");
        if (root == null) {
            return null;
        }
        BinaryTreeNode current = root;

        while (searchValue != current.getValue()) {
            if (searchValue > current.getValue()) {
                current = current.getRight();
            } else {
                current = current.getLeft();
            }

            if (current == null) {
                return null;
            }
        }
        return current;
    }

    public void printTreeDeep() {
        Stack<BinaryTreeNode> bst = new Stack<>();
        String outString = new String();
        bst.push(root);

        while (!bst.isEmpty()) {
            BinaryTreeNode node = bst.pop();

            outString += " " + node.getValue().toString();

            if (node.getRight() != null) {
                bst.push(node.getRight());
            }

            if (node.getLeft() != null) {
                bst.push(node.getLeft());
            }
        }

        System.out.println("Обход в глубину: " + outString);
    }

    public void PrintBinaryTreeWide() {
        ArrayList<Integer> lists = new ArrayList<Integer>();

        Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode tree = queue.poll();
            if (tree.getLeft() != null)
                queue.offer(tree.getLeft());
            if (tree.getRight() != null)
                queue.offer(tree.getRight());
            lists.add(tree.getValue());
        }

        System.out.println("Обход в ширину: " + lists);
    }
}
