package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.Vector;
import KAGO_framework.view.DrawTool;

import java.awt.*;

/**
 * Repräsentiert eine Kugel (einen Kreis), der in eine Schlange eingefügt werden soll. Dabei muss jeder QueueBall immer
 * seinen Vorgänger kennen, damit er zu ihm Abstand halten kann.
 */
public class Ball extends GraphicalObject {

    /**
     * Erzeugt einen neuen QueueBall
     * @param x Startposition x
     * @param y Startposition y
     */
    public Ball(double x, double y){
        coordinates = new Vector(x, y);
    }


    /**
     * Selbsterklärend: zeichnet den die optische Repräsentation eines Ball-Objekts. Wird vom Framework automatisch aufgerufen (jeden Frame 1x).
     */
    @Override
    public void draw(DrawTool drawTool) {
        drawTool.drawFilledCircle(coordinates.x(), coordinates.y(), 20);
        drawTool.setCurrentColor(Color.RED);
        drawTool.drawCircle(coordinates.x(), coordinates.y(),10);
        drawTool.drawCircle(coordinates.x(), coordinates.y(),5);
    }

    /**
     * Wird mit jedem Frame vom Framework aufgerufen und dient zur Manipulation des Objekts im Verlauf
     * der Zeit.
     * @param dt die Sekunden, die seit dem letzten Aufruf von update vergangen sind
     */
    @Override
    public void update(double dt){

    }

}
