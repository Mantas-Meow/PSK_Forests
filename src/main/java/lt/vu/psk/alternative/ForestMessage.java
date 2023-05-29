package lt.vu.psk.alternative;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;

@Dependent
@Alternative
public class ForestMessage implements Message {
    @Override
    public String WriteMessage() {
        return "Forest created";
    }

    public ForestMessage() {

    }
}
