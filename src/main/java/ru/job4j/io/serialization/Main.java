package ru.job4j.io.serialization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
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
        }

    }
}
