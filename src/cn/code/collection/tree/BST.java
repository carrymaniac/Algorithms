package cn.code.collection.tree;

/**
 * 二叉排序树
 * 左子树不空则均小于根节点,右子树不空则均大于根节点
 *
 */
public class BST<T extends Comparable<T>> {

    private Node<T> root;

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

    //节点前驱:查找"小于二叉树中数据值该结点"的"最大结点"。是该节点的左子树中的最大节点。
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
        //存放父节点
        Node<T> y = null;
        //存放当前节点
        Node<T> x = bst.root;

        while(x!=null){//当当前节点不为空
            y = x ;
            int cmp = node.key.compareTo(x.key);
            if(cmp<0){
                x = x.left;
            }else {
                x = x.right;
            }
        }
        //已经找到数的尽头,此时y为叶子节点,x为空
        node.parent = y ;
        if(y==null){
            bst.root = node ;//如果叶子节点为空,说明这颗树是空的,则将根节点设置为node
        }else {
            //比较node节点和y节点大小,按照大小插入位置
            int cmp = node.key.compareTo(y.key);
            if(cmp<0){
                y.left = node;
            }else {
                y.right = node;
            }
        }
    }
    public void insert(T key) {
        Node<T> z=new Node<T>(key,null,null,null);
        // 如果新建结点失败，则返回。
        if (z != null)
            insert(this, z);
    }

    /**
     * 删除节点
     * @param bst
     * @param node
     * @return
     *
     *
     * 没有左右子节点，可以直接删除
     * 存在左节点或者右节点，删除后需要对子节点移动
     * 同时存在左右子节点，不能简单的删除，但是可以通过和后继节点交换后转换为前两种情况
     */
    private Node<T> remove(BST<T> bst,Node<T> node){
        //TODO 理不清
        Node<T> x = null;
        Node<T> y = null;

        if((node.left==null)||(node.right==null))
            //若有一子节点不为空,则操作子节点即可,若两边皆为空,则操作node节点即可
            y=node;
        else
            //当删除的节点的两边皆不为空,则需要node的后继节点(比node大的最小值)来取代node的位置
            y = successor(node);

        //需要操作的节点是否有后续的节点,如果有 设置为x,下面进行x的父节点设置以及y的父节点的子节点设置
        if(y.left!=null){
            x=y.left;
        }else
            x=y.right;//技巧,若right也为null,则此时相当于设置上了x=nulll

        //判断一下x是不是空的,如果不为空,则需要将x的父节点设置为y的父节点
        if(x!=null){
            x.parent = y.parent;
        }
        //设置一下y的父节点的子节点
        if(y.parent==null){
            //y的父节点为空,则说明y是根节点,此时x若有值则设置上去,就算是null则刚好设置为空
            bst.root = x;
        }else if(y.parent.left==y){
            y.parent.left = x ;
        }else {
            y.parent.right = x;
        }

        //将y的值替换到需要删除的节点去
        if(y!=node){
            node.key = y.key;
        }
        return y;
    }
    private Node<T> delete(Node<T> node){
        if((node.left!=null)&&(node.right!=null)){
            //两边都有节点的情况,需要寻找后继节点
            Node<T> succeesor = successor(node);
            //将节点设置为后继节点
            node.key = succeesor.key;
            node = succeesor ;
        }

        Node<T> child;
        if(node.left!=null){
            child = node.left;
        }else{
            child = node.right;
        }

        if(child!=null){
            child.parent = node.parent;
        }
        if(node.parent==null){
            //node是根节点
            root = child;
        }else if(node.parent.left == node){
            node.parent.left = child;
        }else if(node.parent.right == node){
            node.parent.right = child;
        }
        return node;

    }
    public void remove(T key){
        Node<T> z,node;
        if((z=search(root,key))!=null){
            if((node = remove(this,z))!=null){
                //这一步其实进行的是回收操作;因为通过其他节点已经无法引用到该 node 节点了，所以 node 能被 GC 正常回收。
                node = null;
            }
        }
    }

    /**
     * 寻找该节点以下的最大值节点
     * @param node
     * @return
     */
    private Node<T> maximun(Node<T> node){
        if(node == null){
            return null;
        }
        while (node!=null){
            node = node.right;
        }
        return node;
    }

    /**
     * 寻找该节点以下的最小节点
     * @param node
     * @return
     */
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


