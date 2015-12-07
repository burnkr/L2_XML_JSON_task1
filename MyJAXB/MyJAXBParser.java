package L2_XML_JSON.task1.MyJAXB;

import L2_XML_JSON.task1.XMLParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyJAXBParser extends XMLParser {
    private Trains trains;

    @Override
    public List<String> parse() {

        List<String> result = new ArrayList<>();

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Trains.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            trains = (Trains)unmarshaller.unmarshal(new File(fileName));
            for (Train train : trains.getTrains()){
                int depHour = Integer.parseInt(train.departure.split(":")[0]);
                if (depHour >=15 && depHour < 19)
                    result.add(train.toString());
            }
        } catch (JAXBException jbe) {
            System.out.println(jbe.getMessage());
        }

        return result;
    }

    public void saveXML() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Trains.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //marshaller.marshal(trains, System.out);
            marshaller.marshal(trains, new File(fileName));
        } catch (JAXBException jbe) {
            System.out.println(jbe.getMessage());
        }
    }

    public Trains getTrains() {
        return trains;
    }
}
