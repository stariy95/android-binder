package com.kendamasoft.binder.internal.updater;

import android.widget.TextView;

class TextViewUpdater implements ViewUpdater<TextView, Object> {

    @Override
    public void update(TextView view, Object value) {
        view.setText(value == null ? "" : value.toString());
    }
}
