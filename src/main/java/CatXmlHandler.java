import java.util.LinkedList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CatXmlHandler extends DefaultHandler {
  private static final String CAT = "cat";
  private static final String NAME = "name";
  private static final String AGE = "age";

  private transient LinkedList<Cat> cats;
  private transient String elementValue;

  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    elementValue = new String(ch, start, length);
  }

  @Override
  public void startDocument() throws SAXException {
    cats = new LinkedList<>();
  }

  @Override
  public void startElement(String uri, String localName, String qualifiedName, Attributes attr)
      throws SAXException {
    if (CAT.equals(qualifiedName)) {
      cats.add(new Cat());
    }
  }

  @Override
  public void endElement(String uri, String localName, String qualifiedName) throws SAXException {
    switch (qualifiedName) {
      case NAME:
        assert (cats.peekLast() != null);
        cats.peekLast().setName(elementValue);
        break;
      case AGE:
        assert (cats.peekLast() != null);
        cats.peekLast().setAge(elementValue);
        break;
      default:
        // ignored
    }
  }

  public List<Cat> getCats() {
    return cats;
  }
}
