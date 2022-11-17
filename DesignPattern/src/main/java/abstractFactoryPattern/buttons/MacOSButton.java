package abstractFactoryPattern.buttons;

import abstractFactoryPattern.Button;

public class MacOSButton implements Button {

    @Override
    public void paint() {
        System.out.println("You have created MacOSButton.");
    }
}
