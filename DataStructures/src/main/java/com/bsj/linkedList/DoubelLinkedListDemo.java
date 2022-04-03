package com.bsj.linkedList;
import java.util.Stack;

public class DoubelLinkedListDemo {
    public static void main(String[] args) {
        //测试
        System.out.println("双向链表测试");
        //先创建节点
        HeroNode2 hero1 = new HeroNode2(1,"宋江","及时雨");
        HeroNode2 hero2 = new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3,"吴用","智多星");
        HeroNode2 hero4 = new HeroNode2(4,"林冲","豹子头");
        //创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        //显示
        doubleLinkedList.list();

        //修改
        HeroNode2 newHeroNode = new HeroNode2(4,"公孙胜","入云龙");
        System.out.println("修改后的链表情况");
        doubleLinkedList.update(newHeroNode);
        doubleLinkedList.list();

        System.out.println("删除后的链表情况");
        doubleLinkedList.del(1);
        doubleLinkedList.list();
    }

    /**
     *获取单链表的节点的个数（如果是带头节点的链表，需求不统计头节点）
     *  head链表的头节点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode2 head){
        if(head.next == null){
            return 0;
        }
        int length = 0;
        //定义一个辅助变量
        HeroNode2 cur = head.next;
        while(cur != null){
            length++;
            cur = cur.next;
        }
        return length;
    }

    /**
     * 查找单链表中的倒数第k个节点【新浪面试题】
     * 思路：
     *  1，编写一个方法，接收head节点，同时接收一个Index
     *  2,index表示是倒数第index个节点
     *  3，先把链表从头到尾遍历，得到链表的总的长度getLength
     *  4，得到size后，我们从链表的第一个开始遍历（size-index)个，就可以得到
     */
    public static HeroNode2 findLastIndexNode(HeroNode2 head, int index){
        //判断如果链表为空，返回null
        if(head.next == null){
            return null;//返回null
        }
        //第一次遍历，得到链表的总长度
        int size = getLength(head);
        //第二次遍历，size-head位置，就是我们倒数第k个节点
        //先做一个index的检验
        if(index < 0 || index > size){
            return null;
        }
        //定义一个辅助变量
        HeroNode2 temp = head.next;
        for(int i=0;i<size-index;i++){
            temp = temp.next;
        }
        return temp;


    }

    /**
     * 将单链表反转
     * */
    public static void reverseList(HeroNode2 head){
        if(head.next == null || head.next.next == null){
            return ;
        }
        //定义一个辅助的指针（变量），帮助我们遍历原来的链表
        HeroNode2 cur = head.next;
        HeroNode2 next = null;//指向当前节点【cur】的下一个节点
        HeroNode2 reverseHead = new HeroNode2(0,"","");

        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端
        while(cur != null){
            next = cur.next;//先暂时保存当前节点的下一个节点，因为后面需要使用
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur; //将
            cur = next;//让cur后移
        }
        //将head.next 指向reverseHead.next,实现单链表的反转
        head.next = reverseHead.next;
    }

    /**
     * 单链表逆序打印
     * */
    public static void reversePrint(HeroNode2 head){
        //判断为空
        if(head.next == null){
            return ;//为空，无法打印
        }
        //创建栈
        Stack<HeroNode2> stack = new Stack<>();
        //创建辅助节点
        HeroNode2 cur = head.next;
        while(cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        //出栈
        while(stack.size()>0){
            System.out.println(stack.pop());
        }
    }

}

//定义SingleLinkedList 管理我们的英雄
class DoubleLinkedList {
    //先初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode2 head = new HeroNode2(0,"","");

    public HeroNode2 getHead(){
        return head;
    }

    //添加节点到单向链表
    //思路，当不考虑编号顺序时
    //1，找到当前链表的最后节点
    //2.将最后这个节点的next 指向新的节点
    public void add(HeroNode2 heroNode){
        //因为head节点不能动，因此我们需要一个辅助变量temp
        HeroNode2 temp = head;
        //遍历链表，找到最后
        while(true){
            //找到链表的最后
            if(temp.next == null){
                break;
            }
            //如果没有找到，将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //将最后的这个节点的next指向新的节点
        temp.next = heroNode;
        heroNode.pre = temp;
    }
    //第二种方式添加英雄时，根据排名将英雄插入到指定位置
    public void addOrderBy(HeroNode2 heroNode){
        //因为头节点不能支，需要辅助变量
        //因为单链表，因为找的temp是位于添加位置的前一个节点，否则插入不了
        HeroNode2 temp = head;
        boolean flag = false;//flag标志添加的编号是否存在，默认为false
        while (true){
            if(temp.next == null){//说明temp已经在链表的最后
                break;
            }
            if(temp.next.no > heroNode.no) {//位置找到，就在temp的后面插入
                break;
            }else if(temp.next.no == heroNode.no){//说明希望添加的heroNode的编号已然存在
                flag = true;//说明编号存在
                break;
            }
            temp = temp.next;//后移，遍历链表
        }
        if(flag){//说明要添加的节点已经存在，不能添加
            System.out.printf("要添加的节点已经存在%d,不能再添加了\n",heroNode.no);
        }else if(temp.next != null){
            temp.next = heroNode;
            heroNode.pre = temp;
            heroNode.next = temp.next;
            temp.next.pre = heroNode;
        }else {
            temp.next = heroNode;
            heroNode.pre = temp;
            heroNode.next = null;
        }
    }
    //修改节点的信息，根据no编号来修改，即no编号不能改
    //说明
    //1.根据newHeroNode 的no 来修改即可
    public void update(HeroNode2 newHeroNode){
        //判断是否为空
        if(head == null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode2 temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while(true){
            if(temp == null){
                break;//已经遍历完链表，未找到
            }
            if(temp.no == newHeroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if(flag){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else{
            System.out.printf("没有找到要修改的节点的编号%d\n",newHeroNode.no);
        }
    }
    //删除节点
    public void del(int no){
        HeroNode2 temp = head.next;
        boolean flag = false;
        while(true){
            if(temp == null){
                //未找到要删除的节点
                break;
            }
            if(temp.no == no){
                flag = true;
                break;
            }
            temp = temp.next;//temp后移，遍历
        }

        if(flag){
            if(temp.next != null){
                temp.pre.next = temp.next;
                temp.next.pre = temp.pre;
            }else{
                temp.pre.next = null;
            }
        }else{
            System.out.printf("未找到要删除的节点%d",no);
        }
    }

    //显示链表（遍历）
    public void list(){
        //判断链表为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //因为head节点不能动，因此我们需要一个辅助变量temp
        HeroNode2 temp = head.next;
        while(true){
            //判断是否到链表的最后
            if(temp == null){
                return;
            }
            //输出节点的信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }
}


//定义HeroNode,每个HeroNode对象就是一个节点
class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;//指向下一个节点
    public HeroNode2 pre;//指向上一个节点

    public HeroNode2(int hNo, String hName, String hNickName){
        this.no = hNo;
        this.name = hName;
        this.nickname = hNickName;
    }

    //为了显示方便，重写toString

    @Override
    public String toString() {
        return "HeroNode [no="+no+",name="+name+",nickname="+nickname+"]";
    }
}
