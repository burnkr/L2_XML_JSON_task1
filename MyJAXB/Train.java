package L2_XML_JSON.task1.MyJAXB;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "train")
public class Train implements Comparable<Train> {
    @XmlAttribute(name = "id")
    public int id;
    @XmlElement
    public String from;
    @XmlElement
    public String to;
    @XmlElement
    public String date;
    @XmlElement
    public String departure;

    public Train() {
    }

    public Train(int id, String from, String to, String date, String departure) {
        this.id = id;
        this.date = date;
        this.departure = departure;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "Train " + id + "\n" +
                "from: " + from + '\n' +
                "to: " + to + '\n' +
                "date: " + date + '\n' +
                "departure: " + departure + '\n';
    }

    @Override
    public int compareTo(Train t1) {
        Train t2 = this;
        if (t1.id > t2.id) {
            return -1;
        } else {
            if (t1.id == t2.id) {
                return 0;
            } else {
                if (t1.id < t2.id) {
                    return 1;
                }
            }
        }

        return 13; // make compiler happy
    }
}
