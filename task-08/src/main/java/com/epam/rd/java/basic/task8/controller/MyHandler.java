package com.epam.rd.java.basic.task8.controller;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class MyHandler extends DefaultHandler {
    private StringBuilder currentValue = new StringBuilder();
    List<Entity> result;
    Entity currentEntity;

    public List<Entity> getResult(){
        return result;
    }

    @Override
    public void startDocument() throws SAXException {
        result = new ArrayList<>();
    }

    @Override
    public void startElement(String uri,
                             String localName,
                             String qName,
                             Attributes attributes) throws SAXException {

        currentValue.setLength(0);

        if (qName.equalsIgnoreCase("flower")){
            currentEntity = new Entity();
            currentEntity.setRoot("flowers");
        }
        if (qName.equalsIgnoreCase("aveLenFlower")){
            currentEntity.setLenMeasure(attributes.getValue("measure"));
        }
        if (qName.equalsIgnoreCase("tempreture")){
            currentEntity.setTempMeasure(attributes.getValue("measure"));
        }
        if (qName.equalsIgnoreCase("lighting")){
            currentEntity.setLightReq(attributes.getValue("lightRequiring"));
        }
        if (qName.equalsIgnoreCase("watering")){
            currentEntity.setWaterMeasure(attributes.getValue("measure"));
        }
    }

    @Override
    public void endElement(String uri,
                           String localName,
                           String qName) throws SAXException {

        if (qName.equalsIgnoreCase("name")){
            currentEntity.setName(currentValue.toString());
        }
        if (qName.equalsIgnoreCase("soil")){
            currentEntity.setSoil(currentValue.toString());
        }
        if (qName.equalsIgnoreCase("origin")){
            currentEntity.setOrigin(currentValue.toString());
        }
        if (qName.equalsIgnoreCase("stemColour")){
            currentEntity.setStemColour(currentValue.toString());
        }
        if (qName.equalsIgnoreCase("leafColour")){
            currentEntity.setLeafColour(currentValue.toString());
        }
        if (qName.equalsIgnoreCase("aveLenFlower")){
            currentEntity.setAveLenFlower(currentValue.toString());
        }
        if (qName.equalsIgnoreCase("tempreture")){
            currentEntity.setTempreture(currentValue.toString());
        }
        if (qName.equalsIgnoreCase("watering")){
            currentEntity.setWatering(currentValue.toString());
        }
        if (qName.equalsIgnoreCase("multiplying")){
            currentEntity.setMultiplying(currentValue.toString());
        }
        if (qName.equalsIgnoreCase("flower")){
            result.add(currentEntity);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        currentValue.append(ch, start, length);
    }
}
