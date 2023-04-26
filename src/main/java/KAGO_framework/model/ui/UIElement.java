package KAGO_framework.model.ui;

import KAGO_framework.model.InteractiveGraphicalObject;

/**
 * Class representing a UI Element
 */
public abstract class UIElement extends InteractiveGraphicalObject {

    /**
     * returns whether the object was clicked
     * @params coordinates of the click
     */
    public abstract boolean checkClickOn(double x, double y);

    /**
     * Returns a new UIElement, if needed, that complements the current UIElement.
     * Note that this method will only be called once at the first click on this Element
     */
    public UIElement getExpandable(){
        return null;
    }
}
