package KAGO_framework.model.ui;

import KAGO_framework.model.InteractiveGraphicalObject;

/**
 * Class representing a UI Element.
 * <p>
 * Note that Objects of this Class, or subclasses, should be registered in the ViewController at UIElements to provide full functionality
 */
public abstract class UIElement extends InteractiveGraphicalObject {

    /**
     * returns whether the object was clicked
     * @params coordinates of the click
     */
    public abstract boolean checkClickOn(double x, double y);

    /**
     * returns whether the object waits for input, in form of key pressed or released
     */
    public abstract boolean awaitInput();
}
