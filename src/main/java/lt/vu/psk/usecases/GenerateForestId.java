package lt.vu.psk.usecases;

import lt.vu.psk.interceptor.LoggedInvocation;
import lt.vu.psk.services.ForestIdGenerator;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class GenerateForestId implements Serializable {

    @Inject
    ForestIdGenerator forestIdGenerator;

    private CompletableFuture<Integer> forestIdGenerationTask = null;

    @LoggedInvocation
    public String generateNewForestId(){
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        forestIdGenerationTask = CompletableFuture.supplyAsync(() -> forestIdGenerator.generateForestId());

        return "/forestDetails.xhtml?faces-redirect=true&forestId=" + requestParameters.get("forestId");
    }

    public boolean isForestIdGenerationRunning(){
        return forestIdGenerationTask != null && !forestIdGenerationTask.isDone();
    }

    public String getForestIdGeneratorStatus() throws ExecutionException, InterruptedException {
        if (forestIdGenerationTask == null) {
            return null;
        } else if (isForestIdGenerationRunning()){
            return "Forest Id Generator in progress";
        }
        return "Suggested Forest Id: " + forestIdGenerationTask.get();
    }
}
