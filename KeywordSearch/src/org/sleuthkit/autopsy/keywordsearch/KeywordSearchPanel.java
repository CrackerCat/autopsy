/*
 * Autopsy Forensic Browser
 * 
 * Copyright 2011 Basis Technology Corp.
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

/*
 * KeywordSearchPanel
 *
 */
package org.sleuthkit.autopsy.keywordsearch;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import org.apache.solr.client.solrj.SolrServerException;
import org.sleuthkit.autopsy.casemodule.Case;

/**
 *
 * @author dfickling
 */
public class KeywordSearchPanel extends AbstractKeywordSearchPerformer{

    private static final Logger logger = Logger.getLogger(KeywordSearchPanel.class.getName());
    private KeywordPropertyChangeListener listener;
    private boolean active = false;

    /** Creates new form KeywordSearchPanel */
    public KeywordSearchPanel() {
        initComponents();
        customizeComponents();
    }

    private void customizeComponents() {
        
        listener = new KeywordPropertyChangeListener();
        
        KeywordSearch.getServer().addServerActionListener(listener);
        
        Case.addPropertyChangeListener(listener);
        
        searchBox.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchBox.getText().equals("Search...")) {
                    searchBox.setText("");
                    searchBox.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (searchBox.getText().equals("")) {
                    resetSearchBox();
                }
            }
        });
        KeywordSearchListsViewerPanel listsPanel = KeywordSearchListsViewerPanel.getDefault();
        listsPanel.addSearchButtonActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                listsMenu.setVisible(false);
            }
            
        });
        listsMenu.add(listsPanel);
        
    }
    
    private void resetSearchBox() {
        searchBox.setEditable(true);
        searchBox.setText("Search...");
        searchBox.setForeground(Color.LIGHT_GRAY);
        regExCheckboxMenuItem.setEnabled(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        settingsMenu = new javax.swing.JPopupMenu();
        regExCheckboxMenuItem = new javax.swing.JCheckBoxMenuItem();
        listsMenu = new javax.swing.JPopupMenu();
        searchBoxPanel = new javax.swing.JPanel();
        searchBox = new javax.swing.JTextField();
        settingsLabel = new javax.swing.JLabel();
        listsButton = new javax.swing.JButton();

        regExCheckboxMenuItem.setText(org.openide.util.NbBundle.getMessage(KeywordSearchPanel.class, "KeywordSearchPanel.regExCheckboxMenuItem.text")); // NOI18N
        settingsMenu.add(regExCheckboxMenuItem);

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(2000, 23));

        searchBoxPanel.setBorder(new javax.swing.border.LineBorder(java.awt.Color.lightGray, 1, true));
        searchBoxPanel.setPreferredSize(new java.awt.Dimension(255, 18));

        searchBox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        searchBox.setForeground(java.awt.Color.lightGray);
        searchBox.setText(org.openide.util.NbBundle.getMessage(KeywordSearchPanel.class, "KeywordSearchPanel.searchBox.text")); // NOI18N
        searchBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 4, 1));
        searchBox.setEnabled(false);
        searchBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBoxActionPerformed(evt);
            }
        });

        settingsLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/sleuthkit/autopsy/keywordsearch/search-icon.png"))); // NOI18N
        settingsLabel.setText(org.openide.util.NbBundle.getMessage(KeywordSearchPanel.class, "KeywordSearchPanel.settingsLabel.text")); // NOI18N
        settingsLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 2, 1, 2));
        settingsLabel.setMaximumSize(new java.awt.Dimension(23, 20));
        settingsLabel.setMinimumSize(new java.awt.Dimension(23, 20));
        settingsLabel.setPreferredSize(new java.awt.Dimension(23, 20));
        settingsLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                settingsLabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout searchBoxPanelLayout = new javax.swing.GroupLayout(searchBoxPanel);
        searchBoxPanel.setLayout(searchBoxPanelLayout);
        searchBoxPanelLayout.setHorizontalGroup(
            searchBoxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchBoxPanelLayout.createSequentialGroup()
                .addComponent(settingsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(searchBox, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
        searchBoxPanelLayout.setVerticalGroup(
            searchBoxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchBoxPanelLayout.createSequentialGroup()
                .addGroup(searchBoxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(settingsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        listsButton.setText(org.openide.util.NbBundle.getMessage(KeywordSearchPanel.class, "KeywordSearchPanel.listsButton.text")); // NOI18N
        listsButton.setEnabled(false);
        listsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                listsButtonMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(1648, Short.MAX_VALUE)
                .addComponent(listsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchBoxPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchBoxPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(listsButton))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBoxActionPerformed
        if (filesIndexed == 0)
            return;

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        try {
            search();
        } finally {
            setCursor(null);
        }
    }//GEN-LAST:event_searchBoxActionPerformed

    private void settingsLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsLabelMousePressed
        maybeShowSettingsPopup(evt);
    }//GEN-LAST:event_settingsLabelMousePressed

    private void listsButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listsButtonMousePressed
        maybeShowListsPopup(evt);
    }//GEN-LAST:event_listsButtonMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton listsButton;
    private javax.swing.JPopupMenu listsMenu;
    private javax.swing.JCheckBoxMenuItem regExCheckboxMenuItem;
    private javax.swing.JTextField searchBox;
    private javax.swing.JPanel searchBoxPanel;
    private javax.swing.JLabel settingsLabel;
    private javax.swing.JPopupMenu settingsMenu;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getQueryText() {
        return searchBox.getText();
    }

    @Override
    public boolean isLuceneQuerySelected() {
        return !regExCheckboxMenuItem.isSelected();
    }

    @Override
    public boolean isMultiwordQuery() {
        return false;
    }

    @Override
    public List<Keyword> getQueryList() {
        throw new UnsupportedOperationException("No list for single-keyword search");
    }
    
    private class KeywordPropertyChangeListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            String changed = evt.getPropertyName();
            Object oldValue = evt.getOldValue();
            Object newValue = evt.getNewValue();
            
            if (changed.equals(Case.CASE_CURRENT_CASE)) {
                if (newValue == null) {
                    setFields(false);
                } else {
                    setFields(true);
                }
            } else if (changed.equals(Server.CORE_EVT)) {
                final Server.CORE_EVT_STATES state = (Server.CORE_EVT_STATES) newValue;
                switch (state) {
                    case STARTED:
                        try {
                            final int numIndexedFiles = KeywordSearch.getServer().getCore().queryNumIndexedFiles();
                            KeywordSearch.changeSupport.firePropertyChange(KeywordSearch.NUM_FILES_CHANGE_EVT, null, new Integer(numIndexedFiles));
                            //setFilesIndexed(numIndexedFiles);
                        } catch (SolrServerException se) {
                            logger.log(Level.SEVERE, "Error executing Solr query, " + se.getMessage());
                        }
                        break;
                    case STOPPED:
                        break;
                    default:
                }
            }
        }
        
        private void setFields(boolean enabled){
            searchBox.setEnabled(enabled);
            regExCheckboxMenuItem.setEnabled(enabled);
            settingsLabel.setEnabled(enabled);
            listsButton.setEnabled(enabled);
            active = enabled;
        }
    }
    
    private void maybeShowSettingsPopup (MouseEvent evt) {
        if(!active) {
            return;
        }
        if (evt != null && !SwingUtilities.isLeftMouseButton(evt)) {
            return;
        }
        
        settingsMenu.show(searchBoxPanel, 0, searchBoxPanel.getHeight());
    }
    
    private void maybeShowListsPopup (MouseEvent evt) {
        if(!active) {
            return;
        }
        if (evt != null && !SwingUtilities.isLeftMouseButton(evt)) {
            return;
        }
        
        listsMenu.show(listsButton, listsButton.getWidth()-listsMenu.getWidth(), listsButton.getHeight());
    }
}
