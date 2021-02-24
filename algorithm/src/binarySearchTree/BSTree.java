package binarySearchTree;


import java.time.Year;
import java.time.temporal.Temporal;
import java.util.Stack;
import java.util.zip.ZipEntry;

public class BSTree<T extends Comparable<T>> {
    private BSTnode<T> root;

    public class BSTnode<T extends Comparable<T>> {
        T key;
        BSTnode<T> left;
        BSTnode<T> right;
        BSTnode<T> parent;

        public BSTnode(T key, BSTnode<T> parent, BSTnode<T> left, BSTnode<T> right) {
            this.key = key;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }

    private void preOrder_r(BSTnode<T> tree) {
        if (tree != null) {
            System.out.println(tree.key + " ");
            preOrder_r(tree.left);
            preOrder_r(tree.right);
        }
    }

    private void preOrder_nr(BSTnode<T> tree) {
        Stack<BSTnode> stack = new Stack<>();
        stack.push(tree);
        while (!stack.isEmpty()) {
            BSTnode tmp = stack.pop();
            System.out.println(tmp.key + " ");
            if (tmp.right != null)
                stack.push(tmp.right);
            if (tmp.left != null)
                stack.push(tmp.left);
        }
    }

    public void preOrder() {
        preOrder_r(root);
        preOrder_nr(root);
    }

    private void inOrder_r(BSTnode<T> tree) {
        if (tree != null) {
            inOrder_r(tree.left);
            System.out.println(tree.key + " ");
            inOrder_r(tree.right);
        }
    }

    /*

    1)Push the current node to S and set current = current->left until current is NULL
    2) If current is NULL and stack is not empty then
         a) Pop the top item from stack.
         b) Print the popped item, set current = popped_item->right
         c) Go to step 1.
    3) If current is NULL and stack is empty then we are done.
     */
    private void inOrder_nr(BSTnode<T> tree) {
        Stack<BSTnode> stack = new Stack<>();
        BSTnode tmp = tree;
        do {
            while (tmp != null) {
                stack.push(tmp);
                tmp = tmp.left;
            }
            if (tmp == null && !stack.isEmpty()) {
                tmp = stack.pop();
                System.out.println(tmp.key + " ");
                tmp = tmp.right;
            }
        } while (tmp != null || !stack.isEmpty());
    }

    public void inOrder() {
        inOrder_r(root);
        inOrder_nr(root);
    }

    private void postOrder_r(BSTnode<T> tree) {
        if (tree != null) {
            postOrder_r(tree.left);
            postOrder_r(tree.right);
            System.out.println(tree.key + " ");
        }
    }

    /*
    1.1 Create an empty stack
    2.1 Do following while root is not NULL
        a) Push root's right child and then root to stack.
        b) Set root as root's left child.
    2.2 Pop an item from stack and set it as root.
        a) If the popped item has a right child and the right child
           is at top of stack(stack is not empty should considered otherwise exception throwed!), then remove the right child from stack,
           push the root back and set root as root's right child.
        b) Else print root's data and set root as NULL.
    2.3 Repeat steps 2.1 and 2.2 while stack is not empty.
     */
    private void postOrder_nr(BSTnode<T> tree) {
        Stack<BSTnode> stack = new Stack<>();
        BSTnode tmp = tree;
        do {
            while (tmp != null) {
                if (tmp.right != null)
                    stack.push(tmp.right);
                stack.push(tmp);
                tmp = tmp.left;
            }
            tmp = stack.pop();
            if (tmp.right != null && !stack.isEmpty() && stack.peek().equals(tmp.right)) {
                stack.pop();
                stack.push(tmp);
                tmp = tmp.right;
            } else {
                System.out.println(tmp.key + " ");
                tmp = null;
            }
        } while (!stack.isEmpty());
    }

    public void postOrder() {
        postOrder_r(root);
        postOrder_nr(root);
    }

    private BSTnode<T> search(BSTnode<T> tree, T key) {
        if (tree == null || tree.key.equals(key))
            return tree;
        if (key.compareTo(tree.key) < 0) {
            return search(tree.left, key);
        } else {
            return search(tree.right, key);
        }
    }

    public BSTnode<T> search(T key) {
        return search(root, key);
    }

    public BSTnode<T> min(BSTnode tree){
        if (tree==null){
            return null;
        }
        if (tree.left!=null)
            return min(tree.left);
        else return tree;
    }
    public BSTnode<T> min(){
       return min(root);
    }

    public BSTnode<T> max(BSTnode tree){
        if (tree==null){
            return null;
        }
        if (tree.right!=null)
            return max(tree.right);
        else return tree;
    }
    public BSTnode<T> max(){
        return max(root);
    }
    public BSTnode<T> successor(BSTnode x){
        if (x.right !=null)
            return min(x.right);
        if (x.parent.left==x){
            return x.parent;
        }
        BSTnode parentOfx=x.parent;
        while (parentOfx.right==x){
            x=parentOfx;
            parentOfx=x.parent;
        }
        return  parentOfx;
    }

    public BSTnode<T> predecessor(BSTnode x){
       if (x.left!=null)
            return max(x.left);
       if (x.parent.right==x){
           return x.parent;
       }
       BSTnode parentOfx=x.parent;
       while (parentOfx.left==x){
           x=parentOfx;
           parentOfx=x.parent;
       }
       return x;
    }
    private void insert(BSTree<T> tree, BSTnode<T> node) {
        BSTnode<T> y = null;
        BSTnode<T> x = tree.root;
        while (x != null) {
            y = x;
            if (node.key.compareTo(x.key) < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        node.parent = y;
        if (y == null) {//special case when tree is empty
            tree.root = node;
        } else if (node.key.compareTo(y.key) < 0) {
            y.left = node;
        } else {
            y.right = node;
        }
    }

    public void insert(T key) {
        BSTnode<T> node = new BSTnode<>(key, null, null, null);
        if (node != null) {
            insert(this, node);
        }
    }

    private void transplant(BSTnode<T> former,BSTnode<T> latter){
        if (root==former)
            root=latter;
        else if (former.parent.left==former)
            former.parent.left=latter;
        else
            former.parent.right=latter;
        if (latter!=null)
            latter.parent=former.parent;
    }
    public void delete(BSTnode<T> x){
    }

    private void print(BSTnode<T> tree, T key, int direction) {

        if (tree != null) {

            if (direction == 0)    // tree是根节点
                System.out.printf("%2d is root\n", tree.key);
            else                // tree是分支节点
                System.out.printf("%2d is %2d's %6s child\n", tree.key, key, direction == 1 ? "right" : "left");

            print(tree.left, tree.key, -1);
            print(tree.right, tree.key, 1);
        }
    }

    public void print() {
        if (root != null)
            print(root, root.key, 0);
    }

    public static void main(String[] args) {
        final int[] arr = new int[]{15,6,3,2,4,7,13,9,18,17,20};
        int i, ilen;
        BSTree<Integer> tree = new BSTree<>();

        System.out.print("== 依次添加: ");
        ilen = arr.length;
        for (i = 0; i < ilen; i++) {
            System.out.print(arr[i] + " ");
            tree.insert(arr[i]);
        }

        tree.print();
        System.out.println(tree.successor(tree.search(13)).key);
//        tree.postOrder();
//        System.out.println(tree.search(5).key);
    }
}

