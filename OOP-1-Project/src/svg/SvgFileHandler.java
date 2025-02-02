package svg;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import shapes.*;
import storage.SvgRepository;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SvgFileHandler {

    public static void loadFromFile(String filePath, SvgRepository repository) throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
            return;
        }

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        dbFactory.setIgnoringComments(true);
        dbFactory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);

        doc.getDocumentElement().normalize();

        // Парсване на <rect>
        NodeList rectNodes = doc.getElementsByTagName("rect");
        for (int i = 0; i < rectNodes.getLength(); i++) {
            Node node = rectNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;
                double x = getDoubleAttribute(elem, "x", 0);
                double y = getDoubleAttribute(elem, "y", 0);
                double w = getDoubleAttribute(elem, "width", 0);
                double h = getDoubleAttribute(elem, "height", 0);
                String fill = elem.hasAttribute("fill") ? elem.getAttribute("fill") : "black";

                repository.addShape(new RectangleShape(x, y, w, h, fill));
            }
        }

        // Парсване на <circle>
        NodeList circleNodes = doc.getElementsByTagName("circle");
        for (int i = 0; i < circleNodes.getLength(); i++) {
            Node node = circleNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;
                double cx = getDoubleAttribute(elem, "cx", 0);
                double cy = getDoubleAttribute(elem, "cy", 0);
                double r  = getDoubleAttribute(elem, "r",  0);
                String fill = elem.hasAttribute("fill") ? elem.getAttribute("fill") : "black";

                repository.addShape(new CircleShape(cx, cy, r, fill));
            }
        }

        // Парсване на <line>
        NodeList lineNodes = doc.getElementsByTagName("line");
        for (int i = 0; i < lineNodes.getLength(); i++) {
            Node node = lineNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;
                double x1 = getDoubleAttribute(elem, "x1", 0);
                double y1 = getDoubleAttribute(elem, "y1", 0);
                double x2 = getDoubleAttribute(elem, "x2", 0);
                double y2 = getDoubleAttribute(elem, "y2", 0);

                // stroke, stroke-width могат да имат различни имена в SVG, но стандартно са "stroke" и "stroke-width"
                String strokeColor = elem.hasAttribute("stroke") ? elem.getAttribute("stroke") : "black";
                double strokeWidth = getDoubleAttribute(elem, "stroke-width", 1.0);

                // Добавяме новата линия в хранилището
                repository.addShape(new LineShape(x1, y1, x2, y2, strokeColor, strokeWidth));
            }
        }
    }

    private static double getDoubleAttribute(Element elem, String attrName, double defaultVal) {
        if (elem.hasAttribute(attrName)) {
            String val = elem.getAttribute(attrName);
            val = val.replace(',', '.'); // за да се избегнат проблеми с десетични запетайки
            try {
                return Double.parseDouble(val);
            } catch (NumberFormatException e) {
                return defaultVal;
            }
        }
        return defaultVal;
    }

    /**
     * Записване на формите като SVG файл.
     */
    public static void saveToFile(String filePath, SvgRepository repository) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            pw.println("<?xml version=\"1.0\" standalone=\"no\"?>");
            pw.println("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" " +
                    "\"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">");
            pw.println("<svg>");

            for (Shape shape : repository.getAllShapes()) {
                pw.println("  " + shape.toSvg());
            }

            pw.println("</svg>");
        }
    }
}
