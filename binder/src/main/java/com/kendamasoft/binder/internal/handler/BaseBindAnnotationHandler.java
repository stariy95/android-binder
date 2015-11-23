package com.kendamasoft.binder.internal.handler;

import android.util.Log;
import android.view.View;

import com.kendamasoft.binder.Binder;
import com.kendamasoft.binder.internal.BaseBinding;
import com.kendamasoft.binder.Observable;
import com.kendamasoft.binder.utils.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public abstract class BaseBindAnnotationHandler<T extends Annotation> extends AnnotationHandler<T> {

    @Override
    public abstract int[] getViewIds(T annotation);

    @Override
    public void handle(Object object, AccessibleObject member, View topView, List<View> viewList, T annotation, Observable observable) {
        if(viewList.size() != 1) {
            throw new RuntimeException("@Bind annotation expects exactly one view, got " + viewList.size());
        }

        Field field = (Field)member;

        BaseBinding fieldBinding = createBinding(object, field, annotation);
        fieldBinding.setView(viewList.get(0));
        processObservable(object, field, fieldBinding, observable);
        processSetter(object, field, fieldBinding);
        fieldBinding.notifyValueChanged();
    }

    @Override
    public void cleanUp(Object object, AccessibleObject member, View topView, List<View> viewList, T annotation, Observable observable) {
        super.cleanUp(object, member, topView, viewList, annotation, observable);
        Observable foundObservable = null;
        if(object instanceof Observable) {
            foundObservable = (Observable)object;
        } else if(observable != null) {
            foundObservable = observable;
        } else {
            return;
        }
        foundObservable.clearBindings();
        for(View view : viewList) {
            Binder.clearListenerForView(view);
        }
    }

    protected void processObservable(Object object, Field field, BaseBinding fieldBinding, Observable observable) {
        Observable foundObservable = null;
        if(object instanceof Observable) {
            foundObservable = (Observable)object;
        } else if(observable != null) {
            foundObservable = observable;
        } else {
            return;
        }
        foundObservable.addBinding(field.getName(), fieldBinding);
    }

    protected void processSetter(Object object, Field field, BaseBinding fieldBinding) {
        String setterName = ReflectionUtils.setterNameForField(field);
        try {
            Method setter = object.getClass().getMethod(setterName, field.getType());
            fieldBinding.addSetter(setter);
            Log.d(TAG, "Found setter " + setterName);
        } catch (NoSuchMethodException ex) {
            Log.d(TAG, "Not found setter " + setterName);
            // no setter for the field, do nothing
        }
    }

    protected abstract BaseBinding createBinding(Object object, Field field, T annotation);
}
