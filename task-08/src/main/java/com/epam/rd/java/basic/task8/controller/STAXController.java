package com.epam.rd.java.basic.task8.controller;

import org.xml.sax.helpers.DefaultHandler;

import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for StAX parser.
 */
public class STAXController extends DefaultHandler {

    private String xmlFileName;

    public STAXController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    // PLACE YOUR CODE HERE

    public List<Entity> readXMLwithCoursor() throws FileNotFoundException, XMLStreamException {
        List<Entity> list = new ArrayList<>();
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(
                new FileInputStream(xmlFileName));

        int eventType = reader.getEventType();
        Entity entity = new Entity();
        while (reader.hasNext()) {
            eventType = reader.next();

            if (eventType == XMLEvent.START_ELEMENT) {
                switch (reader.getName().getLocalPart()) {
                    case "flowers":
                        entity.setRoot("flowers");
                        break;
                    case "flower":
                        break;
                    case "name":
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS) {
                            entity.setName(reader.getText());
                        }
                        break;
                    case "soil":
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS) {
                            entity.setSoil(reader.getText());
                        }
                        break;
                    case "origin":
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS) {
                            entity.setOrigin(reader.getText());
                        }
                        break;
                    case "visualParameters":
                        break;
                    case "stemColour":
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS) {
                            entity.setStemColour(reader.getText());
                        }
                        break;
                    case "leafColour":
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS) {
                            entity.setLeafColour(reader.getText());
                        }
                        break;
                    case "aveLenFlower":
                        entity.setLenMeasure(reader.getAttributeValue(null, "measure"));
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS) {
                            entity.setAveLenFlower(reader.getText());
                        }
                        break;
                    case "growingTips":
                        break;
                    case "tempreture":
                        entity.setTempMeasure(reader.getAttributeValue(null, "measure"));
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS) {
                            entity.setTempreture(reader.getText());
                        }
                        break;
                    case "lighting":
                        entity.setLightReq(reader.getAttributeValue(null, "lightRequiring"));
                    case "watering":
                        entity.setWaterMeasure(reader.getAttributeValue(null, "measure"));
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS) {
                            entity.setWatering(reader.getText());
                        }
                        break;
                    case "multiplying":
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS) {
                            entity.setMultiplying(reader.getText());
                        }
                        break;
                }
            }
            if (eventType == XMLEvent.END_ELEMENT) {
                if (reader.getName().getLocalPart().equals("flower")) {
                    list.add(entity);
                    entity = new Entity();
                }
            }
        }
        return list;
    }

    public String prepareXML(List<Entity> entities) throws XMLStreamException {
        Entity entity = entities.get(0);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XMLOutputFactory opf = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = opf.createXMLStreamWriter(out);

        writer.writeStartDocument("utf-8", "1.0");

        writer.writeStartElement("flowers");
        writer.writeAttribute("xmlns", "http://www.nure.ua");
        writer.writeAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        writer.writeAttribute("xsi:schemaLocation", "http://www.nure.ua input.xsd ");

        writer.writeStartElement("flower");

        writer.writeStartElement("name");
        writer.writeCharacters(entity.getName());
        writer.writeEndElement();

        writer.writeStartElement("soil");
        writer.writeCharacters(entity.getSoil());
        writer.writeEndElement();

        writer.writeStartElement("origin");
        writer.writeCharacters(entity.getOrigin());
        writer.writeEndElement();

        writer.writeStartElement("visualParameters");

        writer.writeStartElement("stemColour");
        writer.writeCharacters(entity.getStemColour());
        writer.writeEndElement();
        writer.writeStartElement("leafColour");
        writer.writeCharacters(entity.getLeafColour());
        writer.writeEndElement();
        writer.writeStartElement("aveLenFlower");
        writer.writeAttribute("measure", entity.getLenMeasure());
        writer.writeCharacters(entity.getAveLenFlower());
        writer.writeEndElement();

        writer.writeEndElement();

        writer.writeStartElement("growingTips");

        writer.writeStartElement("tempreture");
        writer.writeAttribute("measure", entity.getTempMeasure());
        writer.writeCharacters(entity.getTempreture());
        writer.writeEndElement();
        writer.writeStartElement("lighting");
        writer.writeAttribute("lightRequiring", entity.getLightReq());
        writer.writeEndElement();
        writer.writeStartElement("watering");
        writer.writeAttribute("measure", entity.getWaterMeasure());
        writer.writeCharacters(entity.getWatering());
        writer.writeEndElement();

        writer.writeEndElement();

        writer.writeStartElement("multiplying");
        writer.writeCharacters(entity.getMultiplying());
        writer.writeEndElement();

        writer.writeEndDocument();

        writer.flush();
        writer.close();

        return out.toString(StandardCharsets.UTF_8);
    }

    public void writeFile(String xml, String outputFile) throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");

        StreamSource source = new StreamSource(new StringReader(xml));
        StreamResult result = new StreamResult(new File(outputFile));

        transformer.transform(source, result);
    }
}