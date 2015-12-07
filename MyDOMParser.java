package L2_XML_JSON.task1;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyDOMParser extends XMLParser {

    @Override
    public List<String> parse() {
        List<String> trains = new ArrayList<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = factory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(fileName));
            StringBuilder train = new StringBuilder();

            Element root = doc.getDocumentElement();
            NodeList trainElems = root.getElementsByTagName("train");
            for (int i = 0; i < trainElems.getLength(); i++) {
                boolean outOfDepTime = false;
                train.append("Train " + trainElems.item(i).getAttributes().getNamedItem("id").getNodeValue() + "\n");
                NodeList trainInfo = trainElems.item(i).getChildNodes();
                for (int j = 0; j < trainInfo.getLength(); j++) {
                    Node elem = trainInfo.item(j);
                    if (elem.getNodeType() == Node.ELEMENT_NODE) {
                        String nodeName = elem.getNodeName();
                        String nodeVal = elem.getTextContent();
                        train.append(nodeName + ": ");
                        train.append(nodeVal + "\n");
                        if (nodeName.equals("departure")){
                            int depHour = Integer.parseInt(nodeVal.split(":")[0]);
                            if (depHour >=15 && depHour < 19)
                                outOfDepTime = false;
                            else
                                outOfDepTime = true;
                        }
                    }
                }
                if (!outOfDepTime)
                    trains.add(train.toString());
                train.setLength(0);
                train.trimToSize();
            }
        } catch (ParserConfigurationException | SAXException parseException) {
            System.out.println(parseException.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return trains;
    }
}
