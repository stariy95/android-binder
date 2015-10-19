package com.kendamasoft.binder.updaters;

import android.view.View;

/**
 * Created by stariy on 19.10.15.
 */
public class AlphaUpdater implements ViewUpdater<View, Float> {
    @Override
    public void update(View view, Float value) {
        view.setAlpha(value);
    }
}
