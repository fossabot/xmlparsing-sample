import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

public class SaxParserTest {
  @Test
  void givenSample_thenParse() throws IOException, SAXException, ParserConfigurationException {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser saxParser = factory.newSAXParser();
    CatXmlHandler handler = new CatXmlHandler();

    saxParser.parse("src/test/resources/sample.xml", handler);

    List<Cat> cats = handler.getCats();

    assertNotNull(cats);
    assertEquals(3, cats.size());

    Cat firstCat = cats.get(0);
    assertEquals("Bob", firstCat.getName());
    assertEquals("5", firstCat.getAge());

    Cat secondCat = cats.get(1);
    assertEquals("James", secondCat.getName());
    assertEquals("6", secondCat.getAge());

    Cat thirdCat = cats.get(2);
    assertEquals("Susan", thirdCat.getName());
    assertEquals("7", thirdCat.getAge());
  }
}
