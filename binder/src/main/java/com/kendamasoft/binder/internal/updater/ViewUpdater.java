package com.kendamasoft.binder.internal.updater;

import android.view.View;

public interface ViewUpdater<V extends View, T> {

    void update(V view, T value);

}
