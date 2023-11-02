package com.epam.rd.java.basic.task8.controller;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for DOM parser.
 */
public class DOMController {

    private String xmlFileName;

    public DOMController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    // PLACE YOUR CODE HERE
    public List<Entity> parse() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(xmlFileName);
            doc.getDocumentElement().normalize();
            List<Entity> entities = new ArrayList<>();
            new Entity().setRoot(doc.getDocumentElement().getNodeName());
            NodeList list = doc.getElementsByTagName("flower");
            for (int temp = 0; temp < list.getLength(); temp++) {
                Entity entity = new Entity();
                Node node = list.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    entity.setName(element.getElementsByTagName("name").item(0).getTextContent());
                    entity.setSoil(element.getElementsByTagName("soil").item(0).getTextContent());
                    entity.setOrigin(element.getElementsByTagName("origin").item(0).getTextContent());
                    NodeList list2 = doc.getElementsByTagName("visualParameters");
                    for (int i = 0; i < list2.getLength(); i++) {
                        Node node2 = list2.item(i);
                        if (node2.getNodeType() == Node.ELEMENT_NODE) {
                            Element element2 = (Element) node;
                            entity.setStemColour(element2.getElementsByTagName("stemColour").item(0).getTextContent());
                            entity.setLeafColour(element2.getElementsByTagName("leafColour").item(0).getTextContent());
                            NodeList avenLenList = element2.getElementsByTagName("aveLenFlower");
                            entity.setAveLenFlower(avenLenList.item(0).getTextContent());
                            entity.setLenMeasure(avenLenList.item(0).getAttributes().getNamedItem("measure").getTextContent());
                        }
                    }
                    NodeList list3 = doc.getElementsByTagName("growingTips");
                    for (int i = 0; i < list3.getLength(); i++) {
                        Node node3 = list2.item(i);
                        if (node3.getNodeType() == Node.ELEMENT_NODE) {
                            Element element3 = (Element) node;
                            NodeList tempList = element3.getElementsByTagName("tempreture");
                            entity.setTempreture(tempList.item(0).getTextContent());
                            entity.setTempMeasure(tempList.item(0).getAttributes().getNamedItem("measure").getTextContent());
                            NodeList lightList = element3.getElementsByTagName("lighting");
                            entity.setLighting(lightList.item(0).getTextContent());
                            entity.setLightReq(lightList.item(0).getAttributes().getNamedItem("lightRequiring").getTextContent());
                            NodeList waterList = element3.getElementsByTagName("watering");
                            entity.setWatering(waterList.item(0).getTextContent());
                            entity.setWaterMeasure(waterList.item(0).getAttributes().getNamedItem("measure").getTextContent());
                        }
                    }
                    entity.setMultiplying(element.getElementsByTagName("multiplying").item(0).getTextContent());
                    entities.add(entity);
                }
            }
            return entities;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Document createOutputFile(List<Entity> entities) throws ParserConfigurationException {
        Entity entity = entities.get(0);
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement(entity.getRoot());
        rootElement.setAttribute("xmlns", "http://www.nure.ua");
        rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        rootElement.setAttribute("xsi:schemaLocation", "http://www.nure.ua input.xsd ");
        doc.appendChild(rootElement);


        Element flower = doc.createElement("flower");
        rootElement.appendChild(flower);
        Element name = doc.createElement("name");
        name.setTextContent(entity.getName());
        flower.appendChild(name);
        Element soil = doc.createElement("soil");
        soil.setTextContent(entity.getSoil());
        flower.appendChild(soil);
        Element origin = doc.createElement("origin");
        origin.setTextContent(entity.getOrigin());
        flower.appendChild(origin);
        Element visualParameters = doc.createElement("visualParameters");
        flower.appendChild(visualParameters);
        Element stemColour = doc.createElement("stemColour");
        stemColour.setTextContent(entity.getStemColour());
        visualParameters.appendChild(stemColour);
        Element leafColour = doc.createElement("leafColour");
        leafColour.setTextContent(entity.getLeafColour());
        visualParameters.appendChild(leafColour);
        Element aveLenFlower = doc.createElement("aveLenFlower");
        aveLenFlower.setTextContent(entity.getAveLenFlower());
        aveLenFlower.setAttribute("measure", entity.getLenMeasure());
        visualParameters.appendChild(aveLenFlower);
        Element growingTips = doc.createElement("growingTips");
        flower.appendChild(growingTips);
        Element tempreture = doc.createElement("tempreture");
        tempreture.setTextContent(entity.getTempreture());
        tempreture.setAttribute("measure", entity.getTempMeasure());
        growingTips.appendChild(tempreture);
        Element lighting = doc.createElement("lighting");
        lighting.setAttribute("lightRequiring", entity.getLightReq());
        growingTips.appendChild(lighting);
        Element watering = doc.createElement("watering");
        watering.setTextContent(entity.getWatering());
        watering.setAttribute("measure", entity.getWaterMeasure());
        growingTips.appendChild(watering);
        Element multiplying = doc.createElement("multiplying");
        multiplying.setTextContent(entity.getMultiplying());
        flower.appendChild(multiplying);
        return doc;
    }

    public void writeFile(Document doc, String filePath) throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filePath));

        transformer.transform(source, result);
    }

}
