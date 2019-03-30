package cn.code.collection.tree;

/**
 * 二叉排序树
 * 左子树不空则均小于根节点,右子树不空则均大于根节点
 *
 */
public class BST<T extends Comparable<T>> {

    private Node root;

    private class Node<T extends Comparable<T>>{
        public Node<T> left;
        public Node<T> right;
        public Node<T> parent;
        public T key;
        public Node(T key,Node<T> left,Node<T> right,Node<T> parent){
            this.key = key;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }
    //前序遍历
    private void preOrder(Node node){
        if(node!=null){
            System.out.println(node.key+"");
            preOrder(node.left);
            preOrder(node.right);
        }
    }
    //中序遍历
    private void inOrder(Node node){
        if(node!=null){
            inOrder(node.left);
            System.out.println(node.key+"");
            inOrder(node.right);
        }
    }
    //后序遍历
    private void postOrder(Node node){
        if(node!=null){
            inOrder(node.left);
            inOrder(node.right);
            System.out.println(node.key+"");
        }
    }

    //节点前驱:查找"二叉树中数据值小于该结点"的"最大结点"。是该节点的左子树中的最大节点。
    public Node<T> predecessor(Node<T> node){
        if(node.left!=null){
            return maximun(node.left);
        }
        //若该节点没有左孩子
        //(1)node是一个右孩子,此时该点的前驱节点为该点的父节点
        //(2)node是一个左孩子,此时查找node的最低父节点,且该父节点要有右孩子
        Node<T> y = node.parent;
        while((y!=null)&&(node==y.left)){
            node = y;
            y = y.parent;
        //TODO
        }
        return y;
    }
    //节点后驱:查找"二叉树中数据值大于该结点"的"最小结点"。是该节点的右子树的最小节点
    public Node<T> successor(Node<T> node){
        if(node.right!=null){
            return minimun(node.right);
        }
        //若该节点没有右孩子
        //(1)node是一个左孩子,则node的后继节点为该点的父节点
        //(2)node是一个右孩子,此时查找node的最低父节点,且该父节点要有左孩子,这个最低的父节点就是x的后继节点
        Node<T> y = node.parent;
        while((y!=null)&&(node==y.right)){
        node = y;
        y =  y.parent;
        }
        return y;
    }
    /**
     * 查找(递归版本)
     * @param node 根节点
     * @param key 查找的值
     * @return
     */
    private Node<T> search(Node<T> node,T key){
        if(node==null){
            return null;
        }
        int cmp = key.compareTo(node.key);
        if(cmp<0){
            return search(node.left,key);
        }else if(cmp>0){
            return search(node.right,key);
        }else {
            return node;
        }
    }

    /**
     * 非递归版本的查找
     * @param node
     * @param key
     * @return
     */
    private Node<T> search1(Node<T> node,T key){
        while(node!=null){
            int cmp = key.compareTo(node.key);
            if(cmp<0){
                node = node.left;
            }else if(cmp>0){
                node = node.right;
            }else {
                return node;
            }
        }
        return node;
    }
    //插入
    private void insert(BST<T> bst,Node<T> node){
        Node<T> y = null;
        Node<T> x = bst.root;

        while(x!=null){
            y = x ;
            int cmp = node.key.compareTo(x.key);
            if(cmp<0){
                x = x.left;
            }else {
                x = x.right;
            }
        }
        node.parent = y ;
        if(y==null){
            bst.root = node ;
        }else {
            int cmp = node.key.compareTo(y.key);
            if(cmp<0){
                y.left = node;
            }else {
                y.right = node;
            }
        }
    }
    private Node<T> remove(BST<T> bst,Node<T> node){
        Node<T> x = null;
        Node<T> y = null;

        //TODO


        return null;
    }
    private Node<T> maximun(Node<T> node){
        if(node == null){
            return null;
        }
        while (node!=null){
            node = node.right;
        }
        return node;
    }

    private Node<T> minimun(Node<T> node){
        if(node == null){
            return  null;
        }
        while (node!=null){
            node = node.left;
        }
        return node;
    }


}


