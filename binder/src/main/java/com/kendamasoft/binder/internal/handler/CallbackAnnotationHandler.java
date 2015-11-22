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

public class CallbackAnnotationHandler implements AnnotationHandler<Callback> {

    @Override
    public int[] getViewIds(Callback annotation) {
        return annotation.value();
    }

    @Override
    public void handle(final Object object, AccessibleObject member, View topView, List<View> viewList, Callback annotation, Observable observable) {
        if(!(member instanceof Method)) {
            throw new RuntimeException("Expected member of type Method for @Callback annotation, got " + member.getClass().getSimpleName());
        }
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
}
