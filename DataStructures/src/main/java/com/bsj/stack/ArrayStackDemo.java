package com.bsj.stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        //测试
        ArrayStack stack = new ArrayStack(3);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while(loop){
            System.out.println("show:表示显示栈");
            System.out.println("exit:表示退出栈");
            System.out.println("push:表示入栈");
            System.out.println("pop:表示出栈");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch(key) {
                case "show":
                    stack.list();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                case "push":
                    System.out.println("请输入你要输入的数字");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try{
                        int result = stack.pop();
                        System.out.printf("出栈的顺序是：%d\n",result);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                default:
                    break;
            }
        }
    }
}


//定义一个 ArrayStack 表示栈
class ArrayStack{
    private int maxSize;
    private int[] stack;//数组，数组模拟栈，数据放在数组中
    private int top = -1;

    //构造器
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }
    //栈空
    public boolean isEmpty(){
        return top == -1;
    }
    //栈满
    public boolean isFull(){
        return top == maxSize-1;
    }

    //入栈
    public void push(int value){
        //判断是满
        if(isFull()){
            System.out.println("栈已经满了，不能再添加数据了");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop(){
        //判断是空
        if(isEmpty()){
            //因为有返回值，所以直接抛出异常
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }
    //遍历
    public void list(){
        //判断空
        if(isEmpty()){
            System.out.println("无数据");
            return;
        }
        for(int i = top;i>=0;i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}
