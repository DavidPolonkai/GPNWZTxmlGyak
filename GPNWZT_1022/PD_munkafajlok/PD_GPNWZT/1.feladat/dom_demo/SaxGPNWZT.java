
package dom_demo;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.HandlerBase;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.DefaultHandler;

public class SaxGPNWZT {

    public void startElement(String uri, String localName, String qName, Attributes attributes) {

    }

    public static void main(String[] args) {
        SAXParserFactory saxf = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxf.newSAXParser();
            MyHandler handler = new MyHandler();
            saxParser.parse(new File("cats.xml"), handler);
            // Get Employees list
            List<Cat> catList = handler.getCatList();
            // print employee information
            for (Cat c : catList)
                System.out.println(c);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}