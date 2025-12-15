/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package wiregram.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import model.Component;
import model.math.Coordinate;
import model.Diagram;
import model.DiagramObject;
import model.SelectionManager;
import model.math.Rect;

/**
 *
 * @author scyth
 */
public class DiagramPanel extends javax.swing.JPanel {

    private Diagram diagram = new Diagram();
    public void setDiagram(Diagram d) {this.diagram = d;}
    
    private Coordinate cameraPosition = new Coordinate();
    
    DiagramMouseAdapter mouseAdapter = new DiagramMouseAdapter();
    
    /**
     * Creates new form DiagramPanel
     */
    public DiagramPanel() {
        initComponents();
        
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }
    
    

    
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        
        Artist.draw(cameraPosition, diagram, g2d);
        
        switch (getDragMode()) {

            case DragMode.SelectionBox -> {
                Artist.drawSelectionRect(getSelectionBox(), g2d);
            }
        }
    }
    
    private Coordinate mousePressStart = new Coordinate(), mousePressEnd = new Coordinate();
    private Rect getSelectionBox() { return new Rect(mousePressStart, mousePressEnd); }
    private DragMode dragMode = DragMode.None;
    
    public enum DragMode {
            DraggingCamera,
            SelectionBox,
            None,
    }
    
    private class DiagramMouseAdapter extends MouseAdapter {
        

        
        Coordinate previous = new Coordinate();

        @Override
        public void mouseDragged(MouseEvent e) {
            
            switch(getDragMode()) {
                
                case DragMode.SelectionBox -> {
                    mousePressEnd.set(e.getX(), e.getY());
                }
                
                case DragMode.DraggingCamera -> {
                    cameraPosition.translate(previous.getX() - e.getX(), previous.getY() - e.getY());
                }
                
                default -> {
                
                }
            }

            previous.set(e.getX(), e.getY());
            revalidate();
            repaint();
            super.mouseDragged(e);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
            switch(e.getButton()) {
                case MouseEvent.BUTTON1 -> {
                    setDragMode(DragMode.SelectionBox);
                    mousePressStart.set(e.getX(), e.getY());
                    mousePressEnd.set(e.getX(), e.getY());
                }
                
                case MouseEvent.BUTTON2 -> {
                    setDragMode(DragMode.DraggingCamera);
                    previous.set(e.getX(), e.getY());
                }
                default -> setDragMode(DragMode.None);
            }

            super.mousePressed(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
            switch (getDragMode()) {

                case DragMode.SelectionBox -> {
                    if(!e.isControlDown() && !e.isShiftDown())
                        diagram.deselectAll();
                    
                    //to avoid jitter select, we require the selection box be a minimum size (1 pixel)
                    if(mousePressStart.distance(mousePressEnd) >= 1) {
                        ArrayList<DiagramObject> selectedItems = diagram.selectByRect(cameraPosition, getSelectionBox());
                        for(DiagramObject item : selectedItems) 
                            diagram.setSelected(item, true);
                    }
                }
                default -> {}
            }
            
            setDragMode(DragMode.None);
            
            repaint();
            super.mouseReleased(e);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            
            switch(e.getButton()) {
                case MouseEvent.BUTTON1 -> {
                    //must hold control or shift to select multiple items
                    if(!e.isControlDown() && !e.isShiftDown())
                        diagram.deselectAll();

                    DiagramObject newlySelected = diagram.selectItemAt(cameraPosition, e.getX(), e.getY());
                    if(newlySelected != null)
                        diagram.toggleSelected(newlySelected);

                    revalidate();
                    repaint();
                }
            }
            
            super.mouseClicked(e); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @return the dragMode
     */
    public DragMode getDragMode() {
        return dragMode;
    }

    /**
     * @param dragMode the dragMode to set
     */
    public void setDragMode(DragMode dragMode) {
        System.out.println("Drag mode changed from " + this.dragMode + " to " + dragMode);
        this.dragMode = dragMode;
        
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
