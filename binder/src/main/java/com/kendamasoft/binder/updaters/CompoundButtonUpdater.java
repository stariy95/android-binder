package com.kendamasoft.binder.updaters;

import android.widget.CompoundButton;

/**
 * Created by stariy on 03.10.2015.
 */
public class CompoundButtonUpdater implements ViewUpdater<CompoundButton, Boolean> {
    @Override
    public void update(CompoundButton view, Boolean value) {
        view.setChecked(value);
    }
}
