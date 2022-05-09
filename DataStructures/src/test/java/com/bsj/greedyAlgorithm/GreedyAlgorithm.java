package com.bsj.greedyAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GreedyAlgorithm {
    public static void main(String[] args) {
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("杭州");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //存放所有地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("杭州");
        allAreas.add("大连");
        allAreas.add("成都");

        //存放所有广播区域
        HashMap<String,HashSet<String>> broadcasts = new HashMap<>();
        broadcasts.put("K1",hashSet1);
        broadcasts.put("K2",hashSet2);
        broadcasts.put("K3",hashSet3);
        broadcasts.put("K4",hashSet4);
        broadcasts.put("K5",hashSet5);


        //创建 ArrayList,存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();

        //定义一个临时的集合,在遍历的过程中,存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<>();

        //定义maxKey,保存在一次遍历过程中的
        String maxKey = null;

        while(allAreas.size()!=0){
            //每进行一次while循环,需要
            maxKey = null;
            //遍历 broadcasts,取出对应的key
            for(String key :broadcasts.keySet()){
                //每遍历一次需要
                tempSet.clear();
                //当前这个key能够覆盖的地区
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                //求出tempSet和allAreas集合的交集,交集赋给tempSet
                tempSet.retainAll(allAreas);
                //如果当前这个集合包含的未覆盖地区的数量,比maxKey指向的集合地区还多
                //就需要重置maxSize
                //tempSet.size()>broadcasts.get(maxKey).size())体现贪心算法的特点,每次选择都是最优的
                if(tempSet.size()>0 && (maxKey == null || tempSet.size()>broadcasts.get(maxKey).size())){
                    maxKey = key;
                }
            }
            //maxKey != null,就应该将maxKey加入selects
            if(maxKey != null){
                selects.add(maxKey);
                //将maxKey指向的广播电台覆盖的地区,从allAreas去掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println("得到选择的结果是:"+selects);
    }
}
