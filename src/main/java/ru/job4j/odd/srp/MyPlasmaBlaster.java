package ru.job4j.odd.srp;

public class MyPlasmaBlaster implements Blaster {

    @Override
    public Shot poof(Battery battery) {
        return new Shot();
    }

    public String getChargeOrAttention(Battery battery) {
        return "Батарея бластера заряжена на n% или сейчас взорвётся. Попробуйте позже.";
    }
}

/**
 * 1)нарушение на уровне метода: метод getChargeOrAttention должен быть разделён на вынесение предупреждения
 * и на выдачу информации о заряде т.к. сейчас метод выполняет две разные задачи
 * 2)нарушение на уровне класса: Бластер нужен только чтобы стрелять. Задача предупреждений и выдачи информации
 * не относятся к этой основной задаче
 * 3)нарушение на уровне реализации класса: необходима дополнительная зависимость в виде интерфейса PlasmaBlaster
 */