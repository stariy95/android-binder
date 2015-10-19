package com.kendamasoft.binder.handlers;

import android.view.View;

import com.kendamasoft.binder.annotations.OnClick;
import com.kendamasoft.binder.utils.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * Created by stariy on 17.10.15.
 */
public class OnClickAnnotationHandler extends BaseOnEventAnnotationHandler<OnClick, View.OnClickListener> {

    @Override
    public int[] getViewIds(OnClick annotation) {
        return annotation.value();
    }

    @Override
    protected View.OnClickListener createListenerWithParam(final Object object, final Method method) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReflectionUtils.safeCallMethodWithoutResult(object, method, v);
            }
        };
    }

    @Override
    protected View.OnClickListener createListenerWithoutParam(final Object object, final Method method) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReflectionUtils.safeCallMethodWithoutResult(object, method);
            }
        };
    }

    @Override
    protected void bindListenerToView(View view, View.OnClickListener listener) {
        view.setOnClickListener(listener);
    }
}
