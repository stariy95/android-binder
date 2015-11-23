package com.kendamasoft.binder.internal.handler;

import android.view.View;

import com.kendamasoft.binder.Observable;
import com.kendamasoft.binder.annotation.OnClick;
import com.kendamasoft.binder.utils.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.util.List;

/**
 *
 * @param <T> type of annotation
 * @param <E> type of event listener
 */
public abstract class BaseOnEventAnnotationHandler<T extends Annotation, E> extends AnnotationHandler<T> {

    @Override
    public abstract int[] getViewIds(T annotation);

    @Override
    public void handle(Object object, AccessibleObject member, View topView, List<View> viewList, T annotation, Observable observable) {
        E listener = createListener(object, (Method) member);
        for(View view : viewList) {
            bindListenerToView(view, listener);
        }
    }

    protected E createListener(final Object object, final Method method) {
        final boolean withParam = ReflectionUtils.methodHasParameter(method, View.class);
        if(withParam) {
            return createListenerWithParam(object, method);
        } else {
            return createListenerWithoutParam(object, method);
        }
    }

    @Override
    public void cleanUp(Object object, AccessibleObject member, View topView, List<View> viewList, T annotation, Observable observable) {
        super.cleanUp(object, member, topView, viewList, annotation, observable);
        for(View view : viewList) {
            bindListenerToView(view, null);
        }
    }

    protected abstract E createListenerWithParam(final Object object, final Method method);

    protected abstract E createListenerWithoutParam(final Object object, final Method method);

    protected abstract void bindListenerToView(final View view, final E listener);
}
