package com.kendamasoft.binder.internal;

import android.util.Log;
import android.view.View;

import com.kendamasoft.binder.internal.updater.ViewUpdater;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public abstract class BaseBinding {
    protected static final String TAG = ValueBinding.class.getSimpleName();
    protected static final ThreadLocal<Boolean> inFieldUpdate = new ThreadLocal<>();

    WeakReference<Object> object;
    WeakReference<View> view;
    ViewUpdater updater;
    Field field;
    Method setter;

    public BaseBinding(Object object, Field field) {
        this.object = new WeakReference<>(object);
        this.field = field;
        this.field.setAccessible(true);
    }

    public void addSetter(Method setter) {
        this.setter = setter;
    }

    public void setView(final View view) {
        this.view = new WeakReference<>(view);
    }

    protected void setValue(Object value) {
        try {
            if(setter != null) {
                inFieldUpdate.set(Boolean.TRUE);
                setter.invoke(object.get(), value);
                inFieldUpdate.set(Boolean.FALSE);
            } else {
                field.set(object.get(), value);
            }
        } catch (Exception ex) {
            Log.w(TAG, ex);
        }
    }

    protected Object getValue() {
        try {
            return field.get(object.get());
        } catch (IllegalAccessException ex) {
            Log.w(TAG, ex);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public void notifyValueChanged() {
        if(updater != null && !Boolean.TRUE.equals(inFieldUpdate.get())) {
            updater.update(view.get(), getValue());
        }
    }
}
