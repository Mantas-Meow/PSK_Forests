package lt.vu.psk.decorator;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public class DecoratorImplementation implements ForestDecorator {
    @Inject
    @Delegate
    @Any
    ForestDecorator forestDecorator;

    public String DecoratedString(String string){
        System.out.println("Decorator is used");
        return forestDecorator.DecoratedString("Decorator String ") + "Implemented decorator";
    }
}
