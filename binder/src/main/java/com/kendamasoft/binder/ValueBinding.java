package com.kendamasoft.binder;

import android.view.View;

import com.kendamasoft.binder.adapters.ViewChangeListener;
import com.kendamasoft.binder.updaters.ViewUpdaterFactory;

import java.lang.reflect.Field;

/**
 * Created by stariy on 03.10.2015.
 */
public class ValueBinding extends BaseBinding {

    public ValueBinding(Object object, Field field) {
        super(object, field);
    }

    @Override
    public void setView(View view) {
        super.setView(view);
        updater = ViewUpdaterFactory.getUpdaterForView(view);
        ViewChangeListener changeListener = BindingContext.getListenerForView(view);
        ViewChangeListener.Callback callback = new ViewChangeListener.Callback() {
            @Override
            public void call(Object value) {
                setValue(value);
            }
        };
        changeListener.addCallback(callback);
    }

}
