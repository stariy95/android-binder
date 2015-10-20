package com.kendamasoft.binder.internal.updater;

import android.view.View;

class AlphaUpdater implements ViewUpdater<View, Float> {
    @Override
    public void update(View view, Float value) {
        view.setAlpha(value);
    }
}
