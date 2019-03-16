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

    public void insert(T key){
        RBNode<T> node = new RBNode<>(key,BLACK,null,null,null);
        this.insert(node);
    }

    public void insert(RBNode<T> node){
        //node的父节点
        RBNode<T> current = null ;
        RBNode<T> x = mRoot;

        while(x!=null){
            current = x ;
            int cmp = node.key.compareTo(x.key);
            if(cmp<0){
                x = x.left;
            }else {
                x = x.right;
            }
        }
        //找到要插入的父节点的位置
        node.parent = current;
        //判断该插在左边还是右边
        if(current!=null){
            //和父节点进行比较
            int cmp =node.key.compareTo(current.key);
            if(cmp<0){
                //比父节点小
                current.left = node ;
            }else {
                current.right = node ;
            }
            node.color = RED;

        }else {
            this.mRoot = node ;
        }
    }

    /**
     * 插入后进行修复工作
     * @param node
     */
    public void insertFixUp(RBNode<T> node){
        RBNode<T> parent,gparent;//定义父节点,祖父节点
        //父节点不为空且父节点为红色
        while (((parent = node.parent)!=null)&&isRed(parent)){
            gparent = parent.parent;
            //若父节点在祖父节点的左边
            if(parent==gparent.left){
                RBNode<T> uncle = gparent.right;
                //case1.若叔叔为红色节点
                if(uncle!=null&&isRed(uncle)){
                    //此时将父,叔节点染黑,将祖父节点转为红,并且继续向上检查
                    parent.color = BLACK ;
                    uncle.color= BLACK;
                    gparent.color = RED;
                    node = gparent;
                    continue;
                }
                //case2.此时叔叔为黑,且该节点为父节点的右节点,即不是同一根线上
                if(node == parent.right){
                    leftRotate(parent);
                    RBNode<T> tmp = parent;
                    parent = node;
                    node = tmp;
                }
                //case3.此时叔叔为黑,且该节点为父节点的左节点,即在同一根线上
                parent.color = BLACK;
                gparent.color = RED;
                rightRotate(gparent);
            }else {
                //若父亲节点是祖父节点的右子节点，与上面的完全相反，本质一样的
                RBNode<T> uncle = gparent.left;
                //case1:叔叔节点也是红色
                if (uncle != null & isRed(uncle)) {
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    gparent.color = RED;
                    node = gparent;
                    continue;
                }

                //case2: 叔叔节点是黑色的，且当前节点是左子节点
                if (node == parent.left) {
                    rightRotate(parent);
                    RBNode<T> tmp = parent;
                    parent = node;
                    node = tmp;
                }
                //case3: 叔叔节点是黑色的，且当前节点是右子节点
                parent.color = BLACK;
                gparent.color = RED;
                leftRotate(gparent);
            }
        }
        //将根节点设置为黑色
        this.mRoot.color = BLACK;
    }


    boolean isRed(RBNode<T> parent) {
        if(parent.color == RED){
            return true;
        }else {
            return false;
        }
    }
}
