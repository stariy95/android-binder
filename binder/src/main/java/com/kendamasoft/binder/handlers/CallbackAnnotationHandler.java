package com.kendamasoft.binder.handlers;

import android.util.Log;
import android.view.View;

import com.kendamasoft.binder.BindingContext;
import com.kendamasoft.binder.adapters.ViewChangeListener;
import com.kendamasoft.binder.annotations.Callback;
import com.kendamasoft.binder.utils.ReflectionUtils;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by stariy on 18.10.15.
 */
public class CallbackAnnotationHandler implements AnnotationHandler<Callback> {

    @Override
    public int[] getViewIds(Callback annotation) {
        return annotation.value();
    }

    @Override
    public void handle(final Object object, AccessibleObject member, View topView, List<View> viewList, Callback annotation) {
        if(!(member instanceof Method)) {
            throw new RuntimeException("Expected member of type Method for @Callback annotation, got " + member.getClass().getSimpleName());
        }
        final Method method = (Method)member;
        final boolean hasViewParam = ReflectionUtils.isMethodHasParameter(method, View.class);

        for(final View view : viewList) {
            ViewChangeListener listener = BindingContext.getListenerForView(view);
            ViewChangeListener.Callback callback;
            if(hasViewParam) {
                callback = new ViewChangeListener.Callback() {
                    @Override
                    public void call(Object value) {
                        try {
                            method.invoke(object, view, value);
                        } catch (Exception ex) {
                            Log.e(TAG, "Unable to invoke callback method", ex);
                        }
                    }
                };
            } else {
                callback = new ViewChangeListener.Callback() {
                    @Override
                    public void call(Object value) {
                        try {
                            method.invoke(object, value);
                        } catch (Exception ex) {
                            Log.e(TAG, "Unable to invoke callback method", ex);
                        }
                    }
                };
            }
            listener.addCallback(callback);
        }
    }
}