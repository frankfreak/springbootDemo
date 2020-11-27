package com.ffk.springBootDemo.dom;

import com.google.common.collect.Lists;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.util.List;

public class JaxpDemo {
    private static Document document;
    private static final String filePath = "src/main/resources/person.xml";
    private static final String desFilePath = "src/main/resources/person2.xml";

    private static void init(String filePath) throws IOException, SAXException, ParserConfigurationException {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(filePath);
        } catch (Exception e) {
            throw e;
        }
    }
    private static List<String> getTextContextList(String tagName) {
        // 根据名称获得元素
        NodeList nodeList = document.getElementsByTagName(tagName);
        List<String> list = Lists.newArrayList();
        if (null == nodeList) {
            return list;
        }
        for (int i = 0; i < nodeList.getLength(); i ++) {
            // 直接获取元素的值
            list.add(nodeList.item(i).getTextContent());
        }
        return list;
    }
    private static void addSex(String sex, int index) {
        NodeList nodeList = document.getElementsByTagName("p1");
        if (index + 1 > nodeList.getLength()) {
            return;
        }
        Node node = nodeList.item(index);
        Element element = document.createElement("sex");
        element.appendChild(document.createTextNode(sex));
        node.appendChild(element);
    }

    private static void changeSex(String sex, int index) {
        NodeList nodeList = document.getElementsByTagName("sex");
        if (index + 1 > nodeList.getLength()) {
            return;
        }
        Node node = nodeList.item(index);
        node.setTextContent(sex);
    }

    private static void deleteSex(int index) {
        NodeList nodeList = document.getElementsByTagName("sex");
        if (index + 1 > nodeList.getLength()) {
            return;
        }
        Node node = nodeList.item(index);
        Node parentNode = node.getParentNode();
        parentNode.removeChild(node);
    }

    private static void nodeTraversal(Node parentNode) {
        if (parentNode.getNodeType() == Node.ELEMENT_NODE) {
            System.out.println(parentNode.getNodeName());
        }
        NodeList nodeList = parentNode.getChildNodes();
        if (null == nodeList || nodeList.getLength() == 0) {
            return;
        }
        for (int i = 0; i < nodeList.getLength(); i ++) {
            Node node = nodeList.item(i);
            nodeTraversal(node);
        }
    }
    private static void outPutXmlFile() throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(new DOMSource(document), new StreamResult(desFilePath));
    }

    public static void main(String[] args) throws Exception {
        init(filePath);
        List<String> nameList = getTextContextList("name");
        System.out.println(nameList);

        addSex("male", 0);
        changeSex("female", 0);
//        deleteSex(0);
//        outPutXmlFile();
        nodeTraversal(document);
    }
}
