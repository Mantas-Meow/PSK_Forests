package lt.vu.psk.decorator;

import javax.enterprise.context.Dependent;

@Dependent
public class Decorator  implements ForestDecorator{
    @Override
    public String DecoratedString(String string) {
        return string;
    }
}
