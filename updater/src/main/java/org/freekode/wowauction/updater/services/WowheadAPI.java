package org.freekode.wowauction.updater.services;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WowheadAPI {
    public static Map<String, String> getItemInfo(String itemId) {
        String url = "http://www.wowhead.com/item=" + itemId + "&xml";

        Map<String, String> itemMap = new HashMap<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbFactory.newDocumentBuilder();
            Document doc = builder.parse(url);

            doc.normalizeDocument();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            Node itemNode = doc.getElementsByTagName("item").item(0);

            if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) itemNode;

                itemMap.put("name", elem.getElementsByTagName("name").item(0).getTextContent());
                itemMap.put("level", elem.getElementsByTagName("level").item(0).getTextContent());
                itemMap.put("quality", elem.getElementsByTagName("quality").item(0).getTextContent());
                itemMap.put("class", elem.getElementsByTagName("class").item(0).getTextContent());
                itemMap.put("subclass", elem.getElementsByTagName("subclass").item(0).getTextContent());
                itemMap.put("icon", elem.getElementsByTagName("icon").item(0).getTextContent());
                itemMap.put("inventorySlot", elem.getElementsByTagName("inventorySlot").item(0).getTextContent());
                itemMap.put("htmlTooltip", elem.getElementsByTagName("htmlTooltip").item(0).getTextContent());
                itemMap.put("json", elem.getElementsByTagName("json").item(0).getTextContent());
//                itemMap.put("jsonEquip", elem.getElementsByTagName("jsonEquip").item(0).getTextContent());
//                itemMap.put("link", elem.getElementsByTagName("link").item(0).getTextContent());
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return itemMap;
    }
}
