package hu.domparse.gpnwzt;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMModifygpnwzt {
    private Node root;
    private Document document;
    private Scanner in;

    public static void main(String[] args) {
        try {
            DOMModifygpnwzt domModifier = new DOMModifygpnwzt();
            domModifier.selector();
            domModifier.destructor();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Beállítja a document és a root elemeket
    public DOMModifygpnwzt() throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        this.document = documentBuilder.parse("XMLgpnwzt.xml");
        this.document.getDocumentElement().normalize();
        this.root = document.getDocumentElement();
        this.in = new Scanner(System.in);
    }

    //A destructor az adatok kiírását és a schema alapján történő validációt végzi 
    public void destructor() throws TransformerException, SAXException, IOException {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        Schema schema = factory.newSchema(new File("XMLSchemagpnwzt.xsd"));
        Validator validator = schema.newValidator();
        DOMSource domsource = new DOMSource(this.document);
        validator.validate(domsource);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        StreamResult result = new StreamResult(new File("XMLgpnwzt.xml"));
        transformer.transform(domsource, result);
    }

    //ez a függvény meg a módosítandó nodeot, ellenőrzi hogy lehetséges-e a módosítás és elvégzi
    public void modify(String id) throws Exception {
        Node node = findIdInDoc(id);
        String name = node.getNodeName();
        if (name.equals("beszallito") || name.equals("kapcsolat"))
            throw new Exception("Nem lehetséges módosítani a kért node-ot");
        NodeList nl = node.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node current = nl.item(i);
            if (current.getNodeType() == Node.ELEMENT_NODE) {
                current.setTextContent(readModification(current.getNodeName(), current.getTextContent()));
            }
        }
    }

    //beolvassa az új adatot
    public String readModification(String nodename, String old) {
        System.out.print("Adja meg az új értéket (régi érték: " + old + "): ");
        String ret = in.nextLine();
        return ret;
    }

    // Ez a metódus megkeresi a megadott ID-jű adattagot, hibát dob, ha a
    // hivatkozott adattag nem létezik
    private Node findIdInDoc(String id) throws Exception {
        NodeList nl1 = this.root.getChildNodes();
        for (int i = 0; i < nl1.getLength(); i++) {
            if (nl1.item(i).getNodeType() == Node.ELEMENT_NODE) {
                for (int j = 0; j < nl1.item(i).getChildNodes().getLength(); j++) {
                    if (nl1.item(i).getChildNodes().item(j).getNodeType() == Node.ELEMENT_NODE) {
                        for (int k = 0; k < nl1.item(i).getChildNodes().item(j).getAttributes().getLength(); k++) {
                            if (nl1.item(i).getChildNodes().item(j).getAttributes().item(k).getTextContent().equals(id)
                                    && nl1.item(i).getChildNodes().item(j).getAttributes().item(k).getNodeName()
                                            .equals("id")) {
                                return nl1.item(i).getChildNodes().item(j);
                            }
                        }
                    }
                }
            }
        }
        throw new Exception("Az id vagy referencia nem létezik");
    }

    //kilistázza a módosítható nodeokat bekéri az id-t és meghívja a módisítást
    public void selector() throws Exception {
        new DOMReadgpnwzt().listForModify();
        System.out.println("Kérem válassza ki a módosítandó nodokat az id megadásával: ");
        String choosen=this.in.nextLine();
        modify(choosen);
    }
}
