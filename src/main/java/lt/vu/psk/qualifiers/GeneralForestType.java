package lt.vu.psk.qualifiers;

import javax.enterprise.inject.Specializes;

@Specializes
public class GeneralForestType extends LeafedForestType {
    public String ForestType() {
        return "Forest type is General";
    }
}
