import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class CatXmlStAX {
  /**
   * Processes an XML file.
   *
   * @param path of the xml file
   * @return a list of the extracted cats
   * @throws FileNotFoundException if the file does not exist
   * @throws XMLStreamException if anything is wrong with the xml
   */
  public List<Cat> process(Path path) throws IOException, XMLStreamException {
    LinkedList<Cat> cats = new LinkedList<>();

    XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
    try (FileInputStream fis = new FileInputStream(path.toFile())) {
      XMLEventReader reader = xmlInputFactory.createXMLEventReader(fis);
      while (reader.hasNext()) {
        XMLEvent nextEvent = reader.nextEvent();
        if (nextEvent.isStartElement()) {
          StartElement startElement = nextEvent.asStartElement();
          switch (startElement.getName().getLocalPart()) {
            case "cat":
              cats.add(new Cat());
              break;
            case "name":
              nextEvent = reader.nextEvent();
              cats.peekLast().setName(nextEvent.asCharacters().getData());
              break;
            case "age":
              Attribute unit = startElement.getAttributeByName(new QName("unit"));
              nextEvent = reader.nextEvent();
              if (unit != null) {
                cats.peekLast().setAgeUnit(unit.getValue());
              }
              cats.peekLast().setAge(nextEvent.asCharacters().getData());
              break;
            default:
              // ignored
          }
        }
      }
    }

    return cats;
  }
}
