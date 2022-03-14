package ru.job4j.kiss;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MaxMinTest {

    @Test
    public void findMaxMinInt() {
        List<Integer> list = Arrays.asList(1, 3, 5, 10);
        MaxMin maxMin = new MaxMin();
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        } ;
        Assert.assertEquals(maxMin.max(list, comparator), list.get(3));
        Assert.assertEquals(maxMin.min(list, comparator), list.get(0));
    }

    @Test
    public void findMaxMinStringLength() {
        List<String> list = Arrays.asList("у", "крота", "была", "нора");
        MaxMin maxMin = new MaxMin();
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        } ;
        Assert.assertEquals(maxMin.max(list, comparator), list.get(1));
        Assert.assertEquals(maxMin.min(list, comparator), list.get(0));
    }
}
