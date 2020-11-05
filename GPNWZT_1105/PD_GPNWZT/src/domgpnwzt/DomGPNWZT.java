package domgpnwzt;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class DomGPNWZT {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse("PD_munkafajlok/szemelyek.xml");
            document.getDocumentElement().normalize();
            System.out.println("Gyökér elem: " + document.getDocumentElement().getNodeName());
            NodeList nodeList = document.getElementsByTagName("szemely");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("id: " + element.getAttribute("id"));
                    Node current = element.getFirstChild();
                    NodeList nodeList1 = node.getChildNodes();
                    for (int j = 0; j < nodeList1.getLength(); j++) {
                        if (nodeList1.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            System.out.println(" "+nodeList1.item(j).getNodeName() + ":" + nodeList1.item(j).getTextContent());
                        }
                    }
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
