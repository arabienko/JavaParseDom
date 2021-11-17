import Gard.Bush;
import Gard.Facilities;
import Gard.Tree;

import java.util.List;

public class Garden {
    private List<Tree> tree;
    private List<Bush> bush;
    private List<Facilities> facilities;

    public List<Tree> getTree() {
        return tree;
    }

    public void setTree(List<Tree> tree) {
        this.tree = tree;
    }

    public List<Bush> getBush() {
        return bush;
    }

    public void setBush(List<Bush> bush) {
        this.bush = bush;
    }

    public List<Facilities> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<Facilities> facilities) {
        this.facilities = facilities;
    }

    @Override
    public String toString() {
        return "Garden{" +
                "tree=" + tree +
                ", bush=" + bush +
                ", facilities=" + facilities +
                '}';
    }
}
