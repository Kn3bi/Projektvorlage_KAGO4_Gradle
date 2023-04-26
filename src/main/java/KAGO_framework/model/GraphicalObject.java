package KAGO_framework.model;

import KAGO_framework.Config;
import KAGO_framework.view.DrawTool;
import KAGO_framework.control.Drawable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Zur Vererbung. Methoden können nach Bedarf überschrieben werden.
 * Vorgegebene Klasse des Frameworks. Modifikation auf eigene Gefahr.
 */
public class GraphicalObject implements Drawable {

    // Attribute: um Konstruktoraufrufzwang zu vermeiden wurden hier AUSNAHMSWEISE Startwerte gesetzt
    protected Vector coordinates = new Vector(); // Die Koordinaten des Objekts
    protected double width = 0, height = 0; // Die rechteckige Ausdehnung des Objekts, wobei x/y die obere, linke Ecke angeben
    protected double radius = 0; //Falls ein Radius gesetzt wurde (also größer als 0 ist), wird collidesWith angepasst.

    // Referenzen
    private BufferedImage myImage;

    /**
     * Der generische Konstruktur ermöglicht einen optionalen super-Aufruf in den Unterklassen
     */
    public GraphicalObject(){

    }

    /**
     * Mit diesem Konstruktor kann direkt ein GraphicalObject mit Bild und grundlegenden Methoden
     * verwendet werden.
     * @param picturePath der Pfad zur Bilddatei
     */
    public GraphicalObject(String picturePath){
        this.setNewImage(picturePath);
    }

    /**
     * Mit diesem Konstruktor kann direkt ein GraphicalObject mit Bild und grundlegenden Methoden
     * verwendet werden. Zudem kann es positioniert werden.
     * @param x die x-Koordinate (obere linke Ecke)
     * @param y die y-Koordinate (obere linke Ecke)
     * @param picturePath
     */
    public GraphicalObject(String picturePath, double x, double y){
        coordinates = new Vector(x, y);
        this.setNewImage(picturePath);
    }

    /**
     * Lädt ein Bild, das zur Repräsentation des Objekts benutzt werden kann.
     * Passt automatisch die Attribute für Breite und Höhe der des Bildes an.
     * @param pathToImage Der Pfad zu dem zu ladenden Bild
     */
    public BufferedImage createImage(String pathToImage){
        BufferedImage tmpImage = null;
        try {
            tmpImage = ImageIO.read(new File(pathToImage));
        } catch (IOException e) {
            if ( Config.INFO_MESSAGES) System.out.println("Laden eines Bildes fehlgeschlagen: "+pathToImage+"\n Hast du den Pfad und Dateinamen richtig geschrieben?");
        }
        return tmpImage;
    }

    /**
     * Lädt ein neues Bild und setzt es als aktuelles Bild
     * @param pathToImage Der Pfad zu dem zu ladenden Bild
     */
    public void setNewImage(String pathToImage){
        try {
            myImage = ImageIO.read(new File(pathToImage));
            width = myImage.getWidth();
            height = myImage.getHeight();
        } catch (IOException e) {
            if (Config.INFO_MESSAGES) System.out.println("Laden eines Bildes fehlgeschlagen: " + pathToImage);
        }
    }

    /**
     * Setzt ein BufferedImage als neues Bild, passt width und height des Objekts an die Bilddimension an
     * @param image Der Pfad zu dem zu ladenden Bild
     */
    public void setImage(BufferedImage image) {
        this.myImage = image;
        width = this.myImage.getWidth();
        height = this.myImage.getHeight();
    }

    @Override
    /**
     * Wird vom Hintergrundprozess für jeden Frame aufgerufen. Nur hier kann die grafische Repräsentation des Objekts realisiert
     * werden. Es ist möglich über das Grafikobjekt "drawTool" ein Bild zeichnen zu lassen, aber auch geometrische Formen sind machbar.
     */
    public void draw(DrawTool drawTool){
        if(getMyImage() != null) drawTool.drawImage(getMyImage(),coordinates.x(), coordinates.y());
    }

    @Override
    /**
     * Wird vom Hintergrundprozess für jeden Frame aufgerufen. Hier kann das verhalten des Objekts festgelegt werden, zum Beispiel
     * seine Bewegung.
     */
    public void update(double dt){

    }

