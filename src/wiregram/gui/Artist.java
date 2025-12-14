/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wiregram.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import model.Component;
import model.Connector;
import model.Diagram;
import model.math.Coordinate;
import model.DiagramObject;
import model.math.Rect;

/**
 *
 * @author scyth
 */
public class Artist {
    
    public static final Color DEFAULT_DRAW_COLOR = Color.BLACK;
    public static final Stroke DEFAULT_STROKE = new BasicStroke(1);
    
    private static void resetColorAndStroke(Graphics2D g) { 
        g.setColor(DEFAULT_DRAW_COLOR); 
        g.setStroke(DEFAULT_STROKE); 
    }
    
    public static final Color SELECTED_ITEM_DRAW_COLOR = Color.GREEN;
    public static final Stroke SELECTED_ITEM_STROKE = new BasicStroke(2);
    
    public static void draw(Coordinate camera, Diagram diagram, ArrayList<DiagramObject> selectedItems, Graphics2D g) {
        for(DiagramObject item : diagram.getItems()) {
            drawItem(camera, item, selectedItems, g);
            resetColorAndStroke(g);
        }
    }
    
    public static void drawItem(Coordinate camera, DiagramObject item, ArrayList<DiagramObject> selectedItems, Graphics2D g2d) {
        //get the component location relative to the camera
        Coordinate screenCoord = item.getLocation().onScreen(camera);
        
        if(selectedItems.contains(item)) {
                g2d.setColor(SELECTED_ITEM_DRAW_COLOR);
                g2d.setStroke(SELECTED_ITEM_STROKE);
        } 
        
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
        
        for(DiagramObject child : item.getChildren())
            drawItem(camera, child, selectedItems, g2d);

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
