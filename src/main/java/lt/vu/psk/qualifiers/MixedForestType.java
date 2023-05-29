package lt.vu.psk.qualifiers;

import javax.enterprise.context.Dependent;

@Mixed
@Dependent
public class MixedForestType implements ForestTypeProcessor {
    @Override
    public String ForestType() {
        return "Forest type is mixed";
    }
}
