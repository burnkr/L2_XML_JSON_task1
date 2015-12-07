package L2_XML_JSON.task1;

import L2_XML_JSON.task1.MyJAXB.MyJAXBParser;
import L2_XML_JSON.task1.MyJAXB.Train;
import L2_XML_JSON.task1.MyJAXB.Trains;

public class Main {
    public static void main(String[] args) {
        XMLParser.fileName = "src/L2_XML_JSON/task1/Trains.xml";

        //print(new MySAXParser());

        //print(new MyDOMParser());

        MyJAXBParser parser = new MyJAXBParser();
        print(parser);
        Trains trains = parser.getTrains();
        trains.addTrain("Krivoy Rog", "Kiev", "01.12.2015", "21:52");
        trains.addTrain("Krivoy Rog", "Lvov", "02.12.2015", "16:36");
        parser.saveXML();
    }

    public static void print(XMLParser parser) {
        for (String train : parser.parse())
            System.out.println(train);
    }
}
