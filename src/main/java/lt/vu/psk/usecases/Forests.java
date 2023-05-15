package lt.vu.psk.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.psk.entities.Forest;
import lt.vu.psk.persistance.ForestDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Forests {

    @Inject
    private ForestDAO forestDAO;

    @Getter @Setter
    private Forest forestToCreate = new Forest();

    @Getter
    private List<Forest> allForests;

    @PostConstruct
    public void init() {
        loadAllForests();
    }

    public void loadAllForests() {
        this.allForests = forestDAO.loadAll();
    }

    @Transactional
    public String createForest() {
        this.forestDAO.persist(forestToCreate);
        return "index?faces-redirect=true";
    }
}
