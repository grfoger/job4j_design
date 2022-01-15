package ru.job4j.it;

public class Sandbox {
    public static void main(String[] args) {
        int[][] in = {
                {1}, {}, {}, {}, {2}
        };
        MatrixIt it = new MatrixIt(in);

        System.out.println(it.next());
        System.out.println(it.next());
    }
}
