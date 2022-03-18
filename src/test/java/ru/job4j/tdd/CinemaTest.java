package ru.job4j.tdd;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CinemaTest {

    @Ignore
    @Test
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Ignore
    @Test
    public void whenFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    @Ignore
    @Test
    public void whenCheckSeat() {
        Session session = new Session3D();
        session.setBusy(2, 3);
        session.setBusy(2, 4);
        session.setBusy(5, 5);
        session.setBusy(5, 6);
        session.setBusy(5, 7);
        boolean[][] seats = session.getFreeSet();
        assertTrue(seats[5][8]);
        assertFalse(seats[5][7]);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenWrongSeat() {
        Session session = new Session3D();
        session.setBusy(100500, 987);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenAlreadyBusy() {
        Session session = new Session3D();
        session.setBusy(2, 3);
        session.setBusy(2, 3);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenWrongDate() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date1 = Calendar.getInstance();
        date1.set(1920, 10, 10, 23, 00);
        Calendar date2 = Calendar.getInstance();
        date2.set(2120, 10, 10, 23, 00);
        Ticket ticket1 = cinema.buy(account, 1, 1, date1);
        Ticket ticket2 = cinema.buy(account, 1, 1, date2);
    }

    @Ignore
    @Test
    public void whenRegister() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        Account account = new AccountCinema();
        account.setData("Эбед Надир");
        account.setPassword("крутокрутокруто");
        Ticket ticket = account.buy(session, 5, 7);
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        assertEquals(ticket, cinema.buy(account, 1, 1, date));
    }
}