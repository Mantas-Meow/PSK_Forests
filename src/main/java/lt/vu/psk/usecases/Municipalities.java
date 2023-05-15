package lt.vu.psk.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.psk.entities.Municipality;
import lt.vu.psk.persistance.MunicipalityDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Municipalities {

    @Inject
    private MunicipalityDAO municipalityDAO;

    @Getter @Setter
    private Municipality municipalityToCreate = new Municipality();

    @Getter
    private List<Municipality> allMunicipalities;

    @PostConstruct
    public void init() {
        loadMunicipalities();
    }

    public void loadMunicipalities() {
        this.allMunicipalities = municipalityDAO.loadAll();
    }

    @Transactional
    public String createMunicipality() {
        this.municipalityDAO.persist(municipalityToCreate);
        return "index?faces-redirect=true";
    }
}
