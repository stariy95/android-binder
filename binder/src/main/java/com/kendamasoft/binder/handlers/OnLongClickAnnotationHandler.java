package com.kendamasoft.binder.handlers;

import android.view.View;

import com.kendamasoft.binder.annotations.OnLongClick;
import com.kendamasoft.binder.utils.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * Created by stariy on 19.10.15.
 */
public class OnLongClickAnnotationHandler extends BaseOnEventAnnotationHandler<OnLongClick, View.OnLongClickListener> {


    @Override
    public int[] getViewIds(OnLongClick annotation) {
        return annotation.value();
    }

    @Override
    protected View.OnLongClickListener createListenerWithParam(final Object object, final Method method) {
        return new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return ReflectionUtils.safeCallMethod(object, method, false, v);
            }
        };
    }

    @Override
    protected View.OnLongClickListener createListenerWithoutParam(final Object object, final Method method) {
        return new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return ReflectionUtils.safeCallMethod(object, method, false);
            }
        };
    }

    @Override
    protected void bindListenerToView(View view, View.OnLongClickListener listener) {
        view.setOnLongClickListener(listener);
    }
}
