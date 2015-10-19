package com.kendamasoft.binder.updaters;

import android.view.View;

/**
 * Created by stariy on 03.10.2015.
 */
public interface ViewUpdater<V extends View, T> {

    void update(V view, T value);

}
