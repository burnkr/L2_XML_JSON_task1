package L2_XML_JSON.task1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MySAXParser extends XMLParser {

    @Override
    public List<String> parse() {
        Handler handler = new Handler();

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            File sourceXml = new File(fileName);

            saxParser.parse(sourceXml, handler);

        } catch (ParserConfigurationException | SAXException parseException) {
            System.out.println(parseException.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return handler.trains;
    }

    private class Handler extends DefaultHandler {
        List<String> trains = new ArrayList<>();
        StringBuilder train = new StringBuilder();
        boolean departureTimeFound;
        boolean outOfDepTime;
        boolean trainFound;

        @Override
        public void startElement(String namespaceURI, String localName, String qName, Attributes attrs) {
            if (qName.equals("train")) {
                train.append("Train ");
                train.append((attrs.getLength() > 0 ? attrs.getValue(0) : "-undefined-"));
                trainFound = true;
            } else {
                if (trainFound)
                    train.append(qName + ": ");
            }

            if (qName.equals("departure"))
                departureTimeFound = true;
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String text = "";

            if (trainFound) {
                for (int i = start; i < length + start; i++)
                    text += ch[i];
            }

            if (departureTimeFound) {
                String[] depTime = text.split(":");
                if (Integer.parseInt(depTime[0]) >= 15 && (Integer.parseInt(depTime[0]) < 19))
                    outOfDepTime = false;
                else
                    outOfDepTime = true;
            }

            train.append(text);
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if (qName.equals("train")) {
                if (!outOfDepTime)
                    trains.add(train.toString());
                train.setLength(0);
                train.trimToSize();
                trainFound = false;
            }

            if (qName.equals("departure"))
                departureTimeFound = false;
        }
    }
}
