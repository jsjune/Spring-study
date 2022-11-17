package abstractFactoryPattern.factory;

import abstractFactoryPattern.Button;
import abstractFactoryPattern.Checkbox;
import abstractFactoryPattern.GUIFactory;
import abstractFactoryPattern.buttons.MacOSButton;
import abstractFactoryPattern.checkboxes.MacOSCheckbox;

public class MacOSFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacOSCheckbox();
    }
}
