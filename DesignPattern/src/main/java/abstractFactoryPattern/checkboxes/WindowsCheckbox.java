package abstractFactoryPattern.checkboxes;

import abstractFactoryPattern.Checkbox;

public class WindowsCheckbox implements Checkbox {

    @Override
    public void paint() {
        System.out.println("You have created WindowsCheckbox.");
    }
}
