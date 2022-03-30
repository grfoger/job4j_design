package ru.job4j.design.isp.menu;

import java.util.Scanner;

public class TODOApp {
    public static final ActionDelegate STUB_ACTION = System.out::println;

    private static final String ADD = "exit";
    private static final String SUB = "exit";
    private static final String PRINT = "exit";
    private static final String EXIT = "exit";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new SimpleMenuPrinter();
        while (true) {
            System.out.println("Введите команду:\n"
                    + ADD + " - добавить задачу\n"
                    + SUB + " - добавить подзадачу\n"
                    + PRINT + " - вывести список\n"
                    + EXIT + " - выход");
            String request = scan.nextLine();
            if (EXIT.equals(request)) {
                break;
            } else if (ADD.equals(request)) {
                System.out.println("Введите имя новой основной задачи");
                menu.add(Menu.ROOT, scan.nextLine(), STUB_ACTION);
            } else if (SUB.equals(request)) {
                System.out.println("Введите имя существующей задачи");
                String task = scan.nextLine();
                System.out.println("Введите имя новой подзадачи");
                menu.add(task, scan.nextLine(), STUB_ACTION);
            } else if (PRINT.equals(request)) {
                printer.print(menu);
            } else {
                System.out.println("Команда не распознана, потворите ввод");
            }
        }
    }
}
