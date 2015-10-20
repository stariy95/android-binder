package com.kendamasoft.binder.internal.handler;

import android.view.View;

import com.kendamasoft.binder.annotation.OnFocusChange;
import com.kendamasoft.binder.utils.ReflectionUtils;

import java.lang.reflect.Method;

public class OnFocusChangeAnnotationHandler extends BaseOnEventAnnotationHandler<OnFocusChange, View.OnFocusChangeListener> {
    @Override
    public int[] getViewIds(OnFocusChange annotation) {
        return annotation.value();
    }

    @Override
    protected View.OnFocusChangeListener createListenerWithParam(final Object object, final Method method) {
        return new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                ReflectionUtils.safeCallMethodWithoutResult(object, method, v, hasFocus);
            }
        };
    }

    @Override
    protected View.OnFocusChangeListener createListenerWithoutParam(final Object object, final Method method) {
        return new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                ReflectionUtils.safeCallMethodWithoutResult(object, method, hasFocus);
            }
        };
    }

    @Override
    protected void bindListenerToView(View view, View.OnFocusChangeListener listener) {
        view.setOnFocusChangeListener(listener);
    }
}
