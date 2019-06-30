/*
 * (c) 2019 by Panayotis Katsaloulis
 *
 * CrossMobile is free software; you can redistribute it and/or modify
 * it under the terms of the CrossMobile Community License as published
 * by the CrossMobile team.
 *
 * CrossMobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * CrossMobile Community License for more details.
 *
 * You should have received a copy of the CrossMobile Community
 * License along with CrossMobile; if not, please contact the
 * CrossMobile team at https://crossmobile.tech/contact/
 */
package org.crossmobile.foreign;

import org.crossmobile.foreign.ConnectedAndroidDispatcher.AListener;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.function.Consumer;
import java.util.prefs.Preferences;

public class AndroidTargetSelector extends JDialog implements AListener {

    private static final String LAST_DEVICE = "last.selected.device";

    private List<AndroidDevice> devicelist;
    private String last_selected = Preferences.userNodeForPackage(AndroidTargetSelector.class).get(LAST_DEVICE, null);
    private boolean ignoreSelectEvent;
    private Consumer<String> callback;
    private boolean onlyTheFirstTime = true;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public AndroidTargetSelector(Consumer<String> callback) {
        super((JDialog) null, true);
        initComponents();
        this.callback = callback;
        ConnectedAndroidDispatcher.addListener(AndroidTargetSelector.this);

        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                deviceInfo(null);
            }
        });
        deviceL.addListSelectionListener(e -> {
            int index = deviceL.getSelectedIndex();
            okB.setEnabled(index >= 0);
            if (!ignoreSelectEvent && index >= 0 && devicelist.size() > index)
                Preferences.userNodeForPackage(AndroidTargetSelector.class).put(LAST_DEVICE, last_selected = devicelist.get(index).deviceID);
            ignoreSelectEvent = false;
        });
    }

    private void deviceInfo(String device) {
        ConnectedAndroidDispatcher.removeListener(AndroidTargetSelector.this);
        setVisible(false);
        if (callback != null)
            synchronized (this) {
                if (device != null)
                    Preferences.userNodeForPackage(AndroidTargetSelector.class).put(LAST_DEVICE, device);
                callback.accept(device);
                callback = null;
                notifyAll();
            }
    }

    @Override
    public void onDeviceStateChange(List<AndroidDevice> devices) {
        if (onlyTheFirstTime && devices.size() == 1 && devices.get(0).deviceID.equals(last_selected)) {
            deviceInfo(last_selected);
            return;
        }

        onlyTheFirstTime = false;
        devicelist = devices;
        ignoreSelectEvent = true;
        deviceL.setModel(new AbstractListModel<String>() {
            @Override
            public int getSize() {
                return devicelist.size();
            }

            @Override
            public String getElementAt(int index) {
                return devicelist.get(index).toString();
            }
        });
        SwingUtilities.invokeLater(() -> {
            toFront();
            setVisible(true);
        });
        if (devices.size() >= 1) {
            okB.setEnabled(true);
            ignoreSelectEvent = true;
            int selected = getLastSelectedIndex(devices);
            deviceL.setSelectedIndex(selected < 0 ? 0 : selected);
        }
    }

    private int getLastSelectedIndex(List<AndroidDevice> devices) {
        for (int i = 0; i < devices.size(); i++)
            if (devices.get(i).deviceID.equals(last_selected))
                return i;
        return -1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        deviceL = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        okB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Android Target");
        setPreferredSize(new java.awt.Dimension(450, 260));
        setResizable(false);

        jLabel1.setFont(jLabel1.getFont().deriveFont((jLabel1.getFont().getStyle() | java.awt.Font.ITALIC)));
        jLabel1.setText("Please select deployment target");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 4, 4, 1));
        getContentPane().add(jLabel1, java.awt.BorderLayout.PAGE_START);

        deviceL.setFont(deviceL.getFont().deriveFont(deviceL.getFont().getSize()+1f));
        deviceL.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        deviceL.setFixedCellHeight(32);
        deviceL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deviceLMouseClicked(evt);
            }
        });
        deviceL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                deviceLKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(deviceL);

        getContentPane().add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        okB.setText("Accept");
        okB.setEnabled(false);
        okB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okBActionPerformed(evt);
            }
        });
        jPanel1.add(okB);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okBActionPerformed
        setVisible(false);
        int index = deviceL.getSelectedIndex();
        if (devicelist != null || index >= 0 || index < devicelist.size())
            deviceInfo(devicelist.get(index).deviceID);
        else
            deviceInfo(null);
    }//GEN-LAST:event_okBActionPerformed

    private void deviceLKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deviceLKeyTyped
        if (evt.getKeyChar() == '\n' && deviceL.getSelectedIndex() >= 0)
            okBActionPerformed(null);
    }//GEN-LAST:event_deviceLKeyTyped

    private void deviceLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deviceLMouseClicked
        if (evt.getClickCount() == 2)
            okBActionPerformed(null);
    }//GEN-LAST:event_deviceLMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> deviceL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton okB;
    // End of variables declaration//GEN-END:variables

}
