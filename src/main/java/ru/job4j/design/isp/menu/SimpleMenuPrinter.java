package ru.job4j.design.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {

    private final static String TAB = "\t";

    public void print(Menu menu) {
        menu.forEach(i -> {
            int n = i.getNumber().split("\\.").length;
            String tmpTab = TAB.repeat(n - 1);
            System.out.println(tmpTab + i.getNumber() + i.getName());
        });
    }
}
