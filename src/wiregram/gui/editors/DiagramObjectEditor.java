/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package wiregram.gui.editors;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Optional;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import model.DiagramObject;
import model.ListEventManager;
import wiregram.gui.DiagramTreeModel;

/**
 *
 * @author scyth
 */
public class DiagramObjectEditor extends javax.swing.JPanel {

    /**
     * Creates new form DiagramObjectEditor
     */
    public DiagramObjectEditor() {
        initComponents();
    }
    
    private DiagramObject primaryItem;
    public void setPrimaryItem(DiagramObject item) {        
        this.primaryItem = item;
        
        setVisible(this.primaryItem != null);
        
        refreshChildList();
        
        if(item != null) {
            tfName.setText(item.getName());
            tfRefDes.setText(item.getRefDes());
            locationX.setValue(item.getX());
            locationY.setValue(item.getY());
            width.setValue(item.getWidth());
            height.setValue(item.getHeight());
        } 
    }

    private void refreshChildList() {
        DefaultListModel<DiagramObject> listModel = new DefaultListModel<>();
        
        if(getPrimaryItem() != null) {
            for(DiagramObject child : primaryItem.getChildren()) {
                listModel.addElement(child);
                for (DiagramObject grandchild : child.getChildren()) {
                    listModel.addElement(grandchild);
                }
            }
        }
    }
    
    public DiagramObject getPrimaryItem() { return this.primaryItem; }
    public void pushChangesToPrimaryItem() {
        getPrimaryItem().setName(tfName.getText());
        getPrimaryItem().setRefDes(tfRefDes.getText());
        getPrimaryItem().setLocation((int)locationX.getValue(), (int)locationY.getValue());
        getPrimaryItem().setSize((int)width.getValue(), (int)height.getValue());
    }
    
    @Override
    public void setEnabled(boolean enabled) {
        tfName.setEnabled(enabled);
        tfRefDes.setEnabled(enabled);
        locationX.setEnabled(enabled);
        locationY.setEnabled(enabled);
        width.setEnabled(enabled);
        height.setEnabled(enabled);
        
        selectedItemsTree.setEnabled(enabled);
        
        super.setEnabled(enabled);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    /// INTERNAL EVENT HANDLING
    ////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////
    
    public final SelectedItemsTreeListener SELECTED_ITEMS_TREE_LISTENER = new SelectedItemsTreeListener();
    /**
     * Listens for any changes in which DiagramObject is selected in the Tree
     * and calls setPrimaryItem(tree.SelectedItem
     */
    private class SelectedItemsTreeListener implements TreeSelectionListener {
        /**
         * Listens for selection events from selectedItemsTree
         * @param e 
         */
        @Override
        public void valueChanged(TreeSelectionEvent e) {
            
            if(selectedItemsTree.getLastSelectedPathComponent() instanceof DiagramObject selection) 
                setPrimaryItem(selection);
           
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    /// EXTERNAL EVENT HANDLING
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    
    public final MasterListSelectionChangeListener MASTER_LIST_SELECTION_CHANGE_LISTENER = new MasterListSelectionChangeListener();
    private class MasterListSelectionChangeListener implements ListSelectionListener {
        
        @Override
        public void valueChanged(ListSelectionEvent e) {
            JList source = (JList)e.getSource();
            setPrimaryItem((DiagramObject)source.getSelectedValue());
        }
        
    }

    public final DiagramSelectionListener DIAGRAM_SELECTION_LISTENER = new DiagramSelectionListener();
    /**
     * Listens for changes to the SelectedItems list on the diagram
     */
    public class DiagramSelectionListener implements ListEventManager.ListListener<DiagramObject> {

        @Override
        public void listUpdated(ArrayList<DiagramObject> selectedItems) {
            if(selectedItems == null || selectedItems.isEmpty()) 
                setPrimaryItem(null);
            else {
                DiagramTreeModel model = new DiagramTreeModel("Selection", selectedItems);
                selectedItemsTree.setModel(model);

                TreePath primaryItemPath = model.getPath(selectedItems.getFirst());
                selectedItemsTree.getSelectionModel().setSelectionPath(primaryItemPath);
                selectedItemsTree.scrollPathToVisible(primaryItemPath);
            }
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

        jSeparator1 = new javax.swing.JSeparator();
        nameRefDesPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfRefDes = new javax.swing.JTextField();
        locationSizePanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        locationX = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        locationY = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        width = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        height = new javax.swing.JSpinner();
        btnSave = new javax.swing.JButton();
        selectedItemsScroller = new javax.swing.JScrollPane();
        selectedItemsTree = new javax.swing.JTree();
        selectedItemsTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        selectedItemsTree.addTreeSelectionListener(SELECTED_ITEMS_TREE_LISTENER);

        setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Name: ");

        tfName.setText("jTextField1");
        tfName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel2.setText("RefDes: ");

        tfRefDes.setText("jTextField1");
        tfRefDes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout nameRefDesPanelLayout = new javax.swing.GroupLayout(nameRefDesPanel);
        nameRefDesPanel.setLayout(nameRefDesPanelLayout);
        nameRefDesPanelLayout.setHorizontalGroup(
            nameRefDesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nameRefDesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nameRefDesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(nameRefDesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfRefDes, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                    .addComponent(tfName))
                .addContainerGap())
        );
        nameRefDesPanelLayout.setVerticalGroup(
            nameRefDesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nameRefDesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nameRefDesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(nameRefDesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfRefDes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        locationSizePanel.setLayout(new java.awt.GridLayout(2, 4));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("X: ");
        locationSizePanel.add(jLabel3);
        locationSizePanel.add(locationX);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Y: ");
        locationSizePanel.add(jLabel5);
        locationSizePanel.add(locationY);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Width: ");
        locationSizePanel.add(jLabel4);
        locationSizePanel.add(width);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Height: ");
        locationSizePanel.add(jLabel6);
        locationSizePanel.add(height);

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        selectedItemsScroller.setViewportView(selectedItemsTree);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectedItemsScroller, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnSave, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(locationSizePanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nameRefDesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(selectedItemsScroller, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameRefDesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(locationSizePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave)
                .addContainerGap(11, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        pushChangesToPrimaryItem();
    }//GEN-LAST:event_btnSaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JSpinner height;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel locationSizePanel;
    private javax.swing.JSpinner locationX;
    private javax.swing.JSpinner locationY;
    private javax.swing.JPanel nameRefDesPanel;
    private javax.swing.JScrollPane selectedItemsScroller;
    private javax.swing.JTree selectedItemsTree;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfRefDes;
    private javax.swing.JSpinner width;
    // End of variables declaration//GEN-END:variables

}
