package controller;

import java.util.Map;

public interface XmlExportable {
    String getXmlTagName();
    Map<String, String> getXmlAttributes();
    Map<String, String> getXmlElements();
}

