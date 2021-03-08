package RBTree;

import binarySearchTree.BSTree;

/**
 * @auther zsy
 * @date 2021/2/27-23:11
 */

public class RBTree<T extends Comparable<T>> {
    private RBTnode<T> root;
    private RBTnode<T> Nul;
    public static boolean BLACK=false;
    public static boolean RED=true;
    public RBTree(){
        Nul=new RBTnode<T>(null,false,null,null,null);
        root=Nul;
    }
    public class RBTnode<T extends Comparable<T>> {
        T key;
        boolean color; //false means black, true means red
        RBTnode<T> right;
        RBTnode<T> left;
        RBTnode<T> parent;

        public RBTnode(T key,boolean color, RBTnode parent,RBTnode left,RBTnode right){
            this.key = key;
            this.color=color;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        private void  setRed(){
            color=RED;
        }
        private void setBlack(){
            color=BLACK;
        }
    }
    public RBTnode<T> min(RBTnode tree) {
        if (tree == null) {
            return null;
        }
        if (tree.left != null)
            return min(tree.left);
        else return tree;
    }

    public RBTnode<T> min() {
        return min(root);
    }
    private void leftRotation(RBTnode x){
        RBTnode y=x.right;
        x.right=y.left;
        if (y.left!=Nul)
            y.left.parent=x;
        y.parent=x.parent;
        if(x.parent==Nul)
            root=y;
        else if (x==x.parent.left)
            x.parent.left=y;
        else
            x.parent.right=y;
        y.left=x;
        x.parent=y;
    }
    private void rightRotation(RBTnode y){
        RBTnode x=y.left;
        y.left=x.right;
        if (x.right!=Nul)
            x.right.parent=y;
        x.parent= y.parent;
        if (y.parent==Nul)
            root=x;
        else if (y.parent.left==y)
            y.parent.left=x;
        else y.parent.right=x;
        x.right=y;
        y.parent=x;
    }
    private void insertFixUp(RBTnode z){
        RBTnode y;
        while (z.parent.color==RED){
            if(z.parent==z.parent.parent.left) {
                y=z.parent.parent.right;
               if(y.color==RED){
                   z.parent.setBlack();
                   y.setBlack();
                   z=y.parent;
                   z.setRed();
               }else{
                   if(z==z.parent.right){
                        z=z.parent;
                        leftRotation(z);
                   }
                   z.parent.setBlack();
                   z.parent.parent.setRed();
                   rightRotation(z.parent.parent);
               }
            }else {
                y=z.parent.parent.left;
                if(y.color==RED){
                    z.parent.setBlack();
                    y.setBlack();
                    z=y.parent;
                    z.setRed();
                }else{
                    if(z==z.parent.left){
                        z=z.parent;
                        rightRotation(z);
                    }
                    z.parent.setBlack();
                    z.parent.parent.setRed();
                    leftRotation(z.parent.parent);
                }
            }
        }
        root.setBlack();
    }
    private void insert(RBTnode x){
        RBTnode parentOfTmp=Nul;
        RBTnode tmp=root;
        while (tmp!=Nul)
        {
            parentOfTmp=tmp;
            if (x.key.compareTo(tmp.key)<0)
                tmp=tmp.left;
            else
                tmp=tmp.right;
        }
        x.parent=parentOfTmp;
        if (parentOfTmp==Nul){
            root=x;
        }else if (x.key.compareTo(parentOfTmp.key)<0){
            parentOfTmp.left=x;
        }else {
            parentOfTmp.right=x;
        }
        insertFixUp(x);
    }
    public void insert(T key){
        RBTnode<T> x=new RBTnode<T>(key,true,Nul,Nul,Nul);
        insert(x);
    }

    private void transplant(RBTnode<T> former, RBTnode<T> latter) {
        if (former.parent==Nul)
            root=latter;
        else if (former==former.parent.left)
            former.parent.left=latter;
        else
            former.parent.right=latter;
        latter.parent=former.parent;
    }
    public void delete(RBTnode<T> z){
        RBTnode y=z;
        RBTnode chosen;
        Boolean yOriginColor=z.color;
        if (z.left==Nul){
            chosen=z.right;
            transplant(z,z.right);
        }else if (z.right==Nul){
            chosen=z.left;
            transplant(z,z.left);
        }else {
            y=min(z.right);
            yOriginColor=y.color;
            chosen=y.right;
            if (y.parent==z)
                chosen.parent=y;
            else {
                transplant(y,y.right);
                y.right=z.right;
                y.right.parent=y;
            }
            transplant(z,y);
            y.left=z.left;
            y.left.parent=y;
            y.color=z.color;
        }
        if(yOriginColor==BLACK)
//            deleteFixUp()
            ;
    }

    private void print(RBTnode<T> tree, T key, int direction) {

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
        int[] a={9,8,7,6,5,4,3,2,1};
        RBTree tree=new RBTree();
        for (int i:a){
            tree.insert(i);
        }
        tree.print();
    }
}

