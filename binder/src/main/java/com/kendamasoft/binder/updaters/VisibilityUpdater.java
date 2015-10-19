package com.kendamasoft.binder.updaters;

import android.view.View;

/**
 * Created by stariy on 19.10.15.
 */
public class VisibilityUpdater implements ViewUpdater<View, Boolean> {
    @Override
    public void update(View view, Boolean value) {
        view.setVisibility(value ? View.VISIBLE : View.INVISIBLE);
    }
}
