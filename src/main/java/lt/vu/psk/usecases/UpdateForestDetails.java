package lt.vu.psk.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.psk.entities.Forest;
import lt.vu.psk.interceptor.LoggedInvocation;
import lt.vu.psk.persistance.ForestDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter @Setter
public class UpdateForestDetails implements Serializable {

    private Forest forest;

    @Inject
    private ForestDAO forestDAO;

    @Inject
    private EntityManager em;


    @PostConstruct
    private void init(){
        System.out.println("###########UpdateForestDetails INIT CALLED##############");
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer forestId = Integer.parseInt(requestParameters.get("forestId"));
        this.forest = forestDAO.findOne(forestId);
    }

    @LoggedInvocation
    @Transactional
    public String updateForestMemberNumber() {
        try {
            forestDAO.update(this.forest);
        } catch (OptimisticLockException e) {
            System.out.println(this.forest.getId());
            return "/forestDetails.xhtml?faces-redirect=true&forestId=" + this.forest.getId() + "&error=optimistic-lock-exception";
        }
        return "forests.xhtml?forestId=" + this.forest.getTrees() + "&faces-redirect=true";
    }

}
