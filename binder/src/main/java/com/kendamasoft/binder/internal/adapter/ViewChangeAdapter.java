package com.kendamasoft.binder.internal.adapter;

import android.view.View;

import java.lang.ref.WeakReference;

public abstract class ViewChangeAdapter<T, V extends View> {

    ViewChangeListener changeListener;

    WeakReference<V> viewRef;

    public ViewChangeAdapter(final V view, final ViewChangeListener changeListener) {
        this.viewRef = new WeakReference<>(view);
        this.changeListener = changeListener;
    }

    protected void onUpdate(T value) {
        changeListener.onViewChange(value);
    }
}
