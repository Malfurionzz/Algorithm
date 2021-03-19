package RBTree;
public class OSTree <T extends Comparable<T>>{
    private OSTnode<T> root;
    private OSTnode<T> Nul;
    public static boolean BLACK=false;
    public static boolean RED=true;

    public OSTree(){
        Nul=new OSTnode<T>(null,false,null,null,null,null);
        root=Nul;
    }
    public class OSTnode <T extends Comparable<T>> {
        T key;
        Boolean color; //false means black, true means red
        OSTnode<T> right;
        OSTnode<T> left;
        OSTnode<T> parent;
        Integer size;

        public OSTnode(T key, boolean color, OSTnode parent, OSTnode left, OSTnode right,Integer size){
            this.key = key;
            this.color=color;
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.size= size;
        }
    }

    /**
     * to find the i-th smallest node in OSTree or subtree
     * @param x the root node
     * @param i
     * @return the node
     */
    public OSTnode select(OSTnode x,Integer i){
        Integer r= x.left.size+1;
        if (i==r)
            return x;
        else if (i<r)
            return select(x.left,i);
        else
            return select(x.right,i-r);
    }

    /**
     * get the rank of a node: the order when inOrder traverse
     *用归纳法，初始时求以x为根的子树中x的rank，每次循环上升一次，用归纳法证明最终求得 rank in OSTree。
     * @param x
     * @return A Integer, rank in OSTree
     */
    public Integer rank(OSTnode x){
        Integer r=x.left.size+1;
        OSTnode y=x;
        while (y!=root){
            if (y==y.parent.right){
                r=r+y.parent.left.size+1;
            }
            y=y.parent;
        }
        return r;
    }

}
