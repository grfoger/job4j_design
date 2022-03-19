package ru.job4j.design.srp;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Path;
import java.util.List;

public class OutXml implements Output {
    private List<Employee> list;

    public OutXml(List<Employee> list) {
        this.list = list;
    }

    public void outReport(Path outPath) {
        try {
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            String newReport = "";
            try (StringWriter writer = new StringWriter();
                 PrintWriter out = new PrintWriter(new FileWriter(outPath.toFile()))) {
                marshaller.marshal(new Employees(list), writer);
                newReport = writer.getBuffer().toString();
                out.write(newReport);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }   catch (JAXBException j) {
            j.printStackTrace();
        }
    }
}
