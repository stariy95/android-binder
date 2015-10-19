package com.kendamasoft.binder.handlers;

import android.view.View;

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
public abstract class BaseOnEventAnnotationHandler<T extends Annotation, E> implements AnnotationHandler<T> {

    @Override
    public abstract int[] getViewIds(T annotation);

    @Override
    public void handle(Object object, AccessibleObject member, View topView, List<View> viewList, T annotation) {
        if(!(member instanceof Method)) {
            throw new RuntimeException("Expected member of type Method for @OnEvent annotation, got " + member.getClass().getSimpleName());
        }

        E listener = createListener(object, (Method) member);
        for(View view : viewList) {
            bindListenerToView(view, listener);
        }
    }

    protected E createListener(final Object object, final Method method) {
        final boolean withParam = ReflectionUtils.isMethodHasParameter(method, View.class);
        if(withParam) {
            return createListenerWithParam(object, method);
        } else {
            return createListenerWithoutParam(object, method);
        }
    }

    protected abstract E createListenerWithParam(final Object object, final Method method);

    protected abstract E createListenerWithoutParam(final Object object, final Method method);

    protected abstract void bindListenerToView(final View view, final E listener);
}
