package lt.vu.psk.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.psk.entities.Forest;
import lt.vu.psk.interceptor.LoggedInvocation;
import lt.vu.psk.persistance.ForestDAO;
import lt.vu.psk.qualifiers.ForestTypeProcessor;
import lt.vu.psk.qualifiers.Leafed;
import lt.vu.psk.qualifiers.LeafedForestType;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Model
public class Forests {

    @Inject
    private ForestDAO forestDAO;

    @Getter @Setter
    private Forest forestToCreate = new Forest();

    @Getter
    private List<Forest> allForests;

    @Inject @Leafed
    ForestTypeProcessor forestTypeProcessor;

    @Inject @Any
    private LeafedForestType leafedForestType;

    @Getter
    private List<Forest> leafedForests;

    @PostConstruct
    public void init() {
        loadAllForests();
    }

    public void loadAllForests() {
        this.allForests = forestDAO.loadAll();
    }

    public void loadLeafedForests() {

        List<Forest> forests = forestDAO.loadAll();
        this.leafedForests = forests.stream()
                .filter(s -> s.getForest_name().toUpperCase().contains("Leafed"))
                .collect(Collectors.toList());
    }

    @Transactional
    @LoggedInvocation
    public String createForest() {
        this.forestDAO.persist(forestToCreate);
        return "index?faces-redirect=true";
    }
}
