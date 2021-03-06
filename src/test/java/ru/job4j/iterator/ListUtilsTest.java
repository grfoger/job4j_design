package ru.job4j.iterator;

import static org.hamcrest.core.Is.is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenAddAfterFirst() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 0, 3);
        assertThat(input, is(Arrays.asList(0, 3, 1, 2)));
    }

    @Test
    public void whenRemoveOdd() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        ListUtils.removeIf(input, x -> x % 2 != 0);
        assertThat(input, is(Arrays.asList(0, 2, 4)));
    }

    @Test
    public void whenRemoveEven() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        ListUtils.removeIf(input, x -> x % 2 == 0);
        assertThat(input, is(Arrays.asList(1, 3)));
    }

    @Test
    public void whenReplaceOdd() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        ListUtils.replaceIf(input, x -> x % 2 != 0, 17);
        assertThat(input, is(Arrays.asList(0, 17, 2, 17, 4)));
    }

    @Test
    public void whenReplaceEven() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        ListUtils.replaceIf(input, x -> x % 2 == 0, 17);
        assertThat(input, is(Arrays.asList(17, 1, 17, 3, 17)));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        List<Integer> check = new ArrayList<>(Arrays.asList(0, 1));
        ListUtils.removeAll(input, check);
        assertThat(input, is(Arrays.asList(2, 3, 4)));
    }
}