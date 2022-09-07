package sample;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class XMLParser {
    public static Map<String,Double> rates;

    public void parse(){
        rates =new HashMap<>();

        SAXReader reader = new SAXReader();
        Document document= null;
        try {
            document = reader.read("resources/kantor.xml");
        } catch (DocumentException e) {
            e.printStackTrace();
        }


        if (document!=null) {
            XPath xpath = document.createXPath("//*[local-name()='Cube']/*[local-name()='Cube']/*[local-name()='Cube']");
            List list = xpath.selectNodes(document);

            Iterator iter = list.iterator();

            while (iter.hasNext()) {
                Element element = (Element) iter.next();
                System.out.print(element.attributeValue("currency")+"    -    ");
                System.out.println(element.attributeValue("rate"));
                  rates.put(element.attributeValue("currency"),Double.parseDouble(element.attributeValue("rate")));

            }
        }
    }
}