    /**
     * Überprüft, ob das übergebene Objekt mit diesem GraphicalObject kollidiert (Rechteckkollision). Dabei werden die Koordinaten und
     * die Breite und Höhe des Objekts berücksichtigt.
     * @param gO Das Objekt, das auf Kollision überprüft wird
     * @return True, falls eine Kollision besteht, sonst false.
     */
    public boolean collidesWith(GraphicalObject gO){
        if(radius == 0){
            if(gO.getRadius() == 0)
                return coordinates.x() < gO.getX() + gO.getWidth() && coordinates.x() + width > gO.getX() && coordinates.y() < gO.getY() + gO.getHeight() && coordinates.y() + height > gO.getY();
            return coordinates.x() < gO.getX() + gO.getRadius() && coordinates.x() + width > gO.getX() - gO.getRadius() && coordinates.y() < gO.getY() + gO.getRadius() && coordinates.y() + height > gO.getY() - gO.getRadius();
        }else{
            if(gO.getRadius() == 0)
                return gO.getX() < coordinates.x() + radius && gO.getX() + gO.getWidth() > coordinates.x() - radius && gO.getY() < coordinates.y() + radius && gO.getY() + gO.getHeight() > coordinates.y() - radius;
            return getDistanceTo(gO) <= radius + gO.getRadius();

        }
    }

    /**
     * Prüft, ob ein Punkt innerhalb des GraphicalObjects liegt. Dazu müssen x,y,width und height vom
     * GraphicalObject gesetzt sein (passiert bei Bildzuweisung automatisch)
     * @param pX die x-Koordinate des Punktes
     * @param pY die y-Koordinate des Punktes
     * @return true, falls der Punkt im Objekt liegt, sonst false
     */
    public boolean collidesWith(double pX, double pY){
        if(radius == 0){
            return pX < getX() + getWidth() && pX > getX() && pY < getY() + getHeight() && pY > getY();
        }else{
            double midX = coordinates.x() + radius;
            double midY = coordinates.y() + radius;
            return Math.sqrt(Math.pow(midX - pX, 2) + Math.pow(midY - pY, 2)) < radius;
        }
    }

    /**
     * Berechnet die Distanz zwischen dem Mittelpunkt dieses Objekts und dem Mittelpunkt des übergebenen Objekts.
     * @param gO Das Objekt zu dem die Entfernung gemessen wird.
     * @return Die exakte Entfernung zwischen den Mittelpunkten
     */
    public double getDistanceTo(GraphicalObject gO){
        // Berechne die Mittelpunkte der Objekte
        double midX = coordinates.x();
        double midY = coordinates.x();
        if(radius == 0){
            midX = midX + width/2;
            midY = midY + height/2;
        }

        double midX2 = gO.getX();
        double midY2 = gO.getY();
        if(gO.getRadius() == 0){
            midX2 = midX2 + gO.getWidth()/2;
            midY2 = midY2 + gO.getHeight()/2;
        }

        // Berechne die Distanz zwischen den Punkten mit dem Satz des Pythagoras
        return Math.sqrt( Math.pow(midX-midX2, 2) + Math.pow(midY-midY2,2));
    }

    /**
     * Bewegt das GraphicalObject in eine Richtung
     * @param degrees Die Richtung als Winkel im Gradmaß (0° ist rechts, 90° ist unten, 180° ist links, 270° ist oben)
     * @param speed Die Distanz, die pro Sekunde zurückgelegt werden soll
     * @param dt Der Parameter dt aus der Methode update: die Zeit seit der letzten Frame
     */
    public void moveInDirection(double degrees, double speed, double dt){
        double dx = Math.cos(degrees/180*Math.PI)*speed*dt;
        double dy = Math.sin(degrees/180*Math.PI)*speed*dt;
        coordinates.setX(coordinates.x() + dx);
        coordinates.setY(coordinates.y() + dy);
    }

    /**
     * addiert den übergebenen Vektor auf die Koordinaten des Objektes
     */
    public void move(Vector vector){
        coordinates.add(vector);
    }

    // Sondierende Methoden: "getter"

    public double getX() {
        return coordinates.x();
    }

    public double getY() {
        return coordinates.y();
    }

    public double getZ(){
        return coordinates.z();
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getRadius(){
        return radius;
    }

    public BufferedImage getMyImage() {
        return myImage;
    }

    public Vector getCoordinates(){
        return coordinates;
    }

    // Manipulierende Methoden: "setter"

    public void setX(double x) {
        coordinates.setX(x);
    }

    public void setY(double y) {
        coordinates.setY(y);
    }

    public void setZ(double z){
        coordinates.setZ(z);
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setRadius(double radius){
        this.radius = radius;
    }

}
