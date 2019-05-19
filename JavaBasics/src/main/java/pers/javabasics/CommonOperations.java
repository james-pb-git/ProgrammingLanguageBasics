package pers.javabasics;
import java.util.*;
/**
 * Created by bopang on 2019-05-15.
 */
public class CommonOperations {

    class myComparator implements Comparator<Integer> {
        public int compare(Integer a, Integer b) {
            return Math.abs(a) - Math.abs(b);
        }
    }

    class arrayComparator implements Comparator<int[]> {
        public int compare(int[]a, int[] b) {
            return a[0] - b[0];
        }
    }

    public void arrayOperations() {
        int[] a = new int[]{1,-3,2,-4,3,6,-8};
        Integer[] b = new Integer[a.length];
        for (int idx = 0; idx < a.length; idx ++) {
            b[idx] = a[idx];
        }
        Arrays.sort(b, new myComparator());
        System.out.println(Arrays.toString(b));

        int[][] c = new int[][]{{1,2},{0,3}};
        Arrays.sort(c, new arrayComparator());
        System.out.println(Arrays.toString(c));
    }

    public void stringOperations() {
        String s = new String("Hello world!");
        char ch;
        for(int idx = 0; idx < s.length(); idx ++) {
            ch = s.charAt(idx);
            System.out.print(ch);
        }
        System.out.println();
        return;
    }

    public void hashMapOperations() {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(1, 2);
        map.put(3, 4);
        for(Integer key: map.keySet()) {
            System.out.printf("%d: %d\n", key, map.get(key));
        }
        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            System.out.println(entry);
        }

        int a = 1, b = 2;
        System.out.println(a + "_" + b);

    }
}
