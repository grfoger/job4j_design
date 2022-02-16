package ru.job4j.io.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.io.UsageLog4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class.getName());

    public static void main(String[] args) {
        SpaceGasStation gasStation = new SpaceGasStation(
                true,
                1704,
                new SpaceShip("spaceTruck", 24.5f, 5),
                new String[] {"prepare", "docking", "refuel", "undocking"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(gasStation));

        StringBuilder sb = new StringBuilder();

        try (BufferedReader in = new BufferedReader(new FileReader(new File("spaceGasStation.json")))) {
            while (in.ready()) {
                sb.append(in.readLine());
            }
        } catch (IOException e) {
            LOG.error("Gas station exploded! Bada-booo!");
        }
        String anyGasStation = sb.toString();
        SpaceGasStation someStation = gson.fromJson(anyGasStation, SpaceGasStation.class);
        System.out.println(someStation);
    }
}
