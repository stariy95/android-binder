package com.kendamasoft.binder.updaters;

import android.widget.TextView;

/**
 * Created by stariy on 03.10.2015.
 */
public class TextViewUpdater implements ViewUpdater<TextView, Object> {

    @Override
    public void update(TextView view, Object value) {
        view.setText(value == null ? "" : value.toString());
    }
}
