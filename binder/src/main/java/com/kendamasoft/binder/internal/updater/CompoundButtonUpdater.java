package com.kendamasoft.binder.internal.updater;

import android.widget.CompoundButton;

class CompoundButtonUpdater implements ViewUpdater<CompoundButton, Boolean> {
    @Override
    public void update(CompoundButton view, Boolean value) {
        view.setChecked(value);
    }
}
