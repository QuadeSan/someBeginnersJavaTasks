package com.epam.rd.java.basic.task8.controller;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Controller for SAX parser.
 */
public class SAXController extends DefaultHandler {

    private String xmlFileName;

    public SAXController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    // PLACE YOUR CODE HERE

    public List<Entity> saxParsing() {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try (InputStream is = new FileInputStream(xmlFileName)) {
            SAXParser saxParser = factory.newSAXParser();

            MyHandler myHandler = new MyHandler();
            saxParser.parse(is,myHandler);
            return myHandler.getResult();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
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