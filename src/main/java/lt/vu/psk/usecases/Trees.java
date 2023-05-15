package lt.vu.psk.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.psk.entities.Tree;
import lt.vu.psk.persistance.TreeDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Trees {

    @Inject
    private TreeDAO treeDAO;

    @Getter @Setter
    private Tree treeToCreate = new Tree();

    @Getter
    private List<Tree> allTrees;

    @PostConstruct
    public void init() {
        loadAllTrees();
    }

    public void loadAllTrees() {
        this.allTrees = treeDAO.loadAll();
    }

    @Transactional
    public String createTree() {
        this.treeDAO.persist(treeToCreate);
        return "index?faces-redirect=true";
    }
}
