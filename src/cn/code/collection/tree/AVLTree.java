package cn.code.collection.tree;

/**
 * AVL树是高度平衡的而二叉树。它的特点是：AVL树中任何节点的两个子树的高度最大差别为1。
 */
public class AVLTree<T extends Comparable<T>> {
    private AVLTreeNode mroot;
    class AVLTreeNode<T extends Comparable<T>>{
        T key;  //关键值
        int height; //高度
        AVLTreeNode<T> left; //左孩子
        AVLTreeNode<T> right; //右孩子

        public AVLTreeNode(T key, AVLTreeNode<T> left, AVLTreeNode<T> right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 获取树的高度
     * @param node
     * @return
     */
    private int height(AVLTreeNode<T> node){
        if(node != null){
            return node.height;
        }
        return 0;
    }

    public int height(){
        return  height(mroot);
    }

    private int max(int a , int b ){
        return a>b?a:b;
    }

    /**
     * LL情况,LL：LeftLeft，也称为"左左"。
     * 插入或删除一个节点后，根节点的左子树的左子树还有非空子节点，导致"根的左子树的高度"比"根的右子树的高度"大2，导致AVL树失去了平衡。
     * 需要进行右旋
     右旋示意图：对节点y进行右旋
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
     * @param k2
     * @return 旋转后的根节点
     */
    private AVLTreeNode<T> leftLeftRotation(AVLTreeNode<T> k2){
        AVLTreeNode<T> k1;
        k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = max(height(k2.left),height(k2.right))+1;
        k1.height = max( height(k1.left), k2.height) + 1;
        return k1;
    }

    /**
     *  RR：RightRight，称为"右右"。插入或删除一个节点后，根节点的右子树的右子树还有非空子节点，
     *  导致"根的右子树的高度"比"根的左子树的高度"大2，导致AVL树失去了平衡。
     *  需要进行左旋
     *  左旋示意图：对节点x进行左旋(即以右子为轴,向左旋转)
     *      *     p                       p
     *      *    /                       /
     *      *   x                       y
     *      *  / \                     / \
     *      * lx  y      ----->       x  ry
     *      *    / \                 / \
     *      *   ly ry               lx ly
     * @param k2
     * @return
     */
    private AVLTreeNode<T> rightRightRotation(AVLTreeNode<T> k2){
        AVLTreeNode<T> k1;
        k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2 ;
        k2.height = max(height(k2.right),height(k2.left))+1;
        k1.height = max(height(k1.right),height(k1.left))+1;
        return k1;
    }

    /**
     * LR情况 需要先对k1进行一次RR左旋,再对K3进行一次LL右旋.
     *      *     k3                       k3                       k2
     *      *    /  \                     /                       /    \
     *      *   k1    d                 k2                      k1       k3
     *      *  / \                     / \                     /  \     /  \
     *      lk1  k2      ----->       k1  rk2    ------>      lk1  lk2 rk2  d
     *      *    / \                 / \
     *      *   lk2 rk2            lk1 lk2
     * @param k3
     * @return
     */
    private AVLTreeNode<T> leftRightRotation(AVLTreeNode<T> k3){
        k3.left = rightRightRotation(k3.left);

        return leftLeftRotation(k3);
    }

    private AVLTreeNode<T> rightLeftRotation(AVLTreeNode<T> k3){
        k3.right = leftLeftRotation(k3.right);

        return rightRightRotation(k3);
    }


    private AVLTreeNode<T> insert(AVLTreeNode<T> node,T key){
        if(node == null ){
            //已经循环到树的尽头
            node = new AVLTreeNode<T>(key,null,null);
            if(node==null){
                System.out.println("ERROR:creat avltree node failed!");
                return null;
            }
        }else {
            int cmp = key.compareTo(node.key);
            if(cmp<0){
                //小于根节点
                node.left =insert(node.left,key);
                //插入完成后,如果失衡,需要进行调节
                if(height(node.left)-height(node.right) == 2){
                    if(key.compareTo(node.left.key)<0){
                        //新插入的值是
                        node = leftLeftRotation(node);
                    }else
                        node = leftRightRotation(node);
                }
            }else if(cmp>0){

            }else{//cmp==0
                System.out.println("添加失败：不允许添加相同的节点！");
            }
        }
        node.height = max( height(node.left), height(node.right)) + 1;

        return node;
    }

}
