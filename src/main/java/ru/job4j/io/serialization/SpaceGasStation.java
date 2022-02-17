package ru.job4j.io.serialization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "spaceGasStation")
@XmlAccessorType(XmlAccessType.FIELD)
public class SpaceGasStation {

    @XmlAttribute
    private boolean isBusy;
    @XmlAttribute
    private int availableFuel;
    private SpaceShip refuelType;
    @XmlElementWrapper(name = "operations")
    @XmlElement(name = "operation")
    private String[] operations;

    public SpaceGasStation() {

    }

    public SpaceGasStation(boolean isBusy, int availableFuel, SpaceShip refuelType, String[] operations) {
        this.isBusy = isBusy;
        this.availableFuel = availableFuel;
        this.refuelType = refuelType;
        this.operations = operations;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public int getAvailableFuel() {
        return availableFuel;
    }

    public SpaceShip getRefuelType() {
        return refuelType;
    }

    public String[] getOperations() {
        return operations;
    }

    @Override
    public String toString() {
        return "SpaceGasStation{"
                + "isBusy=" + isBusy
                + ", availableFuel=" + availableFuel
                + ", refuelType=" + refuelType
                + ", operations=" + Arrays.toString(operations)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {
        final SpaceGasStation gasStation = new SpaceGasStation(
                true,
                1704,
                new SpaceShip("spaceTruck", 24.5f, 5),
                new String[] {"prepare", "docking", "refuel", "undocking"});

        JAXBContext context = JAXBContext.newInstance(SpaceGasStation.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(gasStation, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {

        }
    }
}
