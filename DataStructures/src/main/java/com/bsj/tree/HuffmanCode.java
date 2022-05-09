package com.bsj.tree;
import java.io.*;
import java.util.*;
public class HuffmanCode {
    public static void main(String[] args) {
/*        String str = "i like like like java do you like a java";
        byte[] bytes = str.getBytes();
        System.out.println(bytes.length);//40

        byte[] huffmanCodeBytes = huffmanZip(bytes);
        System.out.println("编码后的赫夫曼编码是："+ Arrays.toString(huffmanCodeBytes));
//        System.out.println("长度是："+huffmanCodes.length);

        byte[] sourceBytes = decode(huffmanCodes, huffmanCodeBytes);
        System.out.print("原字符：");
        for(byte b: sourceBytes){
            System.out.print((char)b);
        }*/

//        List<HuffmanNode> nodes = getNodes(bytes);
//        System.out.println(nodes);
//
//        System.out.println("赫夫曼树");
//        HuffmanNode root = createHuffmanTree(nodes);
//        root.preOrder();
//
//
//        Map<Byte, String> codes = getCodes(root);
//        System.out.println("生成的HuffmanCode:"+codes);
//
//        byte[] zip = zip(bytes, huffmanCodes);
//        System.out.println("zip:"+Arrays.toString(zip));
        //测试压缩文件
//        String srcFile = "C:\\MyFile\\myCode\\java_algorithm\\DataStructures\\1.jpg";
//        String dstFile = "C:\\MyFile\\myCode\\java_algorithm\\DataStructures\\dst.zip";
//        zipFile(srcFile,dstFile);
//        System.out.println("压缩文件成功了");
        //测试解压文件
        String zipFile = "C:\\MyFile\\myCode\\java_algorithm\\DataStructures\\dst.zip";
        String dstFile = "C:\\MyFile\\myCode\\java_algorithm\\DataStructures\\1_unzip.jpg";
        unZipFile(zipFile,dstFile);
        System.out.println("解压文件成功！！！");

    }

    /*
    * 编写方法，将一个文件进行压缩
    *  srcFile 你传入的希望压缩的文件的全路径
    *  dstFile 我们压缩后将压缩文件放到哪个目录
    *
    * */

