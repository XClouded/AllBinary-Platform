/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package org.allbinary.input.automation.module.generic.configuration.profile.actions;

import org.allbinary.input.automation.module.generic.configuration.profile.actions.script.condition.ColorAtActionScriptCondition;
import org.allbinary.input.automation.module.generic.configuration.profile.actions.script.GenericProfileActionScriptJPanel;
import org.allbinary.input.automation.module.generic.configuration.profile.actions.script.GenericProfileActionScriptJPanelFactory;
import org.allbinary.input.automation.module.generic.configuration.profile.actions.script.condition.AlwaysActionScriptCondition;
import org.allbinary.input.automation.module.generic.configuration.profile.actions.script.condition.ProfileActionScriptConditionInterface;
import org.allbinary.input.automation.module.generic.configuration.profile.actions.script.condition.TimeIntervalActionScriptCondition;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

/**
 *
 * @author  USER
 */
public class GenericProfileActionJPanel extends javax.swing.JPanel
{
    private GenericProfileAction genericProfileAction;
    
    private GenericProfileActionScriptJPanel genericProfileActionScriptJPanel;
    
    /** Creates new form GenericProfileActionJPanel */
    public GenericProfileActionJPanel()
    {
        initComponents();
    }

    public void updateProfileActionUI()
    {
        this.blankProfileActionScriptJPanel.removeAll();
        
        this.setGenericProfileActionScriptJPanel(
            GenericProfileActionScriptJPanelFactory.getInstance());
        
        this.getGenericProfileActionScriptJPanel().setGenericProfileActionScript(
            this.getGenericProfileAction().getGenericProfileActionScript());
        
        this.getGenericProfileActionScriptJPanel().updateJTree();

        javax.swing.GroupLayout layout =
            (javax.swing.GroupLayout)
            this.blankProfileActionScriptJPanel.getLayout();
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 395, Short.MAX_VALUE)
            .addComponent(
this.getGenericProfileActionScriptJPanel(),             javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 256, Short.MAX_VALUE)
            .addComponent(
this.getGenericProfileActionScriptJPanel(),             javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            );
        
        LogUtil.put(LogFactory.getInstance("Updated UI", this, "updateUI"));
    }

    public GenericProfileAction getGenericProfileAction()
    {
        return genericProfileAction;
    }

    public void setGenericProfileAction(
        GenericProfileAction genericProfileAction)
    {
        this.genericProfileAction = genericProfileAction;
    }

    public GenericProfileActionScriptJPanel getGenericProfileActionScriptJPanel()
    {
        return genericProfileActionScriptJPanel;
    }

    public void setGenericProfileActionScriptJPanel(
        GenericProfileActionScriptJPanel genericProfileActionScriptJPanel)
    {
        this.genericProfileActionScriptJPanel = genericProfileActionScriptJPanel;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        genericProfileActionScriptJScrollPane = new javax.swing.JScrollPane();
        blankProfileActionScriptJPanel = new javax.swing.JPanel();
        ifJLabel = new javax.swing.JLabel();
        newColorAtJButton = new javax.swing.JButton();
        newTimeIntervalJButton = new javax.swing.JButton();
        newAlwaysJButton = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(440, 275));
        setPreferredSize(new java.awt.Dimension(440, 275));
        genericProfileActionScriptJScrollPane.setPreferredSize(new java.awt.Dimension(474, 270));
        blankProfileActionScriptJPanel.setMinimumSize(new java.awt.Dimension(400, 200));
        javax.swing.GroupLayout blankProfileActionScriptJPanelLayout = new javax.swing.GroupLayout(blankProfileActionScriptJPanel);
        blankProfileActionScriptJPanel.setLayout(blankProfileActionScriptJPanelLayout);
        blankProfileActionScriptJPanelLayout.setHorizontalGroup(
            blankProfileActionScriptJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9996, Short.MAX_VALUE)
        );
        blankProfileActionScriptJPanelLayout.setVerticalGroup(
            blankProfileActionScriptJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9967, Short.MAX_VALUE)
        );
        genericProfileActionScriptJScrollPane.setViewportView(blankProfileActionScriptJPanel);

        ifJLabel.setText("Conditions:");

        newColorAtJButton.setText("Color Range At");
        newColorAtJButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                newColorAtJButtonActionPerformed(evt);
            }
        });

        newTimeIntervalJButton.setText("Time Interval");
        newTimeIntervalJButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                newTimeIntervalJButtonActionPerformed(evt);
            }
        });

        newAlwaysJButton.setText("Always On/Off");
        newAlwaysJButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                newAlwaysJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ifJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newColorAtJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newTimeIntervalJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newAlwaysJButton)
                .addContainerGap(61, Short.MAX_VALUE))
            .addComponent(genericProfileActionScriptJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ifJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newColorAtJButton)
                    .addComponent(newTimeIntervalJButton)
                    .addComponent(newAlwaysJButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(genericProfileActionScriptJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
            
    private void newTimeIntervalJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_newTimeIntervalJButtonActionPerformed
    {//GEN-HEADEREND:event_newTimeIntervalJButtonActionPerformed
        LogUtil.put(LogFactory.getInstance("New Time Interval Action Condition", this, "newTimeIntervalJButtonActionPerformed"));
        
        getGenericProfileAction().getGenericProfileActionScript().addCondition(
            (ProfileActionScriptConditionInterface)
            new TimeIntervalActionScriptCondition());
        
        this.updateProfileActionUI();
    }//GEN-LAST:event_newTimeIntervalJButtonActionPerformed
    
    private void newAlwaysJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_newAlwaysJButtonActionPerformed
    {//GEN-HEADEREND:event_newAlwaysJButtonActionPerformed
        LogUtil.put(LogFactory.getInstance("New EveryTime Action Condition", this, "newEveryTimeJButtonActionPerformed"));
        
        getGenericProfileAction().getGenericProfileActionScript().addCondition(
            (ProfileActionScriptConditionInterface)
            new AlwaysActionScriptCondition());
        
        this.updateProfileActionUI();
    }//GEN-LAST:event_newAlwaysJButtonActionPerformed
    
    private void newColorAtJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_newColorAtJButtonActionPerformed
    {//GEN-HEADEREND:event_newColorAtJButtonActionPerformed
        LogUtil.put(LogFactory.getInstance("New Color At Action Condition", this, "newColorAtJButtonActionPerformed"));
        
        getGenericProfileAction().getGenericProfileActionScript().addCondition(
            (ProfileActionScriptConditionInterface)
            new ColorAtActionScriptCondition());
        
        this.updateProfileActionUI();
    }//GEN-LAST:event_newColorAtJButtonActionPerformed
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel blankProfileActionScriptJPanel;
    private javax.swing.JScrollPane genericProfileActionScriptJScrollPane;
    private javax.swing.JLabel ifJLabel;
    private javax.swing.JButton newAlwaysJButton;
    private javax.swing.JButton newColorAtJButton;
    private javax.swing.JButton newTimeIntervalJButton;
    // End of variables declaration//GEN-END:variables
    
}
