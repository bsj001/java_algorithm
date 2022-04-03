package com.bsj.calculator;

public class Calculator {
    public static void main(String[] args) {
        //测试
        String expression = "19+2*6-10";
        //创建两个栈
        ArrayStack numStack = new ArrayStack(10);
        ArrayStack operStack = new ArrayStack(10);
        //定义相关的变量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int result = 0;
        char ch = ' ';//将每次扫描得到的char保存到ch中
        String keepNumber = "";//保存多个数字
        //开始while循环扫描expression
        while(true){
            //依次得到expression的每一个字符
            ch = expression.substring(index,index+1).charAt(0);
            //判断ch是什么，然后做相关的处理
            if(operStack.isOper(ch)){
                //判断当前的符号栈是否为空
                if(!operStack.isEmpty()){
                    //判断优先级
                    if(operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        result = numStack.calculator(num1,num2,oper);
                        //把运算结果入数栈
                        numStack.push(result);
                        //把运算符入符号栈
                        operStack.push(ch);
                    }else{
                        operStack.push(ch);
                    }
                }else{
                    operStack.push(ch);
                }
            }else{//如果是数，则入数栈
//                numStack.push(ch - 48);
                //保存多位数字的思路：
                //1，当处理多位数时，不能发现是一个数就立即入栈，因为它可能是多位数
                //2，在处理数，需要向expression的表达式index后再看一位，如果是数就进行扫描，如果是符号就入栈
                //3，因此我们需要定义一个变量字符串，用于拼接

                //处理多位数
                keepNumber += ch;
                //判断
                if(index == expression.length()-1){
                    numStack.push(Integer.parseInt(keepNumber));
                }else{
                    if(operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                        numStack.push(Integer.parseInt(keepNumber));
                        keepNumber = "";
                    }
                }


            }
            index++;
            if(index >= expression.length()){
                break;
            }
        }
        //当扫描完毕，顺序从数栈和符号栈中取数据运算
        while(true){
            if(operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            result = numStack.calculator(num1,num2,oper);
            //把运算结果入数栈
            numStack.push(result);
        }
        System.out.printf("表达式%s = %d\n",expression,numStack.pop());
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

    //返回当前栈顶的值，不改变栈
    public int peek(){
        return stack[top];
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

    //返回运算符的优先级，优先级是程序员来确定，优先级使用数字表示
    //数字越大，则优先级越高
    public int priority(int oper){
        if(oper == '*' || oper == '/'){
            return 1;
        }else if(oper == '+' || oper == '-'){
            return 0;
        }else{
            return -1;//假定目前的表达式只有+,-,*,/
        }
    }
    //判断是不是一个运算符
    public boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }
    //计算
    public int calculator(int num1,int num2,int oper){
        int value = 0;
        switch(oper){
            case '+':
                value = num1 + num2;
                break;
            case '-':
                value = num2 - num1;
                break;
            case '*':
                value = num1 * num2;
                break;
            case '/':
                value = num2 / num1;
                break;
            default:
                break;
        }
        return value;
    }
}