package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GeneratorTest {

    @Ignore
    @Test
    public void getSimpleTest() {
        Generator generator = new SimpleGenerator();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Arhip");
        map.put("subject", "you");
        String template = "I am a ${name}, Who are ${subject}? ";
        String text = generator.produce(template, map);
        assertThat(text, is("I am a Arhip, Who are you? "));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenNoMapKeys() {
        Generator generator = new SimpleGenerator();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Arhip");
        String template = "I am a ${name}, Who are ${subject}? ";
        generator.produce(template, map);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenMapHaveExtraKeys() {
        Generator generator = new SimpleGenerator();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Arhip");
        map.put("subject", "you");
        map.put("author", "Stanislav Ezhi Letcs");
        String template = "I am a ${name}, Who are ${subject}? ";
        generator.produce(template, map);
    }

}
