package cn.code.collection.tree;

public class RBTree<T extends Comparable<T>> {
    public RBNode<T> mRoot = null;    // 根结点
    public static boolean RED = true;
    public static boolean BLACK = false;
    class RBNode <T extends Comparable<T>> {
        //颜色
        boolean color;
        //关键字（键值）
        T key;
        //左子节点
        RBNode<T> left;
        //右子节点
        RBNode<T> right;
        //父节点
        RBNode<T> parent;

        public RBNode(T key, boolean color, RBNode<T> parent, RBNode<T> left, RBNode<T> right) {
            this.key = key;
            this.color = color;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public T getKey() {
            return key;
        }

        @Override
        public String toString() {
            return "" + key + (this.color == RED ? "RED" : "BLACK");
        }
    }

    /**
     * 左旋示意图：对节点x进行左旋(即以右子为轴,向左旋转)
     *     p                       p
     *    /                       /
     *   x                       y
     *  / \                     / \
     * lx  y      ----->       x  ry
     *    / \                 / \
     *   ly ry               lx ly
     * @param x
     */
    public void leftRotate(RBNode<T> x){
        if(x == null) return;
        //1.将y的左子节点赋给x的右子节点,将x赋值为y的左子节点的父亲
        RBNode<T> y = x.right;
        x.right = y.left;
        if(y.left!=null) {
            y.left.parent = x;
        }
        //2.将x的父节点p赋值为y的父节点,同时将p的子节点设置为y;
        y.parent = x.parent;
        if(x.parent==null){
            //即x为根节点
            this.mRoot = y;
        }else {
            if(x == x.parent.left){
                x.parent.left = y ;
            }else {
                x.parent.right = y ;
            }
        }
        //3.进行交接,将x和y关系对换
        y.left = x ;
        x.parent = y ;
    }

    /*************对红黑树节点y进行右旋操作 ******************/
    /*
     * 右旋示意图：对节点y进行右旋
     *        p                   p
     *       /                   /
     *      y                   x
     *     / \                 / \
     *    x  ry   ----->      lx  y
     *   / \                     / \
     * lx  rx                   rx ry
     * 右旋做了三件事：
     * 1. 将x的右子节点赋给y的左子节点,并将y赋给x右子节点的父节点(x右子节点非空时)
     * 2. 将y的父节点p(非空时)赋给x的父节点，同时更新p的子节点为x(左或右)
     * 3. 将x的右子节点设为y，将y的父节点设为x
     */
    public void rightRotate(RBNode<T> y) {
        RBNode x = y.left;
        //1.将x的右节点设置为y的左节点,并且将y赋值为x的右节点的父节点
        y.left = x.right;
        if(x.right!=null){
            x.right.parent = y;
        }
        //2.将y的父节点变成x的父节点,若y的父节点存在,将其子节点设置为x
        x.parent = y.parent;
        if(y.parent == null){
            //此时说明y本身就是根节点
            this.mRoot = x ;
        }else{
            if(y==y.parent.right){
                //y是p的右子节点
                y.parent.right = x ;
            }else{
                //y是p的左子节点
                y.parent.left = x ;
            }
        }
        //3.将x和y的父子关系进行互换
        x.right = y ;
        y.parent = x ;
    }
}
