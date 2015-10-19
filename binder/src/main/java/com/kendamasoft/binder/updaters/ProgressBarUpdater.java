package com.kendamasoft.binder.updaters;

import android.widget.ProgressBar;

/**
 * Created by stariy on 16.10.15.
 */
public class ProgressBarUpdater implements ViewUpdater<ProgressBar, Integer> {
    @Override
    public void update(ProgressBar view, Integer value) {
        view.setProgress(value);
    }
}
