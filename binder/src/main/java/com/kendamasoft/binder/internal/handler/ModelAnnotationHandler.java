package com.kendamasoft.binder.internal.handler;

import android.util.Log;
import android.view.View;

import com.kendamasoft.binder.Binder;
import com.kendamasoft.binder.Observable;
import com.kendamasoft.binder.annotation.Model;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.List;

public class ModelAnnotationHandler implements AnnotationHandler<Model> {
    @Override
    public int[] getViewIds(Model annotation) {
        return new int[0];
    }

    @Override
    public void handle(Object object, AccessibleObject member, View topView, List<View> viewList, Model annotation, Observable observable) {
        // check some invariants
        if(!(member instanceof Field)) {
            throw new RuntimeException("Expected member of type Field for @Bind annotation, got " + member.getClass().getSimpleName());
        }
        Field field = (Field)member;
        field.setAccessible(true);
        try {
            Binder.register(field.get(object), topView);
        } catch (IllegalAccessException ex) {
            Log.w(TAG, ex);
        }
    }
}
