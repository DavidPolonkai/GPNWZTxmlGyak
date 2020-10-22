package dom_demo;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandler extends DefaultHandler {

    // List to hold Employees object
    private List<Cat> catList = null;
    private Cat cat = null;
    private StringBuilder data = null;

    // getter method for employee list
    public List<Cat> getCatList() {
        return catList;
    }

    boolean bAge = false;
    boolean bName = false;
    boolean bBreed = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("cat")) {
            // create a new Employee and put it in Map
            String id = attributes.getValue("id");
            // initialize Employee object and set id attribute
            cat = new Cat();
            cat.setId(Integer.parseInt(id));
            // initialize list
            if (catList == null)
                catList = new ArrayList<>();
        } else if (qName.equalsIgnoreCase("name")) {
            // set boolean values for fields, will be used in setting Employee variables
            bName = true;
        } else if (qName.equalsIgnoreCase("age")) {
            bAge = true;
        } else if (qName.equalsIgnoreCase("breed")) {
            bBreed = true;
        }
        // create the data container
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (bAge) {
            // age element, set Employee age
            cat.setAge(Integer.parseInt(data.toString()));
            bAge = false;
        } else if (bName) {
            cat.setName(data.toString());
            bName = false;
        } else if (bBreed) {
            cat.setBreed(data.toString());
            bBreed = false;
        }
        if (qName.equalsIgnoreCase("cat")) {
            // add Employee object to list
            catList.add(cat);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }
}
