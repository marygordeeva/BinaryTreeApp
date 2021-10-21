package com.company.entity;

public class BinaryTreeNodeWithParent {
     private BinaryTreeNode TreeNode;
     private BinaryTreeNode Parent;


    public BinaryTreeNodeWithParent(BinaryTreeNode treeNode, BinaryTreeNode parent) {
        TreeNode = treeNode;
        Parent = parent;
    }

    public BinaryTreeNode getTreeNode() {
        return TreeNode;
    }

    public void setTreeNode(BinaryTreeNode treeNode) {
        TreeNode = treeNode;
    }

    public BinaryTreeNode getParent() {
        return Parent;
    }

    public void setParent(BinaryTreeNode parent) {
        Parent = parent;
    }
}
