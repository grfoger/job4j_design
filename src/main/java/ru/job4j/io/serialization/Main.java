package ru.job4j.io.serialization;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        JSONObject jsonSpaceShip = new JSONObject("{\"shipType\":\"spaceTruck\",\"fuelCapacity\":\"24.5\",\"shipLength\":\"5\"}");

        List<String> list = new ArrayList<>();
        list.add("prepare");
        list.add("check");
        list.add("service");
        JSONArray jsonOperations = new JSONArray(list);

        final SpaceGasStation gasStation = new SpaceGasStation(
                true,
                1704,
                new SpaceShip("spaceTruck", 24.5f, 5),
                new String[] {"prepare", "docking", "refuel", "undocking"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isBusy", gasStation.isBusy());
        jsonObject.put("availableFuel", 1704);
        jsonObject.put("shipType", jsonSpaceShip);
        jsonObject.put("operations", jsonOperations);

        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(gasStation).toString());

        /**
        SpaceGasStation gasStation = new SpaceGasStation(
                true,
                1704,
                new SpaceShip("spaceTruck", 24.5f, 5),
                new String[] {"prepare", "docking", "refuel", "undocking"});

        JAXBContext context = JAXBContext.newInstance(SpaceGasStation.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(gasStation, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            SpaceGasStation result = (SpaceGasStation) unmarshaller.unmarshal(reader);
            System.out.println(result);
        } */

    }
}
