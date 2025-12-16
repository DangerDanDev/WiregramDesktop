/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wiregram.gui;

import java.util.ArrayList;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import model.Diagram;
import model.DiagramObject;
import model.Pin;

/**
 *
 * @author scyth
 * @param <T>
 */
public class DiagramObjectChildrenTreeModel implements TreeModel{
    
    private DiagramObject primaryItem;
    public DiagramObject getPrimaryItem() {
        return primaryItem;
    }
    public final void setPrimaryItem(DiagramObject parent) {
        this.primaryItem = parent;
    }
    
    public DiagramObjectChildrenTreeModel(DiagramObject parent) {
        setPrimaryItem(parent);
    }

    @Override
    public Object getRoot() {
        return primaryItem;
    }

    @Override
    public Object getChild(Object parent, int index) {
        DiagramObject dobj_Parent = (DiagramObject) parent;
        
        return dobj_Parent.getChildren().get(index);
    }

    @Override
    public int getChildCount(Object parent) {
        DiagramObject dobj_Parent = (DiagramObject) parent;
        
        return dobj_Parent.getChildren().size();
    }

    @Override
    public boolean isLeaf(Object node) {
        return node instanceof Pin;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        DiagramObject dobj_Parent = (DiagramObject) parent;
        
        return dobj_Parent.getChildren().indexOf(child);
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
}
