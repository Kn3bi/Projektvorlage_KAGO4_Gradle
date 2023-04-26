package KAGO_framework.view.simple_gui;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Repräsentiert ein Label, also ein Textschildchen in einem Fenster
 */
public class Label extends GraphicalObject {

    //Attribute
    private String text;
    private int textsize;
    private boolean hasBorder;
    private int style;
    private String font;
    private int r,g,b;

    /**
     * Erzeugt ein Labelobjekt. Dieses wird nicht automatisch gezeichnet!
     * @param x x obere linke Ecke
     * @param y y obere linke Ecke
     * @param text Text für das Label
     * @param size Textgröße für das Label
     * @param hasBorder Ob das Label einen Rahmen haben soll
     */
    public Label(double x, double y, String text, int size, boolean hasBorder){
        r=0;
        b=0;
        g=0;
        this.hasBorder = hasBorder;
        coordinates.setX(x);
        coordinates.setY(y);
        this.text = text;
        this.textsize = size;
        this.font = "Arial";
        this.style = Font.PLAIN;
        width = 10 + size * text.toCharArray().length * 0.6;
        height = size * 1.1;
    }

    /**
     * Erzeugt ein Labelobjekt. Dieses wird nicht automatisch gezeichnet!
     * @param x x obere linke Ecke
     * @param y y obere linke Ecke
     * @param image Das Bild für das Label
     * @param hasBorder Ob das Label einen Rahmen haben soll
     */
    Label(double x, double y, BufferedImage image, boolean hasBorder){
        coordinates.setX(x);
        coordinates.setY(y);
        this.setImage(image);
        this.hasBorder = hasBorder;
    }

    /**
     * Ändert den Stil der Schriftart
     * @param style MagicConstant für den Stil
     */
    void setStyle(int style){
        this.style = style;
    }

    /**
     * Ändert die Schriftart
     * @param font Gewünschte Schriftart
     */
    public void setFont(String font){
        this.font = font;
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(r, g, b, 255);
        if(getMyImage() != null){
            drawTool.drawImage(getMyImage(),coordinates.x(), coordinates.y());
        }else {
            drawTool.formatText(font, style, textsize);
            drawTool.drawText(coordinates.x() + 5, coordinates.y() + height * 0.8, text);
        }
        if (hasBorder){
            drawTool.setCurrentColor(0, 0, 0, 255);
            drawTool.drawRectangle(coordinates.x(), coordinates.y(), width, height);
        }
    }

    @Override
    public void update(double dt) {

    }

    /**
     * Ändert den Text des Labels
     * @param text der neue Text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Passt die Farbe des Textes an
     * @param r Rotanteil
     * @param g Grünanteil
     * @param b Blauanteil
     */
    void setColor(int r, int g, int b){
        this.r = r;
        this.g = g;
        this.b = b;
    }
}
