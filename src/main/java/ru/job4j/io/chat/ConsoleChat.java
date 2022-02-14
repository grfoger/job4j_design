package ru.job4j.io.chat;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConsoleChat {

    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> phrases = readPhrases();
        List<String> log = new ArrayList<>();
        boolean isRun = true;
        boolean isNotPaused = true;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in));) {
            while (isRun) {
                String input = in.readLine();
                log.add(input);
                if (STOP.equals(input)) {
                    isNotPaused = false;
                }
                if (CONTINUE.equals(input)) {
                    isNotPaused = true;
                }
                if (OUT.equals(input)) {
                    saveLog(log);
                    isRun = false;
                }
                if (isNotPaused && isRun) {
                    String answer = phrases.get(new Random().nextInt(phrases.size()));
                    System.out.println(answer);
                    log.add(answer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Chat is closed. See you later.");

    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            reader.lines().forEach(phrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("chat_log.txt", "bot.txt");
        cc.run();
    }
}
