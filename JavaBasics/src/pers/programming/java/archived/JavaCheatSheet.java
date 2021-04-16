package pers.programming.java.archived;
/**
 * Created by bopang on 9/30/18.
 */

import java.util.*;

public class JavaCheatSheet {


    public void foo(int num) {
        System.out.println(1 % 3);
        System.out.println((-1) % 3);
        System.out.println(1 % (-3));
        System.out.println((-1) % (-3));
        // -1 / 3 = 0 in Java, while -1 // 3 = -1 in Python.
        System.out.println(Math.floor(-0.2));
    }

    public void examplesOfMath() {
        // abs, max, min, sin, exp, log, pow, round, sqrt, random
        int[] arr = new int[]{3,1,2,4,6};
        int minVal = Integer.MAX_VALUE;
        for(int ele : arr) {
            minVal = Math.min(minVal, ele);
        }
        System.out.println(minVal);
    }

    public void testHuman() {
        Human human = new Human(175);
        human.repeatBeath(5);
        System.out.println(human.getHeight());
    }

    public static void main(String[] args) {
        System.out.print("Hello world, and ");
        System.out.println("hello world!");
        JavaCheatSheet jcs = new JavaCheatSheet();
        // jcs.foo(0);
        // jcs.testHuman();
        CommonOperations co = new CommonOperations();
        //co.stringOperations();
        //co.hashMapOperations();
        co.arrayOperations();

        Map<Integer, Integer> counter = new HashMap<>();
        int[] arr = new int[]{1,1,2,3,4,4,5};
        int cnt;
        for (int ele : arr) {
            cnt = counter.getOrDefault(ele, 0);
            counter.put(ele, cnt + 1);
        }
        System.out.println(counter.toString());

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        cur.add(1);
        res.add(cur);
        cur.add(2);
        res.add(cur);
        System.out.println(res.toString());
        Set<Integer> mySet = new HashSet<>();
        mySet.add(1);
        mySet.add(2);
        for (Integer i: mySet) {
            mySet.remove(i);
            mySet.add(i);
            System.out.println(i);
        }



    }
}
