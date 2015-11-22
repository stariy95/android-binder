package com.kendamasoft.binder.internal.handler;

import android.util.Log;
import android.view.View;

import com.kendamasoft.binder.Observable;
import com.kendamasoft.binder.annotation.Inject;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.List;

public class InjectAnnotationHandler implements AnnotationHandler<Inject> {
    @Override
    public int[] getViewIds(Inject annotation) {
        return annotation.value();
    }

    @Override
    public void handle(Object object, AccessibleObject member, View topView, List<View> viewList, Inject annotation, Observable observable) {
        // check some invariants
        if(!(member instanceof Field)) {
            throw new RuntimeException("Expected member of type Field for @Inject annotation, got " + member.getClass().getSimpleName());
        }
        Field field = (Field)member;
        if(viewList.size() == 0) {
            throw new RuntimeException("No ID parameters in @Inject annotation");
        }

        field.setAccessible(true);
        if(viewList.size() > 1) {
            // array or list
            if(!field.getType().isAssignableFrom(List.class)) {
                throw new RuntimeException("Expected List<? extends View> field type, got " + field.getType().getSimpleName());
            }
            try {
                field.set(object, viewList);
            } catch (Exception ex) {
                Log.w(TAG, ex);
            }
        } else {
            // single view
            if(!View.class.isAssignableFrom(field.getType())) {
                throw new RuntimeException("Expected View field type, got " + field.getType().getSimpleName());
            }
            try {
                field.set(object, viewList.get(0));
            } catch (Exception ex) {
                Log.w(TAG, ex);
            }
        }
    }
}
