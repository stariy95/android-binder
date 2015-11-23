package com.kendamasoft.binder.internal.handler;

import android.view.View;

import com.kendamasoft.binder.Binder;
import com.kendamasoft.binder.Observable;
import com.kendamasoft.binder.internal.adapter.ViewChangeListener;
import com.kendamasoft.binder.annotation.Callback;
import com.kendamasoft.binder.utils.ReflectionUtils;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.util.List;

public class CallbackAnnotationHandler extends AnnotationHandler<Callback> {

    @Override
    public int[] getViewIds(Callback annotation) {
        return annotation.value();
    }

    @Override
    public void handle(final Object object, AccessibleObject member, View topView, List<View> viewList, Callback annotation, Observable observable) {
        final Method method = (Method)member;
        final boolean hasViewParam = ReflectionUtils.methodHasParameter(method, View.class);

        for(final View view : viewList) {
            ViewChangeListener listener = Binder.getListenerForView(view);
            ViewChangeListener.Callback callback;
            if(hasViewParam) {
                callback = new ViewChangeListener.Callback() {
                    @Override
                    public void call(Object value) {
                        ReflectionUtils.safeCallMethodWithoutResult(object, method, view, value);
                    }
                };
            } else {
                callback = new ViewChangeListener.Callback() {
                    @Override
                    public void call(Object value) {
                        ReflectionUtils.safeCallMethodWithoutResult(object, method, value);
                    }
                };
            }
            listener.addCallback(callback);
        }
    }

    @Override
    public void cleanUp(Object object, AccessibleObject member, View topView, List<View> viewList, Callback annotation, Observable observable) {
        super.cleanUp(object, member, topView, viewList, annotation, observable);
        for(final View view : viewList) {
            Binder.clearListenerForView(view);
        }
    }
}
