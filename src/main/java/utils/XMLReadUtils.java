package utils;



import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class XMLReadUtils {

    public static Document getDocument(File file) throws Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);
        return document;
    }

    //读取select方法
    public void readSelect(File file) throws Exception{
        Document document = XMLReadUtils.getDocument(file);
        NodeList list = document.getElementsByTagName("mapper");
        Element element = (Element) list.item(0);
        String namespace = element.getAttribute("namespace");
    }
}
