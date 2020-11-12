package hu.meiit.xpathparsegpnwzt;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XpathParseGPNWZT {
    public static void main(String[] args){
        try{
            Document doc = buildDom();
            XPath xpath = buildXpath(doc);
            XPathExpression expr = xpath.compile("//class/student");
            NodeList result =(NodeList) expr.evaluate(doc,XPathConstants.NODESET);
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

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static Document buildDom() throws Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("../studentGPNWZT.xml");
        return doc;
    }

    public static XPath buildXpath(Document doc){
        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();
        return xpath;
    }



}
