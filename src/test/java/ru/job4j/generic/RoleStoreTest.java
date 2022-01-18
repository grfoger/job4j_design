package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

//    public static void main(String[] args) {
//        RoleStore store = new RoleStore();
//        store.add(new Role("1", "developer"));
//        store.add(new Role("1", "developer2"));
//        System.out.println(store.findById("1").getRole());
//    }

    @Test
    public void whenAddAndFindThenUsernameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "developer"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("developer"));
    }

    @Test
    public void whenAddWithSameId() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "developer"));
        store.add(new Role("1", "student"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("developer"));
    }

    @Test
    public void whenAddWithSameIdAndReplace() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "developer"));
        store.add(new Role("1", "student"));
        store.replace("1", new Role("1", "MegaDeveloper"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("MegaDeveloper"));
    }

    @Test
    public void whenDeletedFromStore() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "developer"));
        store.add(new Role("2", "student"));
        store.add(new Role("3", "mentor"));
        store.delete("2");
        Role result = store.findById("2");
        assertNull(result);
    }

    @Test
    public void whenComplexReplace() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "developer"));
        store.add(new Role("2", "student"));
        store.add(new Role("3", "mentor"));
        store.replace("2", store.findById("3"));
        Role result = store.findById("2");
        assertThat(result.getRole(), is("mentor"));
    }
}