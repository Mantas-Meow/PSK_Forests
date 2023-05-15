package lt.vu.psk.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.psk.entities.Tree;
import lt.vu.psk.entities.Forest;
import lt.vu.psk.persistance.TreeDAO;
import lt.vu.psk.persistance.ForestDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Model
public class ForestTrees implements Serializable {

    @Inject
    private ForestDAO forestDAO;

    @Inject
    private TreeDAO treeDAO;

    @Getter @Setter
    private Forest forest;

    @Getter @Setter
    private Tree treeToCreate = new Tree();
    @Getter
    private List<Tree> allTrees;

    public void loadAllTrees() {
        this.allTrees = treeDAO.loadAll();
    }

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer structureId = Integer.parseInt(requestParameters.get("forestId"));
        this.forest = forestDAO.findOne(structureId);
        loadAllTrees();
    }



    @Transactional
    public String createTree() {
        treeToCreate.setForest(this.forest);
        treeDAO.persist(treeToCreate);
        return "trees?faces-redirect=true&forestId=" + this.forest.getId();
    }
}
