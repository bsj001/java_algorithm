package com.bsj.stack;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/*
* 逆波兰表达式的计算
* 运用Stack
* */
public class PolandNotation {

    //完成将一个中缀表达式转成后缀表达式的功能
    //说明
    /**
     * 1,1+((2+3)*4)-5 => 转成 1 2 3 + 4 * + 5 -
     * 2,因为直接对str操作，不方便，因此先将 "1+((2+3)*4)-5" => 中缀的表达式对应的list
     *  即："1+((2+3)*4)-5" => ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
     * */

    public static void main(String[] args) {
        //表达式 (3+4)*5-6 => "3 4 + 5 * 6 -"  结果29
        //4*5-8+60+8/2 =>  4 5 * 8 - 60 + 8 2 / +
//        String suffixExpression = "3 4 + 5 * 6 -";
//        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        /*
        * 思路：
        *  1，先将"3 4 + 5 * 6 - "=》 放到ArrayList中
        *   2，将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算
        * */
//        List<String> rpnList = getListStrings(suffixExpression);
//        System.out.println("rpnList="+rpnList);

//        int res = calculate(rpnList);
//        System.out.println("计算的结果是：" + res);


        //中缀表达式
        String str = "1+((2+3)*4)-5";
        List<String> list = toInfixExpressionList(str);
//        System.out.println("转换后的list为："+list);
        List<String> result = parseSuffixExpressionList(list);
        System.out.println(result);

    }
    //将中缀表达式转成后缀表达式对应的list
    public static List<String> toInfixExpressionList(String s){
        //定义一个List，存放中缀表达式对应的内容
        List<String> ls = new ArrayList<>();
        int i = 0;//这是一个指针，用于遍历中缀表达式字符串
        String str;//对多位数的拼接
        char c;//每遍历一个字符，就放入到c
        do{
            //如果c是一个非数字，需要加入到ls
            if(((c=s.charAt(i))<48) ||((c=s.charAt(i)) > 57)){
                ls.add(""+c);
                i++;//后移
            }else{
                str = "";//先将str清空
                while(i<s.length() && ((c=s.charAt(i))>=48) && (c=s.charAt(i))<=57){
                    str += c;//拼接
                    i++;
                }
                ls.add(str);
            }
        }while(i<s.length());

        return ls;
    }

    //将得到的中缀表达式对应的List => 后缀表达式对应的List
    public static List<String> parseSuffixExpressionList(List<String> ls){
        //定义两个栈
        Stack<String> s1 = new Stack<String>();// 符号栈
        /**
         * 说明：因为s2这个栈，在整个转换过程中，没有pop操作，而且后面我们还需要逆序输出
         * 因此比较麻烦，这里我们就不用Stack<String>直接使用List<String> s2
         * Stack<String> s2 = new Stack<String>(); //存储中间结果的栈s2
         * */
        List<String> s2 = new ArrayList<String>();//存储中间结果的List2

        //遍历 ls
        for(String item:ls){
            //如果是一个数，加入s2
            if(item.matches("\\d")){
                s2.add(item);
            }else if(item.equals("(")){
                s1.push(item);
            }else if(item.equals(")")){
                //如果是右括号，则依次弹出s1栈顶的运算符，并压入s2,直到遇到左括号为止，此时将这一对括号丢弃
                while(!(s1.peek().equals("("))){
                    s2.add(s1.pop());
                }
                s1.pop();//消除括号
            }else {
                //比较优先级
                while(s1.size()!= 0 && Opreation.getValue(s1.peek()) >= Opreation.getValue(item)){
                    s2.add(s1.pop());
                }
                //将item压入栈
                s1.push(item);
            }
        }
        //将s1中剩下的运算符依次弹出并加入s2
        while(s1.size()!= 0){
            s2.add(s1.pop());
        }
        return s2;
    }
    //将一个逆波兰表达式，依次将数据和运算符放入到ArrayList
    public static List<String> getListStrings(String suffixExpression){
        //将 suffixExpression分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for(String ele : split){
            list.add(ele);
        }
        return list;
    }

    //完成逆波兰算法
    public static int calculate(List<String> ls){
        Stack<String> stack = new Stack<>();
        //遍历ls
        for(String item :ls){
            if(item.matches("\\d+")){
                //入栈
                stack.push(item);
            }else{
                //pop出两个数运算再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res;
                if(item.equals("+")){
                    res = num1 + num2;
                }else if(item.equals("-")){
                    res = num1 - num2;
                }else if(item.equals("*")){
                    res = num1 * num2;
                }else if(item.equals("/")){
                    res = num1/num2;
                }else{
                    throw new RuntimeException("输入有误");
                }
                //入栈
                stack.push("" + res);
            }
        }
        //返回最后一个数
        return Integer.parseInt(stack.pop());
    }
}

//定义一个运算符优先级的类
class Opreation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;


    //写一个方法，返回对应的优先级数字
    public static int getValue(String Opreation) {
        int result = 0;
        switch(Opreation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                break;
        }
        return result;
    }
}
