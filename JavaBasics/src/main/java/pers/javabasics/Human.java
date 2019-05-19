package pers.javabasics;

/**
 * Created by bopang on 2019-05-10.
 */
public class Human {

    public Human(int height) {
        this.height = height;
        System.out.println("I'm born!");
    }

    public Human(int height, String str) {
        this.height = height;
        System.out.println(str);
    }

    public int getHeight() {
        return this.height;
    }

    public void growHeight(int h) {
        this.height += h;
    }

    private void breath() {
        System.out.println("Hu ... hu ...");
    }

    public void repeatBeath(int rep) {
        for (int i = 0; i < rep; i ++) {
            breath();
        }
    }

    private int height = 160; // Default value: 0
}
