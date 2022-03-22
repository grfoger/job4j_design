package ru.job4j.design.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {

    private final static String TAB = "  ";

    public void print(Menu menu) {
        menu.forEach(i -> {
            int n = i.getNumber().split("\\.").length;
            String tmpTab = "";
            for (int j = 0; j < n - 1; j++) {
                tmpTab = tmpTab + TAB;
            }
            System.out.println(tmpTab + i.getNumber() + i.getName());
        });
    }
}
