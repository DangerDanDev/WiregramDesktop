/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wiregram.gui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import model.Component;
import model.math.Coordinate;
import model.DiagramObject;
import model.math.Rect;

/**
 *
 * @author scyth
 */
public class Artist {
    public static void drawComponent(Coordinate camera, DiagramObject component, Graphics2D g2d) {
        
        //get the component location relative to the camera
        Coordinate relativeCoordinate = relativeToCamera(camera, component.getLocation());
        
        //draw the component box
        g2d.drawRect(relativeCoordinate.getX(), relativeCoordinate.getY(), component.getWidth(), component.getHeight());
        
        //We want to center the Name and Reference Designator below the component
        //first get the length of the Name + RefDes string
        String componentText = component.toString();        FontMetrics fontMetrics = g2d.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(componentText);
        
        //calculate the lateral center of the component
        int componentCenterX = (relativeCoordinate.getX() + relativeCoordinate.getX() + component.getWidth()) / 2;
        int textX = componentCenterX - textWidth / 2;
        
        g2d.drawString(componentText, 
                textX, //center the text under the component
                relativeCoordinate.getY() + component.getHeight() + g2d.getFontMetrics().getHeight());
    }
    
    public static void drawSelectionRect(Rect selectionRect, Graphics2D g) {
        g.drawRect(selectionRect.getX(), selectionRect.getY(), selectionRect.getWidth(), selectionRect.getHeight());
    }
    
    public static Coordinate relativeToCamera(Coordinate camera, Coordinate coord) {
        return new Coordinate(-camera.getX() + coord.getX(), -camera.getY() + coord.getY());
    }
}
