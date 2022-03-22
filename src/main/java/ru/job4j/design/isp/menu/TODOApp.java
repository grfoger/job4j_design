package ru.job4j.design.isp.menu;

import java.util.Scanner;

public class TODOApp {
    public static final ActionDelegate STUB_ACTION = System.out::println;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new SimpleMenuPrinter();
        while (true) {
            System.out.println("Введите команду:\n"
                    + "add - добавить задачу\n"
                    + "sub - добавить подзадачу\n"
                    + "print - вывести список\n"
                    + "exit - выход");
            String request = scan.nextLine();
            if ("exit".equals(request)) {
                break;
            } else if ("add".equals(request)) {
                System.out.println("Введите имя новой основной задачи");
                menu.add(Menu.ROOT, scan.nextLine(), STUB_ACTION);
            } else if ("sub".equals(request)) {
                System.out.println("Введите имя существующей задачи");
                String task = scan.nextLine();
                System.out.println("Введите имя новой подзадачи");
                menu.add(task, scan.nextLine(), STUB_ACTION);
            } else if ("print".equals(request)) {
                printer.print(menu);
            } else {
                System.out.println("Команда не распознана, потворите ввод");
            }
        }
    }
}
