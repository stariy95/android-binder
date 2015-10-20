package com.kendamasoft.binder.internal.updater;

import android.widget.ProgressBar;

class ProgressBarUpdater implements ViewUpdater<ProgressBar, Integer> {
    @Override
    public void update(ProgressBar view, Integer value) {
        view.setProgress(value);
    }
}
