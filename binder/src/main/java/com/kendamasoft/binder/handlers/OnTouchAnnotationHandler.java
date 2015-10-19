package com.kendamasoft.binder.handlers;

import android.view.MotionEvent;
import android.view.View;

import com.kendamasoft.binder.annotations.OnTouch;
import com.kendamasoft.binder.utils.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * Created by stariy on 19.10.15.
 */
public class OnTouchAnnotationHandler extends BaseOnEventAnnotationHandler<OnTouch, View.OnTouchListener> {

    @Override
    public int[] getViewIds(OnTouch annotation) {
        return annotation.value();
    }

    @Override
    protected View.OnTouchListener createListenerWithParam(final Object object, final Method method) {
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return ReflectionUtils.safeCallMethod(object, method, false, v, event);
            }
        };
    }

    @Override
    protected View.OnTouchListener createListenerWithoutParam(final Object object, final Method method) {
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return ReflectionUtils.safeCallMethod(object, method, false, event);
            }
        };
    }

    @Override
    protected void bindListenerToView(View view, View.OnTouchListener listener) {
        view.setOnTouchListener(listener);
    }
}
