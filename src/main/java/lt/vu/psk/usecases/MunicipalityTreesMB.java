package lt.vu.psk.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.psk.mybatis.model.Tree;
import lt.vu.psk.mybatis.model.Municipality;
import lt.vu.psk.mybatis.dao.TreeMapper;
import lt.vu.psk.mybatis.dao.MunicipalityMapper;
import lt.vu.psk.mybatis.dao.TreesMunicipalitiesMapper;
import lt.vu.psk.mybatis.model.TreesMunicipalities;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static javax.faces.context.FacesContext.getCurrentInstance;

@Model
public class MunicipalityTreesMB implements Serializable {

    @Inject
    private TreeMapper treeMapper;

    @Inject
    private MunicipalityMapper municipalityMapper;

    @Inject
    private TreesMunicipalitiesMapper treesMunicipalitiesMapper;

    @Getter @Setter
    private Municipality municipality;

    @Getter @Setter
    private Tree treeToAdd = new Tree();

    @Getter
    private List<Tree> allExistingTrees;

    @Transactional
    public String addTreeForMunicipality(Integer treeId) {
            TreesMunicipalities treesMunicipalities = new TreesMunicipalities();
            treesMunicipalities.setTreeId(treeId);
            treesMunicipalities.setMunicipalityId(this.municipality.getId());
            treesMunicipalitiesMapper.insert(treesMunicipalities);

        return "/myBatis/municipalitiesAndTrees?faces-redirect=true";
    }

    @Transactional
    public String addNewTreeForMunicipality() {
//        if(treeMapper.getResultCountByTreeName(treeToAdd.getTreeName()) == 0) {
            treeMapper.insert(treeToAdd);
//        }
        Tree addedTree = treeMapper.findByName(treeToAdd.getTreeName());
        TreesMunicipalities treesMunicipalities = new TreesMunicipalities();
        treesMunicipalities.setTreeId(addedTree.getId());
        treesMunicipalities.setMunicipalityId(this.municipality.getId());
        treesMunicipalitiesMapper.insert(treesMunicipalities);

        return "/myBatis/municipalitiesAndTrees?faces-redirect=true";
    }

    @PostConstruct
    private void init() {
        Map<String, String> requestParams = getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();

        Integer municipalityId = Integer.parseInt(requestParams.get("municipalityId"));
        this.municipality = municipalityMapper.selectByPrimaryKey(municipalityId);
        this.allExistingTrees = this.treeMapper.selectAll();
    }
}
