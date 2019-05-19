package pers.javabasics;
/**
 * Created by bopang on 9/30/18.
 */
import java.util.*;
import pers.javabasics.Human;
import pers.javabasics.CommonOperations;

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
        //CommonOperations co = new CommonOperations();
        //co.stringOperations();
        //co.hashMapOperations();
        //co.arrayOperations();
        List<Integer> res = new ArrayList<Integer>(Arrays.asList(0));

    }
}
