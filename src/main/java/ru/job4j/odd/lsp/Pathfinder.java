package ru.job4j.odd.lsp;

public class Pathfinder extends Adventurer {
    public Pathfinder(Quest quest, String name) {
        super(quest, name);
    }

    @Override
    public String action(Quest quest) {
        String currentTask = null;
        switch (quest.ordinal()) {
            case (0):
                currentTask = "Спасти принцессу, изучив следы";
                break;
            case (1):
                currentTask = "Убить дракона, изучив следы";
                break;
            case (2):
                currentTask = "Найти золото, изучив следы";
                break;
            default:
                currentTask = "Бездельничать, изучая следы";
                break;
        }
        return taskNumber + ". Следопыт " + name + "выполнил задачу: " + currentTask;
    }

    /**
     * 1. Ошибка на уровне Предусловий: потеряно условие проверки входных данных
     * 2. Ошибка на уровне Постусловий: потеряно условие проверки выходных данных
     * 3. Ошибка на уровне тела переопределённого метода: потерян счётчик задач, нет специфических проверок увловия.
     * Требуется убедиться что новый класс именно Pathfinder а не Adventurer, а для этого нужно использование getClass
     * или instance of что указывает на ошибку приниципа Лисков.
     *
     */
}
