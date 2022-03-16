package ru.job4j.tdd;

public interface Account {
    public void setData(String login);
    public void setPassword(String password);
    public Ticket buy(Session session, int row, int column);
}
