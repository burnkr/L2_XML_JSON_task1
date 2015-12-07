package L2_XML_JSON.task1.MyJAXB;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@XmlRootElement(name = "trains")
public class Trains {
    @XmlElement(name = "train")
    private List<Train> trains = new ArrayList<>();

    public void addTrain(String from, String to, String date, String departure) {
        int id = trains.get(trains.size() - 1).id + 1;
        trains.add(new Train(id, from, to, date, departure));
        Collections.sort(trains);
    }

    public List<Train> getTrains() {
        return trains;
    }
}
