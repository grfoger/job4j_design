package ru.job4j.odd.dip;

import java.util.HashMap;
import java.util.Map;

public class ClockFactory {
    private String name;
    private Map<Integer, ClockModel> modelMap = new HashMap<>();
    private int lastModel = 1;

    public ClockFactory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ClockModel createClock(int model) {
        ClockModel clock = null;
        if (modelMap.containsKey(model)) {
            clock = new ClockModel(model, modelMap.get(model).getName(), this);
        } else if (model == lastModel) {
            clock = new ClockModel(lastModel, "Модель №" + lastModel, this);
            modelMap.put(lastModel, clock);
            lastModel++;
        } else {
            System.out.println("It is not next available model.");
            throw new IllegalArgumentException("Wrong model.");
        }
        return clock;
    }

    /**
     * 1.Нарушение по полю: Хранилище моделей не выделено в отдельную абстракцию.
     * 2.Нарушение по логированию: Логирование завязано на консоль.
     */
}
