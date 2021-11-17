import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class MyNewTask {
    private static final String TAG_NAME="name";
    private static final String TAG_HIGH="high";
    private static final String TAG_TREE="tree";
    private static final String TAG_BUSH="bush";
    private static final String TAG_ROOT="root";

    private static boolean isFound;
    /**
     * list to save objects from file
     */
    private static List<Garden> garden = new ArrayList<>();
    private static List<Garden> facilities = new ArrayList<>();
    /**
     * @param args
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        startToParseFile();
        //
        for (Garden s:garden) {
            System.out.println(String.format("Our Garden name %s and high %s",s.getName(),s.getHigh()));
        }
    }

    /**
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * getting started with the xml-file
     * create fabric,file request, create parser
     */
    private static void startToParseFile() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        File file=new File("garden.xml");
        SearchXMLHandler handler = new SearchXMLHandler("garden");
        parser.parse(file, handler);
    }

    /**
     * parsing
     */
    private static class SearchXMLHandler extends DefaultHandler {
        private String name;
        private double high;
        private String bench;
        private String lantern;
        private String lastElementName;
        private String elementName;
        private boolean isEntered;
        private Trees trees;
        private Bushes bushes;

       public SearchXMLHandler(String elementName){this.elementName=elementName;}

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            System.out.println("Start parsing...");
        }
        /**
         * @param uri -element space
         * @param localName - local element name
         * @param qName -element name with the prefix
         * @param attributes
         * get the values from the tags and write them to the object, save them to the list
         */
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            lastElementName=qName;

            if (isEntered){
//                System.out.println(String.format("Найден элемент <%s>",qName));
            }
            if (qName.equals(elementName)) {
                isEntered = true;
                isFound = true;
            }

        }
        @Override
        public void characters(char[] ch, int start, int length) {
            String element =new String(ch,start,length);
            element=element.replace("\n", "").trim();

            if (!element.isEmpty()){
                if (lastElementName.equals(TAG_NAME))
                    name=element;
                if (lastElementName.equals(TAG_HIGH)){
                    high=Double.parseDouble(element);
                }
            }
        }
        @Override
        public void endElement(String uri, String localName, String qName) {
            System.out.println(lastElementName);
            if (TAG_TREE.equals(lastElementName)) {
                if ((name != null && !name.isEmpty()) && (high != 0.0)) {
                    garden.add(new Trees(name, high));
                    name = null;
                    high = 0.0;
                }
            }
        }
        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
            System.out.println("End parsing.");
        }


        @Override
        public void ignorableWhitespace(char[] ch, int start, int length) {

        }

    }
    public static class Garden{
        private String name;
        private double high;
        private String bench;
        private String lantern;

        public Garden(String name, double high){
            this.name = name;
            this.high = high;
        }
        public Garden(String bench, String lantern){
            this.bench=bench;
            this.lantern=lantern;
        }

        public String getName() {
            return name;
        }
        public double getHigh() {
            return high;
        }
        public String getBench() {return bench;}
        public String getLantern() {return lantern;}
    }
    /**
     * create abstract class
     */
    public static class Plants extends Garden {
        public Plants(String name, double high) {super(name,high); }
    }
    /**
     * create the Bushes sub-class of the Plants class
     */
    public static class Bushes extends Plants {
        public Bushes (String name, double high) {
            super(name, high);
        }
    }
    /**
     * create the Trees sub-class of the Plants class
     */
    public static class Trees extends Plants {
        public Trees(String name, double high) {
            super(name, high);
        }
    }
    public static class Facilities extends TaskEPAM.Garden {
        public Facilities (String bench, String lantern){super(bench,lantern);}
    }
    /**
     * @param garden - list of objects (Bushes, Trees)
     * counting the number of landings and total height
     */
    public static void plantGarden(ArrayList<Garden> garden){
        int countTrees=0;
        int countBushes=0;
        double highTrees=0.0;
        double highBushes=0.0;

        for (Garden s:garden) {
            if (isaBoolean(s)){
                countTrees++;
                highTrees +=s.high;
            }else {
                countBushes++;
                highBushes +=s.high;
            }
        }
        System.out.println(format("Деревьев посажено: %s, общий рост: %s",countTrees, highTrees));
        System.out.println(format("Кустов посажено: %s, общий рост: %s",countBushes, highBushes));

    }
    /**
     * @param s - object
     * @return boolean: check is/has
     */
    private static boolean isaBoolean(Garden s) {
        return s instanceof Trees;
    }
}
