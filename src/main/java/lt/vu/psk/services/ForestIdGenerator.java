package lt.vu.psk.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class ForestIdGenerator implements Serializable {

    public Integer generateForestId(){
        try{
            Thread.sleep(5000);
        } catch (InterruptedException e){
        }
        Integer generatedForestId = new Random().nextInt(100);
        return generatedForestId;
    }
}