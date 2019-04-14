package cn.code.collection.tree;

import org.w3c.dom.Node;

import java.sql.Array;
import java.util.ArrayList;

/**
 * AVL树是高度平衡的而二叉树。它的特点是：AVL树中任何节点的两个子树的高度最大差别为1。
 */
public class AVLTree<T extends Comparable<T>> {
    private  int size = 0 ;
    private AVLTreeNode<T> mroot;
    class AVLTreeNode<T extends Comparable<T>>{
        T key;  //关键值
        int height; //高度
        AVLTreeNode<T> left; //左孩子
        AVLTreeNode<T> right; //右孩子

        public AVLTreeNode(T key, AVLTreeNode<T> left, AVLTreeNode<T> right) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.height = 1 ;
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
        //记得先更新y的高度值,再更新x的高度值,因为x的高度值取决于y
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
     *
     * 也叫leftRotate
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
    public void add(T key){
        mroot = add(mroot,key);
    }
    /**
     * 添加新的节点
     * @param node
     * @param key
     * @return
     */
    public AVLTreeNode<T> add(AVLTreeNode<T> node,T key){
        if(node == null){
            size++;
            return new AVLTreeNode<T>(key,null,null);
        }
        if(key.compareTo(node.key)<0){
            node.left = add(node.left,key);
        }else if(key.compareTo(node.key)>0){
            node.right = add(node.right,key);
        }else {
            System.out.println("这个值已经存在了");
        }
        //更新自己的高度
        node.height = 1+Math.max(height(node.left),height(node.right));
        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        //LL
        if(balanceFactor>1&&getBalanceFactor(node.left)>=0){
            return leftLeftRotation(node);
        }
        //RR
        if(balanceFactor<-1&&getBalanceFactor(node.right)<=0){
            return rightRightRotation(node);
        }
        //LR
        if(balanceFactor>1&&getBalanceFactor(node.left)<0){
            return leftRightRotation(node);
        }
        //RL
        if(balanceFactor<-1&&getBalanceFactor(node.right)>0){
            return rightLeftRotation(node);
        }

        return node;
    }


    /**
     * 判断一棵树是否为二叉搜索树
     * @return
     */
    public boolean isBST(){
        ArrayList<T> keys = new ArrayList<>();
        inOrder(mroot,keys);
        for(int i= 1 ;i<keys.size();i++){
            if(keys.get(i-1).compareTo(keys.get(i))>0){
                return false;
            }
        }
        return true;
    }

    //中序遍历,并且存放在数组中
    private void inOrder(AVLTreeNode<T> node ,ArrayList<T> keys){
        if(node == null)
            return;
        inOrder(node.left,keys);
        keys.add(node.key);
        inOrder(node.right,keys);
    }

    /**
     * 判断一颗树是不是平衡二叉树
     * @return
     */
    public boolean isBalanced(){
        return isBalanced(mroot);
    }

    /**
     *
     * @param node
     * @return
     */
    private boolean isBalanced(AVLTreeNode<T> node){
        if(node == null){
            return true;
        }
        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor)>1){
            //平衡因子大于1
            return false;
        }
        return isBalanced(node.left)&&isBalanced(node.right);
    }

    /**
     * 计算平衡因子
     * @param node
     * @return
     */
    private int getBalanceFactor(AVLTreeNode<T> node){
        if(node == null){
            return 0;
        }
        return height(node.right)-height(node.left);
    }
}
