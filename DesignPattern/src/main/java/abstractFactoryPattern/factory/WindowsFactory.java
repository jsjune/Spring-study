package abstractFactoryPattern.factory;

import abstractFactoryPattern.Button;
import abstractFactoryPattern.Checkbox;
import abstractFactoryPattern.GUIFactory;
import abstractFactoryPattern.buttons.WindowsButton;
import abstractFactoryPattern.checkboxes.WindowsCheckbox;

public class WindowsFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}
