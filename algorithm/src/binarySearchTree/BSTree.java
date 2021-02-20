package binarySearchTree;

public class BSTree<T extends Comparable<T>> {
    private  BSTnode<T> root;

    public class BSTnode<T extends Comparable<T>>{
        T key;
        BSTnode<T> left;
        BSTnode<T> right;
        BSTnode<T> parent;

        public BSTnode(T key,BSTnode<T> parent,BSTnode<T> left,BSTnode<T> right){
            this.key=key;
            this.parent=parent;
            this.left=left;
            this.right=right;
        }
    }

    private void preOrder_r(BSTnode<T> tree) {
        if (tree != null) {
            System.out.println(tree.key + " ");
            preOrder_r(tree.left);
            preOrder_r(tree.right);
        }
    }
    public void preOrder() {
        preOrder_r(root);
    }

    private void inOrder_r(BSTnode<T> tree) {
        if (tree != null) {
            preOrder_r(tree.left);
            System.out.println(tree.key + " ");
            preOrder_r(tree.right);
        }
    }
    public void inOlder() {
        inOrder_r(root);
    }

    private void postOrder_r(BSTnode<T> tree) {
        if (tree != null) {
            preOrder_r(tree.left);
            preOrder_r(tree.right);
            System.out.println(tree.key + " ");
        }
    }
    public void postOlder() {
        inOrder_r(root);
    }
}

