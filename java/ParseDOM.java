import Gard.Bush;
import Gard.Facilities;
import Gard.Tree;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ParseDOM {
    private static final String TAG_NAME = "name";
    private static final String TAG_HIGH = "high";
    private static final String TAG_TREE = "tree";
    private static final String TAG_FAC = "facilities";
    private static final String TAG_PLAN = "plants";
    private static final String TAG_BENCH = "bench";
    private static final String TAG_LANTERN = "lantern";

    public static void main(String[] args) {

        Garden gar = new Garden();

        File file = new File("garden.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document doc = null;

        try {
            doc = factory.newDocumentBuilder().parse(file);
        } catch (Exception e) {
            System.out.println("Open parsing error... " + e);
        }

        List <Tree> listTree = new ArrayList();
        List <Facilities> listFac = new ArrayList();
        List <Bush> listBush = new ArrayList<>();

        Node nodGarden = doc != null ? doc.getFirstChild() : null;
        NodeList nodChildGarden = nodGarden != null ? nodGarden.getChildNodes() : null;

        Node nodFacilities = null;
        Node nodPlants = null;


        for (int i = 0; i < (nodChildGarden != null ? nodChildGarden.getLength() : 0); i++) {
            if (nodChildGarden.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            switch (nodChildGarden.item(i).getNodeName()) {
                case TAG_FAC: {
                    nodFacilities = nodChildGarden.item(i);
                    break;
                }
                case TAG_PLAN: {
                    nodPlants = nodChildGarden.item(i);

                    break;
                }
            }
        }

        NodeList facList = nodFacilities != null ? nodFacilities.getChildNodes() : null;
        NodeList planList = nodPlants != null ? nodPlants.getChildNodes() : null;

        for (int r = 0; r < (facList != null ? facList.getLength() : 0); r++) {
            if (facList.item(r).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            String bench = "";
            String lantern = "";
            NodeList FacListChild = facList.item(r).getChildNodes();

            for (int k = 0; k < FacListChild.getLength(); k++) {
                if (FacListChild.item(k).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                switch (FacListChild.item(k).getNodeName()) {
                    case TAG_BENCH: {
                        bench = String.valueOf(FacListChild.item(k).getTextContent());

                        break;
                    }
                    case TAG_LANTERN: {
                        lantern = String.valueOf(FacListChild.item(k).getTextContent());
                        break;
                    }
                }
            }
            Facilities fas = new Facilities(bench, lantern);
            listFac.add(fas);
        }

        for (int j = 0; j < (planList != null ? planList.getLength() : 0); j++) {
            if (planList.item(j).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            String name = "";
            double high = 0.0;
            if (planList.item(j).getNodeName().equals(TAG_TREE)) {

                NodeList childTree = planList.item(j).getChildNodes();

                for (int k = 0; k < childTree.getLength(); k++) {
                    if (childTree.item(k).getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }
                    switch (childTree.item(k).getNodeName()) {
                        case TAG_NAME: {
                            name = String.valueOf(childTree.item(k).getTextContent());
                            break;
                        }
                        case TAG_HIGH: {
                            high = Double.parseDouble(childTree.item(k).getTextContent());
                            break;
                        }
                    }
                }

                Tree tree = new Tree(name, high);
                listTree.add(tree);

            } else {
                NodeList childBush = planList.item(j).getChildNodes();
                for (int k = 0; k < childBush.getLength(); k++) {
                    if (childBush.item(k).getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }
                    switch (childBush.item(k).getNodeName()) {
                        case TAG_NAME: {
                            name = String.valueOf(childBush.item(k).getTextContent());
                            break;
                        }
                        case TAG_HIGH: {
                            high = Double.parseDouble(childBush.item(k).getTextContent());
                            break;
                        }
                    }
                }
                Bush bush = new Bush(name, high);
                listBush.add(bush);
            }
        }

        gar.setTree(listTree);
        gar.setFacilities(listFac);
        gar.setBush(listBush);
        System.out.println(gar);
        gar.getTree().stream().filter(tree -> tree.getHigh()>15).forEach(tree -> System.out.println("Trees ="+ tree));
        for (Tree tree : gar.getTree()) {
            if (tree.getHigh() < 15) {
                System.out.println("Trees =" + tree);
            }
        }
    }
}
