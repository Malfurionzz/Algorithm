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
}
