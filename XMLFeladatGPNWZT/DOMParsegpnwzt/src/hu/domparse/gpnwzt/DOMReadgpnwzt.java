package hu.domparse.gpnwzt;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class DOMReadgpnwzt {
    private Node root;
    private Document document;

    public static void main(String[] args) {
        try {
            DOMReadgpnwzt domreader = new DOMReadgpnwzt();
            domreader.gyarTermek();
            // domreader.beszallitogyarto();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // beállítja a document és a root elemeket
    public DOMReadgpnwzt() throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        this.document = documentBuilder.parse("XMLgpnwzt.xml");
        this.document.getDocumentElement().normalize();
        this.root = document.getDocumentElement();
    }

    // kilistázza az xml-ben levő adatokat rendszerezve
    public void listAll() {
        NodeList nodeList = this.root.getChildNodes();
        for (int i = nodeList.getLength() - 1; i >= 0; i--) {
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                System.out.println();
                System.out.println();
                System.out.println(nodeList.item(i).getNodeName().toString().toUpperCase());
                NodeList subNodeList = nodeList.item(i).getChildNodes();
                for (int j = 0; j < subNodeList.getLength(); j++) {
                    Node currentsubnode = subNodeList.item(j);
                    if (currentsubnode.getNodeType() == Node.ELEMENT_NODE) {
                        NamedNodeMap att = currentsubnode.getAttributes();
                        String current = "";
                        for (int k = 0; k < att.getLength(); k++) {
                            Node attnode = att.item(k);
                            if (attnode.getNodeType() == Node.ATTRIBUTE_NODE) {
                                if (j == 1) {
                                    System.out.print(attnode.getNodeName() + "  | ");
                                }
                                current += attnode.getTextContent() + " | ";
                            }
                        }
                        NodeList subsubNodeList = currentsubnode.getChildNodes();
                        for (int k = 0; k < subsubNodeList.getLength(); k++) {
                            Node currentsubsubnode = subsubNodeList.item(k);
                            if (currentsubsubnode.getNodeType() == Node.ELEMENT_NODE) {
                                if (j == 1 && currentsubsubnode.getNodeName() != "cim") {
                                    System.out.print(currentsubsubnode.getNodeName() + " | ");
                                }
                                switch (currentsubsubnode.getNodeName()) {
                                    case "idterm":
                                    case "idbolt":
                                    case "idbesz":
                                    case "idgyar":
                                        current += currentsubsubnode.getAttributes().item(0).getTextContent() + " | ";
                                        break;
                                    case "cim": {
                                        NodeList cimNode = currentsubsubnode.getChildNodes();
                                        for (int l = 0; l < cimNode.getLength(); l++) {
                                            if (cimNode.item(l).getNodeType() == Node.ELEMENT_NODE) {
                                                if (j == 1) {
                                                    System.out.print(cimNode.item(l).getNodeName() + " | ");
                                                }
                                                current += cimNode.item(l).getTextContent() + " | ";
                                            }
                                        }
                                    }
                                        break;
                                    default:
                                        current += currentsubsubnode.getTextContent() + " | ";
                                }
                            }
                        }
                        System.out.println();
                        System.out.print(current);
                    }
                }
            }
        }
    }

    // A gyárat és a gyár által gyártot terméket írja ki
    public void gyarTermek() throws DOMException, Exception {
        NodeList gyarak = this.document.getElementsByTagName("gyar");
        for (int i = 0; i < gyarak.getLength(); i++) {
            NodeList currentnodelist = gyarak.item(i).getChildNodes();
            Node ref = findIdInDoc(gyarak.item(i).getAttributes().getNamedItem("t_id").getTextContent());
            NodeList termek = ref.getChildNodes();
            String current = "";
            for (int j = 0; j < currentnodelist.getLength(); j++) {
                Node currentnode = currentnodelist.item(j);
                if (currentnode.getNodeType() == Node.ELEMENT_NODE) {
                    if (i == 0)
                        System.out.print(currentnodelist.item(1).getParentNode().getNodeName() + ":"
                                + currentnode.getNodeName() + " | ");
                    current += currentnode.getTextContent() + " | ";
                }
            }
            for (int j = 0; j < termek.getLength(); j++) {
                Node currentnode = termek.item(j);
                if (currentnode.getNodeType() == Node.ELEMENT_NODE) {
                    if (i == 0)
                        System.out.print(
                                termek.item(1).getParentNode().getNodeName() + ":" + currentnode.getNodeName() + " | ");
                    current += currentnode.getTextContent() + " | ";
                }
            }
            System.out.println();
            System.out.print(current);
        }
        System.out.println();
    }

    // A gyártót és a hozzá tartozó beszállítót írja ki, a beszállító ciméből csak
    // az országot veszi
    public void beszallitogyarto() throws Exception {
        Node node = this.document.getElementsByTagName("k_besz_gyar").item(0);
        NodeList kapcsolatok = node.getChildNodes();
        System.out.print("alkatreszmennyiseg" + " | ");
        for (int i = 0; i < kapcsolatok.getLength(); i++) {
            if (kapcsolatok.item(i).getNodeType() == Node.ELEMENT_NODE) {
                NodeList kapcsolat = kapcsolatok.item(i).getChildNodes();
                String beszref = kapcsolat.item(1).getAttributes().getNamedItem("refID").getTextContent();
                String gyarref = kapcsolat.item(3).getAttributes().getNamedItem("refID").getTextContent();
                String alkmennyiseg = kapcsolat.item(5).getTextContent();
                NodeList nl1 = findIdInDoc(beszref).getChildNodes();
                NodeList nl2 = findIdInDoc(gyarref).getChildNodes();
                String current = "";
                String nl1name = nl1.item(1).getParentNode().getNodeName();
                String nl2name = nl2.item(1).getParentNode().getNodeName();
                for (int j = 0; j < nl1.getLength(); j++) {
                    Node currentnode = nl1.item(j);
                    if (currentnode.getNodeType() == Node.ELEMENT_NODE) {
                        if (currentnode.getNodeName().equals("cim")) {
                            if (i == 1)
                                System.out.print(
                                        nl1name + ":" + currentnode.getChildNodes().item(1).getNodeName() + " | ");
                            current += currentnode.getChildNodes().item(1).getTextContent() + " | ";
                        } else {
                            if (i == 1)
                                System.out.print(nl1name + ":" + currentnode.getNodeName() + " | ");
                            current += currentnode.getTextContent() + " | ";
                        }
                    }
                }
                for (int j = 0; j < nl2.getLength(); j++) {
                    Node currentnode = nl2.item(j);
                    if (currentnode.getNodeType() == Node.ELEMENT_NODE) {
                        if (i == 1)
                            System.out.print(nl2name + ":" + currentnode.getNodeName() + " | ");
                        current += currentnode.getTextContent() + " | ";
                    }
                }
                System.out.println();
                System.out.print(alkmennyiseg + " | ");
                System.out.print(current);
            }
        }
        System.out.println();
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
        throw new Exception("Az id vagy a referencia nem létezik");
    }

    //a módosításhoz kilistázza a módosítható nodeokat
    public void listForModify() {
        ArrayList<NodeList> arraynodelist = new ArrayList<NodeList>();
        arraynodelist.add(this.document.getElementsByTagName("gyar"));
        arraynodelist.add(this.document.getElementsByTagName("termek"));
        arraynodelist.add(this.document.getElementsByTagName("bolt"));
        for (NodeList nodelist : arraynodelist) {
            for (int i = 0; i < nodelist.getLength(); i++) {
                Node node = nodelist.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.print("id: " + node.getAttributes().getNamedItem("id").getTextContent() + " | tipus: "
                            + node.getNodeName() + " | ");
                    NodeList nodeList2 = node.getChildNodes();

                    for (int j = 0; j < nodeList2.getLength(); j++) {
                        Node node2 = nodeList2.item(j);
                        if (node2.getNodeType() == Node.ELEMENT_NODE) {
                            System.out.print(node2.getNodeName()+": "+node2.getTextContent()+" | ");
                        }
                    }
                    System.out.println();
                }
            }
        }

    }
}
