package com.kendamasoft.binder.internal.updater;

import android.widget.Spinner;

class SpinnerUpdater implements ViewUpdater<Spinner, Integer> {
    @Override
    public void update(Spinner view, Integer value) {
        view.setSelection(value);
    }
}
