package com.bsj.avlTree;

public class AvlTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {4,3,6,5,7,8};
//        int[] arr = {10,12,8,9,7,6};
        int[] arr = {10,11,7,6,8,9,12,13,14};
        AVLTree avlTree = new AVLTree();
        for(int a:arr){
            avlTree.add(new Node(a));
        }

        avlTree.infixOrder();



        System.out.println("树的高度"+avlTree.getRoot().height());
        System.out.println("左子树的高度"+avlTree.getRoot().leftHeight());
        System.out.println("右子树的高度"+avlTree.getRoot().rightHeight());
        System.out.println("当前树的根节点是："+avlTree.getRoot());
        System.out.println("当前树的根节点的右节点是："+avlTree.getRoot().right);
        System.out.println("当前树的根节点的左节点是："+avlTree.getRoot().left);
    }
}

class AVLTree{
    private Node root;

    public Node getRoot() {
        return root;
    }

    //添加节点的方法
    public void add(Node node){
        if(root == null){
            root = node;//如果为空，则让root直接指向node
        }else{
            root.add(node);
        }
    }
    //中序遍历
    public void infixOrder(){
        if(root != null){
            root.infixOrder();
        }else{
            System.out.println("二叉树为空，不能遍历");
        }
    }

    /*
     * 查找要删除的节点
     * */
    public Node search(int value){
        if(root == null){
            return null;
        }else{
            return root.search(value);
        }
    }

    /*
     * 查找要删除节点的父节点
     * */
    public Node searchParent(int value){
        if(root == null){
            return null;
        }else{
            return root.searchParent(value);
        }
    }

    /*
     * 删除节点
     * */
    public void delNode(int value){
        if(root == null){
            return ;
        }

        //1,需要先去找到要删除的节点 targetNode
       Node targetNode = search(value);
        if(targetNode == null){
            return;
        }
        //如果我们发现当前这颗二叉排序树只有一个节点
        if(root.left == null && root.right == null){
            root = null;
            return;
        }

        //去找到targetNode的父节点
        Node parentNode = searchParent(value);
        if(targetNode.left == null && targetNode.right == null){
            //判断targetNode是父节点的左子节点，还是右子节点
            if(parentNode.left != null && parentNode.left.value == value){//是左子树
                parentNode.left = null;
            }else if(parentNode.right != null && parentNode.right.value == value){//是右子树
                parentNode.right = null;
            }
        }else if(targetNode.left != null && targetNode.right != null){//删除有两颗子树的节点
            //从右子树找最小的，还可以从左子树找最大的
            int minValue = delRightTreeMin(targetNode.right);
            targetNode.value = minValue;
        }else{//删除只有一颗子树的节点
            //如果删除的子树有左子节点
            if(targetNode.left != null){
                if(parentNode != null){
                    //如果targetNode是parentNode的左子节点
                    if(parentNode.left.value == value){
                        parentNode.left = targetNode.left;
                    }else {// targetNode是parentNode的右子节点
                        parentNode.right = targetNode.left;
                    }
                }else {
                    root = targetNode.left;
                }
            }else{//如果删除的子树有右子节点
                if(parentNode != null){
                    //如果targetNode是parentNode的左子节点
                    if(parentNode.left.value == value){
                        parentNode.left = targetNode.right;
                    }else{ //如果targetNode是parentNode的右子节点
                        parentNode.right = targetNode.right;
                    }
                }else{
                    root = targetNode.right;
                }
            }
        }
    }

    /*
     * 编写方法：
     *   1，返回以node为根节点的二叉排序树的最小节点的值
     *   2，删除node为根节点的二叉排序树的最小节点
     * */
    public int delRightTreeMin(Node node){
        Node target = node;
        //循环的查找左子节点，就会找到最小值
        while(target.left != null){
            target = target.left;
        }

        //这时target就指向了最小节点
        //删除最小节点
        delNode(target.value);
        return target.value;
    }
}

class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //添加节点的方法
    //递归的形式添加节点，注意需求满足二叉树排序树的要求
    public void add(Node node){
        if(node == null){
            return ;
        }
        //判断传入的节点的值，和当前节点的值的大小比较
        if(node.value < this.value){
            if(this.left == null){
                this.left = node;
            }else{
                //递归的向左子树添加
                this.left.add(node);
            }
        }else{
            if(this.right == null){
                this.right = node;
            }else{
                //递归的向右子树添加
                this.right.add(node);
            }
        }



        //当添加一个节点后，如果 右子树的高度-左子树的高度 > 1 ,左旋转
        if(rightHeight()-leftHeight()>1){
            if(right != null && right.leftHeight() > right.rightHeight()){
                right.rightRotate();
                leftRotate();
            }else {
                leftRotate();
            }
            return;//必要的
        }
        //当添加一个节点后，如果 左子树的高度-右子树的高度 > 1 ,右旋转
        if(leftHeight()-rightHeight()>1){
            //如果它的左子树的右子树高度大于它的左子树的高度
            if(left != null && left.rightHeight() > left.leftHeight()){
                //先对当前节点的左节点  左旋转
                left.leftRotate();
                //再对当前节点进行右旋转
                rightRotate();
            }else{
                //直接进行右旋转即可
                rightRotate();
            }
        }
    }

    //中序遍历
    public void infixOrder(){
        if(this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right != null){
            this.right.infixOrder();
        }
    }

    /*
     * 查找要删除的节点
     *
     * */
    public Node search(int value){
        if(value == this.value){
            return this;
        }else if(value < this.value){
            if(this.left == null){
                return null;
            }else{
                return this.left.search(value);
            }
        }else{
            if(this.right == null){
                return null;
            }else{
                return this.right.search(value);
            }
        }
    }

    /*
     * 查找要删除节点的父节点
     * */
    public Node searchParent(int value){
        //如果当前节点就是要删除的节点的父节点，就返回
        if((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)){
            return this;
        }else{
            if(value < this.value && this.left != null){
                return this.left.searchParent(value);//向左子树递归查找
            }else if(value >= this.value && this.right != null){
                return this.right.searchParent(value);//向右子树递归查找
            }else{
                return null;//无父节点
            }
        }
    }
    //返回当前节点的高度，以该节点为根节点的树的高度
    public int height(){
        return Math.max(left==null?0:left.height(),right==null?0:right.height())+1;
    }
    //返回左子树的高度
    public int leftHeight(){
        if(left == null){
            return 0;
        }
        return left.height();
    }
    //返回右子树的高度
    public int rightHeight(){
        if(right == null){
            return 0;
        }
        return right.height();
    }

    /*
    * 左旋转
    * */
    private void leftRotate(){
        //创建新的节点，以当前根节点的值
        Node newNode = new Node(value);
        //把新的节点的左子树设置成当前节点的左子树
        newNode.left = left;
        //把新的节点的右子树设置成当前节点的右子树的左子树
        newNode.right = right.left;
        //把当前节点的值替换成右子节点的值
        value = right.value;
        //把当前节点的右子树设置成当前节点右子树的右子树
        right = right.right;
        //把当前节点的左子树设置成新的节点
        left = newNode;
    }
    //右旋转
    private void rightRotate(){
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }
}
