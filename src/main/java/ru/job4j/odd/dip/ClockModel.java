package ru.job4j.odd.dip;

public class ClockModel {
    private int modelNumber;
    private String name;
    private ClockFactory factory;

    public ClockModel(int modelNumber, String name, ClockFactory factory) {
        this.modelNumber = modelNumber;
        this.name = name;
        this.factory = factory;
    }

    public int getModelNumber() {
        return modelNumber;
    }

    public String getName() {
        return name;
    }

    public String getFactoryName() {
        return factory.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClockModel that = (ClockModel) o;

        if (modelNumber != that.modelNumber) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = modelNumber;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
    /**
     * 3.Нарушение по полю. Класс часы завязан на конкретную реализацию фабрики.
     */
}
