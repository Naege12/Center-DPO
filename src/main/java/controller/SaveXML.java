package controller;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;
import java.util.Map;

public class SaveXML {
    public static void export(List<? extends XmlExportable> items ,String rootName, String filePath) {
        if (items == null || items.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Нет данных для экспорта");
            return;
        }
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement(rootName);
            doc.appendChild(root);

            for (XmlExportable item : items)
            {
                Element element = doc.createElement(item.getXmlTagName());
                for (Map.Entry<String, String> attr : item.getXmlAttributes().entrySet())
                {
                    element.setAttribute(attr.getKey(), attr.getValue());
                }

                for (Map.Entry<String, String> field : item.getXmlElements().entrySet()) {
                    Element child = doc.createElement(field.getKey());
                    child.setTextContent(field.getValue());
                    element.appendChild(child);
                }

                root.appendChild(element);
            }

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            transformer.transform(new DOMSource(doc), new StreamResult(new File(filePath)));

            System.out.println("XML сохранён: " + filePath);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}