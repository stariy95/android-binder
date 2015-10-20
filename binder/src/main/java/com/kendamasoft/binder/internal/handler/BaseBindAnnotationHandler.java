package com.kendamasoft.binder.internal.handler;

import android.util.Log;
import android.view.View;

import com.kendamasoft.binder.internal.BaseBinding;
import com.kendamasoft.binder.Observable;
import com.kendamasoft.binder.utils.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public abstract class BaseBindAnnotationHandler<T extends Annotation> implements AnnotationHandler<T> {

    @Override
    public abstract int[] getViewIds(T annotation);

    @Override
    public void handle(Object object, AccessibleObject member, View topView, List<View> viewList, T annotation) {
        // check some invariants
        if(!(member instanceof Field)) {
            throw new RuntimeException("Expected member of type Field for @Bind annotation, got " + member.getClass().getSimpleName());
        }
        if(viewList.size() != 1) {
            throw new RuntimeException("@Bind annotation expects exactly one view, got " + viewList.size());
        }

        Field field = (Field)member;

        BaseBinding fieldBinding = createBinding(object, field, annotation);
        fieldBinding.setView(viewList.get(0));
        processObservable(object, field, fieldBinding);
        processSetter(object, field, fieldBinding);
        fieldBinding.notifyValueChanged();
    }

    protected void processObservable(Object object, Field field, BaseBinding fieldBinding) {
        if(!(object instanceof Observable)) {
            return;
        }
        ((Observable)object).addBinding(field.getName(), fieldBinding);
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
