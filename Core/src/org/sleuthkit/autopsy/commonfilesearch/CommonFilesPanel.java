/*
 * Autopsy Forensic Browser
 *
 * Copyright 2018 Basis Technology Corp.
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
package org.sleuthkit.autopsy.commonfilesearch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import org.sleuthkit.autopsy.casemodule.Case;
import org.sleuthkit.autopsy.casemodule.NoCurrentCaseException;
import org.sleuthkit.autopsy.corecomponents.DataResultTopComponent;
import org.sleuthkit.autopsy.corecomponents.TableFilterNode;
import org.sleuthkit.autopsy.coreutils.Logger;
import org.sleuthkit.datamodel.AbstractFile;
import org.sleuthkit.datamodel.SleuthkitCase;
import org.sleuthkit.datamodel.TskCoreException;

/**
 * Panel used for common files search configuration and configuration business
 * logic. Nested within CommonFilesDialog.
 */
public final class CommonFilesPanel extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(CommonFilesPanel.class.getName());

    /**
     * Creates new form CommonFilesPanel
     */
    public CommonFilesPanel() {
        initComponents();
        customizeComponents();
    }

    private void customizeComponents() {
        addListenerToAll(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search();
            }
        });
    }

    void addListenerToAll(ActionListener l) {
        this.searchButton.addActionListener(l);
    }

    private void search() {

        String title = NbBundle.getMessage(this.getClass(), "CommonFilesPanel.search.results.title");
        String pathText = NbBundle.getMessage(this.getClass(), "CommonFilesPanel.search.results.pathText");

        new SwingWorker<List<AbstractFile>, Void>() {

            @Override
            protected List<AbstractFile> doInBackground() throws Exception {

                List<AbstractFile> contentList = null;
                try {
                    Case currentCase = Case.getOpenCase();
                    SleuthkitCase tskDb = currentCase.getSleuthkitCase();

                    //TODO this is sort of a misues of the findAllFilesWhere function and seems brittle...
                    //...consider doing something else
                    contentList = tskDb.findAllFilesWhere("1 == 1 GROUP BY  md5 HAVING  COUNT(*) > 1;");
                } catch (TskCoreException | NoCurrentCaseException ex) {
                    LOGGER.log(Level.WARNING, "Error while trying to get common files.", ex);
                }

                if (contentList == null) {
                    contentList = Collections.<AbstractFile>emptyList();
                }

                return contentList;
            }

            @Override
            protected void done() {
                super.done();

                List<AbstractFile> contentList = null;
                CommonFilesNode sn = null;

                try {
                    contentList = get();
                } catch (InterruptedException | ExecutionException ex) {
                    LOGGER.log(Level.WARNING, "Error while trying to get common files.", ex);
                    contentList = Collections.<AbstractFile>emptyList();
                    JOptionPane.showMessageDialog(WindowManager.getDefault().getMainWindow(), ex.getCause().getMessage(), "", JOptionPane.ERROR_MESSAGE);
                } finally {
                    sn = new CommonFilesNode(contentList);

                    final TopComponent searchResultWin = DataResultTopComponent.createInstance(
                            title,
                            pathText,
                            new TableFilterNode(sn, true, sn.getName()),
                            contentList.size());
                    searchResultWin.requestActive(); // make it the active top component
                }
            }
        }.execute();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchButton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(300, 300));

        org.openide.awt.Mnemonics.setLocalizedText(searchButton, org.openide.util.NbBundle.getMessage(CommonFilesPanel.class, "CommonFilesPanel.searchButton.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(325, Short.MAX_VALUE)
                .addComponent(searchButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(266, Short.MAX_VALUE)
                .addComponent(searchButton)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton searchButton;
    // End of variables declaration//GEN-END:variables
}
