package com.epam.rd.java.basic.task8;

import com.epam.rd.java.basic.task8.controller.*;
import org.w3c.dom.Document;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            return;
        }

        String xmlFileName = args[0];
        System.out.println("Input ==> " + xmlFileName);

        ////////////////////////////////////////////////////////
        // DOM
        ////////////////////////////////////////////////////////

        // get container
        DOMController domController = new DOMController(xmlFileName);
        // PLACE YOUR CODE HERE

        List<Entity> domList = domController.parse();

        // sort (case 1)
        // PLACE YOUR CODE HERE
        Document doc = domController.createOutputFile(domList);
        // save
        String outputXmlFile = "output.dom.xml";
        // PLACE YOUR CODE HERE
        domController.writeFile(doc, outputXmlFile);
        ////////////////////////////////////////////////////////
        // SAX
        ////////////////////////////////////////////////////////

        // get
        SAXController saxController = new SAXController(xmlFileName);
        // PLACE YOUR CODE HERE
        List<Entity> saxList = saxController.saxParsing();
        // sort  (case 2)
        // PLACE YOUR CODE HERE

        // save
        outputXmlFile = "output.sax.xml";
        // PLACE YOUR CODE HERE
        saxController.writeFile(saxController.prepareXML(saxList), outputXmlFile);
        ////////////////////////////////////////////////////////
        // StAX
        ////////////////////////////////////////////////////////

        // get
        STAXController staxController = new STAXController(xmlFileName);
        // PLACE YOUR CODE HERE
        List<Entity> staxList = staxController.readXMLwithCoursor();
        // sort  (case 3)
        // PLACE YOUR CODE HERE
        staxList.remove(0);
        // save
        outputXmlFile = "output.stax.xml";
        // PLACE YOUR CODE HERE
        staxController.writeFile(staxController.prepareXML(staxList), outputXmlFile);
    }

}
