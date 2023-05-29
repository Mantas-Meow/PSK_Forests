package lt.vu.psk.qualifiers;

import javax.enterprise.context.Dependent;

@Leafed
@Dependent
public class LeafedForestType implements ForestTypeProcessor {
    @Override
    public String ForestType() {
        return "Forest type is Leafed";
    }
}
