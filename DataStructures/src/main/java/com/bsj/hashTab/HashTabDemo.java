package com.bsj.hashTab;
import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);
        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while(true){
            //
            System.out.println("add,添加雇员");
            System.out.println("list,遍历雇员");
            System.out.println("find,查找雇员");
            System.out.println("delete,删除雇员");
            System.out.println("exit,退出系统");
            key = scanner.next();
            switch (key){
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的雇员的id");
                    int no = scanner.nextInt();
                    hashTab.findEmpById(no);
                    break;
                case "delete":
                    System.out.println("请输入要删除的雇员的id");
                    int num = scanner.nextInt();
                    hashTab.delete(num);
                    break;
                case "exit":
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}
//表示一个雇员
class Emp{
    public int id;
    public String name;
    public Emp next;//默认为空

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

//创建EmpLinkedList,表示链表
class EmpLinkedList{
    private Emp head;//默认为空
    public void add(Emp emp){
        //如果添加的是第一个雇员
        if(head == null){
            head = emp;
            return;
        }
        //如果不是第一个雇员
        Emp curEmp = head;
        while(true){
            if(curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;//后移
        }
        //添加
        curEmp.next = emp;
    }
    //遍历链表
    public void list(int no){
        if(head == null){
            System.out.println("第"+(no+1)+"条链表为空");
            return;
        }
        Emp curEmp = head;//辅助指针
        System.out.print("这是第"+(no+1)+"条链表");
        while(true){
            System.out.printf("=>id=%d,name=%s\t",curEmp.id,curEmp.name);
            if(curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }
    //根据id，查找雇员
    public Emp findEmpById(int id){
        //判断链表为空
        if(head == null){
            System.out.println("链表为空!!!");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while(true){
            if(curEmp.id == id){//找到了
                break;
            }
            //退出
            if(curEmp.next == null){
                curEmp = null;
                break;
            }
            //后移
            curEmp =  curEmp.next;
        }
        return curEmp;
    }
    //删除链表
    public void deleteEmpById(int id){
        if(head == null){
            //链表为空，无法删除
            System.out.println("该链表为空，不需要删除");
            return;
        }
        //创建辅助指针
        Emp curEmp = head;
        while (true){
            if(head.id == id){//清空链表
                head = null;
                break;
            }
            if(curEmp.next.id == id){
                curEmp = curEmp.next.next;
                System.out.printf("找到了雇员，该id=%d雇员已被删除\n",id);
            }
            //退出
            if(curEmp.next == null){
                //没有找到
                System.out.println("雇员不存在!!!");
                break;
            }
            curEmp = curEmp.next;
        }


    }
}
//创建HashTab 管理多条链表
class HashTab{
    private EmpLinkedList[] empLinkedListArray;
    private int size;

    //构造器
    public HashTab(int size){
        this.size = size;
        //初始化empLinkedListArray
        empLinkedListArray = new EmpLinkedList[size];
        for(int i=0;i<empLinkedListArray.length;i++){
            //初始化
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp){
        //根据员工id,得到该员工应该添加到哪条链表
        int empLinkedListNo = hashFun(emp.id);
        //将emp添加到对应的链表中
        empLinkedListArray[empLinkedListNo].add(emp);
    }
    //删除雇员
    public void delete(int id){
        int empLinkedListNo = hashFun(id);
        //删除
        empLinkedListArray[empLinkedListNo].deleteEmpById(id);
    }
    //根据输入的id,查找雇员
    public void findEmpById(int id){
        //使用散列函数确定到哪条链表查找
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNo].findEmpById(id);
        if(emp != null){//找到
            System.out.printf("在第%d条链表中找到雇员,id=%d\n",(empLinkedListNo+1),id);
        }else {
            System.out.println("没有找到雇员!!!");
        }
    }
    //编写散列函数
    public int hashFun(int id){
        return id%size;
    }
    //遍历Hash表
    public void list(){
        for(int i=0;i<size;i++){
            empLinkedListArray[i].list(i);
        }
    }

}
