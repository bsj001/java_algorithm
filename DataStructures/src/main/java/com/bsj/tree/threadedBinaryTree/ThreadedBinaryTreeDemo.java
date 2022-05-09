package com.bsj.tree.threadedBinaryTree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试中序遍历线索二叉树
        HNode root = new HNode(1, "a");
        HNode b = new HNode(3, "b");
        HNode c = new HNode(6, "c");
        HNode d = new HNode(8, "d");
        HNode e = new HNode(10, "e");
        HNode f = new HNode(14, "f");

        //
        root.setLeft(b);
        root.setRight(c);
        b.setLeft(d);
        b.setRight(e);
        c.setLeft(f);
        //测试线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNode();

//        HNode leftNode = e.getLeft();
//        HNode rightNode = e.getRight();
//        System.out.println("e节点的前驱节点是："+leftNode);
//        System.out.println("e节点的后继节点是："+rightNode);
        //遍历
        System.out.println("使用线索化的方式遍历二叉树：");
        threadedBinaryTree.threadedList();//8,3,10,1,14,6
    }
}

//创建二叉树
class ThreadedBinaryTree{
    private HNode root;
    //为了实现线索化，需要要给指向当前节点的前驱节点的指针
    //在递归进行线索化，pre总是保留前一个节点
    private HNode pre = null;
    //重载threadedNode
    public void threadedNode(){
        this.threadedNode(root);
    }
    /**
     * 编写对二叉树进行中序线索化的方法
     * */
    public void threadedNode(HNode node){
        //如果node == null,不能线索化
        if(node == null){//不能线索化
            return;
        }
        //1,先线索化左子树
        threadedNode(node.getLeft());
        //2,线索化当前节点
        //重点
        //处理当前节点的前驱节点
        //以8节点来理解
        //8节点的.left = null,8节点的.leftType = 1
        if(node.getLeft() == null){
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针的类型，指向前驱节点
            node.setLeftType(1);
        }

        //处理后继节点
        if(pre != null && pre.getRight() == null){
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            //修改前驱节点的右指针类型，指向当前节点
            pre.setRightType(1);
        }
        //每处理一个节点，让当前节点是下一个节点的前驱节点
        pre = node;
        //3,再索化右子树
        threadedNode(node.getRight());
    }



    public void setRoot(HNode root) {
        this.root = root;
    }

    //遍历线索化二叉树的方法
    public void threadedList(){
        //定义一个变量，存储当前遍历的节点，从root开始
        HNode node = root;
        while(node!=null){
            //循环的找到leftType==1的节点，第一个找到的是8节点
            //后面随着遍历而变化，因为当leftType==1时，说明该节点是按照线索化
            //处理后的有效节点
            while(node.getLeftType() == 0){
                node = node.getLeft();
            }
            //打印当前这个节点
            System.out.println(node);
            //如果当前节点的右指针指向的是后继节点，就一直输出
            while(node.getRightType() == 1){
                //就获取到当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }

            //替换这个遍历的节点
            node = node.getRight();
        }
    }



    //前序遍历查找
    public HNode preOrderSearch(int no){
        if(root != null){
            return root.preOrderSearch(no);
        }else {
            return null;
        }
    }
    //中序遍历查找
    public HNode infixOrderSearch(int no){
        if(root != null){
            return root.infixOrderSearch(no);
        }else {
            return null;
        }
    }
    //后序遍历查找
    public HNode postOrderSearch(int no){
        if(root != null){
            return root.postOrderSearch(no);
        }else {
            return null;
        }
    }

    //删除节点
    public void delNo(int no){
        if(root != null){
            if(root.getNo() == no){
                root = null;
            }else {
                root.delNode(no);
            }
        }else{
            System.out.println("空树，不需要删除");
        }
    }

}



//先创建HeroNode节点
class HNode{
    private int no;
    private String name;
    private HNode left;//默认为空
    private HNode right;//默认为空

    //说明
    //1，如果leftType == 0 表示指向的是左子树，如果1则表示指向前驱节点
    //2，如果rightType == 0 表示指向的是右子树，如果1则表示指向后继节点
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HNode getLeft() {
        return left;
    }

    public void setLeft(HNode left) {
        this.left = left;
    }

    public HNode getRight() {
        return right;
    }

    public void setRight(HNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    /*
     * 前序遍历查找
     * */
    public HNode preOrderSearch(int no){
        System.out.println("进入前序查找！！！");
        //先比较当前节点是不是
        if(this.no == no){
            return this;
        }
        //如果不是，则判断当前左子节点是否为空，如果不为空，则向左递归查找
        //如果左递归查找找到，返回
        HNode resNode = null;
        if(this.left != null){
            resNode = this.left.preOrderSearch(no);
        }
        if(resNode != null){//说明左子树找到
            return resNode;
        }


        //右
        if(this.right != null){
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }
    /*
     * 中序遍历查找
     * */
    public HNode infixOrderSearch(int no){
        //如果不是，则判断当前左子节点是否为空，如果不为空，则向左递归查找
        //如果左递归查找找到，返回
        HNode resNode = null;
        if(this.left != null){
            resNode = this.left.infixOrderSearch(no);
        }
        if(resNode != null){//说明左子树找到
            return resNode;
        }
        System.out.println("进入中序查找！！！");
        //先比较当前节点是不是
        if(this.no == no){
            return this;
        }

        //右
        if(this.right != null){
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }


    /*
     * 后序遍历查找
     * */
    public HNode postOrderSearch(int no){
        //如果不是，则判断当前左子节点是否为空，如果不为空，则向左递归查找
        //如果左递归查找找到，返回
        HNode resNode = null;
        if(this.left != null){
            resNode = this.left.postOrderSearch(no);
        }
        if(resNode != null){//说明左子树找到
            return resNode;
        }
        //右
        if(this.right != null){
            resNode = this.right.postOrderSearch(no);
        }
        if(resNode != null){
            return resNode;
        }

        System.out.println("进入后序查找！！！");
        //先比较当前节点是不是
        if(this.no == no){
            return this;
        }

        return resNode;
    }

    //删除节点
    public void delNode(int no){
        if(this.left != null && this.left.no == no){
            this.left = null;
            return;
        }
        if(this.right != null && this.right.no == no){
            this.right = null;
            return;
        }
        if(this.left != null){
            this.left.delNode(no);
        }
        if(this.right != null){
            this.right.delNode(no);
        }
        return;
    }
}