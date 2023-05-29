package lt.vu.psk.alternative;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;

@Dependent
@Alternative
public class AlternativeForestMessage implements Message {
    @Override
    public String WriteMessage() {
        return "Alternative forest created";
    }

    public AlternativeForestMessage() {

    }
}
