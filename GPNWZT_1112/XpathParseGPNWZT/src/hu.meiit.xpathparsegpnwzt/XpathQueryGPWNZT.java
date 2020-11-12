package hu.meiit.xpathparsegpnwzt;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;

import static hu.meiit.xpathparsegpnwzt.XpathParseGPNWZT.buildDom;
import static hu.meiit.xpathparsegpnwzt.XpathParseGPNWZT.buildXpath;

public class XpathQueryGPWNZT {
    public static void main(String[] args) {
        try {
            Document doc = buildDom();
            XPath xpath = buildXpath(doc);
            XPathExpression expr = xpath.compile("//student[@rollno='593']");
            NodeList result = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

            for (int i =0; i<result.getLength();i++){
                if (result.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    NodeList resultchild = result.item(i).getChildNodes();
                    System.out.println("Current Element: " + result.item(i).getNodeName());
                    System.out.println("rollno: "+result.item(i).getAttributes().item(0).getNodeValue());
                    for (int j = 0; j < resultchild.getLength(); j++)
                        if (resultchild.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            System.out.println(resultchild.item(j).getNodeName() + ": " + resultchild.item(j).getTextContent());
                        }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
