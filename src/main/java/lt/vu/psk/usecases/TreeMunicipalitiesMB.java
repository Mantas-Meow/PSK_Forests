package lt.vu.psk.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lt.vu.psk.mybatis.dao.TreeMapper;
import lt.vu.psk.mybatis.model.Tree;

@Model
public class TreeMunicipalitiesMB implements Serializable {

    @Inject
    private TreeMapper treeMapper;

    @Getter @Setter
    private Tree tree;

    @Getter @Setter
    private Tree treeToCreate = new Tree();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer treeId = Integer.parseInt(requestParameters.get("treeId"));
        this.tree = treeMapper.selectByPrimaryKey(treeId);
    }
}
