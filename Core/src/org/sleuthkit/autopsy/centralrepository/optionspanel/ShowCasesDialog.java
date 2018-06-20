/*
 * Central Repository
 *
 * Copyright 2015-2018 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.centralrepository.optionspanel;

import java.util.List;
import java.util.logging.Level;
import javax.swing.JDialog;
import javax.swing.JFrame;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.WindowManager;
import org.sleuthkit.autopsy.centralrepository.datamodel.CorrelationCase;
import org.sleuthkit.autopsy.centralrepository.datamodel.EamDb;
import org.sleuthkit.autopsy.centralrepository.datamodel.EamDbException;
import org.sleuthkit.autopsy.coreutils.Logger;

/**
 * Dialog to display table of CorrelationCase information from the CR tab of options.
 */
@SuppressWarnings("PMD.SingularField") // UI widgets cause lots of false positives
final class ShowCasesDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    
    private final static Logger logger = Logger.getLogger(ShowCasesDialog.class.getName());

    private final ShowCasesTableModel tableModel;
    @Messages({"ShowCasesDialog.title_text=All Cases Details"})
    /**
     * Creates new form ShowCases Panel
     */
    ShowCasesDialog() {
        super((JFrame) WindowManager.getDefault().getMainWindow(),
                Bundle.ShowCasesDialog_title_text(),
                true);
        tableModel = new ShowCasesTableModel();
        initComponents();
        try {
            EamDb dbManager = EamDb.getInstance();
            List<CorrelationCase> eamCases = dbManager.getCases();          
            for(CorrelationCase eamCase : eamCases) {
                tableModel.addEamCase(eamCase);
            }
        } catch (EamDbException ex) {
            logger.log(Level.SEVERE, "Error getting list of cases from database.", ex); // NON-NLS
        }
        display();
    }

    private void display() {
        this.setLocationRelativeTo(WindowManager.getDefault().getMainWindow());
        setVisible(true);
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        showCasesPanel = new javax.swing.JPanel();
        showCasesScrollPane = new javax.swing.JScrollPane();
        outCasesPane = new javax.swing.JPanel();
        innerCaseScrollPane = new javax.swing.JScrollPane();
        caseDetailsTable = new javax.swing.JTable();
        closeButton = new javax.swing.JButton();

        setTitle(org.openide.util.NbBundle.getMessage(ShowCasesDialog.class, "ShowCasesDialog.title")); // NOI18N
        setMinimumSize(new java.awt.Dimension(545, 415));

        showCasesPanel.setPreferredSize(new java.awt.Dimension(527, 407));

        showCasesScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        showCasesScrollPane.setPreferredSize(new java.awt.Dimension(535, 415));

        innerCaseScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        caseDetailsTable.setAutoCreateRowSorter(true);
        caseDetailsTable.setModel(tableModel);
        caseDetailsTable.setToolTipText(org.openide.util.NbBundle.getMessage(ShowCasesDialog.class, "ShowCasesDialog.caseDetailsTable.toolTipText")); // NOI18N
        caseDetailsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        caseDetailsTable.getTableHeader().setReorderingAllowed(false);
        innerCaseScrollPane.setViewportView(caseDetailsTable);
        caseDetailsTable.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(ShowCasesDialog.class, "ShowCasesDialog.caseDetailsTable.AccessibleContext.accessibleDescription")); // NOI18N

        javax.swing.GroupLayout outCasesPaneLayout = new javax.swing.GroupLayout(outCasesPane);
        outCasesPane.setLayout(outCasesPaneLayout);
        outCasesPaneLayout.setHorizontalGroup(
            outCasesPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1222, Short.MAX_VALUE)
            .addGroup(outCasesPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(outCasesPaneLayout.createSequentialGroup()
                    .addComponent(innerCaseScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        outCasesPaneLayout.setVerticalGroup(
            outCasesPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(outCasesPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(innerCaseScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
        );

        showCasesScrollPane.setViewportView(outCasesPane);

        javax.swing.GroupLayout showCasesPanelLayout = new javax.swing.GroupLayout(showCasesPanel);
        showCasesPanel.setLayout(showCasesPanelLayout);
        showCasesPanelLayout.setHorizontalGroup(
            showCasesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 527, Short.MAX_VALUE)
            .addGroup(showCasesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(showCasesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE))
        );
        showCasesPanelLayout.setVerticalGroup(
            showCasesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 407, Short.MAX_VALUE)
            .addGroup(showCasesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(showCasesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(closeButton, org.openide.util.NbBundle.getMessage(ShowCasesDialog.class, "ShowCasesDialog.closeButton.text")); // NOI18N
        closeButton.setActionCommand(org.openide.util.NbBundle.getMessage(ShowCasesDialog.class, "ShowCasesDialog.closeButton.actionCommand")); // NOI18N
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 4, Short.MAX_VALUE)
                .addComponent(showCasesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(closeButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(showCasesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(closeButton)
                .addContainerGap())
        );

        closeButton.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(ShowCasesDialog.class, "ShowCasesDialog.closeButton.AccessibleContext.accessibleName")); // NOI18N

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        dispose();
    }//GEN-LAST:event_closeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable caseDetailsTable;
    private javax.swing.JButton closeButton;
    private javax.swing.JScrollPane innerCaseScrollPane;
    private javax.swing.JPanel outCasesPane;
    private javax.swing.JPanel showCasesPanel;
    private javax.swing.JScrollPane showCasesScrollPane;
    // End of variables declaration//GEN-END:variables



}
