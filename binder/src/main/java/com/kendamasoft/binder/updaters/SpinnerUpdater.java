package com.kendamasoft.binder.updaters;

import android.widget.Spinner;

/**
 * Created by stariy on 20.10.15.
 */
public class SpinnerUpdater implements ViewUpdater<Spinner, Integer> {
    @Override
    public void update(Spinner view, Integer value) {
        view.setSelection(value);
    }
}
