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
package org.crossmobile.gui.parameters;

import com.panayotis.hrgui.HiResComponent;
import com.panayotis.hrgui.HiResPanel;
import org.crossmobile.gui.actives.ActiveIcon;
import org.crossmobile.gui.actives.ActiveToggleButton;
import org.crossmobile.utils.Param;
import org.crossmobile.utils.ParamList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class MultiButtonParameter extends MultiBooleanParameter {

    private ActiveToggleButton[] items;
    private String[] icons;
    private final int columns;

    public MultiButtonParameter(ParamList plist, Param key, String[] parameter, String[] display, String[] icons, String textValue, char separator) {
        this(plist, key, parameter, display, null, icons, textValue, separator);
    }

    public MultiButtonParameter(ParamList plist, Param key, String[] parameter, String[] display, String[] tooltips, String[] icons, String textValue, char separator) {
        this(plist, key, parameter, display, tooltips, icons, textValue, 0, separator);
    }

    public MultiButtonParameter(ParamList plist, Param key, String[] parameter, String[] display, String[] tooltips, String[] icons, String textValue, int columns, char separator) {
        super(plist, key, parameter, display, tooltips, textValue, separator);
        if (icons != null && icons.length != parameter.length)
            throw new RuntimeException("Icon size should match the parameter size");
        this.icons = icons;
        this.columns = columns;
    }

    @Override
    protected final void activateIndex(int idx) {
        items[idx].setSelected(true);
    }

    @Override
    protected HiResComponent initVisuals() {
        HiResPanel visuals = new HiResPanel();
        visuals.setOpaque(false);
        HiResPanel bpanel;
        if (columns <= 1) {
            bpanel = new HiResPanel();
            bpanel.setOpaque(false);
            visuals.setLayout(new BorderLayout());
            visuals.add(bpanel, BorderLayout.WEST);
        } else
            bpanel = visuals;
        GridLayout grid = new GridLayout(columns < 2 ? 1 : 0, columns < 2 ? 1 : columns);
        grid.setHgap(4);
        grid.setVgap(2);
        bpanel.setLayout(grid);

        items = new ActiveToggleButton[value.length];
        ActionListener listener = (ActionEvent ae) -> {
            int index = Integer.parseInt(ae.getActionCommand());
            value[index] = items[index].isSelected();
            fireValueUpdated();
        };
        for (int index = 0; index < items.length; index++) {
            ActiveToggleButton cb = new ActiveToggleButton(display[index], icons == null ? null : new ActiveIcon(icons[index]));
            if (columns > 1)
                cb.setHorizontalAlignment(SwingConstants.LEFT);
            cb.addActionListener(listener);
            bpanel.add(cb);
            cb.setActionCommand(String.valueOf(index));
            cb.setSelected(value[index]);
            cb.setOpaque(false);
            if (tooltips != null)
                cb.setToolTipText(tooltips[index]);
            items[index] = cb;
        }
        return visuals;
    }
}
