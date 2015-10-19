package com.kendamasoft.binder.updaters;

import android.view.View;

/**
 * Created by stariy on 19.10.15.
 */
public class EnabledUpdater implements ViewUpdater<View, Boolean> {
    @Override
    public void update(View view, Boolean value) {
        view.setEnabled(value);
    }
}