    public static void zipFile(String srcFile,String dstFile){
        //创建输入流
        FileInputStream inputStream = null;
        //创建输出流
        FileOutputStream outputStream = null;
        //
        ObjectOutputStream objectOutputStream = null;
        try{
            //创建文件的输入流
            inputStream = new FileInputStream(srcFile);
            //创建一个和原文件大小一样的byte[]
            byte[] bytes = new byte[inputStream.available()];
            //读取文件
            inputStream.read(bytes);
            //获取到文件对应的赫夫曼编码
            byte[] huffmanBytes = huffmanZip(bytes);
            //创建文件的输出流，存放压缩文件
            outputStream = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            objectOutputStream = new ObjectOutputStream(outputStream);
            //把赫夫曼编码后字节数组写入压缩文件
            objectOutputStream.writeObject(huffmanBytes);
            //这里我们以对象流的方式写入赫夫曼编码，是为了以后我们恢复源文件时使用
            //注意一定要把赫夫曼编码写入压缩文件
            objectOutputStream.writeObject(huffmanCodes);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try{
                inputStream.close();
                outputStream.close();
                objectOutputStream.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    /*
    * 文件的解压
    *   zipFile 要解压的文件
    *   dstFile 解压后的文件
    * */
    private static void unZipFile(String zipFile,String dstFile){
        //定义文件输入流
        InputStream inputStream = null;
        //定义一个对象输入流
        ObjectInputStream objectInputStream = null;
        //定义文件的输出流
        OutputStream outputStream = null;
        try{
            //创建文件输入流
            inputStream = new FileInputStream(zipFile);
            //创建一个和inputStream关联的对象输入流
            objectInputStream = new ObjectInputStream(inputStream);
            //读取byte数组 huffmanBytes
            byte[] huffmanBytes = (byte[]) objectInputStream.readObject();
            //读取huffman编码表
            Map<Byte,String> huffmanCodes =(Map<Byte,String>)objectInputStream.readObject();

            //解码
            byte[] decode = decode(huffmanCodes, huffmanBytes);
            //将bytes[]数组写入到目标文件
            outputStream = new FileOutputStream(dstFile);
            //写出数据到dstFile
            outputStream.write(decode);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            try{
                objectInputStream.close();
                outputStream.close();
                inputStream.close();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }


    /*
    * 数据的解压
    *   1,先把字节数组转成为二进制数组
    *   2,再把二进制数组对照成赫夫曼编码的字节数组
    * */
    //flag标志是否需要补高位如果是true,表示需要补高位，如果是false表示不补
    private static String byteToBitString(boolean flag,byte b){
        //使用变量保存b
        int temp = b;//将b转成int
        if(flag){
            temp |= 256;
        }
        String string = Integer.toBinaryString(temp);//返回的是temp对应的二进制的补码
        if(flag){
            return string.substring(string.length()-8);
        }else {
            return string;
        }
    }

    private static byte[] decode(Map<Byte,String> huffmanCodes,byte[] huffmanBytes){
        //1,先得到huffmanBytes对应的二进制的字符串，形式1010100010111...
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for(int i=0;i<huffmanBytes.length;i++){
            //判断是不是最后一个字节
            boolean flag = (i==huffmanBytes.length-1);
            stringBuilder.append(byteToBitString(!flag,huffmanBytes[i]));
        }
//        System.out.println("decode后的字符串："+stringBuilder);
        //把字符串安装指定的赫夫曼编码进行解码
        //把赫夫曼编码表进行调换，因为反向查询a->100 100->a
        Map<String,Byte> map = new HashMap<>();
        for(Map.Entry<Byte,String> entry : huffmanCodes.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }
//        System.out.println(map);
        //创建要给集合，存放byte
        List<Byte> list = new ArrayList<>();
        //i 可以理解成就是索引，stringBuilder
        for (int i = 0; i < stringBuilder.length();) {
            int count = 1;//小的计数器
            boolean flag = true;
            Byte b = null;
            while(flag) {
                //1010100010111...
                //递增的取出key
                String key = stringBuilder.substring(i, i + count);//i不动，让count移动，直到匹配到一个字符
                b = map.get(key);
//                System.out.print(key+" ");
                if (b == null) {//说明没有匹配到
                    count++;
                } else {
                    //匹配到
                    flag = false;
                }
            }
            list.add(b);
            i+=count;//i直接移动到count
        }
        //当for循环结束后，list中就存放了所有的字符
        //把list中的数据放到byte[]并返回
        byte[] b = new  byte[list.size()];
        for(int i=0;i<b.length;i++){
            b[i] = list.get(i);
        }

        return b;
    }


    //编写一个方法，将前面的方法封装起来，便于我们的调用。
    private static byte[] huffmanZip(byte[] bytes){
        List<HuffmanNode> nodes = getNodes(bytes);
        //根据nodes创建的赫夫曼树
        HuffmanNode root = createHuffmanTree(nodes);
        //对应的赫夫曼树
        Map<Byte, String> huffmanCodes = getCodes(root);
//        System.out.println(huffmanCodes);
        //根据生成的赫夫曼编码，压缩得不到压缩后的赫夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes,huffmanCodes);
        return huffmanCodeBytes;
    }

    //编写一个方法，将字符串对应的byte[]数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码压缩后的byte[]
    private static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodes){
        //1，利用huffmanCodes将bytes转成赫夫曼编码对应的字符串
        StringBuilder stringBuilder3 = new StringBuilder();
        //遍历bytes数组
        for(byte b:bytes){
            stringBuilder3.append(huffmanCodes.get(b));
        }

//        System.out.println("测试stringBuilder = "+ stringBuilder3.toString());
        //把stringBuilder3转成byte[]

        //统计返回byte[] huffmanCodeBytes长度
        //一句话 int len = (stringBuilder.length() + 7) /8
        int len;
        if(stringBuilder3.length() % 8 == 0){
            len = stringBuilder3.length() / 8;
        }else{
            len = stringBuilder3.length() / 8 + 1;
        }
        //创建存储压缩扣的byte数组
        byte[] by = new byte[len];
        int index = 0;//记录第几个byte
        for (int i = 0; i <stringBuilder3.length() ; i+=8) {//因为是每8位对应一个byte，所以步长+8
            String strByte;
            if((i+8)>stringBuilder3.length()){//不够8位
                strByte = stringBuilder3.substring(i);
            }else{
                strByte = stringBuilder3.substring(i,i+8);
            }
            by[index] = (byte)Integer.parseInt(strByte,2);
            index++;

        }
        return by;
    }


    public static void preOrder(HuffmanNode root){
        if(root != null){
            root.preOrder();
        }else {
            System.out.println("赫夫曼树为空!!!");
        }
    }

    //生成HuffmanTree对应的HuffmanCode
    //1,将HuffmanCode编码表存放到Map<Byte,String>
    static Map<Byte,String> huffmanCodes = new HashMap<>();
    //2,在生成HuffmanCode表示，需要去拼接路径，定义一个StringBuilder 存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    //重载getCodes
    private static Map<Byte,String> getCodes(HuffmanNode root){
        if(root == null){
            return null;
        }
        //处理root的左子树
        getCodes(root,"0",stringBuilder);
        //处理root的右子树
        getCodes(root,"1",stringBuilder);

        return huffmanCodes;
    }

    private static void getCodes(HuffmanNode node,String code,StringBuilder stringBuilder){
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if(node != null){//如果node == null不处理
            //判断当前node是叶子结点还是非叶子结点
            if(node.data == null){//非叶子结点
                //递归
                //向左
                getCodes(node.left,"0",stringBuilder2);
                //向右
                getCodes(node.right,"1",stringBuilder2);
            }else{
                //就表示找到某个叶子结点的最后
                huffmanCodes.put(node.data,stringBuilder2.toString());
            }
        }
    }


    private static List<HuffmanNode> getNodes(byte[] bytes){
        //1，创建List
        ArrayList<HuffmanNode> nodes = new ArrayList<>();
        //存放每个字符出现的次数 map ->
        Map<Byte,Integer> counts = new HashMap<>();
        for(byte b:bytes){
            Integer count = counts.get(b);
            if(count == null){
                counts.put(b,1);
            }else {
                counts.put(b,count+1);
            }
        }

        //把每一个键值对转成一个Node对象，并加入到nodes集合
        //遍历map
        for(Map.Entry<Byte,Integer> entry: counts.entrySet()){
            nodes.add(new HuffmanNode(entry.getKey(),entry.getValue()));
        }

        return nodes;
    }


    private static HuffmanNode createHuffmanTree(List<HuffmanNode> nodes){
        while(nodes.size()>1){
            Collections.sort(nodes);
            //取出第一个颗最小的二叉树
            HuffmanNode leftNode = nodes.get(0);
            HuffmanNode rightNode = nodes.get(1);
            //创建一颗新的二叉树
            HuffmanNode parent = new HuffmanNode(null,leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            //删除旧的HuffmanNode
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //添加新节点
            nodes.add(parent);
        }

        return nodes.get(0);
    }
}


class HuffmanNode implements Comparable<HuffmanNode>{
    Byte data; //存放数据
    int weight; //权重，存放数据出现的次数
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }


    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }
}
