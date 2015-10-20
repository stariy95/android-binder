package com.kendamasoft.binder.internal.updater;

import android.view.View;

class VisibilityUpdater implements ViewUpdater<View, Boolean> {
    @Override
    public void update(View view, Boolean value) {
        view.setVisibility(value ? View.VISIBLE : View.INVISIBLE);
    }
}
