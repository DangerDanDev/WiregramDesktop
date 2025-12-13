/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wiregram.gui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import model.Component;
import model.Connector;
import model.math.Coordinate;
import model.DiagramObject;
import model.math.Rect;

/**
 *
 * @author scyth
 */
public class Artist {
    public static void drawDiagramItem(Coordinate camera, DiagramObject item, Graphics2D g2d) {
        
        //get the component location relative to the camera
        Coordinate screenCoord = item.getLocation().onScreen(camera);
        
        if(item instanceof Connector connector) {
            drawConnector(screenCoord, connector, g2d);
        }   
        else {
            //draw the component box
            g2d.drawRect(screenCoord.getX(), screenCoord.getY(), item.getWidth(), item.getHeight());
        }
        //We want to center the Name and Reference Designator below the component
        //first get the length of the Name + RefDes string
        String componentText = item.toString();        FontMetrics fontMetrics = g2d.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(componentText);
        
        //calculate the lateral center of the component
        int componentCenterX = (screenCoord.getX() + screenCoord.getX() + item.getWidth()) / 2;
        int textX = componentCenterX - textWidth / 2;
        
        g2d.drawString(componentText, 
                textX, //center the text under the component
                screenCoord.getY() + item.getHeight() + g2d.getFontMetrics().getHeight());
    }
    
    public static void drawConnector(Coordinate relativeCoordinate, Connector connector, Graphics2D g) {
        
        final int PLUG_SIZE_REDUCE = Connector.PLUG_SIZE_REDUCTION;
        
        //jack half of the connector is just a plain ol' rectangle that covers half the width of the connector, nice and easy
        g.drawRect(relativeCoordinate.getX(), relativeCoordinate.getY(), connector.getWidth()/2, connector.getHeight());
        
        //plug half we shave off a little on the top and bottom
        g.drawRect(relativeCoordinate.getX() + connector.getWidth()/2, relativeCoordinate.getY() + PLUG_SIZE_REDUCE, 
                   connector.getWidth()/2, connector.getHeight() - 2*PLUG_SIZE_REDUCE);
        
    }
    
    public static void drawSelectionRect(Rect selectionRect, Graphics2D g) {
        g.drawRect(selectionRect.getX(), selectionRect.getY(), selectionRect.getWidth(), selectionRect.getHeight());
    }
}
