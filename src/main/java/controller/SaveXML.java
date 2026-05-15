package controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

public class SaveXML {

    public static void SaveFile() throws ParserConfigurationException, TransformerConfigurationException, TransformerException
    {
        DocumentBuilderFactory docXML = DocumentBuilderFactory.newInstance();
        docXML.setNamespaceAware(true);
        Document doc = (Document) docXML.newDocumentBuilder().newDocument();

        System.out.println("data.xml создан");

        Element root = doc.createElement("groupe");
        root.setAttribute("groupeName", "IP620");
        doc.appendChild(root);

        Element ID = doc.createElement("ID");
        root.appendChild(ID);
    }
}
