package utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The RefreshAction class that implements the action to be performed when user pushes the Refresh button
 */
public class RefreshAction implements Action {
    private boolean mEnabled = true;

    private HashMap<String, Object> mValues = new HashMap<String, Object>();

    private List<PropertyChangeListener> mChangeListeners;

    @Override
    public Object getValue(String key) {
        return mValues.get(key);
    }

    @Override
    public void putValue(String key, Object value) {
        mValues.put(key, value);
    }

    @Override
    public void setEnabled(boolean b) {
        mEnabled = b;
    }

    @Override
    public boolean isEnabled() {
        return mEnabled;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        if (mChangeListeners == null) {
            mChangeListeners = new ArrayList<PropertyChangeListener>();
        }

        mChangeListeners.add(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        if (mChangeListeners != null && mChangeListeners.contains(listener)) {
            mChangeListeners.remove(listener);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Do nothing
    }

}
