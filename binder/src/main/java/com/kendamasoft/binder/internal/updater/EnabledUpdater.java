package com.kendamasoft.binder.internal.updater;

import android.view.View;

class EnabledUpdater implements ViewUpdater<View, Boolean> {
    @Override
    public void update(View view, Boolean value) {
        view.setEnabled(value);
    }
}
