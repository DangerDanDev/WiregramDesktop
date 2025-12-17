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
    
    private ArrayList<DiagramObject> nodes;
    public ArrayList<DiagramObject> getNodes() {
        return nodes;
    }
    public final void setNodes(ArrayList<DiagramObject> nodes) {
        this.nodes = nodes;
    }
    
    private final Object root;
    
    public TreePath getPath(DiagramObject node) {
        ArrayList<Object> pathNodes = new ArrayList<>();
        
        do {
            pathNodes.addFirst(node);
            
            if(node.getParent() != null)
                node = node.getParent();
        } while (node.getParent() != null);
        
        pathNodes.addFirst(root);
        
        System.out.println(pathNodes.toString());
        return new TreePath(pathNodes.toArray());
    }
    
    public DiagramObjectChildrenTreeModel(Object root, ArrayList<DiagramObject> nodes) {
        this.root = root;
        setNodes(nodes);
    }

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object getChild(Object parent, int index) {
        //root of the tree model is a stand-in, the real root is
        //the ArrayList passed into the constructor
        if(!(parent instanceof DiagramObject))
            return nodes.get(index);
        
        DiagramObject dobj_Parent = (DiagramObject) parent;
        return dobj_Parent.getChildren().get(index);
    }

    @Override
    public int getChildCount(Object parent) {
        if(!(parent instanceof DiagramObject))
            return nodes.size();
        
        DiagramObject dobj_Parent = (DiagramObject) parent;
        return dobj_Parent.getChildren().size();
    }

    @Override
    public boolean isLeaf(Object node) {
        if(!(node instanceof DiagramObject))
            return false;
        
        DiagramObject dobj_node = (DiagramObject) node;
        return dobj_node.getChildren().isEmpty();
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        if(!(parent instanceof DiagramObject))
            return nodes.indexOf(child);
        
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
