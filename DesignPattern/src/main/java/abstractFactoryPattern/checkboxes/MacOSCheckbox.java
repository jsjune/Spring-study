package abstractFactoryPattern.checkboxes;

import abstractFactoryPattern.Checkbox;

public class MacOSCheckbox implements Checkbox {

    @Override
    public void paint() {
        System.out.println("You have created MacOSCheckbox.");
    }
}
