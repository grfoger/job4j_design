package ru.job4j.odd.lsp;

public class Adventurer {

    protected Quest quest;
    protected String name;
    protected int taskNumber;

    public Adventurer(Quest quest, String name) {
        this.quest = quest;
        this.name = name;
    }

    protected String action(Quest quest) {
        if (quest.ordinal() > 2) {
            throw new IllegalArgumentException("Can not take such quest");
        }
        taskNumber++;
        String currentTask = null;
        switch (quest.ordinal()) {
            case (0):
                currentTask = "Спасти принцессу";
                break;
            case (1):
                currentTask = "Убить дракона";
                break;
            case (2):
                currentTask = "Найти золото";
                break;
            default:
                currentTask = "Бездельничать";
                break;
        }

        if (taskNumber < 1) {
            throw new IllegalArgumentException("Wrong task number");
        }
        return taskNumber + ". " + name + "выполнил задачу: " + currentTask;
    }
}
