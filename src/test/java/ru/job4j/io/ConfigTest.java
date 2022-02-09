package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("user"), is("grfoger"));
        assertThat(config.value("user_mail"), is("grfoger@gmail.com"));
    }

    @Test
    public void whenPairWithCommentAndEmpty() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("user"), is("grfoger"));
        assertThat(config.value("user_mail"), is("grfoger@gmail.com"));
        assertThat(config.value("#user_mail"), is(Matchers.nullValue()));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenWrongPair() {
        String path = "./data/wrong_pair.properties";
        Config config = new Config(path);
        config.load();
    }
}
