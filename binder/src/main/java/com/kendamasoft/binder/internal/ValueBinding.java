package com.kendamasoft.binder.internal;

import android.view.View;

import com.kendamasoft.binder.Binder;
import com.kendamasoft.binder.internal.adapter.ViewChangeListener;
import com.kendamasoft.binder.internal.updater.ViewUpdaterFactory;

import java.lang.reflect.Field;

public class ValueBinding extends BaseBinding {

    public ValueBinding(Object object, Field field) {
        super(object, field);
    }

    @Override
    public void setView(View view) {
        super.setView(view);
        updater = ViewUpdaterFactory.getUpdaterForView(view);
        ViewChangeListener changeListener = Binder.getListenerForView(view);
        ViewChangeListener.Callback callback = new ViewChangeListener.Callback() {
            @Override
            public void call(Object value) {
                setValue(value);
            }
        };
        changeListener.addCallback(callback);
    }

}
