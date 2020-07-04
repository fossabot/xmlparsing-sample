import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import javax.xml.stream.XMLStreamException;
import org.junit.jupiter.api.Test;

public class StaxParserTest {
  @Test
  void givenSample_thenParse()
      throws IOException, XMLStreamException {
    CatXmlStAX parser = new CatXmlStAX();

    List<Cat> cats = parser.process(Path.of("src/test/resources/sample.xml"));

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
