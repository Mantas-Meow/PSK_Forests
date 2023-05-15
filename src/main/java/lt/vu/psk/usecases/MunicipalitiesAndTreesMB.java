package lt.vu.psk.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.psk.mybatis.dao.MunicipalityMapper;
import lt.vu.psk.mybatis.model.Municipality;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class MunicipalitiesAndTreesMB implements Serializable {

    @Inject
    private MunicipalityMapper municipalityMapper;

    @Getter
    private List<Municipality> allMunicipalities;

    @Getter @Setter
    private Municipality municipalityToCreate = new Municipality();

    @PostConstruct
    private void init() {
        loadAllMunicipalities();
    }

    @Transactional
    public String createMunicipality() {
        municipalityMapper.insert(municipalityToCreate);
        return "/myBatis/municipalitiesAndTrees?faces-redirect=true";
    }

    private void loadAllMunicipalities() {
        allMunicipalities = municipalityMapper.selectAll();
    }
}
